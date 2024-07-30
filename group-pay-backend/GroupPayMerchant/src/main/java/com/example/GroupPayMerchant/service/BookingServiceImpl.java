package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.exceptions.BookingIDNotFoundException;
import com.example.GroupPayMerchant.exceptions.InvalidStatusException;
import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.models.responses.BankOrderResponse;
import com.example.GroupPayMerchant.repository.BookingRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private WebClient webClient;
    @Autowired
    BookingRepo bookingRepository ;

    @Value("${merchant.details.name}")
    private  String merchantName;

    @Value("${merchant.details.id}")
    private String merchantId;

    @Override
    public BookingDetails createNewBooking(BookingDetails bookingDetails) {

        bookingDetails.setStatus(Status.PENDING);  //Set default status as IN_PROGRESS
        bookingDetails = bookingRepository.save(bookingDetails) ;

        Map<String, Object> body = new HashMap<>();

        body.put("merchantName", merchantName);
        body.put("merchantId", merchantId);
        body.put("amount", bookingDetails.getAmount());
        body.put("numberOfContributors", bookingDetails.getNumberOfContributors());
        body.put("referenceId", bookingDetails.getId());

        BankOrderResponse res = notifyBank(body);

        bookingDetails.setExpiry(LocalDateTime.parse(res.getExpiry()));
        if(Objects.equals(res.getStatus(), "IN_PROGRESS"))
            bookingDetails.setStatus(Status.IN_PROGRESS);
        else
            bookingDetails.setStatus(Status.FAILED);

        bookingRepository.save(bookingDetails);

        return bookingDetails;
    }

    @Override
    public List<BookingDetails> getAllBookings() {
        return bookingRepository.findAll() ;
    }

    @Override
    public BookingDetails getBookingById(UUID id) {
        return bookingRepository.getReferenceById(id) ;
    }

    @Override
    public void deleteBooking(UUID id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDetails updateStatus(UUID bookingId , Status newStatus) {

        BookingDetails bookingDetails = bookingRepository.getReferenceById(bookingId);
        if (bookingDetails==null)
                throw new BookingIDNotFoundException("Booking not found with ID:" + bookingId);

        try{
            Status  status = newStatus ;
            bookingDetails.setStatus(status);
            return bookingRepository.save(bookingDetails) ;
        } catch (IllegalArgumentException e){
            throw  new InvalidStatusException("Invalid status value: " + newStatus) ;
        }
    }

    protected BankOrderResponse notifyBank(Map<String, Object> body) {
        Mono<BankOrderResponse> res = webClient.put().uri("/order/create").accept(MediaType.APPLICATION_JSON)
                .bodyValue(BodyInserters.fromValue(body)).retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response ->
                                switch (response.statusCode().value()) {
                                    case 400 -> Mono.error(new BadRequestException("bad request made"));
                                    case 401, 403 -> Mono.error(new Exception("auth error"));
                                    case 404 -> Mono.error(new Exception("Maybe not an error?"));
                                    case 500 -> Mono.error(new Exception("server error"));
                                    default -> Mono.error(new Exception("something went wrong"));
                                }
                ).bodyToMono(BankOrderResponse.class).log();

        return res.block();

    }
}

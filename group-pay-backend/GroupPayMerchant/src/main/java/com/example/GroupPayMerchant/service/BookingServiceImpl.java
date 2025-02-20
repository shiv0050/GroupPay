package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.exceptions.BookingIDNotFoundException;
import com.example.GroupPayMerchant.exceptions.InvalidStatusException;
import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.models.requests.BankOrderRequest;
import com.example.GroupPayMerchant.models.requests.BookingRequest;
import com.example.GroupPayMerchant.models.responses.BankOrderResponse;
import com.example.GroupPayMerchant.repository.BookingRepo;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.Objects;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private WebClient webClient;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookingRepo bookingRepository ;

    @Value("${merchant.details.name}")
    private  String merchantName;

    @Value("${merchant.details.id}")
    private String merchantId;

    @Override
    public BookingDetails createNewBooking(BookingRequest bookingRequest) {
        bookingRequest.setStatus(Status.PENDING);  //Set default status as IN_PROGRESS
        BookingDetails newBookingDetails = bookingRepository.save(modelMapper.map(bookingRequest,BookingDetails.class)) ;

        BankOrderRequest bankOrderRequest = new BankOrderRequest();
        bankOrderRequest.setMerchantName(merchantName);
        bankOrderRequest.setMerchantId(merchantId);
        bankOrderRequest.setAmount(newBookingDetails.getAmount());
        bankOrderRequest.setNumberOfContributors(newBookingDetails.getNumberOfContributors());
        bankOrderRequest.setReferenceId(newBookingDetails.getId());
        bankOrderRequest.setExpiry(12);
        BankOrderResponse bankOrderResponse = notifyBank(bankOrderRequest);

        if(Objects.equals(bankOrderResponse.getStatus(), "IN_PROGRESS")) {
            newBookingDetails.setStatus(Status.IN_PROGRESS);
            newBookingDetails.setExpiry(LocalDateTime.parse(bankOrderResponse.getExpiry().substring(0, 23) + "Z", DateTimeFormatter.ISO_ZONED_DATE_TIME));
        }
        else
            newBookingDetails.setStatus(Status.FAILED);

        return bookingRepository.save(newBookingDetails);
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

        BookingDetails bookingDetails = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found!"));
        if (bookingDetails == null)
            throw new BookingIDNotFoundException("Booking not found with ID:" + bookingId);

        try {
            bookingDetails.setStatus(newStatus);
            return bookingRepository.save(bookingDetails);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status value: " + newStatus);
        }
    }

    protected BankOrderResponse notifyBank(BankOrderRequest bankOrderRequest) {
        Mono<BankOrderResponse> res = webClient.post().uri("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bankOrderRequest).retrieve()
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

import React,{useState,useContext} from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { MDBCarousel, MDBCarouselItem, MDBCarouselCaption } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
// Import images
import img3 from '../assets/background.avif';
import img2 from '../assets/thailand.jpeg';
import img1 from '../assets/bali.avif';
import { AuthContext } from '../App';

function MerchantDashboard() {
    const [price, setPrice]=useState(0);
    const nevigate=useNavigate();
    const {isLoggedIn}=useContext(AuthContext);
    const handleBookNow=([price,place,id])=>{
        console.log(isLoggedIn);
        {isLoggedIn?nevigate("/checkout",{state:[price,place,id]}):nevigate("/login")}
    };
    return (
        <div style={{backgroundImage:`url(${img3})`,backgroundSize: 'cover',backgroundPosition: 'center', paddingBottom:'3rem'}}>
        <MDBCarousel showIndicators showControls fade > 
        <MDBCarouselItem itemId={1}>
        <img src='https://mdbootstrap.com/img/Photos/Slides/img%20(22).jpg' className='d-block w-100' alt='...' style={{height:'30vh'}}/>
        <MDBCarouselCaption>
          <h3 className='Craouselheading'>Work</h3>
          <h3 className='Craouselheading'>Travel</h3>
          <h3 className='Craouselheading'>Save</h3>
          <h3 className='Craouselheading'>Repeat</h3>
        </MDBCarouselCaption>
      </MDBCarouselItem>
      <MDBCarouselItem itemId={2}>
        <img src='https://mdbootstrap.com/img/Photos/Slides/img%20(15).jpg' className='d-block w-100' alt='...' style={{height:'30vh'}}/>
        <MDBCarouselCaption>
          <h3 className='Craouselheading'>Travel with us</h3>
          <p className='Craouselheading'>Choose us! Together we will make memories</p>
        </MDBCarouselCaption>
      </MDBCarouselItem>

     

      <MDBCarouselItem itemId={3}>
        <img src='https://mdbootstrap.com/img/Photos/Slides/img%20(23).jpg' className='d-block w-100' alt='...' style={{height:'30vh'}}/>
        <MDBCarouselCaption>
        <h3 className='Craouselheading'>Let's Go!</h3>
          
        </MDBCarouselCaption>
      </MDBCarouselItem>
    </MDBCarousel>
    <div className='container-fluid' style={{justifyContent:'center'}}>
        <div className='row' style={{margin:'3rem',marginBottom:'0'}}>
        <div className='col-sm-4'>
        <div className="card" style={{marginLeft:'2rem',border:'solid 2px #318be5f3'}} >
            <div style={{padding:'10px',backgroundColor:'#f2f7f8'}}>
           <img className="card-img-top" src={img2} alt="Card image cap" style={{height:'30vh'}}/>
           <h4 className="card-title" style={{marginTop:'10px',marginBottom:'0'}}>Thailand </h4>
           </div>
               <div className="card-body">
                   <p className="card-text">This will be 4 night-5days trip,we will explore as much as we can.</p>
                   <p className="card-text">Price per head: £500</p>
                   <button  className="btn btn-primary" onClick={()=>handleBookNow([500,'Thailand','10012943'])}>BookNow</button>
                </div>
        </div>
        </div>
       <div className='col-sm-4'>
       <div className="card" style={{marginLeft:'2rem',border:'solid 2px #318be5f3'}}>
               <div style={{padding:'10px',backgroundColor:'#f2f7f8'}}>
                <img className="card-img-top" src={img1} alt="Card image cap" style={{height:'30vh'}}/>   
                 <h4 className="card-title" style={{marginTop:'10px',marginBottom:'0'}}>Bali </h4>
               </div>
               <div className="card-body">
                   
                   <p className="card-text">This will be 4 night-5days trip,we will explore as much as we can.</p>
                   <p className="card-text">Price per head: £700</p>
                   <button  className="btn btn-primary" onClick={()=>handleBookNow([700,'Bali','10012944'])}>BookNow</button>
                </div>
        </div>
       </div>
        </div>
    </div>
    </div>
    );
}

export default MerchantDashboard;

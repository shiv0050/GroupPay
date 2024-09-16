import { Form, Button, Alert, Dropdown } from "react-bootstrap";
import { useState,useContext,useEffect } from "react";
import { Navigate, useLocation ,useNavigate} from "react-router-dom";
import {v4 } from 'uuid';
import { FaCreditCard } from "react-icons/fa";
import { RiBankFill } from "react-icons/ri";
import axios from "axios";

import img from "../assets/background.avif";
import { AuthContext } from "../App";

function MerchantCheckout()
{
    const navigate=useNavigate();
    const [name, setname] = useState("")
    const [lastName, setlastName] = useState("");
    const [person, setperson] = useState(0)
    const Location=useLocation();
    const [price,location,id]=Location.state||[0,"choose","#1"];
    const [total, settotal] = useState(0)
    const [userId, setuserId]=useState(v4())
    const [show, setshow] = useState(false)
    const [usrName,setUsrname]=useState('')
    const [usrEmail,setUsrEmail]=useState('')

    useEffect(()=>{
      let usrName=sessionStorage.getItem('merchUserName')
      if(usrName!=null)
        setUsrname(usrName)
      let usrEmail=sessionStorage.getItem('merchUserEmail')
      if(usrEmail!=null)
        setUsrEmail(usrEmail)
      let usrId=sessionStorage.getItem('merchUserId')
      if(usrId!=null)
        setuserId(usrId)
    },[])
    const handlePerson=(e)=>{
        const numPeople = parseInt(e.target.value, 10);
        setperson(numPeople);
        settotal(numPeople * price);
        
    }
    const handlePayment=(e)=>{
        setPayMeth(e.target.value)
        setshow(true);
    }
    const handleSubmit=(e)=>{
        e.preventDefault();
        if(show)
        {   axios.post("http://localhost:8002/merchant-user/newBooking",{numberOfContributors:person,amount:total,initiatorId:userId,productId:id},
            {headers:{"Content-Type":"application/json"}}
        )
            .then((data)=>{
                console.log(data.data)
                navigate(`/tracker?bookingId=${data.data.id}&amount=${data.data.amount}&expiry=${data.data.expiry}&contributors=${person}`)
})
            .catch((err)=>console.log(err));
        }
        else{
            navigate("/")
        }
    };

    const [payMeth,setPayMeth]=useState('')

    return(
        <div className="checkout-page" style={{backgroundImage:`url(${img})`,backgroundSize: 'cover',backgroundPosition: 'center'}} >
        <div className="checkout-container" >
            <div className="header-container">
                <h3 className="Craouselheading">Whishing you a happy journey to {location}</h3>
            </div>
            <div className="content-container">
                
                <div className="form-container">
                    <Form onSubmit={handleSubmit}>
                        <div className="form-row">
                        <Form.Group className="form-group">
                            <Form.Label>First Name</Form.Label>
                            <Form.Control type="text" value={usrName} placeholder="Enter your First Name" onChange={(e)=>{setname(e.target.value)}} required></Form.Control>
                        </Form.Group>
                        <Form.Group className="form-group">
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control type="text" value={usrEmail} placeholder="Enter your Last Name" onChange={(e)=>{setlastName(e.target.value)}} required></Form.Control>
                        </Form.Group>
                        </div>
                        <div className="form-row">
                        <Form.Group className="form-group">
                            <Form.Label>No. Contributers</Form.Label>
                            <Form.Control type="number" value={person} onChange={handlePerson}></Form.Control>
                        </Form.Group>
                        <Form.Group className="form-group">
                            
                            <Form.Label>Total : </Form.Label>
                            <Form.Control type="text" value={`£${price*person}`} readOnly></Form.Control>
                            
                        </Form.Group>
                        </div>
                        <Form.Label style={{}}>Select payment method :</Form.Label>
                        <div className="form-row" style={{gap:'2rem',display:'flex'}}>
                           <Dropdown style={{outline:''}} >
                                 <Dropdown.Toggle variant="primary"><RiBankFill /> Net Banking</Dropdown.Toggle>
                                 <Dropdown.Menu>
                                    <Dropdown.Item onClick={handlePayment}>Natwest(GroupPay)</Dropdown.Item>
                                    <Dropdown.Item >HDFC</Dropdown.Item>
                                    <Dropdown.Item >SBI</Dropdown.Item>
                                 </Dropdown.Menu>
                                 
                            </Dropdown>
                            <Dropdown >
                                 <Dropdown.Toggle variant="primary"><FaCreditCard /> Card</Dropdown.Toggle>
                                 <Dropdown.Menu>
                                    <Dropdown.Item >Natwest</Dropdown.Item>
                                    <Dropdown.Item >HDFC</Dropdown.Item>
                                    <Dropdown.Item >SBI</Dropdown.Item>
                                 </Dropdown.Menu>
                            </Dropdown>                           
                              
                        </div>
                        <Button variant="primary" style={{justifyContent:'center', margin:'30px 20%'}} type="submit" >Proceed</Button>
                        {/* Your form fields go here */}
                    </Form>
                    
                </div>
            </div>
        </div>
    </div>
      );
}
export default MerchantCheckout;
import { Form, Button, Alert, Dropdown } from "react-bootstrap";
import { useState } from "react";
import { useLocation ,useNavigate} from "react-router-dom";
import {v4 } from 'uuid';
import { FaCreditCard } from "react-icons/fa";
import { RiBankFill } from "react-icons/ri";

import img from "../assets/background.avif";

function MerchantCheckout()
{
    const [name, setname] = useState("")
    const [lastName, setlastName] = useState("");
    const [person, setperson] = useState(0)
    const Location=useLocation();
    const [price,location,id]=Location.state||[0,"choose","#1"];
    const [total, settotal] = useState(0)
    const [userId, setuserId]=useState(v4())
    const [show, setshow] = useState(false)
    const nevigate=useNavigate();

    const handlePerson=(e)=>{
        const numPeople = parseInt(e.target.value, 10);
        setperson(numPeople);
        settotal(numPeople * price);
        
    }
    const handlePayment=()=>{
        console.log("called");
        setshow(true);
    }
    const handleSubmit=(e)=>{
        e.preventDefault();
        {show?nevigate("/bankingPage",{state:[userId,id,person,total]}):nevigate("/")}
    };
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
                            <Form.Control type="text" value={name} placeholder="Enter your First Name" onChange={(e)=>{setname(e.target.value)}} required></Form.Control>
                        </Form.Group>
                        <Form.Group className="form-group">
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control type="text" value={lastName} placeholder="Enter your Last Name" onChange={(e)=>{setlastName(e.target.value)}} required></Form.Control>
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
                        <Button variant="primary" style={{justifyContent:'center', margin:'30px 20%'}} type="submit">Proceed</Button>
                        {/* Your form fields go here */}
                    </Form>
                    
                </div>
            </div>
        </div>
    </div>
      );
}
export default MerchantCheckout;
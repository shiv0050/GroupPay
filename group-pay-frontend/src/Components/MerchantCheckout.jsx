import { Form } from "react-bootstrap";
import { useState, useEffect } from "react";
import { useLocation ,useNavigate} from "react-router-dom";
import axios from "axios";
import {showNotification} from './../utils/NotificationUtils';

import img from "../assets/background.avif";
import { Button, FormControl, InputLabel, MenuItem, Select } from "@mui/material";

function MerchantCheckout()
{
    const navigate=useNavigate();
    const [firstName, setFirstName] = useState("")
    const [lastName, setlastName] = useState("");
    const [person, setperson] = useState(0)
    const Location=useLocation();
    const [price,location,id]=Location.state||[0,"choose","#1"];
    const [total, settotal] = useState(0)
    const [userId, setuserId]=useState(null)
    const [show, setshow] = useState(false)
    const [bank, setBank]=useState('');

    useEffect(()=>{
      let usrName=sessionStorage.getItem('merchUserName')
      if(firstName!=null)
        setFirstName(usrName)
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
        setBank(e.target.value)
        setshow(true);
    }
    const handleSubmit=(e)=>{
        e.preventDefault();
        if(show)
        {   axios.post("http://localhost:8002/merchant-booking/create",{numberOfContributors:person,amount:total,initiatorId:userId,productId:id},
                {headers:{"Content-Type":"application/json"}}
            )
            .then((response)=>{
                const data = response.data;
                console.log(data)
                navigate(`/tracker?id=${data.id}`)
            })
            .catch((err) => {
                console.log(err)
                showNotification("Booking creation error!", "An error occured with the request.", 'danger');
            });
        }
        else{
            console.log("Some error occured, try again later!")
        }
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
                            <Form.Control type="text" value={firstName} placeholder="Enter your First Name" onChange={(e)=>{setFirstName(e.target.value)}} required></Form.Control>
                        </Form.Group>
                        <Form.Group className="form-group">
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control type="text" value={lastName} placeholder="Enter your Last Name" onChange={(e)=>{setlastName(e.target.value)}} required></Form.Control>
                        </Form.Group>
                        </div>
                        <div className="form-row">
                        <Form.Group className="form-group">
                            <Form.Label>Group size</Form.Label>
                            <Form.Control type="number" value={person} onChange={handlePerson}></Form.Control>
                        </Form.Group>
                        <Form.Group className="form-group">
                            
                            <Form.Label>Total </Form.Label>
                            <Form.Control type="text" value={`£${price*person}`} readOnly></Form.Control>
                            
                        </Form.Group>
                        </div>
                        <div style={{display: 'flex', marginTop: 4, alignItems: 'center'}}>
                            <h5>Select payment method : </h5>
                            <FormControl sx={{ m:1, minWidth: 150}} size="small">
                                <InputLabel>Select bank</InputLabel>
                                <Select
                                    value={bank}
                                    label="Select bank"
                                    autoWidth
                                    onChange={handlePayment}
                                >
                                    <MenuItem value={'natwest'}>GroupPay-Natwest</MenuItem>
                                    <MenuItem value={'hdfc'}>GroupPay-HDFC</MenuItem>
                                    <MenuItem value={'sbi'}>GroupPay-SBI</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
                        <div style={{display: 'flex', justifyContent: 'flex-end'}}>
                            <Button variant="contained" sx={{px: 4}} size="medium" type="submit" >Proceed</Button>
                        </div>
                    </Form>                    
                </div>
            </div>
        </div>
    </div>
      );
}
export default MerchantCheckout;
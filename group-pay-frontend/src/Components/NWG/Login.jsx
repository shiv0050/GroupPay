import React,{useState,useContext} from "react";
import Button from '@mui/material/Button';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import TabContext from '@mui/lab/TabContext';
import { Typography } from "@mui/material";
import TextField from '@mui/material/TextField';
import Container from "@mui/material/Container";
import TabPanel from '@mui/lab/TabPanel';
import TabList from '@mui/lab/TabList';
import PinInput from 'react-pin-input';
import axios from 'axios';
import {useParams } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../App";
import { AppContext } from "../MerchantTracking";

const LoginForm = () => {
    const navigate=useNavigate();
    // let { orderId,paymentRefId } = useParams();
    const {comp,setComp}=useContext(AppContext);

    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    const handleEmail=(event)=>{
        setEmail(event.target.value)
    }
    const handlePassword=(event)=>{
        setPassword(event.target.value)
    }
    const authenticateUser=()=>{
        let request={
            userName:email,password:password
        }
        axios.post("http://localhost:8001/user/login",request).then(res=>{
            if(res.data.success){
            sessionStorage.setItem('token',res.data.userToken)
            sessionStorage.setItem('bankUserId',res.data.user.id)
            sessionStorage.setItem('bankUserName',res.data.user.firstName+" "+res.data.user.lasstName)
            sessionStorage.setItem('bankUserEmail',res.data.user.email)
            console.log(res.data.user);
            // setBankUserLoggedIn(res.data.user);
            // navigate("/nwg-netbanking")
            setComp(prev=>{
                return{
                page:'netbanking',
                amount:prev.amount,
                bookingId:prev.bookingId,
                expiry:prev.expiry,
                contributors:prev.contributors
            }
        
        })
    }
})
    }
    return (
        <Container sx={{ display: "flex",flexDirection: "column", alignItems: "center" }}>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Email Id" variant="outlined" onChange={handleEmail}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Password" variant="outlined" type="password" onChange={handlePassword}/>
            <Box sx={{ marginTop: "20px",display:"flex",justifyItems:"space-between"}}>
            <Button sx={{backgroundColor: "#5A287D", color: "white", padding: "1em", borderRadius: "5em" }} size="small" onClick={authenticateUser}>Sign In</Button>
            <Typography sx={{ textDecoration:"underline",fontSize: 14 }} color="#894570" gutterBottom>
                    Forgot Password
                </Typography>
                </Box>
        </Container>
    )
}
const RegisterForm = () => {

    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    const handleEmail=(event)=>{
        setEmail(event.target.value)
    }
    const handlePassword=(event)=>{
        setPassword(event.target.value)
    }
    const [confirmPassword,setConfirmPassword]=useState('')
    const [firstName,setFirstName]=useState('')
    const handleConfirmPassword=(event)=>{
        setConfirmPassword(event.target.value)
    }
    const handleFirstName=(event)=>{
        setFirstName(event.target.value)
    }
    const [lastName,setLastName]=useState('')
    const [phoneNo,setPhoneNo]=useState('')
    const handleLastName=(event)=>{
        setLastName(event.target.value)
    }
    const handlePhoneNo=(event)=>{
        setPhoneNo(event.target.value)
    }
    const [dob,setDob]=useState('')
    const [id,setId]=useState('')
    const handleDob=(event)=>{
        setDob(event.target.value)
    }
    const handleId=(event)=>{
        setId(event.target.value)
    }
    const [address,setAddress]=useState('')
    const [state,setState]=useState('')
    const handleAddress=(event)=>{
        setAddress(event.target.value)
    }
    const handleState=(event)=>{
        setState(event.target.value)
    }
    const [city,setCity]=useState('')
    const [zip,setZip]=useState('')
    const handleCity=(event)=>{
        setCity(event.target.value)
    }
    const handleZip=(event)=>{
        setZip(event.target.value)
    }
    const [pin,setPin]=useState(0)
    const handlePin=(value)=>{
        console.log(value)
        setPin(value)
    }
    const registerUser=({changeTab})=>{
        let request={
            firstName:firstName,lastName:lastName,password:password,phone:phoneNo,dob:dob,addressLine1:address,state:state,city:city,postalCode:zip,email:email,governmentId:id,pin:pin
        }
        axios.post("http://localhost:8001/user/register",request).then(res=>{
            console.log(res.data);
            if(res.data!=null)
                changeTab(1)
        })
    }
    return (
        <Container sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                    <Box sx={{display: "flex", }}>

        <Box sx={{ flexDirection: "column", alignItems: "center" }}>

            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="First Name" variant="outlined" onChange={handleFirstName}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Last Name" variant="outlined" onChange={handleLastName}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Phone Number" variant="outlined" onChange={handlePhoneNo}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Date of birth" variant="outlined" onChange={handleDob}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Email Id" variant="outlined" onChange={handleEmail}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Identity Document Number" variant="outlined" onChange={handleId}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Password" variant="outlined" type="password" onChange={handlePassword}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Confirm Password" variant="outlined" type="password" error={password!=confirmPassword} onChange={handleConfirmPassword}/>
</Box>
<Box sx={{ flexDirection: "column", alignItems: "center" }}>

            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="Address Line 1" variant="outlined" onChange={handleAddress}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="City" variant="outlined" onChange={handleCity}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="State" variant="outlined" onChange={handleState}/>
            <TextField sx={{ marginTop: "10px", width: "25rem" }} id="outlined-basic" label="ZIP Code" variant="outlined" onChange={handleZip}/>
</Box>
</Box>
            <Typography sx={{ marginTop: "20px",fontSize: 14 }} color="#894570" gutterBottom>
                    Set Secret PIN :
                </Typography>
            <PinInput
                    length={6}
                    initialValue=""
                    secret
                    secretDelay={100}
                    onChange={handlePin}
                    type="numeric"
                    inputMode="number"
                    style={{ padding: '10px' }}
                    inputStyle={{ borderColor: '#5A287D' }}
                    inputFocusStyle={{ borderColor: 'blue' }}
                    autoSelect={true}
                    regexCriteria={/^[ A-Za-z0-9_@./#&+-]*$/}
                />
            <Box sx={{ marginTop: "20px",display:"flex",justifyItems:"space-between"}}>
            <Button sx={{backgroundColor: "#5A287D", color: "white", padding: "1em", borderRadius: "5em" }} size="small" onClick={registerUser}>Sign Up</Button>
            </Box>
        </Container>
    )
}
const Login = () => {
    const [value, setValue] = React.useState(1);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    return (

        <Box sx={{ backgroundColor: "#5A287D" }}>
            <TabContext value={value}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Typography sx={{ fontFamily: "sans-serif", fontSize: "48px", fontWeight: "700", color: "white", textAlign: "left", padding: "1em 3em" }}>Sign in</Typography>
                    <TabList
                        selectionFollowsFocus
                        sx={{ marginLeft: "10rem" }}
                        onChange={handleChange} aria-label="lab API tabs example">
                        <Tab sx={{ backgroundColor: "white", color: "#5A287D", borderRadius: "5px 5px 0 0", marginRight: "2px" }}
                            label="Login" value={1} />
                        <Tab sx={{ backgroundColor: "white", color: "#5A287D", borderRadius: "5px 5px 0 0", marginRight: "2px" }}
                            label="Register" value={2} />

                    </TabList>
                </Box>
                <TabPanel sx={{ backgroundColor: "white" }} value={1}><LoginForm /></TabPanel>
                <TabPanel sx={{ backgroundColor: "white" }} value={2}><RegisterForm changeTab={setValue}/></TabPanel>
            </TabContext>
        </Box>
    )
}
export default Login;
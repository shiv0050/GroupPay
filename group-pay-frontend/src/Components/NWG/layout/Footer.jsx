import React from 'react'
import nwgLogo from "../../../assets/Natwest-logo.jpg"
import { Typography } from '@mui/material'
import Container from '@mui/material/Container'
import Box from '@mui/material/Box'

const Footer=()=>{
    return(
<Box 
sx={{ width:"100vw",display:"flex",position:"absolute",bottom:'0px' ,backgroundColor:"#5A287D", justifyContent:"space-between",padding:"1em"}}>  <Box>
        <img src={nwgLogo} width={'200px'}/>
    </Box>
    <div style={{display:"flex"}}>
        <ul style={{display:"flex",margin:"0 5em",flexDirection:"column",color:"white"}}>
            <Typography mb={5}>Banking</Typography>
            <li>Account Statement</li>
            <li>Pending Approvals</li>
        </ul>
        <ul style={{display:"flex",margin:"0 5em",flexDirection:"column",color:"white"}}>
            <Typography mb={5}>Contact Us</Typography>
            <li>Customer Helpline</li>
            <li>Support Email</li>
        </ul>
    </div>
    <Box sx={{alignSelf:"flex-end"}}>
        <Typography sx={{color:"white"}}>Copyright 2024 Natwest Bank</Typography>
    </Box>

</Box>
    )
}
export default Footer;
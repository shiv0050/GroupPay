import React from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import nwgLogo from '../../../assets/natwest.png'
const Header=()=>{
    return(


    <Box sx={{ display:"flex",lexGrow: 1 ,backgroundColor:"#5A287D", justifyContent:"space-between",padding:"1em"}}>
              <Box sx={{ display:"flex", marginLeft:"5em"}}>
                <img src={nwgLogo} sx={{padding:"5px"}} alt="Logo" width="50"/>;
                <Typography fontSize={22} sx={{color:"white",marginTop:"5px"}}>NatWest</Typography>
                </Box>
    </Box>
  );
}
export default Header;
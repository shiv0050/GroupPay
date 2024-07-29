import React, { useState } from "react";
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import nwgLogo from "../../assets/natwest.png"
import Alert from '@mui/material/Alert';
import CheckIcon from '@mui/icons-material/Check';
import PinInput from 'react-pin-input';
import { Container } from "@mui/material";
import Tabs from '@mui/joy/Tabs';
import TabList from '@mui/joy/TabList';
import Tab from '@mui/joy/Tab';
import TabPanel from '@mui/joy/TabPanel';


const SideNav = () => {
    return (
        <Tabs
            aria-label="Vertical tabs"
            orientation="vertical"
            sx={{ minWidth: 300, height: 160 }}
        >
            <TabList>
                <Tab indicatorPlacement="left">Account Summary</Tab>
                <Tab indicatorPlacement="left">Payment Approval</Tab>
                <Tab indicatorPlacement="left">Statement</Tab>
            </TabList>
            <TabPanel value={0}>
            <AccountCard />
            </TabPanel>
            <TabPanel value={1}>
                <Grid container spacing={2}>
                <Grid item xs={5}>
                    <AccountCard/>
                </Grid>
                <Grid item xs={5}>
                    <PaymentCard/>
                </Grid>
            </Grid>
            
            </TabPanel>
            <TabPanel value={2}>
                <b>Third</b> tab panel
            </TabPanel>
        </Tabs>
    )
}
const PaymentCard = (transaction) => {
    const [from, setFrom] = React.useState('');
    const [to, setTo] = React.useState('');

    const handleFromChange = (event) => {
        setFrom(event.target.value);
    };
    const handleToChange = (event) => {
        setTo(event.target.value);
    };
    return (<Container>
        <Alert icon={<CheckIcon fontSize="18" />} severity="success">
            Your payment has been approved.
        </Alert>
        <Box sx={{ display: "flex", flexDirection: "column", textAlign: "left" }}>
            <Typography sx={{ fontSize: 18, backgroundColor: "#5A287D" }} color="white" gutterBottom>
                Check and authorize
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                From
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                {transaction.from}
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                To
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                {transaction.toName}
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                Account Number
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                {transaction.toAcc}
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                Payment Reference
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                {transaction.ref}
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                Amount
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                £ {transaction.amount}
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                Payment Due Datetime
            </Typography>
            <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                {transaction.expiryTimestamp}
            </Typography>
            <Typography sx={{ fontSize: 10 }} color="black" gutterBottom>
                Plese note, the transaction amount will not be deducted until all the payment request for the booking id {transaction.bookingId} are approved.
                If the approval is made after the payment expiry, then the transaction will be deemed failed.
            </Typography>
            <Box>
                <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                    Enter your 6 digit authorization code
                </Typography>
                <PinInput
                    length={6}
                    initialValue=""
                    secret
                    secretDelay={100}
                    onChange={(value, index) => { }}
                    type="numeric"
                    inputMode="number"
                    style={{ padding: '10px' }}
                    inputStyle={{ borderColor: 'red' }}
                    inputFocusStyle={{ borderColor: 'blue' }}
                    autoSelect={true}
                    regexCriteria={/^[ A-Za-z0-9_@./#&+-]*$/}
                />
            </Box>
            <Box display={"flex"} justifyContent={"space-between"}>
                <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                    Back
                </Typography>
                <Button sx={{ backgroundColor: "#5A287D", color: "white" }} size="small">Confirm</Button>

            </Box>
        </Box>
    </Container>
    )
}
const AccountCard = (account) => {
    return (
    
        <Card sx={{ minWidth: 275, border: '1px solid #5A287D', borderBottomWidth: 5 }}>
            <CardContent sx={{ display: 'flex' }}>
                <img src={nwgLogo} alt="Logo" width="50px" />;
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    A/C
                </Typography>

                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    {account.accountNumber}
                </Typography>
                <Typography variant="body2">
                    £{account.balance}
                </Typography>
            </CardContent>
            <CardActions sx={{ display: 'flex' }}>
                <Button sx={{ color: "#894570" }} size="small">View details</Button>
                <Button sx={{ color: "#894570" }} size="small">Make a payment</Button>
                <Button sx={{ color: "#894570" }} size="small">View transactions</Button>
            </CardActions>
        </Card>
    )
}
const Netbanking = () => {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <SideNav/>
        </Box>
    );
}
export default Netbanking;
// const Netbanking =()=>{
//     const [accounts,setAccounts]=useState([])
//     return(
//         <div>
//             <div>
//                 <h2>Online Banking</h2>
//                 <ul>
//                     <li>Account Summary</li>
//                     <li>Debit</li>
//                     <li>Credit</li>
//                     <li>Cards</li>
//                 </ul>
//             </div>
//             <div>
//                 <h2>Your Accounts</h2>
//                 {accounts.map(account=>{
//                     return(
//                         <div key={account.id}>

//                         </div>
//                     )
//                 })}
//             </div>
//             <div></div>
//         </div>
//     )
// }
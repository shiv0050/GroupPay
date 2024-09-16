import React, { useState, useEffect, useContext } from "react";
import ReactDOMServer from "react-dom/server";
import { AppContext } from "../MerchantTracking";

import { AuthContext } from '../../App';
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
import axios from "axios";
import { v4 as uuid } from 'uuid'
import Login from "./Login";
import { IFrame } from './layout/IFrame'
import { useNavigate } from "react-router-dom";
import { FaHandsAslInterpreting } from "react-icons/fa6";



const getAccounts = (token) => {
    axios.get('', { headers: { "Authorization": `Bearer ${token}` } })
        .then(res => {
            return res.data
        })
        .catch(err => {
            throw err
        })
}
const Netbanking = () => {
    const [accounts, setAccounts] = useState([{
        accountNumber: uuid(),
        balance: 500
    },
    {
        accountNumber: uuid(),
        balance: 1000
    }, {
        accountNumber: uuid(),
        balance: 200
    }])
    const [accountSelected, setAccountSelected] = useState({});
    const [transaction, setTransaction] = useState({

    })
    useEffect(() => {
        // let token = JSON.parse(sessionStorage.getItem('token'));
        // console.log(token);
        // const bankAccounts=getAccounts(token)
        // setAccounts(bankAccounts)
    }, [])
    const AccountCard = () => {
        console.log(accounts)

        return (
            accounts.map(account => {
                return (
                    <div onClick={() => setAccountSelected(account)}>
                        <Card  sx={{ marginBottom:"2em",width: "25em", border: '1px solid #5A287D', borderBottomWidth: 5 }} >
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
                    </div>
                )
            })

        )
    }
    const PaymentCard = () => {
        const navigate = useNavigate()
        const [userName, setUserName] = useState('')
        const [usrId, setUsrId] = useState(null)
        const [payConf, setPayConf] = useState(false)

       

        const { comp, setComp } = useContext(AppContext);

        const createTransaction = (event) => {

            let req = {
                userId: sessionStorage.getItem('bankUserId'),
                amount: comp.amount,
                bookingId: comp.bookingId,
    
            };

            axios.post('http://localhost:8001/transaction/create', req, { headers: { "Authorization": `Bearer ${sessionStorage.getItem('token')}` } })
                .then(res => {
                    if(res.data!=null){
                    setPayConf(true)
                    navigate(`/tracker?bookingId=${comp.bookingId}&amount=${comp.amount}&expiry=${comp.expiry}&contributors=${comp.contributors}&frombank=true`)
                }
                })
                    
                .catch(err => {
                    throw err
                })
        };

        console.log(comp);
        const [pin,setPin]=useState(null)
const handlePin=(value)=>{
setPin(value)
}
        if
            (payConf) {
                return (
                    <Alert icon={<CheckIcon fontSize="18" />} severity="success">
                        Your payment has been approved.
                    </Alert>
                )
        }
        else {
            return (

                <Container>

                    <Box sx={{ display: "flex", flexDirection: "column", textAlign: "left", opacity: accountSelected == "" ? "0.3" : "1" }}>
                        <Typography sx={{ fontSize: 18, backgroundColor: "#5A287D" }} color="white" gutterBottom>
                            Check and authorize
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            From
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            {sessionStorage.getItem('bankUserName')}
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            To
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            Merchant Travel
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            Account Number
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            {accountSelected.accountNumber}
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            Payment Reference
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            {comp.bookingId}
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            Amount
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            £ {comp.amount}
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="black" gutterBottom>
                            Payment Due Datetime
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="#894570" gutterBottom>
                            {comp.expiry}
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
                                onChange={(value, index) =>  handlePin(value)}
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
                            <Button sx={{ backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Confirm</Button>

                        </Box>
                    </Box>
                </Container>
            )
        }
    
}

return (
    <Grid justifyContent="center" container spacing={1}>
        <Grid item xs={5}>
            <Box>
                <AccountCard />
            </Box>
        </Grid>
        <Grid item xs={5}>
            <Box>

                <PaymentCard />
            </Box>
        </Grid>
    </Grid>
);
}
export default Netbanking;
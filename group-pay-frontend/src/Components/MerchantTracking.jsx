import React, { useState,useContext,createContext,useEffect } from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography"
import nwgLogo from "../assets/Natwest-logo.jpg"
import Button from '@mui/material/Button';
import axios from 'axios';
import { AuthContext } from '../App';
import NetBanking from './NWG/Netbanking';
import { v4 as uuid } from 'uuid'
import { SettingsPhoneTwoTone } from '@mui/icons-material';
import Login from './NWG/Login';
import {useLocation, useNavigate} from 'react-router-dom';
import { IFrame } from './NWG/layout/IFrame';
import Header from './NWG/layout/Header';
import Footer from './NWG/layout/Footer';
import { useSearchParams } from 'react-router-dom';


export const AppContext = createContext();

const Tracker = ({ amount, bookingId }) => {
    const [searchParams] = useSearchParams();
    const location = useLocation();
    const navigate= useNavigate()
    const { isLoggedIn } = useContext(AuthContext);
    const [transactions, setTransactions] = useState([])
    const [show, setShow] = useState(false)
    const [comp, setComp] = useState({
        page:'login',
        amount:searchParams.get('amount'), 
        bookingId: searchParams.get('bookingId') ,
        referenceId:"",
        expiry:searchParams.get('expiry'),
        contributors:searchParams.get('contributors')
})
    const [usrId, setUsrId] = useState(null)
    const [bookingConf, setBookingConf] = useState(false)

    const [transaction, setTransaction] = useState(
        

        {
            status: '',
            transactionId: '',
            timestamp: ''

        }
    )

    const populateRows = () => {
        let rows = transactions.map(transaction => {
            return {
                user: transaction.firstName + " " + transaction.lastName + "\n" + transaction.email,
                paymentStatus: transaction.paymentStatus,
                timestamp: transaction.createdAt,
                id: transaction.transactionId
            }
        })
        return rows;
    }
    const rows = [
        ...populateRows()
    ]
    const getTransactions = () => {
        axios.get(`http://localhost:8002/merchant-transaction/transactions/${searchParams.get('bookingId')}`)
            .then((response) => {
                let { data } = response
                console.log(data)
                let formattedData=data.map(({firstName,lastname,email,paymentStatus,createdAt,transactionId}) => 
                    ({firstName,lastname,email,paymentStatus,createdAt,transactionId})
            )
                
                setTransactions(formattedData)
                if(data.length==searchParams.get('contributors')){
                    setBookingConf(true)
                }
            }
            )
    }
    useEffect(() => {
        if(searchParams.get('frombank')=="true" && searchParams.get('frombank'))
            setShow(false)
        getTransactions()
        let usrId=sessionStorage.getItem('merchUserId')
        if(usrId!=null)
            setUsrId(usrId)
    },[])

    const createTransaction = () => {
        let request = { userId: usrId, amount: searchParams.get('amount'), bookingId: searchParams.get('bookingId') }
        axios.post('http://localhost:8002/merchant-transaction/create', request)
            .then((response) => {
                let { data } = response
                if (data != null)
                    // navigate('/nwg-login')
                console.log("resp",data.data)
                setComp({...comp,referenceId:data.data.paymentRefId})
                setShow(true)

            })
    }
    useEffect(()=>{
        console.log(comp);
    },[comp])
    return (
        <Box>
            {
                show? (
                    <AppContext.Provider value={{ comp, setComp,show,setShow }}>
                      <Header/>
                    <IFrame>
                            {comp.page == "login" ? <Login /> : <NetBanking />}
                    </IFrame>
                    <Footer/>
                    </AppContext.Provider>

                )
            :(
            <TableContainer component={Paper} sx={{}}>
                <Typography fontSize={24} textAlign={"center"}>Track your GroupPay Order</Typography>

                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>User Name/Email</TableCell>
                            <TableCell align="right">Payment Status</TableCell>
                            <TableCell align="right">Timestamp</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map((row) => (
                            <TableRow
                                key={row.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >

                                <TableCell align="right">{row.firstName+" "+row.lastName+"\n"+row.email}</TableCell>
                                <TableCell align="right">{row.paymentStatus}</TableCell>
                                <TableCell align="right">{row.timestamp}</TableCell>

                            </TableRow>
                        ))}

                    </TableBody>
                </Table>

                <Button sx={{ margin:"5em 50%",backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Pay Now</Button>

                <Box sx={{ display: "flex", alignItems: "center", justifySelf: "flex-start", flexDirection: "column" }}>
                    <Typography>powered by</Typography>

                    <img src={nwgLogo} width={'100px'} />
                </Box>
            </TableContainer>
            )}
        </Box>
    )
}
export default Tracker;  //export the component
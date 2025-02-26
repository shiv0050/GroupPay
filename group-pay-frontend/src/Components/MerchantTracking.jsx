import React, { useState, useContext, createContext, useEffect, useMemo } from 'react'
import Box from "@mui/material/Box"
import Typography from "@mui/material/Typography"
import nwgLogo from "../assets/Natwest-logo.jpg"
import Button from '@mui/material/Button';
import axios from 'axios';
import { AuthContext } from '../App';
import NetBanking from './NWG/Netbanking';
import { IFrame } from './NWG/layout/IFrame';
import Header from './NWG/layout/Header';
import Footer from './NWG/layout/Footer';
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component
import { ClientSideRowModelModule, ModuleRegistry } from 'ag-grid-community'; 
import BankLogin from './BankLogin';

ModuleRegistry.registerModules([ ClientSideRowModelModule ]); 

export const AppContext = createContext();

const Tracker = () => {

    const [transactions, setTransactions] = useState([])
    const colDefs = useMemo(() => [
        { name: "Name", field: "name" },
        { name: "Email", field: "email" },
        { name: "Status", field: "paymentStatus" },
        { name: "Timestamp", field: "createdAt" }
    ]);
    const [show, setShow] = useState(false)
    const [showAlert, setShowAlert] = useState(false)
    const [bookingId, setBookingId] = useState('')
    const [usrId, setUsrId] = useState(null)

    const [comp, setComp] = useState({
        page: 'login',
        amount: 0,
        paymentRefId: "",
        expiry: "",
        contributors: ""
    })
    
    useEffect(() => {
        init()
    }, [])

    useEffect(() => {
        if(bookingId){
            getBookingDetails()
            getTransactions()
            checkBookingComplete()
        }
    }, [bookingId])

    const init = () => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const id = urlParams.get('id');
        setBookingId(id);

        let usrId = sessionStorage.getItem('merchUserId')
        if (usrId != null)
            setUsrId(usrId)
    }
    const getTransactions = () => {
        axios.get(`http://localhost:8002/merchant-transaction/transactions/${bookingId}`)
            .then((response) => {
                let { data } = response
                console.log("m_txnlist", data)
                setTransactions(data)
            }
        )
    }
    const checkBookingComplete = () => {
        axios.get(`http://localhost:8002/merchant-transaction/status/${bookingId}`)
            .then((response) => {
                let { data } = response
                console.log("booking status", data)
                setShowAlert(data)
            }
        )
    }
    const getBookingDetails = () => {
        axios.get(`http://localhost:8002/merchant-booking/${bookingId}`)
            .then((response) => {
                let { data } = response
                console.log("booking details", data)  
                setUsrId(data.initiatorId)
                setComp({...comp, amount: data.amount, contributors: data.contributors, expiry: data.expiry})          
            }
        )
    }
    const createTransaction = () => {
        let request = { userId: usrId, amount: comp.amount / comp.contributors, bookingId: bookingId }
        axios.post('http://localhost:8002/merchant-transaction/create', request)
            .then((response) => {
                let { data } = response
                if (data != null)
                    console.log("Merchant txn Created", data)
                setComp({ ...comp, paymentRefId: data.paymentRefId })
                setShow(true)

            })
    }
    return (
        <>
            <Button sx={{ margin: "5em 50%", backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Pay your share</Button>

            <Box>
                {
                    show ? (
                        <AppContext.Provider value={{ comp, setComp, show, setShow }}>
                            <Header />
                            <IFrame>
                                {comp.page == "login" ? <BankLogin /> : <NetBanking />}
                            </IFrame>
                            <Footer />
                        </AppContext.Provider>

                    )
                        :

                        showAlert ? (
                            <div style={{ height: 500 }}>booking Complete</div>

                        ) : (<div style={{ height: 500 }}>
                            <AgGridReact
                                rowData={transactions}
                                columnDefs={colDefs}
                            />

                            <Button sx={{ margin: "5em 50%", backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Pay Now</Button>

                            <Box sx={{ display: "flex", alignItems: "center", justifySelf: "flex-start", flexDirection: "column" }}>
                                <Typography>powered by</Typography>

                                <img src={nwgLogo} width={'100px'} />
                            </Box>

                        </div>
                        )}
            </Box>
        </>
    )
}
export default Tracker;  //export the component
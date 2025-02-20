import React, { useState, useContext, createContext, useEffect, useMemo } from 'react'
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
import { useLocation, useNavigate } from 'react-router-dom';
import { IFrame } from './NWG/layout/IFrame';
import Header from './NWG/layout/Header';
import Footer from './NWG/layout/Footer';
import { useSearchParams } from 'react-router-dom';
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component


export const AppContext = createContext();

const Tracker = ({ amount, bookingId, contributors }) => {

    const { isLoggedIn } = useContext(AuthContext);
    const [transactions, setTransactions] = useState([])
    const colDefs = useMemo(() => [
        { name: "Name", field: "name" },
        { name: "Email", field: "email" },
        { name: "Status", field: "paymentStatus" },
        { name: "Timestamp", field: "createdAt" }
    ]);
    const [show, setShow] = useState(false)
    const [showAlert, setShowAlert] = useState(false)

    const [comp, setComp] = useState({
        page: 'login',
        amount: amount,
        bookingId: bookingId,
        paymentRefId: "",
        expiry: "",
        contributors: contributors
    })
    const [usrId, setUsrId] = useState(null)





    const getTransactions = () => {
        axios.get(`http://localhost:8002/merchant-transaction/transactions/${bookingId}}`)
            .then((response) => {
                let { data } = response
                console.log("m_txnlist", data)
                setTransactions(data)
            }
            )
    }
    const checkBookingComplete = () => {
        axios.get(`http://localhost:8002/merchant-transanction/${bookingId}/status}`)
            .then((response) => {
                let { data } = response
                console.log("booking status", data)
                setShowAlert(true)
            
            }
            )
    }
    useEffect(() => {
        setShow(false)
        getTransactions()
        checkBookingComplete()
        let usrId = sessionStorage.getItem('merchUserId')
        if (usrId != null)
            setUsrId(usrId)
    }, [show])

    const createTransaction = () => {
        let request = { userId: usrId, amount: amount / contributors, bookingId: bookingId }
        axios.post('http://localhost:8002/merchant-transaction/create', request)
            .then((response) => {
                let { data } = response
                if (data != null)
                    console.log("Merchant txn Created", data)

                setComp({ ...comp, paymentRefId: data.paymentRefId })
                setShow(true)

            })
    }
    useEffect(() => {
        console.log(comp);
    }, [comp])
    return (
        <>
            // Data Grid will fill the size of the parent container

            <Button sx={{ margin: "5em 50%", backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Pay your share</Button>

            <Box>
                {
                    show ? (
                        <AppContext.Provider value={{ comp, setComp, show, setShow }}>
                            <Header />
                            <IFrame>
                                {comp.page == "login" ? <Login /> : <NetBanking />}
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
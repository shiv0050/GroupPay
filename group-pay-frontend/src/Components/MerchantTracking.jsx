import React, { useState } from 'react'
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
import { v4 as uuid } from 'uuid'
import { SettingsPhoneTwoTone } from '@mui/icons-material';
import Login from './NWG/Login';
export const AppContext = createContext();

const Tracker = ({ amount, bookingId }) => {
    const { isLoggedIn } = useContext(AuthContext);
    const [transactions, setTransactions] = useState([])
    const [show, setShow] = useState(false)
    const [comp, setComp] = useState('')
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
        axios.get(`http://localhost:8001/merchant-user/transactions/${bookingId}`)
            .then((response) => {
                let { data } = response
                let formattedData = data.map(item => { item.firstName + " " + item.lastName, item.paymentStatus, item.createdAt, item.transactionId })
                setTransactions(formattedData)
            }
            )
    }
    useEffect(() => {
        getTransactions()
    })
    const createTransaction = () => {
        let request = { userId: isLoggedIn.userId, amount: amount, bookingId: bookingId }
        axios.post('http://localhost:8001/merchant-user/create', request)
            .then((response) => {
                let { data } = response
                if (data != null)
                    setShow(true)
            })
    }
    return (
        <Box>
            {
                show && (

                    <iframe style={{ width: "100vw", height: "100vh" }}>
                        <AuthContext.Provider value={{ comp, setComp }}>
                            {comp == "login" ? <Login /> : <NetBanking />}
                        </AuthContext.Provider>
                    </iframe>
                )
            }
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

                                <TableCell align="right">{row.user}</TableCell>
                                <TableCell align="right">{row.paymentStatus}</TableCell>
                                <TableCell align="right">{row.timestamp}</TableCell>

                            </TableRow>
                        ))}

                    </TableBody>
                </Table>

                <Button sx={{ backgroundColor: "#5A287D", color: "white" }} size="small" onClick={createTransaction}>Pay Now</Button>

                <Box sx={{ display: "flex", alignItems: "center", justifySelf: "flex-start", flexDirection: "column" }}>
                    <Typography>powered by</Typography>

                    <img src={nwgLogo} width={'100px'} />
                </Box>
            </TableContainer>
        </Box>
    )
}
export default Tracker;  //export the component
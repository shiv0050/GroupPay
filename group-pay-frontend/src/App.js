import React, { useState, createContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MerchantHeader from './Components/MerchantHeader';
import MerchantLogin from './Components/MerchantLogin';
import MerchantDashboard from './Components/MerchantDashboard';
import MerchantFooter from './Components/MerchantFooter';
import MerchantCheckout from './Components/MerchantCheckout';
import MerchantTracking from './Components/MerchantTracking'
import Login from './Components/NWG/Login';
import Netbanking from './Components/NWG/Netbanking';
export const AuthContext = createContext();

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(null);

  return (
    <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
      <Router>
        <MerchantHeader />
        <Routes>
          
          <Route path="/login" element={<MerchantLogin />} />
          <Route path="/" element={<MerchantDashboard/>} /> 
          <Route path="/checkout" element={<MerchantCheckout/>}/>
          <Route path="/tracker" element={<MerchantTracking/>}/>
          <Route path="/nwg-login" element={<Login/>}/>
          <Route path="/nwg-netbanking" element={<Netbanking/>}/>

        </Routes>
        <MerchantFooter/>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;
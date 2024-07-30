import React, { useState, createContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MerchantHeader from './Components/MerchantHeader';
import MerchantLogin from './Components/MerchantLogin';
import MerchantDashboard from './Components/MerchantDashboard';
import MerchantFooter from './Components/MerchantFooter';
import MerchantCheckout from './Components/MerchantCheckout';

export const AuthContext = createContext();

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
      <Router>
        <MerchantHeader />
        <Routes>
          
          <Route path="/login" element={<MerchantLogin />} />
          <Route path="/" element={<MerchantDashboard/>} /> 
          <Route path="/checkout" element={<MerchantCheckout/>}/>
        </Routes>
        <MerchantFooter/>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;
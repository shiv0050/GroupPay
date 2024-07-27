import React, { Component } from 'react'
import './login.css'
function MerchantLogin({FaUser,FaLock}) {

    return (
      <div className='form-container'>
        <h2 className='loginH2'> LogIn</h2>
        <form>
            <div className='lform-control'>
            <input type='text' placeholder='Enter email address' />
            <FaUser/>
            </div>
            <div className='lform-control'>
            
            <input type='password' placeholder='Enter password' />
            <FaLock/>
            </div>
            <div>
                <button className="btn btn-sm btn-outline-secondary" type='submit'>Sign In</button>
            </div>
        </form>
      </div>
    )
  
}

export default MerchantLogin
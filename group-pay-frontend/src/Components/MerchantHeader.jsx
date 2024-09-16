import { useNavigate, Link } from 'react-router-dom'
import booknow from '../assets/logo4.png';
import { FaUserLarge } from "react-icons/fa6";
import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../App';

function MerchantHeader() {
  const { isLoggedIn } = useContext(AuthContext);
  const headerStyle = {
    backgroundColor: '#002f72',
    color: '#fff',
  }
  const navigate = useNavigate();
  const handleLogin = () => {
    navigate('/login');
  }
  const handleLogout = () => {
    sessionStorage.removeItem("merchUserId")
    sessionStorage.removeItem("merchUserName")
    sessionStorage.removeItem("merchUserEmail")
    sessionStorage.removeItem("token")
    setUserName('')

    navigate('/login');
  }
  const [userName, setUserName] = useState('')
  useEffect(() => {
    let usrName = sessionStorage.getItem('merchUserName')
    if (usrName != null)
      setUserName(usrName)
  }, [])
  return (
    <nav className="navbar navbar-expand-lg bg-primary" style={{ backgroundColor: '#002f72' }}>
      <div className="container-fluid">
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <a className="navbar-brand" href="#"><img src={booknow} alt="Logo" style={{ height: '45px' }} /></a>
        <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link active" aria-current="page" to="/" style={{ color: 'white' }}>Home</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link active" to="/" style={{ color: 'white' }}>Dashboard</Link>
            </li>

          </ul>
          <form className="d-flex" role="search">
            <input className="form-control me-2 active" type="search" placeholder="Search" aria-label="Search" />
            <button className="btn btn-sm  " type="submit" style={{ margin: '0.2em', color: 'white' }}> Search</button>

          </form>
          {isLoggedIn != null ? <button className="btn btn-sm " type="button" style={{ color: 'white' }} onClick={handleLogout}>Logout</button>
            :
            <button className="btn btn-sm " type="submit" style={{ color: 'white' }} onClick={handleLogin}>Login</button>
          }
        </div>
      </div>
    </nav>
  )
}

export default MerchantHeader;
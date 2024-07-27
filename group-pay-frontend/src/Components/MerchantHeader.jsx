
import logo4 from '../assets/logo.png';
function MerchantHeader() {
    const headerStyle={
      backgroundColor: '#002f72',
      color: '#fff',
    }
    return (
      <nav className="navbar navbar-expand-lg bg-primary"  style={{backgroundColor: '#002f72;'}}>
  <div className="container-fluid">
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <a className="navbar-brand" href="#"><img src={logo4} alt="Logo" style={{ height: '40px' }} /></a>
    <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <a className="nav-link active" aria-current="page" href="#" style={{color:'white'}}>Home</a>
        </li>
        <li className="nav-item">
          <a className="nav-link active" href="#" style={{color:'white'}}>Dashboard</a>
        </li>
        
      </ul>
      <form className="d-flex" role="search">
        <input className="form-control me-2 active" type="search" placeholder="Search" aria-label="Search"/>
        <button className="btn btn-sm  " type="submit" style={{ margin: '0.2em' ,color:'white'}}> Search</button>
        
      </form>
      <button className="btn btn-sm " type="submit"style={{color:'white'}} >Login</button>
    </div>
  </div>
</nav>
    )
  }
  
export default MerchantHeader;
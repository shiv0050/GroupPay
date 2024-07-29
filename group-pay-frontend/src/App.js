import logo from './logo.svg';
import './App.css';
import Login from './Components/NWG/Login';
import Netbanking from './Components/NWG/Netbanking';
import Header from './Components/NWG/layout/Header';
function App() {
  return (
    <div className="App">
      <Header/>
      <Login/>
      {/* <Netbanking/> */}
    </div>
  );
}

export default App;

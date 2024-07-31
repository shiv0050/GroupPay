import './App.css';
import Login from './Components/NWG/Login';
import Netbanking from './Components/NWG/Netbanking';
import Header from './Components/NWG/layout/Header';
import Footer from './Components/NWG/layout/Footer';
export const AuthContext = createContext();

function App() {
  const [bankUserLoggedIn, setBankUserLoggedIn] = useState(null);

  return (
    <AuthContext.Provider value={{ bankUserLoggedIn, setBankUserLoggedIn }}>
      <Router>
        <Header />
        <Routes>
          <Route path="/nwg-login" element={<Login />} />
          <Route path="/nwg-netbanking" element={<Netbanking />} />
        </Routes>
        <Footer />
      </Router>
    </AuthContext.Provider>
  );
}

export default App;

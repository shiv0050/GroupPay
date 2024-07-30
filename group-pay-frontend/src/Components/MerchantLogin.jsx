import React, { useState ,useContext, useEffect} from "react";
import { Form, Button, Alert } from "react-bootstrap";
import "./login.css";
import { useNavigate } from "react-router-dom";
import { RiBankFill } from "react-icons/ri";
import axios from 'axios';
import img from "../assets/background.avif";
import Logo from "../assets/logo4.png";
import { AuthContext } from "../App";

const Login = () => {
  const [inputUsername, setInputUsername] = useState("");
  const [inputPassword, setInputPassword] = useState("");
  const nevigate=useNavigate();
  const {setIsLoggedIn}=useContext(AuthContext);
  const [show, setShow] = useState(false);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  const [success, setSuccess]=useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    await delay(500);
    console.log(`Username :${inputUsername}, Password :${inputPassword}`);
    const response=axios.post("http://localhost:8002/merchant-user/login",{email:inputUsername,password:inputPassword})
    .then(data => {
      console.log(data.data.success);
      if (!data.data.success) {
        setShow(true);
      }
      else{
  
          setIsLoggedIn(true);
          nevigate("/");
      }
    })
    .catch(error => console.log(error));
    console.log(response);
    
    setLoading(false);
  };

  const handlePassword = () => {};

  function delay(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }



  return (
    <div
      className="sign-in__wrapper" style={{backgroundImage:`url(${img})`,backgroundSize: 'cover',backgroundPosition: 'center'}}
    >
      {/* Overlay */}
      <div className="sign-in__backdrop"></div>
      {/* Form */}
      <Form className="shadow p-4 bg-white rounded" onSubmit={handleSubmit}>
        {/* Header */}
        <img
          className="img-thumbnail mx-auto d-block mb-2"
          src={Logo}
          alt="logo"
        />
        <div className="h4 mb-2 text-center">Sign In</div>
        {/* ALert */}
        {show ? (
          <Alert
            className="mb-2"
            variant="danger"
            onClose={() => setShow(false)}
            dismissible
          >
            Incorrect username or password.
          </Alert>
        ) : (
          <div />
        )}
        <Form.Group className="mb-2" controlId="username">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            value={inputUsername}
            placeholder="Enter your email"
            onChange={(e) => setInputUsername(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group className="mb-2" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={inputPassword}
            placeholder="Enter Password"
            onChange={(e) => setInputPassword(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group className="mb-2" controlId="checkbox">
          <Form.Check type="checkbox" label="Remember me" />
        </Form.Group>
        {!loading ? (
          <Button className="w-100" variant="primary" type="submit">
            Log In
          </Button>
        ) : (
          <Button className="w-100" variant="primary" type="submit" disabled>
            Logging In...
          </Button>
        )}
        <div className="d-grid justify-content-end">
          <Button
            className="text-muted px-0"
            variant="link"
            onClick={handlePassword}
          >
            Forgot password?
          </Button>
        </div>
      </Form>
    </div>
  );
};

export default Login;

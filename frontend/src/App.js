import logo from './logo.png';
import './App.css';

import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import NavBar from './components/NavBar';
import Registration from './components/Registration';
import Login from './components/Login';
import RegisterUser from './components/RegisterUser';

/*
	React Router is a standard library for routing in React. 
	It enables the navigation among views of various components in a React Application, 
  allows changing the browser URL, and keeps the UI in sync with the URL. 

	React Router is a JavaScript framework that lets us handle client and server-side 
  routing in React applications. 
  It enables the creation of single-page web or mobile apps that allow navigating without 
  refreshing the page. 
  It also allows us to use browser history features while preserving the right application
   view.

   Used Version6 of Router

 > npm install react-router-dom --save
*/

import{library} from '@fortawesome/fontawesome-svg-core';
import{faSignIn, faCameraRetro} from '@fortawesome/free-solid-svg-icons';
import AboutUs from './components/AboutUs';
import { useEffect, useState } from 'react';
import AdminDashboard from './components/AdminDashboard';
library.add(faSignIn,faCameraRetro);

function App() {

  const [userId,setUserId] = useState();

  useEffect(() => {
    const loggedInUser = localStorage.getItem('userId');
    if(loggedInUser) {
      setUserId(loggedInUser);
    }
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" /> 
        <h1>EzLoans</h1>
      </header>

      <section>
        <div style={{
          backgroundImage: "url(/images/back.jpg)",
          backgroundSize:'cover', backgroundRepeat:'no-repeat',
          minHeight:'80vh', minWidth:'100vw'
        }}>
          <Router>
            <NavBar/>
            <Routes>
              <Route path='/login' element = { userId ? <Navigate replace to={"/dashboard"} /> : <Login role="user" />} />
              <Route path='/login-admin' element={ userId ? <Navigate replace to={"/dashboard"} /> : <Login role="admin"/>}/>
              <Route path='/dashboard' element={ userId ? <AdminDashboard /> : <Navigate replace to={"/login-admin"} /> } />
              <Route path='/about-us' element={<AboutUs />} />
              <Route path='/register-user' element={<RegisterUser />} />
            </Routes>
          </Router>
        </div>
      </section>

      <footer className='footer'>
        <p>&copy; All Rights Reserved to Wells Fargo</p>
      </footer>
    </div>
  );
}

export default App;

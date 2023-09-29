import React from "react";
import { Link, useNavigate } from 'react-router-dom';

import '../styles/NavBar.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

/* In HTML we use Hyperlink for Navigation - <a href=""> 
In ReactJS we use router for navigation
*/

//Arrow functional Component

const NavBar = (props) => {

    const navigate = useNavigate();

    const handleLogout = () => {
        console.log("Logout");
        var id = props.id;
        localStorage.removeItem("credentials");
        localStorage.removeItem("adminId");
        localStorage.removeItem("userId");
        props.setId();
        navigate('/');
    }

    return (
        <nav classNme='navbar'>
            {/* <ul className="nav-list"> */}
            {
                props.Id ? <ul className="nav-list">
                    <li className="nav-item">
                        <Link to="/dashboard" className="navlink nav-button">Dashboard</Link>
                    </li>

                    <li className="nav-item-right">
                        <Link to="/about-us" className="navlink nav-button">About Us</Link>
                    </li>

                    <li className="nav-item-right">
                        <Link to="/login" className="navlink nav-button" onClick={handleLogout}>Logout</Link>
                    </li>
                </ul> : <ul className="nav-list">
                    <li className="nav-item">
                        <Link to="/login" className="navlink nav-button">User Login <span><FontAwesomeIcon icon="sign-in"></FontAwesomeIcon></span> </Link>
                    </li>

                    <li className="nav-item">
                        <Link to="/login-admin" className="navlink nav-button">Admin Login <span><FontAwesomeIcon icon="sign-in"></FontAwesomeIcon></span> </Link>
                    </li>

                    <li className="nav-item-right">
                        <Link to="/about-us" className="navlink nav-button">About Us</Link>
                    </li>
                </ul>
            }
            {/* <li className="nav-item">
                    <Link to="/login" className="navlink">User Login</Link>
                    <span><FontAwesomeIcon icon="sign-in"></FontAwesomeIcon></span>
                </li>

                <li className="nav-item">
                    <Link to="/login-admin" className="navlink">Admin Login</Link>
                </li>

                <li className="nav-item-right">
                    <Link to="/about-us" className="navlink">About Us</Link>
                </li>
            </ul> */}
        </nav>
    );
}

export default NavBar;
import React from "react";
import {Link} from 'react-router-dom';

import '../styles/NavBar.css';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'

/* In HTML we use Hyperlink for Navigation - <a href=""> 
In ReactJS we use router for navigation
*/

//Arrow functional Component

const NavBar = () => {
    return(
        <nav classNme='navbar'>
            <ul className="nav-list">
                <li className="nav-item">
                    <Link to="/login" className="navlink">User Login</Link>
                    <span><FontAwesomeIcon icon="sign-in"></FontAwesomeIcon></span>
                </li>

                <li className="nav-item">
                    <Link to="/login-admin" className="navlink">Admin Login</Link>
                </li>

                <li className="nav-item-right">
                    <Link to="/about-us" className="navlink">About Us</Link>
                </li>
            </ul>
        </nav>
     );
}

export default NavBar;
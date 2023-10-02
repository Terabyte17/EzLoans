import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/Login.css';
import axios from 'axios';

const Login = (props) => {

    const navigate = useNavigate();
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });
    const [loginUnsuccessful, setLoginUnsuccessful] = useState(false);
    const [submitBtnDisabled, setSubmitBtnDisabled] = useState(false);

    const onChangeHandler = (event) => {
        console.log("Event is: ", event)
        const { name, value } = event
        setCredentials((prev) => {
            return { ...prev, [name]: value }
        })
    }

    const onLoginError = () => {
        console.log("login unsucessful")
        setLoginUnsuccessful(true);
        setSubmitBtnDisabled(false);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Submit successful: ", credentials);
        console.log(props.role);
        const postUrl = 'http://localhost:8088/ezloans/api/login/' + props.role;

        try {
            axios.post(postUrl, { username: credentials.username }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }, {
                auth: {
                    username: credentials.username,
                    password: credentials.password
                }
            }
            ).then(res => {
                console.log("yes1");
                console.log(res.data);
                if (res.data == "") {
                    onLoginError();
                } else {
                    console.log("Response is: ", res);
                    if (props.role === "admin")
                        localStorage.setItem('adminId', res.data);
                    else localStorage.setItem('userId', res.data);
                    localStorage.setItem('credentials', JSON.stringify(credentials));
                    props.setId(res.data);
                    navigate('/dashboard', { replace: true });
                }
            })
        } catch (error) {
            console.log("Auth error - ", error);
        }

        // const item_detail = {
        //     "itemDesc": "4 Seater dining table",
        //     "issueStatus": false,
        //     "itemMake": "Wooden",
        //     "itemCategory": "Furniture"
        // }

        // try {
        //     axios.post('http://localhost:8088/ezloans/api/items', item_detail, {
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //     },
        //         // {
        //         //     auth: {
        //         //         username: "yash",
        //         //         password: "abc12345"
        //         //     }
        //         // }
        //     )
        //         .then(res => {
        //             if (res.login === 'true') {
        //                 window.location = '/dashboard'
        //             }
        //         })
        // }
        // catch (error) {
        //     console.log(error);
        // }
    }


    // return (
    //     <div>
    //         { loginUnsuccessful && <h3>Error in Logging in</h3>}
    //         <div className='login-container'>
    //             <h2 className='form-heading'>{props.role} Login</h2>
    //             <form onSubmit={handleSubmit}>
    //                 <div className="mb-3">
    //                     <label className='form-label'>Username</label>
    //                     <input
    //                         type="text"
    //                         className="form-control"
    //                         placeholder="Enter username"
    //                         name="username"
    //                         value={credentials.username}
    //                         onChange={(e) => onChangeHandler(e.target)}
    //                     />
    //                 </div>
    //                 <div className="mb-3">
    //                     <label className='form-label'>Password</label>
    //                     <input
    //                         type="password"
    //                         className="form-control"
    //                         placeholder="Enter password"
    //                         name="password"
    //                         value={credentials.password}
    //                         onChange={(e) => onChangeHandler(e.target)}
    //                     />
    //                 </div>
    //                 <div className="d-grid">
    //                     <button type="submit" className="btn btn-primary">
    //                         Submit
    //                     </button>
    //                 </div>
    //             </form>
    //         </div>
    //     </div>
    // )

    return (
        <div className='login-page'>
            {loginUnsuccessful && <h3 className="error-message">Error in Logging in</h3>}
            <div className='login-container'>
                <h2 className='form-heading'>{props.role} Login</h2>
                <form onSubmit={handleSubmit} className='login-form'>
                    <div className="mb-3">
                        <label className='form-label'>Username</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter username"
                            name="username"
                            value={credentials.username}
                            onChange={(e) => onChangeHandler(e.target)}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className='form-label'>Password</label>
                        <input
                            type="password"
                            className="form-control"
                            placeholder="Enter password"
                            name="password"
                            value={credentials.password}
                            onChange={(e) => onChangeHandler(e.target)}
                            required
                        />
                    </div>
                    <div className="d-grid">
                        <button type="submit" className="btn btn-primary" disabled={submitBtnDisabled}>
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    )

}

export default Login
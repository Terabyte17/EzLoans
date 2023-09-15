import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/Login.css';
import axios from 'axios';

const Login = (props) => {
    
    const [credentials, setCredentials] = useState({
        email: '',
        password: ''
    });

    const onChangeHandler = (event) => {
        console.log("Event is: ", event)
        const {name,value} = event
        setCredentials((prev) => {
            return {...prev, [name]: value}
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Submit successful: ", credentials);
        axios.post('http://localhost:8080/', { credentials}) // enter api endpoint here
            .then( res => {
                if(res.login === 'true'){
                    window.location = '/dashboard'
                }
            })
    }


    return (
        <div className='login-container'>
            <h2 className='form-heading'>{props.role} Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                <label className='form-label'>Email address</label>
                <input
                    type="email"
                    className="form-control"
                    placeholder="Enter email"
                    name="email"
                    value={credentials.username}
                    onChange={(e) => onChangeHandler(e.target)}
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
                />
                </div>
                <div className="d-grid">
                <button type="submit" className="btn btn-primary">
                    Submit
                </button>
                </div>
            </form>
        </div>
    )
}

export default Login
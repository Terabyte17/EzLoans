import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/RegisterUser.css';

const RegisterUser = (props) => {
    return (
        <div className='register-container'>
            <h2 className='form-heading'>Register User {props.action}</h2>
            <form className='register-form'>
                <div className='form-fields'>
                    <div>
                        <div className="mb-3">
                        <label className='form-label'>Name</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter Name"
                            value={props?.data?.employeeName}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Designation</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter Designation"
                            value={props?.data?.designation}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Department</label>
                        <select className="form-control" value={props?.data?.department}>
                            <option value="Sales">Sales</option>
                            <option value="Marketing">Marketing</option>
                            <option value="Engineering">Engineering</option>
                        </select>
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Gender</label>
                        <select className="form-control" value={props?.data?.gender}>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="PNTS">Prefer Not To Say</option>
                        </select>
                        </div>    
                    </div>
                    <div>
                        <div className="mb-3">
                        <label className='form-label'>Date of Birth</label>
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Enter Date of Birth"
                            value={props?.data?.dob}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Date of Joining</label>
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Enter Date of Joining"
                            value={props?.data?.doj}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Email address</label>
                        <input
                            type="email"
                            className="form-control"
                            placeholder="Enter email"
                            value={props?.data?.email}
                        />
                        </div>
                        
                        <div className="mb-3">
                        <label className='form-label'>Password</label>
                        <input
                            type="password"
                            className="form-control"
                            placeholder="Enter password"
                        />
                        </div>
                    </div>
                </div>
                <div className="d-grid">
                <button type="submit" className="submit-btn btn btn-primary">
                    Submit
                </button>
                </div>
            </form>
        </div>
    )
}

export default RegisterUser
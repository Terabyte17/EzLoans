import { React, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/RegisterUser.css';

const RegisterUser = (props) => {

    const [formData,setFormData] = useState({
        employeeId: props?.data?.employeeId,
        employeeName: props?.data?.employeeName,
        designation: props?.data?.designation,
        department: props?.data?.department,
        gender: props?.data?.gender,
        dob: props?.data?.dob,
        doj: props?.data?.doj,
    })

    const onChangeHandler = (event) => {
        console.log("Event is: ", event)
        const { name, value } = event
        setFormData((prev) => {
            return { ...prev, [name]: value }
        })
    }

    const handleSubmit = (e,action) => {
        e.preventDefault();
        console.log("form data", formData);
    }

    return (
        <div className='register-container'>
            <h2 className='form-heading'>Register User {props.action}</h2>
            <form className='register-form' onSubmit={() => handleSubmit(props.action)}>
                <div className='form-fields'>
                    <div>
                        <div className="mb-3">
                        <label className='form-label'>Name</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter Name"
                            value={props?.data?.employeeName}
                            onChange={(e) => onChangeHandler(e.target)}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Designation</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter Designation"
                            value={formData.designation}
                            onChange={(e) => onChangeHandler(e.target)}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Department</label>
                        <select className="form-control" value={formData.department}
                            onChange={(e) => onChangeHandler(e.target)}>
                            <option value="Sales">Sales</option>
                            <option value="Marketing">Marketing</option>
                            <option value="Engineering">Engineering</option>
                        </select>
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Gender</label>
                        <select className="form-control" value={formData.gender}
                            onChange={(e) => onChangeHandler(e.target)}>
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
                            value={formData.dob}
                            onChange={(e) => onChangeHandler(e.target)}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Date of Joining</label>
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Enter Date of Joining"
                            value={formData.doj}
                            onChange={(e) => onChangeHandler(e.target)}
                        />
                        </div>
                        <div className="mb-3">
                        <label className='form-label'>Email address</label>
                        <input
                            type="email"
                            className="form-control"
                            placeholder="Enter email"
                            value={formData.email}
                            onChange={(e) => onChangeHandler(e.target)}
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
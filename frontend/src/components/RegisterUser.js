import { React, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/RegisterUser.css';
import AdminService from '../services/AdminService';
import { useNavigate } from 'react-router-dom';


const RegisterUser = (props) => {

    const navigate = useNavigate();




    const [formData, setFormData] = useState({
        employeeId: props?.data?.employeeId,
        employeeName: props?.data?.employeeName,
        designation: props?.data?.designation,
        department: props.data ? props?.data?.department : "Sales",
        gender: props.data ? props?.data?.gender : "Male",
        dob: props?.data ? props?.data?.dob : new Date().toISOString().substring(0, 10),
        doj: props?.data ? props?.data?.doj : new Date().toISOString().substring(0, 10),
        username: props?.data?.username,
        email: props?.data?.email,
        password: props?.data?.password,
    })



    const onChangeHandler = (event) => {
        console.log("Event is: ", event)
        const { name, value } = event
        setFormData((prev) => {
            return { ...prev, [name]: value }
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        if (props.action == "add") {
            formData["admin"] = { "username": formData["username"], "password": formData["password"], "role": "ROLE_USER", "enabled": "true" };
            AdminService.createCustomerData(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("New customer response: ", response);
                props.handleCloseForm();
                console.log("Form closed");

            }).catch((error) => {
                console.log("Incomplete data");
                console.log(error);
            })
        } else {
            AdminService.updateCustomerData(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("Customer update: ", response);
                props.handleCloseForm();
                console.log("Form closed");
            }).catch((error) => {
                console.log("Update issue: ", error);
            })
        }
        console.log("form data", formData);

    }

    const handleClickCloseButton = () => {
        props.handleCloseForm();
    }

    return (
        <div className='register-container-custdata'>
            <button onClick={handleClickCloseButton}>Close</button>
            <h2 className='form-heading-apploan'>{props.action == "add" ? "Add" : "Edit"} Employee</h2>
            <form className='register-form' onSubmit={(e) => handleSubmit(e)}>
                <div className='form-fields'>
                    <div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Name</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Name"
                                name="employeeName"
                                value={formData?.employeeName}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Designation</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Designation"
                                name="designation"
                                value={formData?.designation}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Department</label>
                            <select className="form-control" value={formData.department ? formData?.department : "Sales"}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="department"
                            >
                                <option value="Sales">Sales</option>
                                <option value="Marketing">Marketing</option>
                                <option value="Engineering">Engineering</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Gender</label>
                            <select className="form-control" value={formData.gender ? formData?.gender : "Male"}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="gender"
                            >
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="PNTS">Prefer Not To Say</option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Date of Birth</label>
                            <input
                                type="date"
                                className="form-control"
                                placeholder="Enter Date of Birth"
                                value={formData?.dob ? new Date(formData.dob).toISOString().substring(0, 10) : new Date().toISOString().substring(0, 10)}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="dob"
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Date of Joining</label>
                            <input
                                type="date"
                                className="form-control"
                                placeholder="Enter Date of Joining"
                                value={formData?.doj ? new Date(formData.doj).toISOString().substring(0, 10) : new Date().toISOString().substring(0, 10)}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="doj"
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Email address</label>
                            <input
                                type="email"
                                className="form-control"
                                placeholder="Enter email"
                                value={formData?.email}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="email"
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Username</label>
                            <input
                                type="text"
                                name="username"
                                value={formData?.username}
                                onChange={(e) => onChangeHandler(e.target)}
                                className="form-control"
                                placeholder="Enter username"
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Password</label>
                            <input
                                type="password"
                                name="password"
                                value={formData?.password}
                                onChange={(e) => onChangeHandler(e.target)}
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


/*import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/RegisterUser.css';
import AdminService from '../services/AdminService';

const RegisterUser = (props) => {
    const [isFormOpen, setIsFormOpen] = useState(true); // Track whether the form is open or closed

    const [formData, setFormData] = useState({
        // ... (your existing state properties)
    });

    const onChangeHandler = (event) => {
        const { name, value } = event.target;
        setFormData((prev) => {
            return { ...prev, [name]: value }
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        if (props.action === "add") {
            AdminService.createCustomerData(formData)
                .then((response) => {
                    console.log("New customer response: ", response);
                })
                .catch((error) => {
                    console.log("Incomplete data");
                });
        } else {
            AdminService.updateCustomerData(formData)
                .then((response) => {
                    console.log("Customer update: ", response);
                })
                .catch((error) => {
                    console.log("Update issue: ", error);
                });
        }
        console.log("form data", formData);
    }

    // Function to handle the close button click
    const handleClose = () => {
        setIsFormOpen(false); // Set the form as closed
    };

    return (
        <div>
            {isFormOpen ? (
                <div className="register-container">
                    <div className="close-button-container">
                        <button
                            type="button"
                            className="close-button btn btn-outline-danger"
                            onClick={handleClose}
                        >
                            Close
                        </button>
                    </div>
                    <h2 className='form-heading'>Register User {props.action}</h2>
                    <form className='register-form' onSubmit={(e) => handleSubmit(e)}>
                        <div className='form-fields'>
                            <div>
                                <div className="mb-3">
                                    <label className='form-label'>Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Name"
                                        name="employeeName"
                                        value={formData?.employeeName}
                                        onChange={(e) => onChangeHandler(e.target)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className='form-label'>Designation</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Designation"
                                        name="designation"
                                        value={formData?.designation}
                                        onChange={(e) => onChangeHandler(e.target)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className='form-label'>Department</label>
                                    <select className="form-control" value={formData.department ? formData?.department : "Sales"}
                                        onChange={(e) => onChangeHandler(e.target)}
                                        name="department"
                                    >
                                        <option value="Sales">Sales</option>
                                        <option value="Marketing">Marketing</option>
                                        <option value="Engineering">Engineering</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label className='form-label'>Gender</label>
                                    <select className="form-control" value={formData.gender ? formData?.gender : "Male"}
                                        onChange={(e) => onChangeHandler(e.target)}
                                        name="gender"
                                    >
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
                                        value={formData?.dob ? new Date(formData.dob).toISOString().substring(0, 10) : new Date().toISOString().substring(0, 10)}
                                        onChange={(e) => onChangeHandler(e.target)}
                                        name="dob"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className='form-label'>Date of Joining</label>
                                    <input
                                        type="date"
                                        className="form-control"
                                        placeholder="Enter Date of Joining"
                                        value={formData?.doj ? new Date(formData.doj).toISOString().substring(0, 10) : new Date().toISOString().substring(0, 10)}
                                        onChange={(e) => onChangeHandler(e.target)}
                                        name="doj"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className='form-label'>Email address</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        placeholder="Enter email"
                                        value={formData?.email}
                                        onChange={(e) => onChangeHandler(e.target)}
                                        name="email"
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
            ) : null}
        </div>
    );
}

export default RegisterUser;
*/
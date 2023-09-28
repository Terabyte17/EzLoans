import { React, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/ApplyLoan.css';
import AdminService from '../services/AdminService';

const ApplyLoan = (props) => {


    const [formData, setFormData] = useState({
        loanId: props?.data?.loanId,
        loanType: props?.data?.loanType,
        durationInYears: props?.data?.durationInYears,
        issueDate: props.data ? props?.data?.issueDate : new Date().toISOString().substring(0, 10)
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
        console.log(props.action);
        if (props.action == "add") {
            AdminService.createLoanCard(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("New customer response: ", response);
                props.handleCloseForm();
            }).catch((error) => {
                console.log("Incomplete data");
            })
        } else {
            AdminService.updateLoanCard(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("Customer update: ", response);
                props.handleCloseForm();
            }).catch((error) => {
                console.log("Update issue: ", error);
            })
        }
        console.log("form data", formData);
    }

    return (
        <div className='register-container'>
            <h2 className='form-heading'>Register User {props.action}</h2>
            <form className='register-form' onSubmit={(e) => handleSubmit(e)}>
                <div className='form-fields'>
                    <div>
                        <div className="mb-3">
                            <label className='form-label'>Loan Type</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Loan Type"
                                name="loanType"
                                value={props?.data?.loanType}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label'>Loan Duration</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Loan Duration"
                                name="durationInYears"
                                value={formData?.durationInYears}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label'>Date of Issue</label>
                            <input
                                type="date"
                                className="form-control"
                                placeholder="Enter Date of Issue"
                                value={formData?.issueDate ? new Date(formData.issueDate).toISOString().substring(0, 10) : new Date().toISOString().substring(0, 10)}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="issueDate"
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

export default ApplyLoan;


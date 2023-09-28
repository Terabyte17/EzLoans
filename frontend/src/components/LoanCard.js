/*import React,{useState,useEffect, useImperativeHandle} from 'react'
import {useNavigate} from 'react-router-dom';

import AdminService from '../services/AdminService';

function LoanCard() {
    const history = useNavigate();
    // state Management using useState() Hook
    const [loanCard,setLoanCard] =useState([]);
    
    useEffect(() => {
        fetchLoanCard();
    }, []);
    

    const fetchLoanCard = () => {
        AdminService.getLoanCard().then((response) => {
            setLoanCard(response.data);
        })
    }

    /*const addProduct = () => {
        history('/addProduct/_add');
    }

     /*
    
    We are using the map operator to loop over our products list and create the view

    return(
        <div>
    <br/>
   
    <h1 className="text-warning">Loan Card Management</h1>
    <br/>
        <div className = "row justify-content-center">
            <button className='btn btn-info-w-auto onClick={addProduct'>Add Loan Card</button>
        </div>
    <br/>
    <div className="row justify-content-center" >
        <table className="table table-success w-auto">
         <thead>
            <tr className="table-danger">
                <th> Loan ID</th>
                <th> Loan Type</th>
                <th> Duration (in yrs)</th>
                <th> Actions</th>
            </tr>
        </thead>
        <tbody>
                {loanCard.map(
                        data => 
                        <tr key={data.loanId}>
                            <td> {data.loanId} </td>
                            <td> {data.loanType} </td>
                            <td> {data.durationInYears}</td>
                        </tr>
                    )
                }
        </tbody>
        </table>
    </div>
   
</div>
        )
}

export default LoanCard */

import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';

import AdminService from '../services/AdminService';
import ApplyLoan from './ApplyLoan';

/*
<tbody>
                {CustomerData.map(
                        data =>
                        <tr key={data.employeeId}>
                            <td> {data.employeeName} </td>
                            <td> {data.designation} </td>
                            <td> {data.department} </td>
                            <td> {data.gender} </td>
                            <td> {data.dob} </td>
                            <td> {data.doj} </td>
                            <td> {data.email} </td>
                         
                        </tr>
                    )
                }
        </tbody>

*/

function LoanCard() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [loanCard, setLoanCard] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [loanForm, setLoanForm] = useState();

    useEffect(() => {
        fetchLoanCard();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [loanCard]);

    const handleCloseForm = () => {
        setLoanForm();
        fetchLoanCard();
    }

    function handleEditLoanCard(key) {
        console.log("key is: ", key);
        setLoanForm(<ApplyLoan data={loanCard[key]} action="edit" handleCloseForm={handleCloseForm} />);
    }

    const handleDeleteLoanCard = (key) => {
        console.log("key is : ", key);
        AdminService.deleteLoanCard(loanCard[key], localStorage.getItem("credentials")).then((response) => {
            console.log("Delete status: ", response);
        }).catch((error) => {
            console.log("Delete failed: ", error);
        })

    }

    const fetchLoanCard = () => {
        AdminService.getLoanCard(localStorage.getItem("credentials")).then((response) => {
            setLoanCard(response.data);
        }
        ).catch((error) => {
            console.log(error);
        })
    }

    // uncomment this later
    // const data = await AdminService.getCustomerData()
    // //     // console.log(response.data);

    // //     console.log("customerdata - ",customerData);
    // // })
    // console.log(data.data);
    // setCustomerData(data.data);
    // populateTableFields();
    // .catch((error) => {
    //     console.error('Error fetching data:', error.data);
    //     // Handle the error gracefully, e.g., display a message to the user
    // }).then(() => populateTableFields());
    // populateTableFields();


    const populateTableFields = () => {
        console.log("populate is being run",)
        if (loanCard.length === 0) {
            console.log("loan card length", loanCard);
            setTableData([]);
        } else {
            var tableFields = [];
            loanCard.map(
                (data, index) => {
                    var issuedate = new Date(data.issueDate);
                    var string_issuedate = issuedate.toISOString().substring(0, 10);
                    tableFields.push(
                        <tr key={data.loanId}>
                            <td> {data.loanId} </td>
                            <td> {data.loanType} </td>
                            <td> {data.durationInYears} </td>
                            <td> {string_issuedate} </td>
                            <td><button onClick={() => handleEditLoanCard(index)}>Edit</button></td>
                            <td><button onClick={() => handleDeleteLoanCard(index)}>Delete</button></td>
                        </tr>)
                    // console.log(data.dob, Date(data.dob * 1000))
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);

        }
    }

    const handleAddLoanCard = () => {
        // history('/addProduct/_add');
        setLoanForm(<ApplyLoan action="add" handleCloseForm={handleCloseForm} />);
    }

    /*
     
    We are using the map operator to loop over our products list and create the view
    */




    return (
        <div>
            <br />
            <h1 className="text-warning">Loan Card Management</h1>
            <br />
            <div>
                {loanForm}
            </div>
            <div className="row justify-content-center">
                <button className='btn btn-info-w-auto' onClick={handleAddLoanCard}>Apply Loan</button>
            </div>
            <br />
            <div className="row justify-content-center" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-success w-auto">
                    <thead>
                        <tr className="table-danger">
                            <th> Loan Id</th>
                            <th> Loan Type</th>
                            <th> Duration (in yrs)</th>
                            <th> Date of Issue</th>
                            <th> Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {/* uncomment this */}
                        {/* {customerData.length == 0 ? <p>Add customers now</p> : customerData.map(
                            data =>
                            <tr key={data.employeeId}>
                                <td> {data.employeeId} </td>
                                <td> {data.employeeName} </td>
                                <td> {data.designation} </td>
                                <td> {data.department} </td>
                                <td> {data.gender} </td>
                                <td> {data.dob} </td>
                                <td> {data.doj} </td>
                                <td> {data.email} </td>
                           
                            </tr>
                        )
                    } */}
                        {/* <tr>{tableFields.map( data => <td>hi</td>)}</tr> */}
                        {tableData}

                    </tbody>
                </table>
                }
            </div>
        </div>
    )
}

export default LoanCard
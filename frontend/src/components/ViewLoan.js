import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';

import UserService from '../services/UserService';


function ViewLoan() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [viewLoan, setViewLoan] = useState([]);
    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        fetchViewLoan();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [viewLoan]);

    const fetchViewLoan = () => {
        UserService.getCustomerLoans(localStorage.getItem("credentials"), localStorage.getItem("userId")).then((response) => {
            setViewLoan(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    const populateTableFields = () => {
        console.log("populate is being run",)
        if (viewLoan.length === 0) {
            console.log("view loan length", viewLoan);
            setTableData([]);
        } else {
            var tableFields = [];
            viewLoan.map(
                (data, index) => {
                    var issueDate = new Date(data.cardIssueDate);
                    var string_issuedate = issueDate.toISOString().substring(0, 10);
                    tableFields.push(
                        <tr key={data.loanIssueId}>
                            <td> {data.loanIssueId} </td>
                            <td><a href="http://localhost:3000/loan"> {data.loanCard.loanId} </a></td>
                            <td> {string_issuedate} </td>
                        </tr>)
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }


    return (
        <div>
            <br />
            <h1 className="text-warning">View Loan</h1>
            <br />
            <div className="row justify-content-center" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-success w-auto">
                    <thead>
                        <tr className="table-danger">
                            <th> Loan Id</th>
                            <th> Loan Card Id</th>
                            <th> Issue Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tableData}
                    </tbody>
                </table>
                }
            </div>
        </div>
    )
}

export default ViewLoan


import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';
import '../styles/ViewLoan.css';
import UserService from '../services/UserService';


function ViewLoan() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [viewLoan, setViewLoan] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [loanDetails, setLoanDetails] = useState();

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

    const handleCloseLoanDetails = () => {
        setLoanDetails();
    }

    const handleLoan = (loanId) => {
        console.log("Loan clicked");
        UserService.getLoanDetails(localStorage.getItem("credentials"), loanId).then((response) => {
            console.log(response.data);
            var issueDate = new Date(response.data.issueDate);
            var string_issuedate = issueDate.toISOString().substring(0, 10);
            setLoanDetails(<div className='loan-details'>
                <div>
                    <div><span className='loan-header'>Loan Type: </span>{response.data.loanType}</div>
                    <div><span className='loan-header'>Loan Duration: </span>{response.data.durationInYears}</div>
                    <div><span className='loan-header'>Issue Date: </span>{string_issuedate}</div>
                </div>
                <div>
                    <button onClick={handleCloseLoanDetails}>Close</button>
                </div>
            </div>);
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
                            <td><button onClick={() => handleLoan(data.loanCard.loanId)}> {data.loanCard.loanId} </button></td>
                            <td> {string_issuedate} </td>
                        </tr>)
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }


    return (
        <div className="customer-data">
            <br />
            <h1 className="text-dark">View Loans</h1>
            <br />
            <div>
                {loanDetails}
            </div>
            <div className="table-responsive mt-3" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-bordered">
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


import React,{useState,useEffect, useImperativeHandle} from 'react'
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
    */

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

export default LoanCard
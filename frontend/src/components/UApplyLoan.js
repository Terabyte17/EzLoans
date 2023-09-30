import React, { useState, useEffect, useImperativeHandle } from 'react';
import { useNavigate } from 'react-router-dom';

import AdminService from '../services/AdminService';
import UserService from '../services/UserService';

function UApplyLoan() {
    const [item, setItem] = useState([]);
    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        fetchItem();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [item]);

    const fetchItem = () => {
        AdminService.getItemData(localStorage.getItem("credentials")).then((response) => {
            setItem(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    const populateTableFields = () => {
        console.log("populate is being run",)
        if (item.length === 0) {
            console.log("item length", item);
            setTableData([]);
        } else {
            var tableFields = [];
            item.map(
                (data, index) => {
                    //var dob = new Date(data.dob), doj = new Date(data.doj);
                    //var string_dob = dob.toISOString().substring(0, 10);
                    //var string_doj = doj.toISOString().substring(0, 10);
                    tableFields.push(
                        <tr key={data.itemId}>
                            <td> {data.itemId} </td>
                            <td> {data.itemDesc} </td>
                            <td> {data.issueStatus === true ? "True" : "False"} </td>
                            <td> {data.itemValuation} </td>
                            <td> {data.itemMake} </td>
                            <td> {data.itemCategory} </td>
                            <td><button onClick={() => handlePurchaseItem(index)}>Purchase</button></td>
                        </tr>)
                    // console.log(data.dob, Date(data.dob * 1000))
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }

    const handlePurchaseItem = (key) => {
        console.log("key is : ", key);
        UserService.purchaseItem(item[key], localStorage.getItem("credentials"), localStorage.getItem("userId")).then((response) => {
            console.log("Purchase status: ", response);
        }).catch((error) => {
            console.log("Purchase failed: ", error);
        })
        fetchItem();
    }

    return (
        <div className="loan-data">
            <br />
            <h1 className="text-dark">Apply Loan</h1>
            <br />
            <div className="table-responsive mt-3" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-bordered">
                    <thead>
                        <tr className="table-danger">
                            <th> Item Id</th>
                            <th> Description</th>
                            <th> Status</th>
                            <th> Valuation</th>
                            <th> Make</th>
                            <th> Category</th>
                            <th> Action</th>
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

export default UApplyLoan;
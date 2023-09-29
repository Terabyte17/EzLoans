import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';

import UserService from '../services/UserService';


function ViewItem() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [viewItem, setViewItem] = useState([]);
    const [tableData, setTableData] = useState([]);

    useEffect(() => {
        fetchViewItem();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [viewItem]);

    const fetchViewItem = () => {
        UserService.getCustomerItems(localStorage.getItem("credentials"), localStorage.getItem("userId")).then((response) => {
            setViewItem(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    const populateTableFields = () => {
        console.log("populate is being run",)
        if (viewItem.length === 0) {
            console.log("view item length", viewItem);
            setTableData([]);
        } else {
            var tableFields = [];
            viewItem.map(
                (data, index) => {
                    tableFields.push(
                        <tr key={data.itemId}>
                            <td> {data.itemDesc} </td>
                            <td> {data.issueStatus} </td>
                            <td> {data.itemValuation} </td>
                            <td> {data.itemMake} </td>
                            <td> {data.itemCategory}</td>
                        </tr>)
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }


    return (
        <div>
            <br />
            <h1 className="text-warning">View Items</h1>
            <br />
            <div className="row justify-content-center" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-success w-auto">
                    <thead>
                        <tr className="table-danger">
                            <th> Item Id</th>
                            <th> Description</th>
                            <th> Status</th>
                            <th> Value</th>
                            <th> Make</th>
                            <th> Category</th>
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

export default ViewItem
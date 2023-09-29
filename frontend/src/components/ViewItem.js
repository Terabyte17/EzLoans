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
            console.log(viewItem);
            var tableFields = [];
            viewItem.map(
                (data, index) => {
                    var issueDate = new Date(data.purchaseDate);
                    var string_issuedate = issueDate.toISOString().substring(0, 10);
                    let base_link = "http://localhost:8088/items";
                    let itemId = data.item.itemId;
                    base_link.concat(itemId);
                    document.querySelector('#baselink').innerHTML = base_link
                    tableFields.push(
                        <tr key={data.issueId}>
                            <td> {data.issueId}</td>
                            <td> <a href="baselink"> {data.item.itemId}</a> </td>
                            <td> {data.item.itemValuation} </td>
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
            <h1 className="text-warning">View Items</h1>
            <br />
            <div className="row justify-content-center" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-success w-auto">
                    <thead>
                        <tr className="table-danger">
                            <th> Issue Id</th>
                            <th> Item Id</th>
                            <th> Item Valuation</th>
                            <th> Purchase Date</th>
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
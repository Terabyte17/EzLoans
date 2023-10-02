import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';

import '../styles/ViewItem.css';
import UserService from '../services/UserService';


function ViewItem() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [viewItem, setViewItem] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [itemDetails, setItemDetails] = useState();

    useEffect(() => {
        fetchViewItem();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [viewItem]);

    const handleCloseItemDetails = () => {
        setItemDetails();
    }

    const handleItem = (itemId) => {
        console.log("Loan clicked");
        UserService.getItemDetails(localStorage.getItem("credentials"), itemId).then((response) => {
            console.log(response.data);
            setItemDetails(<div className='item-details'>
                <div>
                    <div><span className='item-header'>Item Description: </span>{response.data.itemDesc}</div>
                    <div><span className='item-header'>Issue Status: </span>{(response.data.issueStatus == true || response.data.issueStatus == "True") ? "True" : "False"}</div>
                    <div><span className='item-header'>Item Make: </span>{response.data.itemMake}</div>
                    <div><span className='item-header'>Item Valuation: </span>{response.data.itemValuation}</div>
                    <div><span className='item-header'>Item Category: </span>{response.data.itemCategory}</div>
                </div>
                <div>
                    <button onClick={handleCloseItemDetails}>Close</button>
                </div>
            </div>);
        })
    }

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
                    // let base_link = "http://localhost:8088/items";
                    // let itemId = data.item.itemId;
                    // base_link.concat(itemId);
                    // document.querySelector('#baselink').innerHTML = base_link
                    tableFields.push(
                        <tr key={data.issueId}>
                            <td> {data.issueId}</td>
                            <td> <button onClick={() => handleItem(data.item.itemId)}> {data.item.itemId} </button> </td>
                            <td> {data.item.itemValuation} </td>
                            <td> {string_issuedate} </td>
                        </tr>)
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }


    return (
        <div className='customer-data'>
            <br />
            <h1 className="text-dark">View Items</h1>
            <br />
            <div>
                {itemDetails}
            </div>
            <div className="table-responsive mt-3" >
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-bordered">
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
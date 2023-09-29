/*import React,{useState,useEffect, useImperativeHandle} from 'react'
import {useNavigate} from 'react-router-dom';

import AdminService from '../services/AdminService';

function ItemData() {
    const history = useNavigate();
    // state Management using useState() Hook
    const [itemData,setItemData] =useState([]);
    
    useEffect(() => {
        fetchItemData();
    }, []);
    

    /*const fetchItemData = () => {
        AdminService.getItemData().then((response) => {
            setItemData(response.data);
        })
        
    }*/

/*const fetchItemData = () => {
    AdminService.getItemData()
        .then((response) => {
            setItemData(response.data);
        })
        .catch((error) => {
            console.error('Error fetching data:', error);
            // Handle the error gracefully, e.g., display a message to the user
        });
};

/*const addProduct = () => {
    history('/addProduct/_add');
}

 /*
 
We are using the map operator to loop over our products list and create the view

return(
    <div>
<br/>
 
<h1 className="text-warning">Items Data Management</h1>
<br/>
    <div className = "row justify-content-center">
        <button className='btn btn-primary btn-small'>Add Item</button>
    </div>
<br/>
<div className="row justify-content-center" >
    <table className="table table-success w-auto">
     <thead>
        <tr className="table-danger">
            <th> Item Id</th>
            <th> Description</th>
            <th> Status</th>
            <th> Make</th>
            <th> Category</th>
            <th> Actions</th>
        </tr>
    </thead>
    <tbody>
            {itemData.map(
                    data => 
                    <tr key={data.itemId}>
                        <td> {data.itemId} </td>
                        <td> {data.itemDesc} </td>
                        <td> {data.issueStatus} </td>
                        <td> {data.itemMake}</td>
                        <td> {data.itemCategory}</td>
                        
                    </tr>
                )
            }
    </tbody>
    </table>
</div>
 
</div>
    )
}

export default ItemData*/

import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';

import AdminService from '../services/AdminService';

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

function ItemData() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [itemData, setItemData] = useState([]);

    useEffect(() => {
        fetchItemData();
    }, []);

    const location = window.location;
    console.log(location);
    const fetchItemData = () => {
        UserService.getItemDetails(localStorage.getItem("credentials"),).then((response) => {
            setItemData(response.data);
        }).catch((error) => {
            console.log(error);
        })


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
    }

    // const handleCloseForm = () => {
    //     setItemForm();
    //     fetchItemData();
    // }


    // const populateTableFields = () => {
    //     console.log("populate is being run",)
    //     if (itemData.length === 0) {
    //         console.log("item data length", itemData);
    //         setTableData([]);
    //     } else {
    //         var tableFields = [];
    //         itemData.map(
    //             (data, index) => {
    //                 //var dob = new Date(data.dob), doj = new Date(data.doj);
    //                 //var string_dob = dob.toISOString().substring(0, 10);
    //                 //var string_doj = doj.toISOString().substring(0, 10);
    //                 tableFields.push(
    //                     <tr key={data.itemId}>
    //                         <td> {data.itemId} </td>
    //                         <td> {data.itemDesc} </td>
    //                         <td> {data.issueStatus === true ? "True" : "False"} </td>
    //                         <td> {data.itemValuation} </td>
    //                         <td> {data.itemMake} </td>
    //                         <td> {data.itemCategory} </td>
    //                         <td><button onClick={() => handleEditItem(index)}>Edit</button></td>
    //                         <td><button onClick={() => handleDeleteItem(index)}>Delete</button></td>
    //                     </tr>)
    //                 // console.log(data.dob, Date(data.dob * 1000))
    //             })
    //         setTableData(tableFields);
    //         console.log("table fields", tableFields);
    //     }
    // }

    // const handleAddItem = () => {
    //     // history('/addProduct/_add');
    //     setItemForm(<AddItem action="add" handleCloseForm={handleCloseForm} />);
    // }

    /*
   
   We are using the map operator to loop over our products list and create the view
   */




    return (
        <div className="customer-data">
            <br />
            <h1 className="text-dark">Items Data Management</h1>
            <br />
            ItemData.itemMake
        </div>
    )
}

export default ItemData
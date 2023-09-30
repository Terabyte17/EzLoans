import React, { useState, useEffect, useImperativeHandle } from 'react'
import { useNavigate } from 'react-router-dom';
import '../styles/Dash.css';

import AdminService from '../services/AdminService';
import RegisterUser from './RegisterUser';

//import ProductService from '../service/ProductService';
/*
this code will come below </thead>
<tbody>
                {products.map(
                        prod => 
                        <tr key={prod.id}>
                            <td> {prod.pid} </td>
                            <td> {prod.name} </td>
                            <td> {prod.brand} </td>
                            <td> {prod.madein} </td>
                            <td> {prod.price} </td>
                          
                        </tr>
                    )
                }
        </tbody>
*/

function CustomerData() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [customerData, setCustomerData] = useState([]);
    const [tableData, setTableData] = useState([]);
    const [customerForm, setCustomerForm] = useState();
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        fetchCustomerData();
    }, []);

    useEffect(() => {
        populateTableFields();
    }, [customerData]);

    const fetchCustomerData = () => {
        AdminService.getCustomerData(localStorage.getItem("credentials")).then((response) => {
            setCustomerData(response.data);
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

    const handleCloseForm = () => {
        setCustomerForm();
        fetchCustomerData();
    }

    function handleEditCustomer(key) {
        console.log("key is: ", key);
        setCustomerForm(<RegisterUser data={customerData[key]} action="edit" handleCloseForm={handleCloseForm} />);
    }

    const handleDeleteCustomer = (key) => {
        console.log("key is : ", key);
        AdminService.deleteCustomerData(customerData[key], localStorage.getItem("credentials")).then((response) => {
            console.log("Delete status: ", response);
        }).catch((error) => {
            console.log("Delete failed: ", error);
        })
        fetchCustomerData();

    }

    const populateTableFields = () => {
        console.log("populate is being run",)
        if (customerData.length === 0) {
            console.log("customer data length", customerData);
            setTableData([]);
        } else {
            var tableFields = [];
            customerData.map(
                (data, index) => {
                    var dob = new Date(data.dob), doj = new Date(data.doj);
                    var string_dob = dob.toISOString().substring(0, 10);
                    var string_doj = doj.toISOString().substring(0, 10);
                    tableFields.push(
                        <tr key={data.employeeId}>
                            <td> {data.employeeId} </td>
                            <td> {data.employeeName} </td>
                            <td> {data.designation} </td>
                            <td> {data.department} </td>
                            <td> {data.gender} </td>
                            <td> {string_dob} </td>
                            <td> {string_doj} </td>
                            <td> {data.email} </td>
                            <td><button onClick={() => handleEditCustomer(index)}>Edit</button></td>
                            <td><button onClick={() => handleDeleteCustomer(index)}>Delete</button></td>
                        </tr>)
                    // console.log(data.dob, Date(data.dob * 1000))
                })
            setTableData(tableFields);
            console.log("table fields", tableFields);
        }
    }

    const handleAddCustomer = () => {
        // history('/addProduct/_add');
        setCustomerForm(<RegisterUser action="add" handleCloseForm={handleCloseForm} />);
    }


    /*
   
   We are using the map operator to loop over our products list and create the view
   */

    return (
        <div className="customer-data">
            <h1 className="text-dark">Employee Data Management</h1>
            <div>
                {customerForm}
            </div>
            <div className="add-customer-btn">
                <button className="btn btn-primary" onClick={handleAddCustomer}>Add Employee</button>
            </div>
            <div className="table-responsive mt-3">
                {tableData.length === 0 ? <p>No data</p> : <table className="table table-bordered">
                    {/* <table className="table table-bordered"> */}
                    <thead>
                        <tr className="table-danger">
                            <th>Employee Id</th>
                            <th>Employee Name</th>
                            <th>Designation</th>
                            <th>Department</th>
                            <th>Gender</th>
                            <th>Date of Birth</th>
                            <th>Date of Joining</th>
                            <th>Email Id</th>
                            <th colspan="2">Actions</th>
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

export default CustomerData
import React,{useState,useEffect, useImperativeHandle} from 'react'
import {useNavigate} from 'react-router-dom';

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

function CustomerData() {
    /*const history = useNavigate();
    // state Management using useState() Hook */
    const [customerData,setCustomerData] =useState([]);
    
    useEffect(() => {
        fetchCustomerData();
    }, []);
    

    const fetchCustomerData = () => {
        AdminService.getCustomerData().then((response) => {
            setCustomerData(response.data);
        })
    } 

   /* const addProduct = () => {
        history('/addProduct/_add');
    }

     /*
    
    We are using the map operator to loop over our products list and create the view
    */

    return(
        <div>
    <br/>
   
    <h1 className="text-warning">Customer Data Management</h1>
    <br/>
        <div className = "row justify-content-center">
            <button className='btn btn-info-w-auto onClick={addProduct'>Add Product</button>
        </div>
    <br/>
    <div className="row justify-content-center" >
        <table className="table table-success w-auto">
         <thead>
            <tr className="table-danger">
                <th> Employee Id</th>
                <th> Employee Name</th>
                <th> Designation</th>
                <th> Department</th>
                <th> Gender</th>
                <th> Date of Birth</th>
                <th> Date of Joining</th>
                <th> Email Id</th>
                <th> Action</th>
            </tr>
        </thead>
        <tbody>
                {customerData.map(
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
                }
        </tbody>
        </table>
    </div>
   
</div>
        )
}

export default CustomerData

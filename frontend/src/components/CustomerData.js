import React,{useState,useEffect, useImperativeHandle} from 'react'
import {useNavigate} from 'react-router-dom';

import AdminService from '../services/AdminService';
import RegisterUser from './RegisterUser';

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
    const [tableData, setTableData] = useState([]);
    const [customerForm, setCustomerForm] = useState();
    
    useEffect(() => {
        fetchCustomerData();
    }, []);

    function handleEditCustomer(key) {
        console.log("key is: ", key);
        setCustomerForm(<RegisterUser data={customerData[key]} action="edit" />);
    }
    
    const handleDeleteCustomer = (key) => {
        console.log("key is : ", key);

    }

    async function fetchCustomerData() {
        // uncomment this later
        const data = await AdminService.getCustomerData()
        //     // console.log(response.data);

        //     console.log("customerdata - ",customerData);
        // })
        console.log(data.data);
        setCustomerData(data.data);
        populateTableFields();
        // .catch((error) => {
        //     console.error('Error fetching data:', error.data);
        //     // Handle the error gracefully, e.g., display a message to the user
        // }).then(() => populateTableFields());
        // populateTableFields();
    } 


    const populateTableFields = () => {
        console.log("populate is being run", )
        // if(customerData.length === 0){
        //     console.log("customer data length", customerData);
        //     setTableData([]);
        // } else {
            var tableFields = [];
            customerData.map(
                (data,index) => tableFields.push(
                <tr key={data.employeeId}>
                    <td> {data.employeeId} </td>
                    <td> {data.employeeName} </td>
                    <td> {data.designation} </td>
                    <td> {data.department} </td>
                    <td> {data.gender} </td>
                    <td> {data.dob} </td>
                    <td> {data.doj} </td>
                    <td> {data.email} </td>
                    <td><button onClick={() => handleEditCustomer(index)}>Edit</button></td>
                    <td><button onClick={() => handleDeleteCustomer(index)}>Delete</button></td>
                </tr>)
            )
            setTableData(tableFields);
            console.log("table fields" , tableFields);
        // }
    }

   const handleAddCustomer = () => {
        // history('/addProduct/_add');
        setCustomerForm(<RegisterUser action="add" />);
    }

     /*
    
    We are using the map operator to loop over our products list and create the view
    */


    

    return(
        <div>
            <br/>
            <h1 className="text-warning">Customer Data Management</h1>
            <br/>
            <div>
                { customerForm }
            </div>
            <div className = "row justify-content-center">
                <button className='btn btn-info-w-auto' onClick={handleAddCustomer}>Add Customer</button>
            </div>
            <br/>
        <div className="row justify-content-center" >
            { tableData.length === 0 ? <p>No data</p> : <table className="table table-success w-auto">
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
                    {/* uncomment this */}
                    {/* {customerData.length == 0 ? <p>Add customers now</p> : customerData.map(
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
                    } */}
                    {/* <tr>{tableFields.map( data => <td>hi</td>)}</tr> */}
                    { tableData }
                
                </tbody>
            </table>
            }
        </div>
    </div>
        )
}

export default CustomerData

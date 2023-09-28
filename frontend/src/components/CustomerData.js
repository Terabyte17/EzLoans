import React,{useState,useEffects, useImperativeHandle} from 'react'
import {useNavigate} from 'react-router-dom';
import '../styles/Dash.css';

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
    // state Management using useState() Hook
    const [products,setProducts] =useStae([]);
    
    useEffect(() => {
        fetchProducts();
    
        return cleanUp = () => {
            
        }
    }, []);
    

    const fetchProducts = () => {
        ProductService.getProducts().then((response) => {
            setProducts(response.data);
        })
    }

    const addProduct = () => {
        history('/addProduct/_add');
    }

     /*
    
    We are using the map operator to loop over our products list and create the view
    */

    return (
        <div className="customer-data">
            <h1 className="text-dark">Customer Data Management</h1>
            <div className="add-customer-btn">
                <button className="btn btn-primary">Add Customer</button>
            </div>
            <div className="table-responsive mt-3">
                <table className="table table-bordered">
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
                            <th>Actions</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    )
}

export default CustomerData
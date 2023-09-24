import React,{useState,useEffect, useImperativeHandle} from 'react'
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

    const fetchItemData = () => {
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
    */

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

export default ItemData
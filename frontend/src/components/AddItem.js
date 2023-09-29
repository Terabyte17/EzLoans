import { React, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import '../styles/AddItem.css';
import AdminService from '../services/AdminService';
import { useNavigate } from 'react-router-dom';

const AddItem = (props) => {

    const navigate = useNavigate();


    const [formData, setFormData] = useState({
        itemId: props?.data?.itemId,
        itemDesc: props?.data?.itemDesc,
        issueStatus: props.issueStatus ? props?.data?.issueStatus : "False",
        itemMake: props.data ? props?.data?.itemMake : "Wooden",
        itemCategory: props.data ? props?.data?.itemCategory : "Furniture",
        itemValuation: props?.data?.itemValuation
    })

    const onChangeHandler = (event) => {
        console.log("Event is: ", event)
        const { name, value } = event
        setFormData((prev) => {
            return { ...prev, [name]: value }
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        if (props.action == "add") {
            AdminService.createItem(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("New item response: ", response);
                props.handleCloseForm();

            }).catch((error) => {
                console.log("Incomplete data: ", error);
            })
        } else {
            AdminService.updateItem(formData, localStorage.getItem("credentials")).then((response) => {
                console.log("Item update: ", response);
                props.handleCloseForm();
                // props.handleCloseForm();
            }).catch((error) => {
                console.log("Update issue: ", error);
            })
        }
        console.log("form data", formData);
    }

    return (
        <div className='register-container-custdata'>
            <h2 className='form-heading-apploan'>{props.action == "add" ? "Add" : "Edit"} Item</h2>
            <form className='register-form' onSubmit={(e) => handleSubmit(e)}>
                <div className='form-fields'>
                    <div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Item Description</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Item Description"
                                name="itemDesc"
                                value={formData?.itemDesc}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Issue Status</label>
                            <select className="form-control" value={formData.issueStatus ? formData?.issueStatus : "False"}
                                name="issueStatus"
                                onChange={(e) => onChangeHandler(e.target)}
                            >
                                <option value="True">True</option>
                                <option value="False">False</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Item Valuation</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Item Valuation"
                                name="itemValuation"
                                value={formData?.itemValuation}
                                onChange={(e) => onChangeHandler(e.target)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Item Make</label>
                            <select className="form-control" value={formData.itemMake ? formData?.itemMake : "Wooden"}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="itemMake"
                            >
                                <option value="Wooden">Wooden</option>
                                <option value="Glass">Glass</option>
                                <option value="Plastic">Plastic</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className='form-label-apploan'>Item Category</label>
                            <select className="form-control" value={formData.itemCategory ? formData?.itemCategory : "Furniture"}
                                onChange={(e) => onChangeHandler(e.target)}
                                name="itemCategory"
                            >
                                <option value="Furniture">Furniture</option>
                                <option value="Crockery">Crockery</option>
                                <option value="Stationary">Stationary</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div className="d-grid">
                    <button type="submit" className="submit-btn btn btn-primary">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    )
}

export default AddItem
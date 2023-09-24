import React from "react";
import "../styles/AdminDash.css"


const AdminDash = () => {
    return (
        <div className="dashboard-container">
            <div className="heading">
                <h1>Admin Dash Board</h1>
            </div>
            <div className="button-container">
                <button className="btn btn-primary">Customer Data Management</button>
                <button className="btn btn-primary">Loan Data Management</button>
                <button className="btn btn-primary">Items Data Management</button>
            </div>
        </div>
    )
}

export default AdminDash
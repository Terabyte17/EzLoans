import React, { useState } from "react";
import '../styles/Dash.css';
import ViewLoan from "./ViewLoan";
import ViewItem from "./ViewItem";


const UserDashboard = () => {

    const [tabContent, setTabContent] = useState(0);

    const tabContentComponents = [<h2 style={{ marginTop: "1%" }}>Select an option</h2>, <ViewLoan />, <ViewItem />]

    const handleClick = (e, id) => {
        console.log("Value is ", e, id);
        if (tabContent == id)
            setTabContent(0);
        else setTabContent(id);
    }

    return (
        <div className="dashboard-container">
            <div className="heading">
                <h1>UserDashboard Dashboard</h1>
            </div>
            <div className="button-container">
                <button className="btn btn-primary" onClick={(e) => handleClick(e, 1)}>View Loans</button>
                <button className="btn btn-primary" onClick={(e) => handleClick(e, 2)}>View Items</button>
                <button className="btn btn-primary" onClick={(e) => handleClick(e, 3)}>Apply Loan</button>
            </div>
            {
                tabContentComponents[tabContent]
            }
        </div>
    );
};

export default UserDashboard;
import React, { useState } from "react";
import '../styles/Dash.css';
import CustomerData from "./CustomerData";

const AdminDashboard = () => {

  const [tabContent, setTabContent] = useState(0);

  const tabContentComponents = [<h2>Choose an option</h2>, <CustomerData />, <></>, <></>]

  const handleClick = (e, id) => {
    console.log("Value is ", e, id);
    if(tabContent == id)
      setTabContent(0);
    else setTabContent(id);
  }

  return (
    <div className="dashboard-container">
      <div className="heading">
        <h1>Admin Dashboard</h1>
      </div>
      <div className="button-container">
        <button className="btn btn-primary" onClick={(e) => handleClick(e,1)}>Customer Data Management</button>
        <button className="btn btn-primary" onClick={(e) => handleClick(e,2)}>Loan Card Management</button>
        <button className="btn btn-primary" onClick={(e) => handleClick(e,3)}>Items Data.value Management</button>
      </div>
      {
        tabContentComponents[tabContent]
      }
    </div>
  );
};

export default AdminDashboard;
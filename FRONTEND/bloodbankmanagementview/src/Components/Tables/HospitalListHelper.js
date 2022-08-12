import React from "react";
import { Table } from "reactstrap";
import bdrop from "../images/modify6.png";
function HospitalListHelper({ users }) {
  return (
    <div>
      <div>
        <header>
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <img
              src={bdrop}
              width="40px"
              height="40px"
              alt="drop"
              style={{ marginLeft: "5%" }}
            />
            <div className="button" style={{ marginLeft: "50px" }}>
              <a href="/adminDashboard" className="navbar-brand">
                Admin Dashboard
              </a>
            </div>
          </nav>
        </header>
      </div>
      <h3 style={{ textAlign: "center", color: "purple", paddingTop: "5%" }}>
        Hospital List
      </h3>
      <div>
        <div className="row">
          <Table
            className="table table-striped table-bordered"
            style={{
              marginTop: "40px",
              borderRadius: "20px",
              marginLeft: "80px",
              borderColor: "black",
              width: "90%",
            }}
            bordered
          >
            <thead>
              <tr>
                <th>Hospital Name</th>
                <th>Contact</th>
                <th>Address</th>
                <th>City</th>
              </tr>
            </thead>
            <tbody>
              {users.map((hospital) => (
                <tr key={hospital.userId}>
                  <td>{hospital.name}</td>
                  <td>{hospital.phoneNumber}</td>
                  <td>{hospital.address}</td>
                  <td>{hospital.city}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  );
}

export default HospitalListHelper;

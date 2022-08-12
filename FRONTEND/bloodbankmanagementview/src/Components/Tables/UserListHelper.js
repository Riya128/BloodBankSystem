import React from "react";
import bdrop from "../images/modify6.png";
import { Table } from "reactstrap";

function UserListHelper({ users }) {
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
        User List
      </h3>

      <div>
        <div className="row">
          <Table
            className="table table-striped table-bordered"
            style={{
              marginTop: "40px",
              borderRadius: "20px",
              marginLeft: "80px",
              borderColor: "purple",
              width: "90%",
            }}
            bordered
            responsive
          >
            <thead>
              <tr>
                <th>User Name</th>
                <th>Contact</th>
                <th>Email</th>
                <th>Address</th>
                <th>City</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.userId}>
                  <td>{user.name}</td>

                  <td>{user.phoneNumber}</td>
                  <td>{user.email}</td>
                  <td>{user.address}</td>
                  <td>{user.city}</td>
                  <td></td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    </div>
  );
}

export default UserListHelper;

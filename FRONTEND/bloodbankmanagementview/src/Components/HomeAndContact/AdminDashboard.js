import React, { useState } from "react";
import { Button } from "reactstrap";
import bdrop from "../images/modify6.png";
import { Navigate } from "react-router-dom";
import "./custom.css";

function AdminDashboard() {
  const [radioValue, setRadioValue] = useState("");
  const [isLogoutValue, setIsLogoutValue] = useState(false);

  const radioHandler = (e) => {
    e.preventDefault();
    setRadioValue(e.target.value);
    console.log(e.target.value);
  };
  const logoutHandler = (e) => {
    //e.preventDefault();
    setIsLogoutValue(true);
    localStorage.removeItem("admintoken");
    localStorage.removeItem("userid");
    localStorage.removeItem("hospitalid");
    localStorage.removeItem("bloodbankid");

    //console.log(isLogout);
  };

  return (
    <>
      <div>
        {isLogoutValue ? <Navigate to="/" /> : <h3></h3>}
        <header>
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <a href="/" style={{ marginLeft: "2%" }}>
              <img src={bdrop} width="40px" height="40px" alt="drop" />
            </a>
            <div className="navbar-brand" style={{ marginLeft: "50px" }}>
              Admin Dashboard
            </div>
            <Button
              color="danger"
              style={{ marginLeft: "75%" }}
              onClick={(e) => {
                logoutHandler();
              }}
            >
              Logout
            </Button>
          </nav>
        </header>
      </div>

      <div class="adminouter" style={{ paddingTop: "60px" }}>
        <div class="container">
          <div class="row">
            <div class="col-lg-3 mb-4">
              <div class="card" style={{ backgroundColor: "lightsteelblue" }}>
                <a
                  href="/getBloodBankList"
                  class="btn btn-outline-primary btn-sm"
                  style={{ backgroundColor: "purple", color: "whitesmoke" }}
                >
                  <h6 style={{ padding: "40px 40px" }}> BloodBanks</h6>
                </a>
              </div>
            </div>
            <div class="col-lg-3 mb-4">
              <div class="card">
                <a
                  href="/getUserList"
                  class="btn btn-outline-primary btn-sm"
                  style={{ backgroundColor: "purple", color: "whitesmoke" }}
                >
                  <h6 style={{ padding: "40px 40px" }}> Registered Users </h6>
                </a>
              </div>
            </div>
            <div class="col-lg-3 mb-4">
              <div class="card">
                <a
                  href="/getHospitalList"
                  class="btn btn-outline-primary btn-sm"
                  style={{ backgroundColor: "purple", color: "whitesmoke" }}
                >
                  <h6 style={{ padding: "40px 40px" }}>Hospitals</h6>
                </a>
              </div>
            </div>
            <div class="col-lg-3 mb-4">
              <div class="card">
                <a
                  href="/getDonorList"
                  class="btn btn-outline-primary btn-sm"
                  style={{ backgroundColor: "purple", color: "whitesmoke" }}
                >
                  <h6 style={{ padding: "40px 40px " }}> Donors</h6>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AdminDashboard;

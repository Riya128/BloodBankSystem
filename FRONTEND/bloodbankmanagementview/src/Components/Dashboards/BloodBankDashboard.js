import React, { useState } from "react";
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import bdrop from "../images/modify6.png";
import DonorForm from "../Register/DonorForm";
import SearchDonor from "../Tables/SearchDonor";
import { Button } from "reactstrap";
import { Navigate } from "react-router-dom";
import "./Stylesheet.css";

function BloodBankDashboard() {
  const [radioValue, setRadioValue] = useState("");
  const [isLogoutValue, setIsLogoutValue] = useState(false);

  const radioHandler = (e) => {
    e.preventDefault();
    setRadioValue(e.target.value);
    //console.log(e.target.value);
  };
  const logoutHandler = (e) => {
    e.preventDefault();
    localStorage.removeItem("bloodbanktoken");
    localStorage.removeItem("bloodbankid");
    setIsLogoutValue(true);

    //console.log(isLogout);
  };
  return (
    <div>
      {isLogoutValue ? <Navigate to="/" /> : <h3></h3>}
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <a href="/" style={{ marginLeft: "2%" }}>
            <img src={bdrop} width="40px" height="40px" alt="drop" />
          </a>
          <div className="button" style={{ marginLeft: "50px" }}>
            <a href="/bloodbankDashboard" className="navbar-brand">
              BloodBank Dashboard
            </a>
          </div>
          <div className="button">
            <a
              style={{ marginLeft: "30px", marginRight: "1000px" }}
              href="/editBloodBank"
              className="navbar-brand"
            >
              Edit
            </a>
          </div>
          <Button
            color="danger"
            onClick={(e) => {
              logoutHandler(e);
            }}
          >
            Logout
          </Button>
        </nav>
      </header>
      <div>
        <div class="bloodbankouter">
          <FormControl id="formcontrol">
            <FormLabel id="demo-row-radio-buttons-group-label">
              <h3
                style={{ color: "black", marginLeft: "50%", paddingTop: "6%" }}
              >
                Select appropriate option
              </h3>
            </FormLabel>
            <RadioGroup
              row
              aria-labelledby="demo-row-radio-buttons-group-label"
              name="row-radio-buttons-group"
              style={{
                paddingTop: "100px",
                marginLeft: "30%",
                paddingBottom: "40px",
              }}
            >
              <FormControlLabel
                value="donor"
                control={<Radio />}
                label="Add Donor"
                onChange={radioHandler}
              />
              <FormControlLabel
                value="searchDonor"
                control={<Radio />}
                label="Looking for Donors"
                onChange={radioHandler}
                style={{ marginLeft: "60%" }}
              />
            </RadioGroup>
            <div className="style-form" style={{ marginLeft: "-15%" }}>
              {radioValue === "donor" && <DonorForm />}
              {radioValue === "searchDonor" && <SearchDonor />}
            </div>
          </FormControl>
        </div>
      </div>
    </div>
  );
}

export default BloodBankDashboard;

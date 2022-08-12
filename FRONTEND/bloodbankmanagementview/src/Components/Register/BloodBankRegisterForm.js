import React, { Component } from "react";
import Home from "../HomeAndContact/Home";
import Axios from "axios";
import { Navigate } from "react-router-dom";
import "./style.css";

class BloodBankRegisterForm extends Component {
  constructor(props) {
    super(props);
    this.url = "http://localhost:8999/bloodbank-service/bloodbank/register";
    this.state = {
      name: "",
      email: "",
      password: "",
      phoneNumber: 0,
      city: "",
      address: "",
      isRegistered: false,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }
  handleSubmit(event) {
    event.preventDefault();
    const user = {
      name: this.state.name,
      email: this.state.email,
      password: this.state.password,
      phoneNumber: this.state.phoneNumber,
      city: this.state.city,
      address: this.state.address,
    };
    console.log(user);
    Axios.post(this.url, user).then((res) => {
      if (res.status === 201) {
        this.setState({ isRegistered: true });
      }
    });
  }

  render() {
    return (
      <div>
        {this.state.isRegistered ? (
          <Navigate to="/bloodbankLogin" />
        ) : (
          <h3></h3>
        )}
        <Home />
        <br></br>
        <div
          className="color-overlay d-flex justify-content-center align-items-center"
          id="main1"
        >
          <form className="rounded p-4 p-sm-3" onSubmit={this.handleSubmit}>
            <h3 style={{ color: "purple" }}>Create your BloodBank Account </h3>
            <h3>Sign Up</h3>
            <div className="form-group mb-3">
              <label>Name</label>
              <input
                type="text"
                name="name"
                className="form-control"
                placeholder="Enter bloodbank name"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>Email</label>
              <input
                type="email"
                name="email"
                className="form-control"
                placeholder="Enter Email id"
                pattern="^[a-z0-9](\.?[a-z0-9]){5,}@gmail\.com$"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>Password</label>
              <input
                type="password"
                name="password"
                className="form-control"
                placeholder="Enter your password"
                pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                onChange={this.handleChange}
                required
              />
              <label
                title="password should contain atleast 8 characters 
                                   and lowercase letters(A-Z) and uppercase letters(a-z) and special characters(.@_/$%)"
                style={{ fontSize: "90%" }}
              >
                For password Guidelines{" "}
                <em style={{ color: "purple" }}>Learn more</em>
              </label>
            </div>
            <div className="form-group mb-3">
              <label>Phone Number</label>
              <input
                type="tel"
                name="phoneNumber"
                className="form-control"
                placeholder="Enter bloodbank Contact Number"
                onChange={this.handleChange}
                required
                minLength={10}
                maxLength={10}
              />
            </div>
            <div className="form-group mb-3">
              <label>City</label>
              <input
                type="text"
                name="city"
                className="form-control"
                placeholder="Enter City"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>Address</label>
              <input
                type="text"
                name="address"
                className="form-control"
                placeholder="Enter bloodbank Address"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="d-grid">
              <button
                type="submit"
                className="btn btn-primary"
                style={{ backgroundColor: "purple" }}
              >
                Submit
              </button>
            </div>
            <p>
              Already have an account? <a href="/bloodbankLogin">Click</a> here
              to login.
            </p>
          </form>
        </div>
      </div>
    );
  }
}

export default BloodBankRegisterForm;

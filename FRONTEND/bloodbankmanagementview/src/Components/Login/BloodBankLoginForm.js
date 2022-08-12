import React, { Component } from "react";
import Home from "../HomeAndContact/Home";
import { Navigate } from "react-router-dom";
import Axios from "axios";
import "./custom.css";

class BloodBankLoginForm extends Component {
  constructor(props) {
    super(props);
    this.url = "http://localhost:8999/bloodbank-service/bloodbank/login";
    this.state = {
      email: "",
      password: "",
      isSuccessfullLogin: false,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }
  handleSubmit(event) {
    event.preventDefault();
    const bloodbanklogin = {
      email: this.state.email,
      password: this.state.password,
    };
    console.log(bloodbanklogin);
    Axios.post(this.url, bloodbanklogin)
      .then((res) => {
        const array = res.data.split(" ");
        const token = array[0];
        const id = array[1];
        localStorage.setItem("bloodbanktoken", JSON.stringify(token));
        localStorage.setItem("bloodbankid", JSON.stringify(id));

        console.log(token);
        console.log(id);
        if (res.status === 200) {
          this.setState({ isSuccessfullLogin: true });
        }
      })
      .catch((err) => {
        if (err) {
          return alert("Please Enter Valid Credentials to Login");
        }
      });
  }
  render() {
    return (
      <div>
        {this.state.isSuccessfullLogin ? (
          <Navigate to="/bloodbankDashboard" />
        ) : (
          <h3></h3>
        )}
        <Home />
        <div
          className="color-overlay d-flex
                justify-content-center align-items-center"
          id="main"
        >
          <form
            className="rounded p-4 p-sm-3"
            onSubmit={this.handleSubmit}
            style={{ marginLeft: "0%", marginTop: "0%" }}
          >
            <h3 style={{ color: "purple" }}>
              Login into your BloodBank Account
            </h3>
            <div className="form-group mb-3">
              <label>Email address</label>
              <input
                type="email"
                name="email"
                className="form-control"
                placeholder="Enter email"
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
                placeholder="Enter password"
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
              Not a Registered BloodBank? <a href="/bloodbankRegister">Click</a>{" "}
              here to register
            </p>
          </form>
        </div>
      </div>
    );
  }
}

export default BloodBankLoginForm;

import React, { Component } from "react";
import axios from "axios";
import Home from "../HomeAndContact/Home";
import { Navigate } from "react-router-dom";
import "./style.css";

class DonorForm extends Component {
  constructor(props) {
    super(props);
    this.url = "http://localhost:8999/donor-service/donor";
    this.state = {
      name: "",
      age: 0,
      phoneNumber: 0,
      email: "",
      bloodGroup: "",
      city: "",
      address: "",
      isSuccessfullAdd: false,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handlebgchange = this.handlebgchange.bind(this);
  }
  handlebgchange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }
  handleSubmit(event) {
    event.preventDefault();
    const donor = {
      name: this.state.name,
      age: this.state.age,
      email: this.state.email,
      bloodGroup: this.state.bloodGroup,
      phoneNumber: this.state.phoneNumber,
      city: this.state.city,
      address: this.state.address,
    };
    console.log(donor);
    axios
      .post(this.url, donor)
      .then((res) => {
        console.log(res.data);
        console.log(res.status);
        if (res.status === 201) {
          this.setState({ isSuccessfullAdd: true });
          event.target.reset();
          alert("Donor added successfully");
        }
      })
      .catch((err) => {
        if (err) {
          return alert("Sorry! Could'nt register");
        }
      });
  }

  render() {
    return (
      <div>
        <div
          className="color-overlay d-flex justify-content-center align-items-center"
          style={{ marginTop: "300px", paddingBottom: "200px" }}
        >
          <form
            className="rounded p-3 p-md-6"
            onSubmit={this.handleSubmit}
            style={{ marginTop: "50px", width: "500px", marginLeft: "55%" }}
          >
            <h3 style={{ color: "blue" }}>Add Donor</h3>

            <div className="form-group mb-3">
              <label>Name</label>
              <input
                type="text"
                name="name"
                className="form-control"
                placeholder="Enter your Name"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>Age</label>
              <input
                type="number"
                name="age"
                className="form-control"
                placeholder="Enter age"
                min="18"
                max="60"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>PhoneNumber</label>
              <input
                type="tel"
                name="phoneNumber"
                className="form-control"
                placeholder="Enter your phone number"
                onChange={this.handleChange}
                required
                minLength={10}
                maxLength={10}
              />
            </div>
            <div className="form-group mb-3">
              <label>Email</label>
              <input
                type="email"
                name="email"
                className="form-control"
                pattern="^[a-z0-9](\.?[a-z0-9]){5,}@gmail\.com$"
                placeholder="Enter your email Id"
                onChange={this.handleChange}
                required
              />
            </div>

            <div className="form-group mb-3">
              <label>Blood Group</label>
              <select
                name="bloodGroup"
                className="form-control"
                placeholder="Enter your blood group"
                onChange={this.handlebgchange}
                required
              >
                <option value="none">Select BloodGroup</option>
                <option value="A+">A+</option>
                <option value="B+">B+</option>
                <option value="AB+">AB+</option>
                <option value="O+">O+</option>
                <option value="A-">A-</option>
                <option value="B-">B-</option>
                <option value="AB-">AB-</option>
                <option value="O-">O-</option>
              </select>
            </div>
            <div className="form-group mb-3">
              <label>City</label>
              <input
                type="text"
                name="city"
                className="form-control"
                placeholder="Enter your City"
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
                placeholder="Enter your Addres"
                onChange={this.handleChange}
                required
              />
            </div>
            <div className="d-grid">
              <button type="submit" className="btn btn-primary">
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default DonorForm;

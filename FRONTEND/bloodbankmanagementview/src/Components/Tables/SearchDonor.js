import React, { Component } from "react";
import DonorService from "../../Services/DonorService";
import searchIcon from "../images/searchIcon.png";

class SearchDonor extends Component {
  constructor(props) {
    super(props);

    this.state = {
      donors: [],
      donorbloodgroup: "",
    };
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(event) {
    this.setState({ donorbloodgroup: event.target.value });
  }
  componentDidMount() {
    DonorService.getDonors().then((res) => {
      console.log(res.data);
      this.setState({ donors: res.data });
    });
  }
  render() {
    return (
      <div style={{ marginLeft: "20%", paddingTop: "5%" }}>
        <div>
          <label>
            <img
              src={searchIcon}
              width="20px"
              height="20px"
              alt="drop"
              style={{ marginRight: "20px" }}
            />
          </label>
          <input
            type="text"
            value={this.state.donorbloodgroup}
            placeholder="Enter blood group"
            onChange={this.handleChange}
          />
        </div>
        <div style={{ marginTop: "10px" }}>
          <h2 className="text-center" style={{ marginLeft: "18%" }}>
            Donor List
          </h2>
        </div>
        <div>
          <div className="row" style={{ paddingLeft: "100px" }}>
            <table
              className="table table-striped table-bordered"
              style={{ borderColor: "purple" }}
            >
              <thead>
                <tr>
                  <th>Donor Name</th>
                  <th>Age</th>
                  <th>Contact</th>
                  <th>BloodGroup</th>
                  <th>City</th>
                </tr>
              </thead>
              {this.state.donorbloodgroup === "" ? (
                <tbody>
                  {this.state.donors.map((donor) => (
                    <tr key={donor.userId}>
                      <td>{donor.name}</td>
                      <th>{donor.age}</th>
                      <th>{donor.phoneNumber}</th>
                      <th>{donor.bloodGroup}</th>
                      <th>{donor.city}</th>
                    </tr>
                  ))}
                </tbody>
              ) : (
                <tbody>
                  {this.state.donors
                    .filter(
                      (donor) => donor.bloodGroup === this.state.donorbloodgroup
                    )
                    .map((donor) => (
                      <tr key={donor.userId}>
                        <td>{donor.name}</td>
                        <th>{donor.age}</th>
                        <th>{donor.phoneNumber}</th>
                        <th>{donor.bloodGroup}</th>
                        <th>{donor.city}</th>
                      </tr>
                    ))}
                </tbody>
              )}
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default SearchDonor;

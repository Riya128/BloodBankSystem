import React, { Component } from "react";
import HospitalService from "../../Services/HospitalService";
import Pagination from "./Pagination";
import "./styleTables.css";
import HospitalListHelper from "./HospitalListHelper";
class HospitalListComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      hospitals: [],
      currentPage: 1,
      usersPerPage: 5,
    };
  }

  componentDidMount() {
    HospitalService.getHospitals().then((res) => {
      this.setState({ hospitals: res.data });
    });
  }
  render() {
    const indexOfLastUser = this.state.currentPage * this.state.usersPerPage;
    const indexOfFirstUser = indexOfLastUser - this.state.usersPerPage;
    const currentUsers = this.state.hospitals.slice(
      indexOfFirstUser,
      indexOfLastUser
    );
    const paginate = (pageNumber) => {
      this.setState({ currentPage: pageNumber });
    };
    return (
      <>
        <HospitalListHelper users={currentUsers} />
        <Pagination
          usersPerPage={this.state.usersPerPage}
          paginate={paginate}
          totalLength={this.state.hospitals.length}
        />
      </>
    );
  }
}
export default HospitalListComponent;

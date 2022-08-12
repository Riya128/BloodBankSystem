import React, { Component } from "react";
import BloodBankService from "../../Services/BloodBankService";
import BloodBankListHelper from "./BloodBankListHelper";
import Pagination from "./Pagination";
import "./styleTables.css";
class BloodBankList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      bloodbanks: [],
      currentPage: 1,
      usersPerPage: 5,
    };
  }

  componentDidMount() {
    BloodBankService.getBloodBanks().then((res) => {
      this.setState({ bloodbanks: res.data });
    });
  }
  render() {
    const indexOfLastUser = this.state.currentPage * this.state.usersPerPage;
    const indexOfFirstUser = indexOfLastUser - this.state.usersPerPage;
    const currentUsers = this.state.bloodbanks.slice(
      indexOfFirstUser,
      indexOfLastUser
    );

    const paginate = (pageNumber) => {
      this.setState({ currentPage: pageNumber });
    };
    return (
      <>
        <BloodBankListHelper users={currentUsers} />
        <Pagination
          usersPerPage={this.state.usersPerPage}
          paginate={paginate}
          totalLength={this.state.bloodbanks.length}
        />
      </>
    );
  }
}
export default BloodBankList;

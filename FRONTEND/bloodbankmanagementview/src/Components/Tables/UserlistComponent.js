import React, { Component } from "react";
import UserService from "../../Services/UserService";
import UserListHelper from "./UserListHelper";
import Pagination from "./Pagination";
class UserlistComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      users: [],
      currentPage: 1,
      usersPerPage: 5,
    };

    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(event) {
    this.setState({ donorbloodgroup: event.target.value });
  }
  componentDidMount() {
    UserService.getUsers().then((res) => {
      this.setState({ users: res.data });
    });
  }

  render() {
    //get current user
    const indexOfLastUser = this.state.currentPage * this.state.usersPerPage;
    const indexOfFirstUser = indexOfLastUser - this.state.usersPerPage;
    const currentUsers = this.state.users.slice(
      indexOfFirstUser,
      indexOfLastUser
    );

    const paginate = (pageNumber) => {
      this.setState({ currentPage: pageNumber });
    };
    return (
      <>
        <UserListHelper users={currentUsers} />
        <Pagination
          usersPerPage={this.state.usersPerPage}
          paginate={paginate}
          totalLength={this.state.users.length}
        />
      </>
    );
  }
}
export default UserlistComponent;

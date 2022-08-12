import axios from "axios";
import React, { useState, useEffect } from "react";
import { Button, Container, Table } from "reactstrap";
import DonorService from "../../Services/DonorService";
import base_url from "../bootapi";
import bdrop from "../images/modify6.png";

const ListDonorComponent = () => {
  const [donors, setDonors] = useState([]);

  useEffect(() => {
    getAllDonorsFromServer();
  }, []);
  const getAllDonorsFromServer = () => {
    DonorService.getDonors().then(
      (response) => {
        //success
        setDonors(response.data);
        //toast.success("courses has been loaded");
      },
      (error) => {
        //for error
        alert("Oops!! Can't fetch donors");
        //console.log(error);
      }
    );
  };

  const deleteDonor = (id) => {
    console.log(id);
    axios.delete(`${base_url}/${id}`).then(
      (response) => {
        update(id);
        getAllDonorsFromServer();
      },
      (error) => {}
    );
  };
  useEffect(() => {
    getAllDonorsFromServer();
  }, []);
  const update = (id) => {
    setDonors(donors.filter((c) => c.id != id));
  };

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
        Donor List
      </h3>
      <Table
        style={{
          marginTop: "40px",
          borderRadius: "20px",
          marginLeft: "80px",
          borderColor: "purple",
          width: "90%",
        }}
        bordered
      >
        <thead>
          <tr>
            <th>Address</th>
            <th>Age</th>
            <th>Blood Group</th>
            <th>City</th>
            <th>Email Id</th>
            <th>Contact No.</th>
            <th>Name</th>
            <th>Delete Record</th>
          </tr>
        </thead>
        <tbody>
          {donors.map((donor) => (
            <tr key={donor.userId}>
              <td>{donor.address}</td>
              <td>{donor.age}</td>
              <td>{donor.bloodGroup}</td>
              <td>{donor.city}</td>
              <td>{donor.email}</td>
              <td>{donor.phoneNumber}</td>
              <td>{donor.name}</td>
              <td>
                <Container>
                  <Button
                    color="danger"
                    onClick={() => {
                      deleteDonor(donor.userId);
                    }}
                  >
                    Delete
                  </Button>
                </Container>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default ListDonorComponent;

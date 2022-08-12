import axios from "axios";
const donorurl = "http://localhost:8999/donor-service/donor/alldonors";
class DonorService {
  getDonors() {
    const token = JSON.parse(localStorage.getItem("admintoken"));
    return axios.get(donorurl, {
      //   headers: { Authorization: "Bearer " + token },
    });
  }
}
export default new DonorService();

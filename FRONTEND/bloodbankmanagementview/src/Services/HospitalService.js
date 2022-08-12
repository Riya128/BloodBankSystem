import axios from 'axios';
const hospitalurl="http://localhost:8999/hospital-service/hospital/allhospitals"
class HospitalService{
    getHospitals(){
        const token =JSON.parse(localStorage.getItem('admintoken'));
        return axios.get(hospitalurl,{ headers: {'Authorization': 'Bearer '+token}});
    }
}
export default new HospitalService()


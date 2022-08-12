import axios from 'axios';
const bloodbankurl="http://localhost:8999/bloodbank-service/bloodbank/allbloodbanks"
class BloodBankService{
    getBloodBanks(){
        const token =JSON.parse(localStorage.getItem('admintoken'));
        return axios.get(bloodbankurl,{ headers: {'Authorization': 'Bearer '+token}});
    }
}
export default new BloodBankService()


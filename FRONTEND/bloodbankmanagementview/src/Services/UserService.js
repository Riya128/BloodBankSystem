import axios from 'axios';
const userurl="http://localhost:8999/user-service/user/allusers";
class UserService{
    getUsers(){
        const token =JSON.parse(localStorage.getItem('admintoken'));
        return axios.get(userurl,{ headers: {'Authorization': 'Bearer '+token}});
    }
}
export default new UserService()


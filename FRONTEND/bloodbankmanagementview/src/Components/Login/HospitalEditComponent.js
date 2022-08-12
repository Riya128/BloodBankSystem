import React,{useState,useEffect} from 'react'
import axios from 'axios';
import bdrop from '../images/modify6.png'
import { Button} from "reactstrap";
import { Navigate } from 'react-router-dom';
function HospitalEditComponent() {
   
let token = JSON.parse(localStorage.getItem("hospitaltoken"));
let id = JSON.parse(localStorage.getItem("hospitalid"));
   const base_url ="http://localhost:8999/hospital-service/hospital"
     let URL = `${base_url}/${id}`;
     
    
   {console.log(token);}
    const [users,setUsers]=useState({})
    const [isLogoutValue, setIsLogoutValue] = useState(false);
    useEffect(()=>{
   
        
    getUserById();
        console.log(id);
        console.log(token);
            
    },[])
    
    
    const { name , city , address, phoneNumber} = users;
    const onInputChange = e =>{
        setUsers({...users,[e.target.name]:e.target.value})
    }
    
    const FormHandle = e =>{
        e.preventDefault();

        updateDataToServer(users,e);  
            
    }
    
    const updateDataToServer=(data,e) =>{
        axios.put(URL,data,{headers: {'Authorization': 'Bearer '+token}}).then(
           (response)=>{

                   alert("Hospital Details Updated Successfully");

            },(error)=>{
                    alert("Operation failed");
            }
        );
    };
    
    const getUserById= async e =>{
        console.log(token);
        const userInfo = await axios.get(URL,{ headers: {'Authorization': 'Bearer '+token}});
        setUsers(userInfo.data);     
    }
    const logoutHandler = (e) => {
        e.preventDefault();
        setIsLogoutValue(true);
        localStorage.removeItem(token);
        
      };
    return (
        <div>
            {isLogoutValue?(<Navigate to="/"/>):(<h3></h3>)}
    <header>
    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
    <img src={bdrop} width="40px" height="40px" alt='drop' style={{marginLeft:"5%"}}/>
    {/* <div className='navbar-brand' style={{marginLeft:"50px"}}>User Dashboard</div> */}
    <div className="button" style={{marginLeft:"50px"}}><a href="/hospitalDashboard" className="navbar-brand">Hospital Dashboard</a></div>
    <div className='button' ><a  style={{marginLeft:"30px" , marginRight:"950px"}} href="/editHospital" className='navbar-brand' >Edit</a></div>
    <Button color="danger" onClick={(e)=>{ logoutHandler(e);}}>Logout</Button>
    </nav></header>
                    <h2 style={{textAlign:"center"}}>Update Hospital Details</h2>
                    <div style={{marginRight:"30%",marginBottom:"40%"}}>
                    <form onSubmit={e => FormHandle(e)} style={{width:"40%"}}>
                        <div >
                            <label >Name</label>
                            <input type="text" class="form-control" name="name"    value={name} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div >
                            <label >City</label>
                            <input type="text" class="form-control" name="city"   value={city} onChange={(e) =>onInputChange(e)} />
                        </div>
                        <div >
                            <label >address</label>
                            <input type="text" class="form-control" name="address"   value={address} onChange={(e) =>onInputChange(e)}  />
                        </div>
                        <div >
                            <label >Contact</label>
                            <input type="text" class="form-control" name="phoneNumber"   value={phoneNumber} onChange={(e) =>onInputChange(e)}  />
                        </div>
                        <div >
                        <button type="submit" class="btn btn-outline-success my-2 text-center mr-2" style={{marginLeft:"40%"}}>Update </button>
                        
                        </div>
                    </form>
                </div>
            </div>
            
        
    )
}
export default HospitalEditComponent
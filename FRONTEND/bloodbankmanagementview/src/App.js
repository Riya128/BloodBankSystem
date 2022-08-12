import './App.css';
import { BrowserRouter as Router, Routes,Route} from 'react-router-dom';
import Home from './Components/HomeAndContact/Home'; //import home component
import Contact from './Components/HomeAndContact/Contact' //import contact component
import UserRegisterForm from './Components/Register/UserRegisterForm'; //import user register form component
import BloodBankRegisterForm from './Components/Register/BloodBankRegisterForm'; //import bloodbank register form component
import HospitalRegisterForm from './Components/Register/HospitalRegisterForm'; //import hospital register form component
import UserLoginForm from './Components/Login/UserLoginForm'; //import user login form component
import HospitalLoginForm from './Components/Login/HospitalLoginForm'; //import hospital login form component
import BloodBankLoginForm from './Components/Login/BloodBankLoginForm'; //import bloodbank login form component
import DonorForm from './Components/Register/DonorForm'; //import add donor form component
import ListDonorComponent from './Components/Tables/ListDonorComponent'; //import get donor list component 
import AdminLoginForm from './Components/Login/AdminLoginForm';  //import admin login form component
import AdminDashboard from './Components/HomeAndContact/AdminDashboard';
import UserlistComponent from './Components/Tables/UserlistComponent';
import HospitalListComponent from './Components/Tables/HospitalListComponent';
import BloodBankList from './Components/Tables/BloodBankList';
import MainHome from './Components/HomeAndContact/MainHome';
import LearnThings from './Components/HomeAndContact/LearnThings';
import UserDashboard from './Components/Dashboards/UserDashboard';
import HospitalDashboard from './Components/Dashboards/HospitalDashboard';
import BloodBankDashboard from './Components/Dashboards/BloodBankDashboard';
import UserEditComponent from './Components/Login/UserEditComponent';
import HospitalEditComponent from './Components/Login/HospitalEditComponent';
import BloodBankEditComponent from './Components/Login/BloodBankEditComponent';


function App() {
  return (
    <div className='App-bg'>
      <Router>
     <Routes>
         {/* home */}
         <Route path='/main' element={<Home/>} />
         <Route path='/' element={<MainHome/>}/>
         {/* contact */}
         <Route path='/contact' element={<Contact/>} />
         {/* Registration form components routing  */}
         <Route path='/userRegister' element={<UserRegisterForm/>} />
         <Route path='/hospitalRegister' element={<HospitalRegisterForm/>} />
         <Route path='/bloodbankRegister' element={<BloodBankRegisterForm/>} />
         {/* Donor form component routing  */}
         <Route path='/addDonor' element={<DonorForm/>} />
         {/* Login form components routing */}
         <Route path='/userLogin' element={<UserLoginForm/>} />
         <Route path='/hospitalLogin' element={<HospitalLoginForm/>} />
         <Route path='/bloodbankLogin' element={<BloodBankLoginForm/>} />
         <Route path='/adminLogin' element={<AdminLoginForm/>}/>
         {/* Donor List Component routing  */}
         <Route path='/getDonorList' element={<ListDonorComponent/>}/>
         <Route path='/adminDashboard' element={<AdminDashboard/>}/>
         <Route path='/getUserList' element={<UserlistComponent/>}/>
         <Route path='/getHospitalList' element={<HospitalListComponent/>}/>
          <Route path='/getBloodBankList' element={<BloodBankList/>}/>    
          <Route path='/learnthings' element={<LearnThings/>}/> 
          {/* Dashboard routing     */}
          <Route path='/userDashboard' element={<UserDashboard/>}/>
          <Route path='/hospitalDashboard' element={<HospitalDashboard/>}/>
          <Route path='/bloodbankDashboard' element={<BloodBankDashboard/>}/>
          {/* Edit Details */}
          <Route path ="/editUser" element={<UserEditComponent />}/>
          <Route path ="/editHospital" element={<HospitalEditComponent />}/>
          <Route path ="/editBloodBank" element={<BloodBankEditComponent />}/>
     
          
     </Routes>
</Router>
    </div>

  );
}

export default App;

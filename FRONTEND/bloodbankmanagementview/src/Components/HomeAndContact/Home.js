
import React, { Component } from 'react';
import bdrop from '../images/bdrop.png'
import './custom.css'


class Home extends Component {
    render() {
        return (
            
                <div>
                    
                       
                    <header>
                    <h3 className='header'>Blood Bank Management System</h3>
                    
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark" >
                        
                        <div><img src={bdrop} width="40px" height="40px" alt='drop'/></div>
                    <div><a href="/" className='navbar-brand' id='menu1' >Home</a></div>
                    <div className="dropdown">
                        <button type="button" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" style={{fontSize:"20px"}} id="menu" >
                            Login
                        </button>
                        <div className="dropdown-menu" >
                            <a className="dropdown-item" href="/userLogin">User</a>
                            <a className="dropdown-item"href="/hospitalLogin">Hospital</a>
                            <a className="dropdown-item" href="/bloodbankLogin">BloodBank</a>
                            <a className='dropdown-item' href="/adminLogin">Admin</a>
                        </div>
                    </div>
                    <div className="dropdown">
                        <button type="button" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" style={{fontSize:"20px"}} id='menu' >
                            Register
                        </button>
                        <div className="dropdown-menu">
                            <a className="dropdown-item" href="/userRegister">User</a>
                            <a className="dropdown-item"href="/hospitalRegister">Hospital</a>
                            <a className="dropdown-item" href="/bloodbankRegister">BloodBank</a>
                        </div>
                    </div>
                    <div><a href="/contact" style={{marginLeft:"18%"}} className='navbar-brand' id='menu1' >ContactUs</a></div>
                    <div><a href="https://www.mohfw.gov.in/" className='navbar-brand' target="_blank" style={{borderRadius:"10%" ,paddingInline:"10px",marginLeft:"850px"}}> Covid-19 HelpLine </a></div>
                    
           
                    </nav>
                    </header>
                {/* <div><Redirection/></div> */}
                {/* <div><AdminDashboard/></div> */}
                {/* <UserlistComponent/> */}
                
           </div>
           
        );
    }
}

export default Home;
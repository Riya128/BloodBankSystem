import React,{useState} from 'react'
import Home from './Home'
import homepage from '../images/homepage.jpg'
import {  CardBody } from 'reactstrap';
import Popup from './Popup';
import './custom.css'





function MainHome() {

  const [isOpen, setIsOpen] = useState(false);
 
  const togglePopup = () => {
    setIsOpen(!isOpen);
  }
 

  return (
    <div>
    <div><Home/></div>
    <div>
      <img src={homepage} width="100%" height="auto"/>
    </div>
     <div class="outer">
      <div class="container" style={{marginTop:"60px"}}>
        <div class="row">
            <div class="col-lg-3 mb-4">
              <div class="card1" >
                 
                <CardBody>
                  <h5 style={{color:"lightskyblue"}}>Facts</h5>
                  <p><ul><li>AB+ are universal acceptor and O-ve are universal donors</li>
                  <li>platelets are especially needed by cancer patients</li>
                  </ul></p>
                </CardBody>
              </div>
            </div>
            <div class="col-lg-3 mb-4">
                <div class="card1">
                  <CardBody>
                    <p>For more information to donate blood go to following link</p>
                    <div>
                      <input type="button" className='eligible' value="Eligibility to Donate"  onClick={togglePopup}/>  
                        {isOpen && <Popup
                                    content={<>
                                      <b style={{color:"red"}}>Who are Eligibile to Donate Blood?</b>
                                      <p><ul>
                                        <li>Age between 18 and 65 years</li>
                                        <li>Haemoglobin-not less than 12.5g/dL</li>
                                        <li> Pulse between 50 and 100/minute with no irregularities </li>
                                        <li> Blood Pressure Systolic 100-180 mm Hg and Diastolic 50 - 100 mm Hg </li>
                                        <li> Temperature Normal (oral temperature not exceeding 37.5 C)</li>
                                        <li> Body weight not less than 45 kg.</li>
                                        </ul></p>
                                      </>}
                                    handleClose={togglePopup}
                         />}
                    </div>
                    Want to learn more about Blood Donation!!!
                   <div><a href="/learnthings">Click here</a></div>
                  </CardBody>
                </div>
              </div>

            </div>
          </div>
       </div>
       <div style={{textAlign:"center",backgroundColor:"lightgray",height:"60px",paddingTop:"25px"}}>
        <cite>cts@cognizant.com 2020</cite>
       </div>
    </div>
 )
}

export default MainHome
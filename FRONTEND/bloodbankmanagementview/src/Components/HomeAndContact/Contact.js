import React, { Component } from 'react';
import { Card, CardText, CardBody,
    CardTitle, CardSubtitle, Button } from 'reactstrap';
import Home from './Home';
import './contactcss.css'
import gmaillogo from '../images/gmaillogo.png'
import twitterlogo from '../images/twitterlogo.png'
import linkedinlogo from '../images/linkedinlogo.jpg'

class Contact extends Component {
    render() {
        return (
            <div >
                <div><Home/></div>
                <div id='contactmain'>
                <Card>
        <CardBody>
          <h3 style={{color:"purple"}}>Contact Us</h3>
          
          <CardText><img src={gmaillogo} alt="email" width="40px" height="30px"/><label style={{color:"purple"}}><a href="https://mail.google.com/mail/?view=cm&fs=1&to=email@domain.example">Email:</a></label><em>cognizantdev@gmail.com</em></CardText>
          <CardText><img src={twitterlogo} alt="email" width="70px" height="30px"/><label style={{color:"purple"}}>Twitter:</label><em><a href="https://twitter.com/i/flow/login">cognizantdev@gmail.com</a></em></CardText>
          <CardText><img src={linkedinlogo} alt="email" width="40px" height="30px"/><label style={{color:"purple"}}>Linkedin:</label><em><a href="http://www.linkedin.com/in/tarv-gupta">cognizantdev@linkedin.in</a></em></CardText>
          
        </CardBody></Card>
    
      </div></div>
                               
                    
               
            
        
        );
    }
}

export default Contact;
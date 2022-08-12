import React from 'react'
import { Table } from 'reactstrap'
import Home from './Home'
import './custom.css'

function LearnThings() {
  return (
    <div>
    <div><Home/></div>
    <div><h4 style={{color:"black", textAlign:"center", paddingTop:"5%", paddingBottom:"3%", fontFamily:"verdana"}}>Compatible Blood type Donors</h4></div>
    <div>
        <Table bordered responsive style={{borderColor:"black",width:"1200px",textAlign:'center',marginLeft:"170px"}}>
            <thead >
            <tr><td><strong>Blood Type</strong></td>
            <td><strong>Donate Blood to</strong></td>
            <td><strong>Receive Blood From</strong></td></tr></thead>
            <tbody>
                <tr>
                    <td className='bgroup'>A+</td><td>A+, AB+</td><td>A+,  A-, O+, O-</td>
                </tr>
                <tr>
                    <td className='bgroup'>O+</td><td>O+, A+, B+, AB+</td><td>O+,O-</td>
                </tr>
                <tr>
                    <td className='bgroup'>B+</td><td>B+, AB+</td><td>B+, B-, O+, O-</td>
                </tr>
                <tr>
                    <td className='bgroup'>AB+</td><td>AB+</td><td>Everyone</td>
                </tr>
                <tr>
                    <td className='bgroup'>A-</td><td>A+, AB+, A-, AB-</td><td>A-, O-</td>
                </tr>
                <tr>
                    <td className='bgroup'>O-</td><td>Everyone</td><td>O-</td>
                </tr>
                <tr>
                    <td className='bgroup'>B-</td><td>B+, AB+, AB-, B-</td><td>B-, O-</td>
                </tr>
                <tr>
                    <td className='bgroup' >AB-</td><td>AB+, AB-</td><td>AB-, A-, B-, O-</td>
                </tr>
                
            </tbody>
        </Table>
    </div>
    </div>
  )
}

export default LearnThings
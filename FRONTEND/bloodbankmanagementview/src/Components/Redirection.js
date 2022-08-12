import React,{useState} from 'react'
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";

import DonorForm from './Register/DonorForm'
import ListDonorComponent from './Tables/ListDonorComponent'

function Redirection() {
  const [radioValue, setRadioValue] = useState("");

  const radioHandler = (e) => {
    e.preventDefault();
    setRadioValue(e.target.value);
    console.log(e.target.value);
  };

  return (
    <>
      <div class="outer">
        <FormControl>
          <FormLabel id="demo-row-radio-buttons-group-label">
            Select appropriate option
          </FormLabel>
          <RadioGroup
            row
            aria-labelledby="demo-row-radio-buttons-group-label"
            name="row-radio-buttons-group"
          >
            <FormControlLabel
              value="donor"
              control={<Radio />}
              label="As a Donor"
              onChange={radioHandler}
            />
            <FormControlLabel
              value="searchDonor"
              control={<Radio />}
              label="search for Donors"
              onChange={radioHandler}
            />
          
          </RadioGroup>
          {radioValue === "donor" && <DonorForm />}
          {radioValue === "searchDonor" && <ListDonorComponent />}
        
        </FormControl>
      </div>
    </>
  );
}

export default Redirection;

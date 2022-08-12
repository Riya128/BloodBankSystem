package com.cognizant.Donor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Donor.Model.Donor;
import com.cognizant.Donor.Repository.DonorRepository;

@CrossOrigin("http://localhost:3000")
@RestController
public class DonorController {
	
	@Autowired //enables you to inject the object dependency implicitly. It internally uses setter or constructor injection.
	private DonorRepository donorRepository;
	
	@GetMapping("/greetdonor")
	public String greetDonor() {
		return "Hello Donor";
	}
	//Dharani ->save donor and get list of donors
	@PostMapping("/addDonor")
	public Donor addDonor(@RequestBody Donor donor, BindingResult result) {
		/*@RequestBody: It is used to bind HTTP request with an object in a method parameter. 
		Internally it uses HTTP MessageConverters to convert the body of the request. 
		When we annotate a method parameter with @RequestBody,
		the Spring framework binds the incoming HTTP request body to that parameter(donor)*/
		donorRepository.save(donor);
		System.out.println(donor);
		return donor;
	}

	@GetMapping("/getDonorList")
	public List<Donor> getDonorList(){
		return donorRepository.findAll();
	}

	
	//Done By Tarv ->delete Donors by Admin
	
	@DeleteMapping("/getDonors/{donorId}")
	@ResponseBody
	public ResponseEntity<HttpStatus> deleteDonor(@PathVariable Long donorId){ //the @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.
		try {
			//this.donorService.deleteDonors(Long.parseLong(donorId));
			donorRepository.deleteById(donorId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

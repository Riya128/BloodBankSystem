package com.cognizant.Donor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.Donor.Model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor,Long>{

}

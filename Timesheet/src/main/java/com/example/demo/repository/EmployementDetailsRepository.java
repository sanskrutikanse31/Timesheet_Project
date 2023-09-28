package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.bean.EmployementDetails;

public interface EmployementDetailsRepository extends JpaRepository<EmployementDetails, Integer>  {

}

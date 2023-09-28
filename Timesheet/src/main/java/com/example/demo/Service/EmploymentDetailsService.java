package com.example.demo.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ExceptionHandler.ResourceNotFoundException;
import com.example.demo.Implementation.EmploymentDetailsServiceImpl;
import com.example.demo.bean.Employee;
import com.example.demo.bean.EmployementDetails;

@Service
public class EmploymentDetailsService {
	
	@Autowired
	private EmploymentDetailsServiceImpl empdetailsimpl;
	private EmployeeService empservice;
	
	// Add the work needs to be done inside the table
	public void add(EmployementDetails empdetails){
		empdetailsimpl.add(empdetails);
	}
	
	//Delete the unwanted description from the table based on table id
	public void deletebyId(int id) {
		ArrayList<EmployementDetails> arr = empdetailsimpl.print();
		for(EmployementDetails emp : arr) {
			if(emp.getId()==id) {
				empdetailsimpl.deletebyId(id);
			}
		}
		throw new ResourceNotFoundException("Timesheet","EmpId",id);
	}
	
	//Reterive all the data present in the employeedetails table
	public ArrayList<EmployementDetails> print(){
		return (ArrayList<EmployementDetails>) empdetailsimpl.print();
	}
	
	//Employee will get the history of only his personnel data
	public ArrayList<EmployementDetails> getAllById(int Empid){
		ArrayList<EmployementDetails> ans = empdetailsimpl.getAllById(Empid);
		if(ans==null) {
			throw new ResourceNotFoundException("Employee","EmpId",Empid);
		}else {
			return ans;
		}
	}
}

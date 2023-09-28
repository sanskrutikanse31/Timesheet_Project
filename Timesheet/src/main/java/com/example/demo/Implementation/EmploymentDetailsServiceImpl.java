package com.example.demo.Implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.EmployementDetails;
import com.example.demo.repository.EmployementDetailsRepository;

@Service
public class EmploymentDetailsServiceImpl {
	
	@Autowired
	private EmployementDetailsRepository empdetailsrepo;
	
	//Adding data inside the table
	public void add(EmployementDetails empdetails){
		empdetailsrepo.save(empdetails);
	}
	
	//Deleting a particular entry from the table based on its table id
	public void deletebyId(int id) {
		empdetailsrepo.deleteById(id);
	}
	
	//Retrieve all the entries inside the table
	public ArrayList<EmployementDetails> print(){
		return (ArrayList<EmployementDetails>) empdetailsrepo.findAll();
	}
	
	//Get the details of all the table entries of a particular emp id
	public ArrayList<EmployementDetails> getAllById(int Empid){
		ArrayList<EmployementDetails> arr = new ArrayList<>();
		ArrayList<EmployementDetails> arr1 = print();
		for(EmployementDetails e : arr1) {
			if(e.getEmpid()==Empid) {
				arr.add(e);
			}
		}
		if(arr.size()==0) {
			return null;
		}else {
			return arr;
		}
		
	}
}

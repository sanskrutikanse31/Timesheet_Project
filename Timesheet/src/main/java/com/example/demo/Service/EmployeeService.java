package com.example.demo.Service;

import com.example.demo.ExceptionHandler.DuplicateIdException;
import com.example.demo.ExceptionHandler.ResourceNotFoundException;
import com.example.demo.Implementation.*;
import com.example.demo.bean.*;
import com.example.demo.repository.EmployementDetailsRepository;
import com.example.demo.repository.LeaveRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This file will contain all the business logic of the project

@Service
public class EmployeeService {
	 
	 @Autowired
	 private final EmployeeServiceImpl empService; 
	 private final LeaveRepository leaverepo;
	 private final EmployementDetailsRepository empdetrepo;
	
	 public EmployeeService(EmployeeServiceImpl empService,LeaveRepository leaverepo,EmployementDetailsRepository empdetrepo) {
		super();
		this.empService = empService;
		this.leaverepo = leaverepo;
		this.empdetrepo = empdetrepo;
	}

	//It will make the necessary check before adding the data inside the table
	public void add(Employee employee){
		ArrayList<Employee> emp = empService.print();
		for(Employee e : emp) {
			if(e.getEmpId()==employee.getEmpId()) {
				throw new DuplicateIdException("EmpId is already present");
			}
		}
		empService.add(employee);
	}
	
	//print all the data present inside the database
	public ArrayList<Employee> print() {
		return (ArrayList<Employee>) empService.print();
	}
	
	//It will make the necessary check before adding the data inside the table
	public void delete(int id) {
		ArrayList<Employee> emp = empService.print();
		for(Employee e : emp) {
			if(e.getEmpId()==id) {
				empService.delete(e.getId());
				return;
			}
		}
		throw new ResourceNotFoundException("Employee","Empid",id);
	}
	
	//It will update the data present inside the system
	public void update(int Empid,Employee employee) {
		Employee existingemp = getdetails(Empid);
		empService.update(existingemp , employee);
	}
	
	// Return all the details of the employee based on the emp id
	public Employee getdetails(int empId) {
		ArrayList<Employee> emp = empService.print();
		for(Employee e : emp) {
			if(e.getEmpId()==empId) {
				return e;
			}
		}
		throw new ResourceNotFoundException("Employee","EmpId",empId);
	}
	
	public Employee getdetailsbyId(int id) {
		Employee emp = empService.getdetailsbyId(id);
		if(emp==null) {
			throw new ResourceNotFoundException("Employee","EmpId",id);
		}else {
			return emp;
		}
	}
	
	public ArrayList<Employee> getrequiredDetails(int id){
		
		ArrayList<Employee> emp =  empService.getrequiredDetails(id);
		if(emp==null) {
			throw new ResourceNotFoundException("Employee","EmpId",id);
		}else {
			return emp;
		}
	}
	
	public ArrayList<Employee> getdetailsbymanagerprimarykey(int id){
		ArrayList<Employee> emp = empService.getdetailsbymanagerprimarykey(id);
		if(emp.size()==0) {
			throw new ResourceNotFoundException("Employee","EmpId",id);
		}else {
			return emp;
		}	
	}
	
	public void applyleave(EmployeeLeave leave) {
		empService.applyleave(leave);
	}
	
//	here it is employe's leave details using employee id
	public ArrayList<EmployeeLeave> getdetailsbyemployeeId(int id){
		ArrayList<EmployeeLeave> arr = (ArrayList<EmployeeLeave>)leaverepo.findAll();
		ArrayList<EmployeeLeave> ans = new ArrayList<>();
		for(EmployeeLeave e:arr) {
			if(e.getEmployee().getId()==id && e.getApproval().equals("pending")) {
				ans.add(e);
			}
		}
		return ans;
	}
	
//	here it is employe's timesheet using employee id
	public ArrayList<EmployementDetails> gettimesheetbyemployeeId(int id){
		ArrayList<EmployementDetails> arr = (ArrayList<EmployementDetails>)empdetrepo.findAll();
		ArrayList<EmployementDetails> ans = new ArrayList<>();
		for(EmployementDetails e:arr) {
			if(e.getEmployee().getId()==id && e.getStatus().equals("pending")) {
				ans.add(e);
			}
		}
		return ans;
	}
	
	//fetch details of employee table on the basis of manager primary id
	public ArrayList<Employee> fetchdetails(int id){
		ArrayList<Employee> emp = empService.print();
		ArrayList<Employee> ans = new ArrayList<>();
		for(Employee e: emp) {
			if(e.getManager().getId()==id) {
				ans.add(e);
			}
		}
		return ans;
	}
	
}

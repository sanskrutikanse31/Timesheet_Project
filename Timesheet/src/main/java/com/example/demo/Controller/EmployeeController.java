package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Service.EmploymentDetailsService;
import com.example.demo.Service.ManagerService;
import com.example.demo.bean.*;
import com.example.demo.repository.LeaveRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	
	
	private EmployeeService empservice;
	private EmploymentDetailsService empdetailsService;
	@Autowired
	private ManagerService managerservice;
	@Autowired
	private LeaveRepository lop;
	
	
	
	public EmployeeController(EmployeeService empservice, EmploymentDetailsService empdetailsService) {
		super();
		this.empservice = empservice;
		this.empdetailsService = empdetailsService;
	}
	
	
	
	// this is the home page of employee where his data is fetched using check id provided with login page
	@GetMapping("/home/{EmpId}")
	public Employee home(@PathVariable int EmpId) {
		return empservice.getdetails(EmpId); // this is checked with emp id
	}
	
	
	
	// Add the details of employee(timesheet) in the EmployeeDetails table using employee id
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody EmployementDetails empdetails) {
		ArrayList<Employee> emp = empservice.print();
		for(Employee e:emp) {
			if(e.getEmpId()==empdetails.getEmpid()) {
				empdetails.setEmployee(e);
				empdetailsService.add(empdetails);
				return "sucessfull";
			}
		}
		return "Not sucessfull";
	}
	
	
	
	//Get the timesheet description of the particular employee using employee id
	@GetMapping("/description/{Empid}")
	public ArrayList<EmployementDetails> description(@PathVariable int Empid){
		return empdetailsService.getAllById(Empid);
	}
	
	
	
	//Delete the timesheet desciption on the basis of the table id(primary key)
	@DeleteMapping("/DeleteDescription/{id}")
	public void deletedesp(@PathVariable int id) {
		empdetailsService.deletebyId(id);
	}
	
	
	
	//update the timesheet details on the basic of employment details id(primaryKey)
	@PutMapping("/update/{id}")
	public void updateempdetails(@RequestBody EmployementDetails empdetails,@PathVariable("id") int id) {
		ArrayList<EmployementDetails> emp = empdetailsService.getAllById(empdetails.getEmpid());
		for(EmployementDetails e:emp) {
			if(e.getId()==id) {
				e.setDate(empdetails.getDate());
				e.setDescription(empdetails.getDescription());
				e.setTime(empdetails.getTime());
				empdetailsService.add(e);
				break;
			}
		}
		return;
	}
	
	//check the lop of the employee based on his particular id
	@RequestMapping("/lop/{EmpId}")
	public ArrayList<EmployeeLeave> checkLop(@PathVariable int EmpId) {
		ArrayList<EmployeeLeave> emp = (ArrayList<EmployeeLeave>) lop.findAll();
		ArrayList<EmployeeLeave> ans = new ArrayList<>();
		for(EmployeeLeave e:emp) {
			if(e.getEmpId()==EmpId) {
				ans.add(e);
			}
		}
		return ans;
	}
	
	// to apply for a leave which is going to be approved by manager
	@PostMapping("/applyleave")
	public void applyleave(@RequestBody EmployeeLeave leave) {
		empservice.applyleave(leave);
	}
	
	//To get all his personal timesheet request by his empId
	@GetMapping("/leave/{EmpId}")
 	public ArrayList<EmployeeLeave> leaveshow(@PathVariable int EmpId){
		ArrayList<EmployeeLeave> emp = (ArrayList<EmployeeLeave>)lop.findAll();
		ArrayList<EmployeeLeave> ans = new ArrayList<>();
		for(EmployeeLeave em : emp) {
			if(em.getEmpId()==EmpId) {
				ans.add(em);
			}
		}
		return ans;
 	}
	
}
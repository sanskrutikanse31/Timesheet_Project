package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Implementation.EmploymentDetailsServiceImpl;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Service.EmploymentDetailsService;
import com.example.demo.Service.ManagerService;
import com.example.demo.bean.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	private EmploymentDetailsService empdetservice;
	private ManagerService managerService;
	private EmployeeService empService;
	
	
	public ManagerController(ManagerService managerService, EmployeeService empService,EmploymentDetailsService empdetservice) {
		super();
		this.managerService = managerService;
		this.empService = empService;
		this.empdetservice = empdetservice;
	}
	
	
	// sign up page of manager, data will be stored in manager table
	@PostMapping("/sign-up")
	public String signup(@RequestBody Manager manager) {
		managerService.add(manager);
		return "Sucessfull";
	}
	
	// Manager will add the data of the employee inside the employee table using manager employee id
	@PostMapping("/home/add/{empId}")
	public void add(@RequestBody Employee employee, @PathVariable("empId") int empId) {
		Manager manager = managerService.getid(empId);
		employee.setManager(manager);
		empService.add(employee);
		return;
	}
	
	//manger will get all the pending leave request on the basis of his manager's employee id
	@GetMapping("/getleaverequests/{EmpId}")
	public ArrayList<EmployeeLeave> getleaverequest(@PathVariable int EmpId){
		int id = managerService.getleaverequest(EmpId);
		ArrayList<Employee> emp = empService.fetchdetails(id);
//		System.out.println(emp);
		ArrayList<EmployeeLeave> ans = new ArrayList<>();
		for(Employee e:emp) {
			ArrayList<EmployeeLeave> arr = empService.getdetailsbyemployeeId(e.getId());
			for(EmployeeLeave em:arr) {
				ans.add(em);
			}
		}
		return ans;
	}
	
	
	//manger will get all the pending timesheet request on the basis of his employee id
	@GetMapping("/getpendingtimesheet/{EmpId}")
	public ArrayList<EmployementDetails> getpendingtimesheet(@PathVariable int EmpId){
		int id = managerService.getleaverequest(EmpId);
		ArrayList<Employee> emp = empService.getdetailsbymanagerprimarykey(id);
		ArrayList<EmployementDetails> ans = new ArrayList<>();
		for(Employee e:emp) {
			ArrayList<EmployementDetails> arr = empService.gettimesheetbyemployeeId(e.getId());
			for(EmployementDetails em:arr) {
				ans.add(em);
			}
		}
		return ans;
	}
	
	// Manager updating his personal data in manager table
	@RequestMapping("/update")
	public ResponseEntity<Manager> updatemethod(@RequestBody Manager manager) {
		managerService.update(manager.getEmpid(),manager);
		return ResponseEntity.ok(manager);
	}
	
	// Manager will remove the data of employee from employee table using the employee id of the employee
	@DeleteMapping("/home/remove/{Empid}")
	public void remove(@PathVariable int Empid) {
		empService.delete(Empid);
	}
	
	    
	//Fetching all the details of the employee which comes under this manager using employee id of the manager
    @GetMapping("/home/{EmpId}")
	public List<Employee> home(@PathVariable int EmpId) {
		Manager manager = managerService.getid(EmpId);  // this is emp id by check page
		return manager.getEmployees();
	}
    
    //updating details of the employee using employee employeeid
    @PutMapping("/updateemployee")
    public void update(@PathVariable int EmpId, @RequestBody Employee emp) {
    	empService.update(EmpId, emp);
    	return;
    }
	
    //It will fetch all the pending employmentdetails(timesheet) of the employee using his employee id
	@GetMapping("/timesheet/{EmpId}")
	public ArrayList<EmployementDetails> timesheet(@PathVariable int EmpId) {
		ArrayList<EmployementDetails> emp = empdetservice.print();
		ArrayList<EmployementDetails> ans = new ArrayList<>();
		for(EmployementDetails e:emp) {
			if(e.getEmpid()==EmpId && e.getStatus().equals("pending")) {
				ans.add(e);
			}
		}
		return ans;
	}
	
	//It will update the employeedetails and set it to approved or disapproved based on primay key(id) inside emploementdetails
    @PostMapping("timesheetapproval/{id}/{status}")
    public void timesheetapproval(@PathVariable("id") int id , @PathVariable("status") String status) {
    	System.out.println(status);
    	ArrayList<EmployementDetails> emp = empdetservice.print();
		for(EmployementDetails e:emp) {
			if(e.getId()==id) {
				e.setStatus(status);
				empdetservice.add(e);
			}
		}
		return;
    }
    
    //Getting all the leavedetails of an particular employee using his employeeid
 	@GetMapping("/leave/{EmpId}")
 	public ArrayList<EmployeeLeave> leaveshow(@PathVariable int EmpId){
 		return managerService.approvalfind(EmpId);
 	}
 	
 	//Approving or disapproving the leaves of the employees
 	@PutMapping("/leaveapproval")
 	public void leaveapproval(@RequestBody EmployeeLeave leave) {
 		managerService.leaveapproval(leave);
 	}
	
	
	//automatically get called and the lop will be updated inside the lop table based on every individual employeeid
	@PostMapping("/lop/{Empid}")
	public void lop(@PathVariable int Empid) {
		managerService.lop(Empid);
	}
	
	//return all the data present inside the employee table
	@GetMapping("allemployees")
	public ArrayList<Employee> getalldata(){
		return empService.print();
	}
	
	//return the size of the table that is total employees present inside the employee table
	@GetMapping("/size")
	public int getsize() {
		ArrayList<Employee> emp = empService.print();
		return emp.size();
	}
	
	//fetch the total lop history available with an employee based on his employeeid
	@GetMapping("/getlop/{EmpId}")
	public ArrayList<LossOfPay> getLop(@PathVariable int EmpId){
		return managerService.getLop(EmpId);
	}
	
	
	
}

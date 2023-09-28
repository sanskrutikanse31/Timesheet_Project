package com.example.demo.Implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Employee;
import com.example.demo.bean.EmployeeLeave;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRepository;

@Service
public class EmployeeServiceImpl {
	
	 @Autowired
	 private final EmployeeRepository empRepo; 
	 private final LeaveRepository leaverepo;

	public EmployeeServiceImpl(EmployeeRepository empRepo, LeaveRepository leaverepo) {
		super();
		this.empRepo = empRepo;
		this.leaverepo = leaverepo;
	}

	//add data inside the table
	public void add(Employee employee) {
		empRepo.save(employee);
		return;
	}
	
	//print all the data from the table
	public ArrayList<Employee> print() {
		return (ArrayList<Employee>) empRepo.findAll();
	}
	
	//delete the required data from the list
	public void delete(int id) {
		empRepo.deleteById(id);
	}
	
	//update the employee details in the table
	public void update(Employee existingemp,Employee employee) {
		existingemp.setName(employee.getName());
		existingemp.setEmail_Id(employee.getEmail_Id());
		existingemp.setDepartment(employee.getDepartment());
		existingemp.setPhno(employee.getPhno());
		existingemp.setType(employee.getType());
	}
	
	//Return the details of employee based on normal id
	public Employee getdetailsbyId(int Empid) {
//		return empRepo.findById(id).get();
		ArrayList<Employee> emp =  print();
		for(Employee e : emp) {
			if(e.getEmpId()==Empid) {
				return e;
			}
		}
		return null;
	}
	
//	Return the details of employee based on manager id
	public ArrayList<Employee> getrequiredDetails(int id){
		ArrayList<Employee> arr =  print();
		ArrayList<Employee> ans =  new ArrayList<>();
		for(Employee e:arr) {
			if(e.getManager().getEmpid()==id) {
				ans.add(e);
			}
		}
		return ans;
	}
	
	public ArrayList<Employee> getdetailsbymanagerprimarykey(int id){  // this is the primary key id
		ArrayList<Employee> emp = print();
		ArrayList<Employee> ans = new ArrayList<>();
		for(Employee e:emp) {
			if(e.getManager().getId()==id) {
				ans.add(e);
			}
		}
		return ans;
	}
	
	public void applyleave(EmployeeLeave leave) {
		 Employee emp = getdetailsbyId(leave.getEmpId());
		 System.out.println(emp);
		 leave.setEmployee(emp);
		leaverepo.save(leave);
	}
}

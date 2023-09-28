package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.ExceptionHandler.ResourceNotFoundException;
import com.example.demo.ExceptionHandler.passwordmismatchexception;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Service.ManagerService;
import com.example.demo.bean.*;
@Controller
public class mainController {
	
//	Check check = new Check();
	private EmployeeService empService;
	private ManagerService managerService;
	
	
	public mainController(EmployeeService empService, ManagerService managerService) {
		super();
		this.empService = empService;
		this.managerService = managerService;
	}

	@RequestMapping("/sign-in")
	public Check login(@RequestBody Check check) {
		if(check.getType().equals("Manager")) {
			Manager manager = managerService.getid(check.getId());
			if(manager==null) {
				throw new ResourceNotFoundException("Manager","Employee Id", check.getId()); // handle the null pointer exception
			}else {
				if(check.getPassword().equals(manager.getPassword())) {
					return check;
			    }
		    }
		}else{
			Employee employee = empService.getdetailsbyId(check.getId());
			if(employee==null) {
				throw new ResourceNotFoundException("Employee","Employee Id", check.getId()); // handle the null pointer exception
			}else{
				
			if(check.getPassword().equals(employee.getPassword())) {
				return check;
			  }
		  }
	  }
		throw new passwordmismatchexception("password doesnot match");
	}
	
	
}

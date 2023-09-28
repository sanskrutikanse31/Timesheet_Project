package com.example.demo.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.EmployementDetails;
import com.example.demo.bean.Employee;
import com.example.demo.bean.EmployeeLeave;
import com.example.demo.bean.LossOfPay;
import com.example.demo.bean.Manager;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.ExceptionHandler.ResourceNotFoundException;
import com.example.demo.Implementation.*;


@Service
@RequestMapping("/manager")
public class ManagerService {
	
	
	private final ManagerServiceImpl manageSerimp;
	private final EmploymentDetailsServiceImpl empdetserviceimpl;
	private final LeaveRepository leaverepo;
	private final EmployeeServiceImpl empserimpl;

	//Constructor
	public ManagerService(ManagerServiceImpl manageSerimp,EmploymentDetailsServiceImpl empdetserviceimpl, LeaveRepository leaverepo,EmployeeServiceImpl empserimpl) {
		super();
		this.manageSerimp = manageSerimp;
		this.empdetserviceimpl = empdetserviceimpl;
		this.leaverepo = leaverepo;
		this.empserimpl=empserimpl;
	}

	//add data inside the manager table
	public void add(Manager manager) {
		manageSerimp.add(manager);
		return;
	}
	
	//Retrieve all the data present inside the manager table
	public ArrayList<Manager> print() {
		return manageSerimp.print();
	}
	
	//delete data from the Manager table
	public void delete(int Empid) {
		Manager existingmanager = manageSerimp.getid(Empid);
		if(existingmanager==null) {
			throw new ResourceNotFoundException("Manager","EmpId",Empid);
		}else {
			manageSerimp.delete(existingmanager.getId());
		}
	}
	
	//update the manager data inside the manager table
	public void update(int Empid,Manager manager) {
		Manager existingmanager = manageSerimp.getid(Empid);
		if(existingmanager==null) {
			throw new ResourceNotFoundException("Manager","EmpId",Empid);
		}else {
			manageSerimp.update(existingmanager, manager);
		}
		
	}
	
	// Function to get the data of manager with the help of Empid
	public Manager getid(int Empid) {
		Manager man =  manageSerimp.getid(Empid);
		if(man==null) {
			throw new ResourceNotFoundException("Manager","EmpId",Empid);
		}else {
			return man;
		}
	}
	
	public int getleaverequest(int EmpId){
		Manager man = getid(EmpId);
		return man.getId();
	}
	
	//calculating lop and auto updating in the database
	public void lop(@PathVariable int EmpId) {
		ArrayList<EmployementDetails> emp = empdetserviceimpl.getAllById(EmpId);
		EmployementDetails employee = emp.get(emp.size()-1);
		LocalDate lastupdate = employee.getDate();
		LocalDate currDate = LocalDate.now();
		LocalDate leavestartDate = startday(EmpId);
		LocalDate leaveEndDate = endday(EmpId);
		int extradays=0;
		if(leavestartDate!=null && leaveEndDate!=null) {
			extradays = leavedates(lastupdate ,currDate ,leavestartDate ,leaveEndDate);
		}
		long NumberOfDays = ChronoUnit.DAYS.between(lastupdate, currDate)+1-extradays;
		if(leavestartDate.isEqual(currDate) && NumberOfDays==11) {
			NumberOfDays = NumberOfDays - ChronoUnit.DAYS.between(leavestartDate, leaveEndDate)+1;
		}
		if(NumberOfDays==5) {
			return; // Remainder mail need to be send
		}else {
			if(NumberOfDays>10) {
				int daystoadd = (int)NumberOfDays-10;
				LocalDate lopday = lastupdate.plusDays(daystoadd);
				manageSerimp.addlop(lopday, EmpId);
				return;
			}
		}
	}
	
	//getting all the lop based on the employee id
	public ArrayList<LossOfPay> getLop(int EmpId){
		ArrayList<LossOfPay> lop = manageSerimp.printlop();
		ArrayList<LossOfPay> ans = new ArrayList<LossOfPay>();
		for(LossOfPay l : lop) {
			if(l.getEmpId()==EmpId) {
				ans.add(l);
			}
		}
		return ans;
	}
	
	// Finding all the pending leaves based on a particular employee id
	public ArrayList<EmployeeLeave> approvalfind(int EmpId) {
		ArrayList<EmployeeLeave> leave = manageSerimp.leaveprint();
		ArrayList<EmployeeLeave> ans = new ArrayList<EmployeeLeave>();
		for(EmployeeLeave l:leave) {
			if(l.getEmployee().getEmpId()==EmpId && l.getApproval().equals("pending") ) {
				ans.add(l);
			}
		}
		
		return ans;
	}
	
	// Approving/Disapproving the leaves
	public void leaveapproval(EmployeeLeave leave) {
		ArrayList<EmployeeLeave> arr = manageSerimp.leaveprint();
		for(EmployeeLeave l:arr) {
			if(l.getEmpId()==leave.getEmpId() && l.getApproval().equals("pending")) {
				manageSerimp.Leaveapproval(l,leave);
				break;
			}
		}
		return;
	}
	
	//find leave start days
	public LocalDate startday(int EmpId) {
		ArrayList<EmployeeLeave> emp = (ArrayList<EmployeeLeave>)leaverepo.findAll();
		for(EmployeeLeave e : emp) {
			if(e.getEmpId()==EmpId && e.getApproval().equals("approved")) {
				return e.getStart_Date();
			}
		}
		return null;
	}
	
	//find leave end date
		public LocalDate endday(int EmpId) {
			ArrayList<EmployeeLeave> emp = (ArrayList<EmployeeLeave>)leaverepo.findAll();
			for(EmployeeLeave e : emp) {
				if(e.getEmpId()==EmpId && e.getApproval().equals("approved")) {
					return e.getEnd_Date();
				}
			}
			return null;
		}
	
	//calculating leave days
	public int leavedates(LocalDate lastupdate , LocalDate currentDate , LocalDate leavestartDate , LocalDate leaveEndDate) {
		if((leavestartDate.isAfter(lastupdate) || leavestartDate.isEqual(lastupdate)) &&(leaveEndDate.isBefore(currentDate)|| leaveEndDate.isEqual(currentDate))) {
			return (int)ChronoUnit.DAYS.between(leavestartDate,leaveEndDate)+1;
		}else if(leavestartDate.isEqual(currentDate)) {
			return (int)ChronoUnit.DAYS.between(leavestartDate,leaveEndDate)+1;
		}
		return 0;
	}
	
}

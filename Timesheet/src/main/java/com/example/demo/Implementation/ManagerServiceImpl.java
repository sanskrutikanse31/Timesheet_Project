package com.example.demo.Implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.bean.Employee;
import com.example.demo.bean.EmployeeLeave;
import com.example.demo.bean.LossOfPay;
import com.example.demo.bean.Manager;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.LossOfPayRepository;
import com.example.demo.repository.ManagerRepository;

@Service
public class ManagerServiceImpl {
	@Autowired
	 private final ManagerRepository manageRepo; 
	 private final LossOfPayRepository loprepo;
	 private final EmployeeServiceImpl empserimpl;
	 private final LeaveRepository leaverepo;

	public ManagerServiceImpl(ManagerRepository manageRepo, LossOfPayRepository loprepo, EmployeeServiceImpl empserimpl,
			LeaveRepository leaverepo) {
		super();
		this.manageRepo = manageRepo;
		this.loprepo = loprepo;
		this.empserimpl = empserimpl;
		this.leaverepo = leaverepo;
	}

	//add data inside the manager table
	public void add(Manager manager) {
		manageRepo.save(manager);
		return;
	}
	
	//Retrieve all the data present inside the manager table
	public ArrayList<Manager> print() {
		return (ArrayList<Manager>) manageRepo.findAll();
	}
	
	//delete data from the manager table
	public void delete(int id) {
		manageRepo.deleteById(id);
	}
	
	//update the manager data inside the manager table
	public void update(Manager existingmanager,Manager manager) {
		existingmanager.setName(manager.getName());
		existingmanager.setEmailid(manager.getEmailid());
		existingmanager.setPhoneno(manager.getPhoneno());
		manageRepo.save(existingmanager);
	}
	
	// Function to get the data of manager with the help of id
	public Manager getid(int Empid) {
		ArrayList<Manager> arr = print();
		for(Manager man : arr) {
			if(man.getEmpid()==Empid) {
				return man;
			}
		}
		return null;
	}
	
	//Adding the lop on the particular table
	public void addlop(LocalDate date,int EmpId) {
		LossOfPay lop = new LossOfPay();
		lop.setDate(date);
		lop.setEmpId(EmpId);
		lop.setLop(1);
		Employee e = empserimpl.getdetailsbyId(EmpId);
		lop.setEmployee(e);
		loprepo.save(lop);
	}
	
	// Fetching all the data of the lop table
	public ArrayList<LossOfPay> printlop() {
		return (ArrayList<LossOfPay>) loprepo.findAll();
	}
	
	//Fetching all the data's of the leave table
	public ArrayList<EmployeeLeave> leaveprint() {
		return (ArrayList<EmployeeLeave>) leaverepo.findAll();
	}
	
	//Updating the leave status in the table by manager
	public Void Leaveapproval(EmployeeLeave present, EmployeeLeave updated) {
		present.setApproval(updated.getApproval());
		leaverepo.save(present);
		return null;
	}
}

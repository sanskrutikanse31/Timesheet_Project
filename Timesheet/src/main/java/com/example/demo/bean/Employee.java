package com.example.demo.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email_Id;
	@Column(unique = true)
	private int empId;
	private int phno;
	private String department;
	private String type;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonIgnore
	private List<EmployementDetails> employementDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonIgnore
	private List<LossOfPay> LossOfPay;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonIgnore
	private List<EmployeeLeave> applyLeave;
	
	@ManyToOne
	@JsonIgnore
	private Manager manager;
	
	public Employee() {
		super();
	}

	public Employee(int id,String name, String email_Id, int empId, int phno, String department, String type,String password) {
		super();
		this.name = name;
		this.email_Id = email_Id;
		this.empId = empId;
		this.phno = phno;
		this.department = department;
		this.type = type;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getPhno() {
		return phno;
	}

	public void setPhno(int phno) {
		this.phno = phno;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<EmployementDetails> getEmployementDetails() {
		return employementDetails;
	}

	public void setEmployementDetails(List<EmployementDetails> employementDetails) {
		this.employementDetails = employementDetails;
	}
	
	
	public List<LossOfPay> getLossOfPay() {
		return LossOfPay;
	}

	public void setLossOfPay(List<LossOfPay> lossOfPay) {
		LossOfPay = lossOfPay;
	}

	public List<EmployeeLeave> getApplyLeave() {
		return applyLeave;
	}

	public void setApplyLeave(List<EmployeeLeave> applyLeave) {
		this.applyLeave = applyLeave;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email_Id=" + email_Id + ", empId=" + empId + ", phno="
				+ phno + ", department=" + department + ", type=" + type + ", password=" + password + "]";
	}

	
	
}

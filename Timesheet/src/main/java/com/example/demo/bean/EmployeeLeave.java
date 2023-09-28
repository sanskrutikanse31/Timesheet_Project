package com.example.demo.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class EmployeeLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int empId;
	String type;
	String reason;
	LocalDate start_Date;
	LocalDate end_Date;
	String approval;
	
	
	
	public EmployeeLeave(int empId, String type, String reason, LocalDate start_Date, LocalDate end_Date, String approval) {
		super();
		this.empId = empId;
		this.type = type;
		this.reason = reason;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.approval = approval;
	}
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
	
	
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public EmployeeLeave() {
		super();
	}


	public int getId() {
		return id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LocalDate getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(LocalDate start_Date) {
		this.start_Date = start_Date;
	}
	public LocalDate getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(LocalDate end_Date) {
		this.end_Date = end_Date;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}


	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", empId=" + empId + ", type=" + type + ", reason=" + reason
				+ ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", approval=" + approval + ", employee="
				+ employee + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}

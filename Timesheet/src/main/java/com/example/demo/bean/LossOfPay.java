package com.example.demo.bean;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class LossOfPay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int EmpId;
	private LocalDate date;
	private int lop;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
	
	
	
	public LossOfPay() {
		super();
	}
	
	public LossOfPay(int EmpId, LocalDate date, int lop) {
		super();
		this.EmpId = EmpId;
		this.date = date;
		this.lop = lop;
	}
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getEmpId() {
		return EmpId;
	}

	public void setEmpId(int empId) {
		EmpId = empId;
	}
	
	public int getId() {
		return id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getLop() {
		return lop;
	}
	public void setLop(int lop) {
		this.lop = lop;
	}
	
	
}

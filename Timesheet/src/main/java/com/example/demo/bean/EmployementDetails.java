package com.example.demo.bean;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployementDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int empid;
	String description;
	LocalDate date;
	LocalTime time;
	String status;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;

	public EmployementDetails() {
		super();
	}

	public EmployementDetails(int id,int empid, String description, LocalDate date, LocalTime time,String status) {
		super();
		this.id = id;
		this.empid = empid;
		this.description = description;
		this.date = date;
		this.time = time;
		this.status = status;
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmpid() {
		return this.empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployementDetails [id=" + id + ", Empid=" + this.empid + ", description=" + description + ", date=" + date
				+ ", time=" + time + ", status=" + status + ", employee=" + employee + "]";
	}
	
}

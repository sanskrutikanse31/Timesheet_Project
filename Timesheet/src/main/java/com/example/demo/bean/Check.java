package com.example.demo.bean;

public class Check {
	int id;
	String password;
	String type;
	public Check() {
		super();
	}
	public Check(int id, String password,String type) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Check [id=" + id + ", password=" + password + ", type=" + type + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

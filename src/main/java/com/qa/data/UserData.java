package com.qa.data;

public class UserData {
	
	//this is POJO (Plain Old Java Object)
	
	String name;
	String job;
	String id;
	String createdAt;
	
	
	public UserData(){
		
	}
	
	public UserData(String name, String job){
		this.name = name;
		this.job = job;
	}

	//Generate getter and setter >> go to source and create
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}

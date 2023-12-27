package com.despicable.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TeacherDto {

	private Long id;

	private String name;

	private String gender;

	private String department;

	private String qualification;

	private String address;

	private Date dob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "TeacherDto [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", qualification=" + qualification + ", address=" + address + ", dob=" + dob + "]";
	}
	
	

	
}

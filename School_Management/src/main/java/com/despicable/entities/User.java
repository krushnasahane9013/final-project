package com.despicable.entities;

import java.sql.Date;

import com.despicable.dto.StudentDto;
import com.despicable.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserRole role;
	private String fatherName;
	private String motherName;
	private String StudentClass;
	private Date dob;
	private String address;
	private String gender;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getStudentClass() {
		return StudentClass;
	}

	public void setStudentClass(String studentClass) {
		StudentClass = studentClass;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public StudentDto getStudentDto() {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(id);
		studentDto.setName(name);
		studentDto.setEmail(email);
		studentDto.setAddress(address);
		studentDto.setDob(dob);
		studentDto.setStudentClass(StudentClass);
		studentDto.setGender(gender);
		studentDto.setFatherName(fatherName);
		studentDto.setMotherName(motherName);
		return studentDto;
	}

	public void updateFromDto(StudentDto studentDto) {
		
	}

}

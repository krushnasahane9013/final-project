package com.despicable.services.admin;

import java.util.List;

import com.despicable.dto.FeeDto;
import com.despicable.dto.SingleStudentDto;
import com.despicable.dto.SingleTeacherDto;
import com.despicable.dto.StudentDto;
import com.despicable.dto.StudentLeaveDto;
import com.despicable.dto.TeacherDto;

public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();

	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);

	List<StudentLeaveDto> getAllAppliedLeaves();

	StudentLeaveDto changeLeaveStatus(Long leaveId, String status);

	// Teacher Operations

	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);

	SingleTeacherDto getTeacherById(Long teacherId);

	TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);

	

}

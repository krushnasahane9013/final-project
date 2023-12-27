package com.despicable.services.student;

import java.util.List;

import com.despicable.dto.SingleStudentDto;
import com.despicable.dto.StudentDto;
import com.despicable.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long studentId);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

	List<StudentLeaveDto> getAllAppliedLeaveByStudentId(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

}
 
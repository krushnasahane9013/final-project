package com.despicable.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.despicable.dto.SingleStudentDto;
import com.despicable.dto.StudentDto;
import com.despicable.dto.StudentLeaveDto;
import com.despicable.services.student.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId) {
		SingleStudentDto singleStudentDto = studentService.getStudentById(studentId);
		if (singleStudentDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleStudentDto);
	}

	@PostMapping("/leave")

	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto) {
		StudentLeaveDto submittedStudentLeaveDto = studentService.applyLeave(studentLeaveDto);
		if (submittedStudentLeaveDto == null)
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentLeaveDto);
	}
	
	
	@GetMapping("/leave/{studentId}")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaveById(@PathVariable Long studentId) {
		List<StudentLeaveDto> studentLeaveDtos=studentService.getAllAppliedLeaveByStudentId(studentId);
		if (studentLeaveDtos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(studentLeaveDtos);
	}
	
	@PutMapping("{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
		StudentDto createdStudentDto = studentService.updateStudent(studentId, studentDto);
		if (createdStudentDto == null)
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
	}

}

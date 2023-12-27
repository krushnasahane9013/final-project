package com.despicable.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.despicable.dto.SingleStudentDto;
import com.despicable.dto.StudentDto;
import com.despicable.dto.StudentLeaveDto;
import com.despicable.entities.StudentLeave;
import com.despicable.entities.User;
import com.despicable.enums.StudentLeaveStatus;
import com.despicable.repository.StudentLeaveRepository;
import com.despicable.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final UserRepository userRepository;

	public StudentServiceImpl(UserRepository userRepository, StudentLeaveRepository studentLeaveRepository, StudentLeaveRepository studentLeaveRepository2) {
		this.userRepository = userRepository;
		this.studentLeaveRepository = studentLeaveRepository;
	}

	private final StudentLeaveRepository studentLeaveRepository;

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		Optional<User> optionalUser = userRepository.findById(studentId);
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		Optional<User> optionalUser = userRepository.findById(studentLeaveDto.getUserid());
		if (optionalUser.isPresent()) {
			StudentLeave studentLeave = new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setDate(new Date());
			studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave SubmittedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto stLeaveDto = new StudentLeaveDto();
			stLeaveDto.setId(SubmittedStudentLeave.getStudentId());
			return stLeaveDto;

		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeaveByStudentId(Long studentId) {
	    return studentLeaveRepository.findAllByStudentId(studentId)
	            .stream()
	            .map(StudentLeave::getStudentLeaveDto)
	            .collect(Collectors.toList());
	}

	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		Optional<User> optionalUser = userRepository.findById(studentId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(studentDto.getName());
			user.setGender(studentDto.getGender());
			user.setAddress(studentDto.getAddress());
			user.setDob(studentDto.getDob());
			user.setStudentClass(studentDto.getStudentClass());
			user.setFatherName(studentDto.getFatherName());
			user.setMotherName(studentDto.getMotherName());
			user.setEmail(studentDto.getEmail());
			User updatedStudent = userRepository.save(user);
			StudentDto updatedStudentDto = new StudentDto();
			updatedStudentDto.setId(updatedStudent.getId());
			return updatedStudentDto;
		}
		return null;
	}
}

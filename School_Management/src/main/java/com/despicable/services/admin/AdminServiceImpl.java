package com.despicable.services.admin;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.despicable.dto.FeeDto;
import com.despicable.dto.SingleStudentDto;
import com.despicable.dto.SingleTeacherDto;
import com.despicable.dto.StudentDto;
import com.despicable.dto.StudentLeaveDto;
import com.despicable.dto.TeacherDto;
import com.despicable.entities.Fee;
import com.despicable.entities.StudentLeave;
import com.despicable.entities.Teacher;
import com.despicable.entities.User;
import com.despicable.enums.StudentLeaveStatus;
import com.despicable.enums.UserRole;
import com.despicable.repository.FeeRepository;
import com.despicable.repository.StudentLeaveRepository;
import com.despicable.repository.TeacherRepository;
import com.despicable.repository.UserRepository;

import io.jsonwebtoken.lang.Objects;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final UserRepository userRepository;
	private final FeeRepository feeRepository;
	private final StudentLeaveRepository studentLeaveRepository;
	private final TeacherRepository teacherRepository;

	public AdminServiceImpl(UserRepository userRepository, FeeRepository feeRepository,
			StudentLeaveRepository studentLeaveRepository, TeacherRepository teacherRepository) {
		this.userRepository = userRepository;
		this.feeRepository = feeRepository;
		this.studentLeaveRepository = studentLeaveRepository;
		this.teacherRepository = teacherRepository;
	}

	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if (adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
		}
	}

	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findFirstByEmail(studentDto.getEmail());
		if (optionalUser.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepository.save(user);
			StudentDto createdStudentDto = new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
		}
		return null;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		// TODO Auto-generated method stub
		return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		userRepository.deleteById(studentId);

	}

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		Optional<User> optionalUser = userRepository.findById(studentId);
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
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

	@Override
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		Optional<User> optionalStudent = userRepository.findById(studentId);
		if (optionalStudent.isPresent()) {
			Fee fee = new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreatedDate(new Date(System.currentTimeMillis()));
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee = feeRepository.save(fee);
			FeeDto paidfeeDto = new FeeDto();
			paidfeeDto.setId(paidFee.getId());
			return paidfeeDto;
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeaves() {
		return studentLeaveRepository.findAll().stream().map(StudentLeave::getStudentLeaveDto)
				.collect(Collectors.toList());
	}

	@Override
	public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
		Optional<StudentLeave> optionalStudentLeave = studentLeaveRepository.findById(leaveId);

		if (optionalStudentLeave.isPresent()) {
			StudentLeave studentLeave = optionalStudentLeave.get();

			if ("Approve".equalsIgnoreCase(status)) {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);
			} else {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
			}

			StudentLeave updatedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto updatedStudentLeaveDto = new StudentLeaveDto();
			updatedStudentLeaveDto.setId(updatedStudentLeaveDto.getId()); // Fix the typo here
			// Populate other fields in updatedStudentLeaveDto as needed
			return updatedStudentLeaveDto;
		}
		return null;
		// Consider throwing an exception or returning an appropriate response for
		// non-existent leave ID
		// throw new EntityNotFoundException("Leave with ID " + leaveId + " not found");
		// or return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave with ID " +
		// leaveId + " not found");
	}

	// Teacher Operations

	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher = teacherRepository.save(teacher);
		TeacherDto createdTeacherDto = new TeacherDto();
		createdTeacherDto.setId(createdTeacher.getId());
		createdTeacherDto.setName(createdTeacher.getName());
		createdTeacherDto.setDepartment(createdTeacher.getDepartment());
		createdTeacherDto.setQualification(createdTeacher.getQualification());
		createdTeacherDto.setGender(createdTeacher.getGender());
		createdTeacherDto.setAddress(createdTeacher.getAddress());
		return createdTeacherDto;
	}

	@Override
	public List<TeacherDto> getAllTeachers() {
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());

	}

	@Override
	public void deleteTeacher(Long teacherId) {
		teacherRepository.deleteById(teacherId);
	}

	@Override
	public SingleTeacherDto getTeacherById(Long teacherId) {
		SingleTeacherDto singleTeacherDto = new SingleTeacherDto();
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
			return singleTeacherDto;
		}
		return null;
	}

	@Override
	public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			Teacher updateTeacher = optionalTeacher.get();
			updateTeacher.setName(teacherDto.getName());
			updateTeacher.setGender(teacherDto.getGender());
			updateTeacher.setAddress(teacherDto.getAddress());
			updateTeacher.setDob(teacherDto.getDob());
			updateTeacher.setDepartment(teacherDto.getDepartment());
			updateTeacher.setQualification(teacherDto.getQualification());
			Teacher updatedTeacher = teacherRepository.save(updateTeacher);
			TeacherDto updatedTeacherDto = new TeacherDto();
			updatedTeacherDto.setId(updatedTeacher.getId());
			return updatedTeacherDto;

		}
		return null;
	}

}
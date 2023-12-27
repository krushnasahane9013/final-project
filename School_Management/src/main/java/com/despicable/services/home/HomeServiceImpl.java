package com.despicable.services.home;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.despicable.dto.TeacherDto;
import com.despicable.entities.Teacher;
import com.despicable.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl {
	
	private final TeacherRepository teacherRepository;

	public HomeServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	public List<TeacherDto> getAllTeachers(){
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}
	
	

}

package com.despicable.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.despicable.dto.TeacherDto;
import com.despicable.services.home.HomeServiceImpl;

@RequestMapping("/api/teachers/")

public class HomeController {

	private final HomeServiceImpl homeServiceImpl;

	public HomeController(HomeServiceImpl homeServiceImpl) {

		this.homeServiceImpl = homeServiceImpl;
	}

	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers() {
		List<TeacherDto> allTeachers = homeServiceImpl.getAllTeachers();
		return ResponseEntity.ok(allTeachers);
	}

}

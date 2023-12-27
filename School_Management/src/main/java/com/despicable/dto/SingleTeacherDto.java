package com.despicable.dto;

import lombok.Data;

@Data
public class SingleTeacherDto {
	
	private TeacherDto teacherDto;

	public TeacherDto getTeacherDto() {
		return teacherDto;
	}

	public void setTeacherDto(TeacherDto teacherDto) {
		this.teacherDto = teacherDto;
	}
	
	

}

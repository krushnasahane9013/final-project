package com.despicable.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.despicable.entities.StudentLeave;

public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long> {


	List<StudentLeave>findAllByStudentId(Long studentId);

}

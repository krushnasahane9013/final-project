package com.despicable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.despicable.entities.Teacher;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long>{

}

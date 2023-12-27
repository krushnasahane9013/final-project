package com.despicable.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.despicable.dto.StudentDto;
import com.despicable.entities.User;
import com.despicable.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByRole(UserRole userRole);

	Optional<User> findFirstByEmail(String email);

	List<User> findAllByRole(UserRole student);
}

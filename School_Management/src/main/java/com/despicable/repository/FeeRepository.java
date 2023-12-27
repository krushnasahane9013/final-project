package com.despicable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.despicable.entities.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee,Long> {

}

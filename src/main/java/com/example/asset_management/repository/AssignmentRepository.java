package com.example.asset_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.asset_management.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
	

	    @Query(value = "DELETE FROM assignment WHERE assignment_id = :assId", nativeQuery = true)
	void deleteAssignmentById(int assId);

}

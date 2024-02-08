package com.example.asset_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asset_management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public abstract boolean existsByEmployeeEmail(String email);

}

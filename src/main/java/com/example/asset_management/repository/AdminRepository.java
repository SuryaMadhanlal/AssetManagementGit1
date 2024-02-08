package com.example.asset_management.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asset_management.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
    public abstract Admin findByUserNameAndPassword(String username , String password);

}

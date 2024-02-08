package com.example.asset_management.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.asset_management.entity.DeskDetails;

public interface DeskRepository extends JpaRepositoryImplementation<DeskDetails, Integer>{

}

package com.example.asset_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asset_management.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

}

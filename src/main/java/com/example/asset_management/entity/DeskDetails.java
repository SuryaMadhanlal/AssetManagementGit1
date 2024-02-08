package com.example.asset_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deskDetails")
public class DeskDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deskNumber;
	
	@Column(length = 20 )
	private String department;
	
	
	private int lanNumber;
	
	private int switchCount;
	
	private boolean deskAvailability;

}

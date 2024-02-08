package com.example.asset_management.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignment")
public class Assignment {
	
	@Id
	@NotNull(message = "Enter Assignment ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assignmentId;

	
	private LocalDate assignmentEntryDate;

//	@JsonBackReference(value = "assignments")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
	private Employee employee;
//	@JsonBackReference(value = "assignments")
//	@JsonManagedReference
//	@JsonIgnore
//	private Employee employee;
	
//	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "assetId", referencedColumnName = "assetId")
	private Asset asset;

//	@JsonIgnore
//	@OneToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "deskNumber", referencedColumnName = "deskNumber")
//	private DeskDetails deskDetails;

}

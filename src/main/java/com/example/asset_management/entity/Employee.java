package com.example.asset_management.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import com.example.asset_management.entity.enumerated.EEmployeeDesignation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Enter Employee ID")
	private int employeeId;
	
	@NotNull(message = "Enter Employee Name")
	@Column(length = 40 )
	private String employeeName;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 40 )
	private EEmployeeDesignation employeeDesignation;
	
	@Column(length = 40)
	private String employeeDepartment;
	
	@Email(message = "Enter valid Email")
	@Column(unique = true,length = 40)
	private String employeeEmail;
	
	@Pattern(regexp = "^(\\+91|0)?[6789]\\d{9}$",message = "Invalid Phone Number")
//	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be a 10-digit number and should begin with 6-9")
	
	@Column(unique = true,length = 10)
	private String employeeContactNumber;

	

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "employee")
//	@JsonManagedReference(value = "assignments")
//	@JsonBackReference
	@JsonIgnore
	private List<Assignment> assignments;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(referencedColumnName = "deskNumber", name = "deskNumber")
	private DeskDetails deskRef;


}

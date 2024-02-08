package com.example.asset_management.entity;
/**
 * @author suryam 
 * 
 */
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.example.asset_management.entity.enumerated.ECDDrive;
import com.example.asset_management.entity.enumerated.EFloppyDrive;
import com.example.asset_management.entity.enumerated.ELocation;
import com.example.asset_management.entity.enumerated.EMake;
import com.example.asset_management.entity.enumerated.EMonitorMake;
import com.example.asset_management.entity.enumerated.EMonitorWarrantyStatus;
import com.example.asset_management.entity.enumerated.EOS;
import com.example.asset_management.entity.enumerated.EWarrantyStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_inventory")
public class Asset {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Enter Asset Id")
	private int assetId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private ELocation location;
	
	@NotNull(message = "Enter Asset Type")
	@Column(length = 20 )
	private String assetType;
	
	@Column(length = 20 )
	private String cpuSerialNumber;
	
	@Column(length = 20 , unique = true)
	
	private String ipAddress;
	
	@Column(length = 20 )
	private String hostName;
	
	@Column(length = 20 )
	private String macAddress;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EMake make;
	
	@Column(length = 20 )
	private String model;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EOS os;
	
	@Column(length = 20 )
	private String cpuProcessor;
	
	@Column(length = 20 )
	private String processorSpeed;
	
	@Column(length = 20 )
	private String ram;
	
	@Column(length = 20 )
	private String hardDisk;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private ECDDrive cdDrive;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EFloppyDrive floppyDrive;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate cpuWarrantyStartDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate cpuWarrantyEndDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate installationDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EWarrantyStatus warrantyStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EMonitorMake monitorMake;
	
	@Column(length = 20 )
	private String monitorTypeAndModel;
	
	@Column(length = 20 )	
	private String monitorSerialNumber;

	@Column(length = 20 )
	private String monitorSize;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate monitorWarrantyStartDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate monitorWarrantyEndDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20 )
	private EMonitorWarrantyStatus monitorWarrantyStatus;
	
	private boolean assetAvailability;


}

package com.example.asset_management.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
	private Date timeStamp;
	private String message;
	private String details;
}

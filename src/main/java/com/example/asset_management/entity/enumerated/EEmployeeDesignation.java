package com.example.asset_management.entity.enumerated;

public enum EEmployeeDesignation {

	SOFTWAREENGINEER("SOFTWARE ENGINEER"), SOFTWAREENGINEERTRAINEE("SOFTWARE ENGINEER TRAINEE"),
	SOFTWAREARCHITECT("SOFTWARE ARCHITECT");

	private String description;

	EEmployeeDesignation(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

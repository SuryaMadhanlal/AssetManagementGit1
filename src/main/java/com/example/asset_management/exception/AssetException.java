package com.example.asset_management.exception;


public class AssetException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3227276263327925028L;
	private String errorMessage;

	public AssetException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		
		return this.errorMessage;
	}
	

}

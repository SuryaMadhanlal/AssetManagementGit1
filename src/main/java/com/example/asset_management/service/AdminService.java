package com.example.asset_management.service;

import java.util.List;

import com.example.asset_management.entity.Admin;
import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.Assignment;
import com.example.asset_management.entity.DeskDetails;
import com.example.asset_management.entity.Employee;
import com.example.asset_management.exception.AssetException;

public interface AdminService {
	//Asset 
	
	public Admin loginCustomer(String userName, String password) throws AssetException;
	
	public Asset createAsset(Asset asset) throws AssetException;
	
	public Asset readAssetById(int assetId) throws AssetException;
	
	public List<Asset> getAvailableAssets() throws AssetException;
	
	public List<Asset> readAllAssets() throws AssetException;
	

	public Asset updateAsset(int assetID, Asset updatedAsset) throws AssetException;

	public String deleteAsset(int assetID) throws AssetException;
	
	
	
	//Employee
	public Employee readEmployeeByIdFromAdmin(int id) throws AssetException;
	
	public Employee createEmployee(Employee employee) throws AssetException;

	public Employee readEmployeeById(int employeeId) throws AssetException;
	
	public List<Employee> readAllEmployees() throws AssetException;

	public Employee updateEmployeeById(int employeeId, Employee employee) throws AssetException;

	public String deleteEmployeeById(int employeeID) throws AssetException;

	
	//Assignment

	public Assignment createAssignment(int assetId,int employeeId,int deskNumber);
	
	public Assignment createAssignmentWithoutDesk(int assetId,int employeeId);
	public List<Assignment> readAllAssignments() throws AssetException;
	
	public Assignment readAssignmentById(int assignmentId) throws AssetException;


	public Assignment updateAssignment(int assignmentId,int assetId, int employeeId) throws AssetException;

	public String deleteAssignment(int assignmentId) throws AssetException;
	
	//Desk
	
	public DeskDetails createDesk(DeskDetails deskDetails) throws AssetException;
	
	public List<DeskDetails> readAllDeskDetails() throws AssetException;
	
	public DeskDetails readByDeskId(int deskNumber) throws AssetException;
	
	public DeskDetails updateDesk(int deskNumber,DeskDetails deskDetails) throws AssetException;
	
	public String deleteDesk(int deskNumber)throws AssetException;
	
	public List<DeskDetails> getAvailableDesks() throws AssetException;
	
}

package com.example.asset_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.asset_management.entity.Admin;
import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.Assignment;
import com.example.asset_management.entity.DeskDetails;
import com.example.asset_management.entity.Employee;
import com.example.asset_management.service.AdminService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(value = "createAsset/")
	public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
		Asset receiveAsset = adminService.createAsset(asset);
		return  new ResponseEntity<Asset>(receiveAsset, HttpStatus.CREATED) ;
	}
	
	@GetMapping(value = "login/{userName}/{password}")
	public  ResponseEntity<Admin>  loginUser(@PathVariable String userName , @PathVariable String password ) {
		Admin admin = adminService.loginCustomer(userName, password);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK) ;

	}

	@GetMapping(value = "getAllAsset")
	public  ResponseEntity <List<Asset>>  readAllAssets() {
		List<Asset> assets = adminService.readAllAssets();
		return new ResponseEntity<List<Asset>>(assets, HttpStatus.OK) ;

	}

	@GetMapping(value = "getAssetById/{assetId}")
	public ResponseEntity<Asset> readAssetByID(@PathVariable int assetId) {
		Asset returnAsset = adminService.readAssetById(assetId);
		return new ResponseEntity<Asset>(returnAsset, HttpStatus.OK);

	}

	@PutMapping(value = "updateAssetById/{assetId}")
	public ResponseEntity<Asset> updateAssetByID(@PathVariable int assetId, @RequestBody Asset asset) {
		Asset returnAsset = adminService.updateAsset(assetId, asset);
		return  new ResponseEntity<Asset>(returnAsset, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteAssetById/{assetId}")
	public ResponseEntity<String> deleteAsset(@PathVariable int assetId) {
		return  new ResponseEntity<String>( adminService.deleteAsset(assetId), HttpStatus.OK);
		 
	}
	
	
	
	//Desk 
	
	@PostMapping(value = "createDesk/")
	public ResponseEntity<DeskDetails> createDesk(@RequestBody DeskDetails deskDetails) {
		 DeskDetails returnDesk =    adminService.createDesk(deskDetails);
		 return  new ResponseEntity<DeskDetails>(returnDesk, HttpStatus.CREATED);
	}
	

	
	@GetMapping(value = "getAllDesk")
	public ResponseEntity <List<DeskDetails>> readAllDeskDetails() {
		List<DeskDetails> deskDetails = adminService.readAllDeskDetails();
		return new ResponseEntity<List<DeskDetails>>(deskDetails, HttpStatus.OK) ;

	}
	@GetMapping(value = "readByDeskId/{deskNumber}")
	public ResponseEntity<DeskDetails> readDeskById(@PathVariable int deskNumber) {
		DeskDetails returnDeskById = adminService.readByDeskId(deskNumber);
		return new ResponseEntity<DeskDetails>(returnDeskById, HttpStatus.OK);
	}
	
	@GetMapping(value = "getAvailableDesks")
	public ResponseEntity<List<DeskDetails>> getAvailableDesks() {
		List<DeskDetails> deskDetails = adminService.getAvailableDesks();
		return new ResponseEntity<List<DeskDetails>>(deskDetails, HttpStatus.OK) ;

	}
	
	@GetMapping(value = "getAvailableAssets")
	public ResponseEntity<List<Asset>> getAvailableAssets() {
		List<Asset> asset = adminService.getAvailableAssets();
		return new ResponseEntity<List<Asset>>(asset, HttpStatus.OK) ;

	}
	
	
	
	@PutMapping(value = "updateDeskById/{deskNumber}")
	public ResponseEntity<DeskDetails> updateDeskByID( @PathVariable int deskNumber,  @RequestBody DeskDetails  deskDetails) {
		DeskDetails returnDesk = adminService.updateDesk(deskNumber,deskDetails);
		return new ResponseEntity<DeskDetails>(returnDesk, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteDeskById/{deskNumber}")
	public ResponseEntity<String> deleteDesk(@PathVariable int deskNumber) {
		return new ResponseEntity<String>( adminService.deleteDesk(deskNumber), HttpStatus.OK);
	}
	
	@PostMapping(value = "createAssignment/{assetId}/{employeeId}/{deskNumber}")
	public ResponseEntity<Assignment>  createAssignment( @PathVariable int assetId, @PathVariable int employeeId, @PathVariable int deskNumber) {
	Assignment createdAssignment = 	adminService.createAssignment(assetId,employeeId,deskNumber);
	return new ResponseEntity<Assignment>(createdAssignment, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "createAssignmentWithoutDesk/{assetId}/{employeeId}")
	public ResponseEntity<Assignment>  createAssignment( @PathVariable int assetId, @PathVariable int employeeId) {
	Assignment createdAssignment = 	adminService.createAssignmentWithoutDesk(assetId,employeeId);
	return new ResponseEntity<Assignment>(createdAssignment, HttpStatus.CREATED);
	}
	

	@GetMapping(value = "getAllAssignment")
	public  ResponseEntity <List<Assignment>>  readAllAssignments() {
		List<Assignment> assignment = adminService.readAllAssignments();
		return new ResponseEntity<List<Assignment>>(assignment, HttpStatus.OK) ;
	}
	
	@GetMapping(value="getAssignmentById/{assignmentId}")
	public ResponseEntity<Assignment> readAssignmentById(@PathVariable int assignmentId){
		Assignment assignment = adminService.readAssignmentById(assignmentId);
		return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "updateAssignmentById/{assignmentId}/{assetId}/{employeeId}")
	public ResponseEntity<Assignment> updateAssignmentById( @PathVariable int assignmentId, @PathVariable int  assetId,@PathVariable int employeeId) {
		Assignment returnAssignment = adminService.updateAssignment(assignmentId, assetId,employeeId);
		return new ResponseEntity<Assignment>(returnAssignment, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteAssignmentById/{assignmentId}")
	public ResponseEntity<String> deleteAssignmentById(@PathVariable int assignmentId) {
		return new ResponseEntity<>( adminService.deleteAssignment(assignmentId),HttpStatus.OK);
	}
	@PostMapping(value = "createEmployee/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>( adminService.createEmployee(employee),HttpStatus.CREATED);
	}

	@GetMapping(value = "readEmployeeById/{employeeId}")
	public ResponseEntity<Employee> readEmployeeById(@PathVariable int employeeId) {
		Employee readEmplById = adminService.readEmployeeById(employeeId);
		return new ResponseEntity<Employee>(readEmplById,HttpStatus.OK);

	}
	
	@GetMapping(value = "getAllEmployees")
	public ResponseEntity <List<Employee>> readAllEmployees() {
		List<Employee> readAllEmployees = adminService.readAllEmployees();
		return new ResponseEntity<List<Employee>>(readAllEmployees, HttpStatus.OK) ;

	}
	
	@PutMapping(value = "updateEmployeeById/{employeeId}")
	public  ResponseEntity<Employee> updateEmployeeByID(@PathVariable int employeeId, @RequestBody Employee employee) {
		Employee returnEmployee = adminService.updateEmployeeById(employeeId, employee);
		return  new ResponseEntity<Employee>(returnEmployee,HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteEmployeeById/{employeeId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int employeeId) {
		return  new ResponseEntity<String>( adminService.deleteEmployeeById(employeeId),HttpStatus.OK);
	}
}

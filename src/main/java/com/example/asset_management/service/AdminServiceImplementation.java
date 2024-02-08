package com.example.asset_management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asset_management.entity.Admin;
import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.Assignment;
import com.example.asset_management.entity.DeskDetails;
import com.example.asset_management.entity.Employee;
import com.example.asset_management.exception.AssetException;
import com.example.asset_management.repository.AdminRepository;
import com.example.asset_management.repository.AssetRepository;
import com.example.asset_management.repository.AssignmentRepository;
import com.example.asset_management.repository.DeskRepository;
import com.example.asset_management.repository.EmployeeRepository;

@Service
public class AdminServiceImplementation implements AdminService {
	@Autowired
	public AssetRepository assetRepository;
	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public AdminRepository adminRepository;

	@Autowired
	public AssignmentRepository assignmentRepository;
	@Autowired
	public DeskRepository deskRepository;

	@Override
	public Asset createAsset(Asset asset) throws AssetException {
//		if (asset.getAssetId() <= 0) {
//			throw new AssetException("Asset ID must be Greater than zero");
//		}
		Optional<Asset> existingAsset = assetRepository.findById(asset.getAssetId());
		Asset returnAsset = null;
		if (existingAsset.isPresent()) {
			// An asset with the same ID already exists, handle the duplication error
			throw new AssetException("Asset with ID " + asset.getAssetId() + " already exists.");
		} else {
			// Save the new asset
			asset.setAssetAvailability(true);
			returnAsset = assetRepository.save(asset);
			
		}
		return returnAsset;
	}

	@Override
	public Asset readAssetById(int assetId) throws AssetException {
		Optional<Asset> existingAsset = assetRepository.findById(assetId);
		Asset returnAsset = null;
		if (existingAsset.isPresent()) {
			returnAsset = existingAsset.get();
		} else {
			throw new AssetException("Asset with ID " + assetId + " doesn't exist");
					}
		return returnAsset;
	}

	@Override
	public List<Asset> readAllAssets() {
		List<Asset> assets = null;
		long assetCount = assetRepository.count();
		if (assetCount == 0) {
			throw new AssetException("There is no record in this table");
		} else {
			assets = assetRepository.findAll();
		}
		return assets;
	}

	@Override
	public Asset updateAsset(int assetId, Asset updatedAsset) {
		if (updatedAsset.getAssetId() <= 0) {
			throw new AssetException("Asset ID must be Greater than zero");
		}
		Optional<Asset> existingAssetOptional = assetRepository.findById(assetId);

		if (existingAssetOptional.isPresent()) {
			Asset existingAsset = existingAssetOptional.get();

			// Update specific fields if they are not null in the updatedAsset
			if (updatedAsset != null) {
				if (updatedAsset.getAssetType() != null) {
					existingAsset.setAssetType(updatedAsset.getAssetType());
				}
				if (!updatedAsset.getIpAddress().equals("string")) {
					existingAsset.setIpAddress(updatedAsset.getIpAddress());
				}

				return assetRepository.save(updatedAsset);
			} else {
				throw new AssetException("The updated Asset is null");
			}
		} else {
			throw new AssetException("Asset with ID " + assetId + " not found");
		}
	}

	@Override
	public String deleteAsset(int assetID) {
		Optional<Asset> existingAssetOptional = assetRepository.findById(assetID);
		if (existingAssetOptional.isPresent()) {
			assetRepository.deleteById(assetID);
			return "Deleted Successfully";
		} else {
			throw new AssetException("Asset Not Found");
		}
	}

	@Override
	public Assignment createAssignment(int assetId, int employeeId, int deskNumber) {
//		if (assignmentId > 0) {
			Optional<Asset> optionalAsset = assetRepository.findById(assetId);
			Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
			Optional<DeskDetails> optionalDesk = deskRepository.findById(deskNumber);
			if (!optionalAsset.isPresent() || !optionalEmployee.isPresent() || !optionalDesk.isPresent()) {
				throw new AssetException("Invalid data");
			}
			Asset asset = optionalAsset.get();
			Employee employee = optionalEmployee.get();
			DeskDetails desk = optionalDesk.get();
//			 if(optionalEmployee.get().getDeskRef()!=null){
//				 optionalEmployee.get().getDeskRef().setDeskAvailability(true);
//			 }
			if (!desk.isDeskAvailability()) {
				throw new AssetException("Desk Not available");
			}

			if (!asset.isAssetAvailability()) {
				throw new AssetException("Asset Not available");
			}

			Assignment assignment = new Assignment();

//			assignment.setDeskDetails(desk);
			desk.setDeskAvailability(false);

			assignment.setAsset(asset);
			asset.setAssetAvailability(false);

			assignment.setEmployee(employee);
			LocalDate today = LocalDate.now();
			assignment.setAssignmentEntryDate(today);
//			assignment.setAssignmentId(assignmentId);

			employee.setDeskRef(desk);

			employeeRepository.save(employee);
			assetRepository.save(asset);
			deskRepository.save(desk);

			assignmentRepository.save(assignment);

			return assignment;
		}
//		} else {
//			throw new AssetException("Assignment ID Should be Greater than zero");
//		}
	

	@Override
	public Assignment createAssignmentWithoutDesk(int assetId, int employeeId) {
//		if (assignmentId > 0) {
			Optional<Asset> optionalAsset = assetRepository.findById(assetId);
			Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

			Asset asset = optionalAsset.get();
			Employee employee = optionalEmployee.get();
			
			
//			 if(optionalEmployee.get().getDeskRef()!=null){
//				 optionalEmployee.get().getDeskRef().setDeskAvailability(true);
//			 }

			if (!asset.isAssetAvailability()) {
				throw new AssetException("Asset Not available");
			}

			Assignment assignment = new Assignment();
			assignment.setAsset(asset);
			asset.setAssetAvailability(false);
			assignment.setEmployee(employee);
			LocalDate today = LocalDate.now();
			assignment.setAssignmentEntryDate(today);
//			assignment.setAssignmentId(assignmentId);
			employeeRepository.save(employee);
			assetRepository.save(asset);
			assignmentRepository.save(assignment);

			return assignment;
		} 
//	else {
//		throw new AssetException("Assignment ID Should be Greater than zero");
//	}
//}

	@Override
	public List<Assignment> readAllAssignments() {
		List<Assignment> assignment = new ArrayList<Assignment>();
		long assignmentCount = assignmentRepository.count();
		if (assignmentCount != 0) {
			assignment = assignmentRepository.findAll();
			return assignment;
		} else {
			throw new AssetException("There is no record in this table");
		}
	}

	public Assignment updateAssignment(int assignmentId, int assetId, int employeeId) {
		Optional<Assignment> existingAssignmentOptional = assignmentRepository.findById(assignmentId);
		Optional<Asset> existingAsset = assetRepository.findById(assetId);
//		Optional<DeskDetails> existingDeskDetails = deskRepository.findById(deskNumber);
		Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
		if (existingAssignmentOptional.isPresent() && existingAsset.isPresent() 
				&& existingEmployee.isPresent()) {

//			if (existingDeskDetails.get().isDeskAvailability() && existingAsset.get().isAssetAvailability())
//			if (!existingDeskDetails.get().isDeskAvailability()
////					&& !(existingDeskDetails.get() == existingAssignmentOptional.get().getDeskDetails())
//			) {
//				throw new AssetException("Desk Not available");
//			}
			if (!existingAsset.get().isAssetAvailability()
					&& !(existingAsset.get() == existingAssignmentOptional.get().getAsset())) {
				throw new AssetException("Asset Not available");
			}
			int oldAssetId = existingAssignmentOptional.get().getAsset().getAssetId();
			assetRepository.findById(assetId).get().setAssetAvailability(false);
			existingAssignmentOptional.get().setAsset(assetRepository.findById(assetId).get());// assert set
			int j = existingEmployee.get().getDeskRef().getDeskNumber();
			Optional<DeskDetails> deskDetails1 = deskRepository.findById(j);
			DeskDetails deskDetails2 = deskDetails1.get();
			deskDetails2.setDeskAvailability(true);
			assetRepository.findById(oldAssetId).get().setAssetAvailability(true);
//			existingEmployee.get().setDeskRef((deskRepository.findById(deskNumber).get()));
			DeskDetails details = existingEmployee.get().getDeskRef();
			details.setDeskAvailability(false);
			
			existingAssignmentOptional.get().setEmployee(employeeRepository.findById(employeeId).get());
			assignmentRepository.save(existingAssignmentOptional.get());
			return existingAssignmentOptional.get();
		} else {
			throw new AssetException("Invalid credentials");
		}
	}

	@Override
	public String deleteAssignment(int assignmentId) {
		Assignment existingAssignmentOptional = assignmentRepository.findById(assignmentId).get();
		if (existingAssignmentOptional != null) {

			existingAssignmentOptional.getAsset().setAssetAvailability(true);
			assignmentRepository.deleteById(assignmentId);

			return " Assignment Deleted Successfully";
		} else {
			throw new AssetException("Assignment Not Found");
		}
	}

	@Override
	public Employee readEmployeeByIdFromAdmin(int id) throws AssetException {
		Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
		if (existingEmployeeOptional.isPresent()) {
			return existingEmployeeOptional.get();
		} else {
			throw new AssetException("Employee not found");
		}
	}

	@Override
	public DeskDetails createDesk(DeskDetails deskDetails) throws AssetException {
//		if (deskDetails.getDeskNumber() <= 0) {
//			throw new AssetException("Desk Number must be Greater than zero");
//		}
		Optional<DeskDetails> existingDesk = deskRepository.findById(deskDetails.getDeskNumber());
		DeskDetails returnDesk = null;
		if (existingDesk.isPresent()) {
			// An Desk with the same ID already exists, handle the duplication error here
			throw new AssetException("Desk with ID " + deskDetails.getDeskNumber() + " already exists.");
		} else {
			// Save the new asset
			deskDetails.setDeskAvailability(true);
			returnDesk = deskRepository.save(deskDetails);
		}
		return returnDesk;
	}

	@Override
	public List<DeskDetails> readAllDeskDetails() throws AssetException {
		List<DeskDetails> desks = null;
		long deskCount = deskRepository.count();
		if (deskCount == 0) {
			throw new AssetException("There is no record in this table");
		} else {
			desks = deskRepository.findAll();
		}
		return desks;
	}

	@Override
	public List<DeskDetails> getAvailableDesks() throws AssetException {
		try {
			List<DeskDetails> allDesks = deskRepository.findAll();
			List<DeskDetails> availableDesks = allDesks.stream().filter(DeskDetails::isDeskAvailability)
					.collect(Collectors.toList());

			return availableDesks;
		} catch (Exception e) {
			// Handle exceptions or rethrow as AssetException if necessary
			throw new AssetException("Error fetching available desks");
		}
	}

	@Override
	public DeskDetails readByDeskId(int deskNumber) throws AssetException {
		Optional<DeskDetails> existingDesk = deskRepository.findById(deskNumber);
		DeskDetails returnDesk = null;
		if (existingDesk.isPresent()) {
			returnDesk = existingDesk.get();
		} else {
			throw new AssetException("Desk  with ID " + deskNumber + " doesn't exist");
		}
		return returnDesk;
	}

	@Override
	public DeskDetails updateDesk(int deskNumber, DeskDetails deskDetails) throws AssetException {

		Optional<DeskDetails> existingDesk = deskRepository.findById(deskNumber);

		if (existingDesk.isPresent()) {
			DeskDetails existingDesk1 = existingDesk.get();
			if (existingDesk1 != null) {
				if (existingDesk1.getLanNumber() != 0) {
					existingDesk1.setLanNumber(deskDetails.getLanNumber());
				}
				if (existingDesk1.getSwitchCount() != 0) {
					existingDesk1.setSwitchCount(deskDetails.getSwitchCount());
				}
				if (!existingDesk1.getDepartment().equals("string")) {
					existingDesk1.setDepartment(deskDetails.getDepartment());
				}
			}
			existingDesk1.setDeskNumber(deskNumber);
			return deskRepository.save(existingDesk1);
		} else {
			throw new AssetException("Desk with desk number " + deskNumber + "not found");
		}
	}

	@Override
	public String deleteDesk(int deskNumber) throws AssetException {
		Optional<DeskDetails> existingDeskOptional = deskRepository.findById(deskNumber);
		if (existingDeskOptional.isPresent()) {
			deskRepository.deleteById(deskNumber);
			return "Deleted Successfully";
		} else {
			throw new AssetException("Desk Not Found");
		}
	}

	@Override
	public Assignment readAssignmentById(int assignmentId) throws AssetException {
		Optional<Assignment> existingAssignment = assignmentRepository.findById(assignmentId);
		if (existingAssignment.isPresent()) {
			return existingAssignment.get();
		} else {
			throw new AssetException("Asset with ID " + assignmentId + " doesn't exist");
		}
	}

	@Override
	public Employee createEmployee(Employee employee) throws AssetException {
//		if (employee.getEmployeeId() <= 0) {
//			throw new AssetException("Employee ID must be Greater than zero");
//		}
		Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee.getEmployeeId());
		if (existingEmployeeOptional.isPresent()) {
			throw new AssetException("Employee with " + employee.getEmployeeId() + "already exist");
		}
	
		else {
			Optional<DeskDetails> optioanlDesk = deskRepository.findById(employee.getDeskRef().getDeskNumber());
			if (!optioanlDesk.isPresent()) {
				throw new AssetException("Desk not Available");
			}
			optioanlDesk.get().setDeskAvailability(false);
			employee.setDeskRef(optioanlDesk.get());

			return employeeRepository.save(employee);

		}
	}

	@Override
	public Employee readEmployeeById(int employeeId) throws AssetException {
		Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
		Employee returnEmployee = null;
		if (existingEmployee.isPresent()) {
			returnEmployee = existingEmployee.get();
		} else {
			throw new AssetException("Employee with ID " + employeeId + " doesn't exist");
		}
		return returnEmployee;
	}

	@Override
	public Employee updateEmployeeById(int employeeId, Employee updatedEmployee) {
		Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employeeId);

		if (existingEmployeeOptional.isPresent()) {
			Employee existingEmployee = existingEmployeeOptional.get();
			if (!existingEmployee.getEmployeeDepartment().equals("string")) {
				existingEmployee.setEmployeeDepartment(updatedEmployee.getEmployeeDepartment());
			}
			if (!existingEmployee.getEmployeeDesignation().equals("string")) {
				existingEmployee.setEmployeeDesignation(updatedEmployee.getEmployeeDesignation());
			}
			if (existingEmployee.getEmployeeContactNumber() != null) {
				existingEmployee.setEmployeeContactNumber(updatedEmployee.getEmployeeContactNumber());
			}
			if (!existingEmployee.getEmployeeEmail().equals("string")) {
				existingEmployee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
			}
			if (!existingEmployee.getEmployeeName().equalsIgnoreCase("string")) {
				existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
			}
			
			
			DeskDetails desk= deskRepository.findById(updatedEmployee.getDeskRef().getDeskNumber()).get();
			if(updatedEmployee.getDeskRef().getDeskNumber()!=existingEmployee.getDeskRef().getDeskNumber()) {
				existingEmployee.getDeskRef().setDeskAvailability(true);
				desk.setDeskAvailability(false);			
				
			}
			
			updatedEmployee.setDeskRef(desk);
			
			
			return employeeRepository.save(updatedEmployee);
		} else {
			throw new AssetException("Employee Not Found");

		}

	}

	@Override
	public String deleteEmployeeById(int employeeId) {
		Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employeeId);
		if (existingEmployeeOptional.isPresent()) {
			

			if(!(existingEmployeeOptional.get().getAssignments().size()>0)) {
					
					
					existingEmployeeOptional.get().getDeskRef().setDeskAvailability(true);
			employeeRepository.deleteById(employeeId);

			return "Deleted Successfully";
			}
			else {
				
				throw new AssetException("Employee is Already Assigned");
			}
		} else {
			throw new AssetException("Employee Not Found");
		}
	}

	@Override
	public List<Employee> readAllEmployees() throws AssetException {
		List<Employee> employees = null;
		long employeeCount = employeeRepository.count();
		if (employeeCount == 0) {
			throw new AssetException("There is no record in this table");
		} else {
			employees = employeeRepository.findAll();
		}
		return employees;

	}

	@Override
	public List<Asset> getAvailableAssets() throws AssetException {
		try {
			List<Asset> allAssets = assetRepository.findAll();
			List<Asset> availableAssets = allAssets.stream().filter(Asset::isAssetAvailability) // Assuming
																								// isDeskAvailability is
																								// the getter for
																								// deskAvailability
					.collect(Collectors.toList());

			return availableAssets;
		} catch (Exception e) {
			// Handle exceptions or rethrow as AssetException if necessary
			throw new AssetException("Error fetching available desks");
		}
	}

	@Override
	public Admin loginCustomer(String userName, String password) throws AssetException {

		Admin admin = adminRepository.findByUserNameAndPassword(userName, password);
//		Admin dtoModel = null;
		List<Admin> admins = adminRepository.findAll();

		boolean isDuplicate = admins.stream().anyMatch(
				customer1 -> customer1.getUserName().equals(userName) && customer1.getPassword().equals(password));

		if (!isDuplicate) {
			throw new AssetException("Invalid Username or Password ");
		}

//		dtoModel = mapper.map(customers, CustomerDto.class);
		return admin;
	}
}

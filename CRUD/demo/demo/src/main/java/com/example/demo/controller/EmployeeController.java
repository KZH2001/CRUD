package com.example.demo.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;


@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	
@Autowired
private EmployeeRepository employeeRepository;

//get all employee
@GetMapping("/employees")
public List<Employee> getAllEmployees(){
	return employeeRepository.findAll();
}

//create employee
@PostMapping("/employees")
public Employee createEmployee(@RequestBody Employee employee) {
	return employeeRepository.save(employee);
}

//get employee by id 
@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	Employee employee = employeeRepository.findById(id)
			.orElseThrow(() ->  new ResourceNotFoundException("Employee not exist id :" + id));
	return ResponseEntity.ok(employee);
}

//update employee id
@PutMapping("/employees/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
	Employee employee = employeeRepository.findById(id)
			.orElseThrow(() ->  new ResourceNotFoundException("Employee not exist id :" + id));
	
	employee.setFirstName(employeeDetails.getFirstName());
	employee.setLastName(employeeDetails.getLastName());
	employee.setEmailId(employeeDetails.getEmailId());
	
	Employee updatedEmployee = employeeRepository.save(employee);
	return ResponseEntity.ok(updatedEmployee);

}

//delete employee id
@DeleteMapping("/employees/{id}")
public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
	Employee employee = employeeRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist id :" + id));
	
	employeeRepository.delete(employee);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
	}


}

package org.fox.springdemo.rest;

import org.fox.springdemo.entity.Employee;
import org.fox.springdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		List<Employee> employeeList = employeeService.getEmployees();

		if(employeeList.isEmpty()){
			throw new EmployeeNotFoundException("No employees could be found");
		}

		return employeeList;
 	}

	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id)
	{
		Employee employee = employeeService.getEmployee(id);

		if(employee == null){
			throw new EmployeeNotFoundException("Employee id not found - " + id);
		}

		return employee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {

		//force save of new item, instead of update
		employee.setId(0);
		employeeService.saveEmployee(employee);
		return employee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		employeeService.saveEmployee(employee);
    	return employee;
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		Employee employee = employeeService.getEmployee(id);

		if(employee == null){
			throw new EmployeeNotFoundException("Employee id not found - " + id);
		}

		employeeService.deleteEmployee(id);
		return "Deleted employee id - " + id;
	}

}
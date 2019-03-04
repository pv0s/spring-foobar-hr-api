package org.fox.springdemo.service;

import org.fox.springdemo.dao.EmployeeDAO;
import org.fox.springdemo.entity.Employee;
import org.fox.springdemo.rest.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Transactional
    @Override
    public List<Employee> getEmployees() {

        List<Employee> employeeList = employeeDAO.getEmployees();

        if (employeeList.isEmpty()) {
            throw new EmployeeNotFoundException("No employees could be found");
        }
        return employeeDAO.getEmployees();
    }

    @Transactional
    @Override
    public Employee getEmployee(int id) {

        Employee employee = employeeDAO.getEmployee(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + id);
        }

        return employee;
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {

        getEmployee(id);
        employeeDAO.deleteEmployee(id);
    }

}
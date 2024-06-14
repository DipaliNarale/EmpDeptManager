package com.example.employeedepartmentmanager.service;

import com.example.employeedepartmentmanager.dto.EmployeeDTO;
import com.example.employeedepartmentmanager.entity.Employee;
import com.example.employeedepartmentmanager.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
        return convertToDto(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        validateEmployeeDTO(employeeDTO); // Validate DTO before creating

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee); // Copy DTO properties to entity
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        validateEmployeeDTO(employeeDTO); // Validate DTO before updating

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        // Update entity with DTO properties
        BeanUtils.copyProperties(employeeDTO, employee, "id"); // Exclude copying 'id'
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDto(updatedEmployee);
    }

    private EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (employeeDTO.getSalary() <= 0) {
            throw new IllegalArgumentException("Employee salary must be greater than 0");
        }
        if (employeeDTO.getName() == null || employeeDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }
    }
}
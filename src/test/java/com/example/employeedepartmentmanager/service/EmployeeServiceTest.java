package com.example.employeedepartmentmanager.service;

import com.example.employeedepartmentmanager.dto.EmployeeDTO;
import com.example.employeedepartmentmanager.entity.Employee;
import com.example.employeedepartmentmanager.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeDTO employeeDTO1;
    private EmployeeDTO employeeDTO2;

    @BeforeEach
    void setUp() {
        employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        employeeDTO1.setName("John Doe");
        employeeDTO1.setSalary(50000.0);

        employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setId(2L);
        employeeDTO2.setName("Jane Smith");
        employeeDTO2.setSalary(0.0);
    }

    @Test
    void testGetAllEmployees() {
        // Mock repository behavior
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(), new Employee()));

        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());  // Assert size of returned list
    }

    @Test
    void testGetEmployeeById() {
        // Mock repository behavior
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(1L);
        assertNotNull(employeeDTO);
    }

    @Test
    void testCreateEmployee() {
        // Mock repository behavior
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

        EmployeeDTO savedEmployeeDTO = employeeService.createEmployee(employeeDTO1);
        assertNotNull(savedEmployeeDTO);
    }

    @Test
    void testUpdateEmployee() {
        // Mock repository behavior
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(1L, employeeDTO1);
        assertNotNull(updatedEmployeeDTO);
    }
}
package com.example.employeedepartmentmanager.service;

import com.example.employeedepartmentmanager.entity.Department;
import com.example.employeedepartmentmanager.entity.Employee;
import com.example.employeedepartmentmanager.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateTotalSalaryByDepartmentId() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("John");
        emp1.setSalary(30000.0);

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Jane");
        emp2.setSalary(25000.0);

        department.setEmployees(Arrays.asList(emp1, emp2));

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        double totalSalary = departmentService.calculateTotalSalaryByDepartmentId(1L);
        assertEquals(55000.0, totalSalary);
    }

    @Test
    public void testEvaluateDepartmentPerformance() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("John");
        emp1.setSalary(30000.0);

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Jane");
        emp2.setSalary(25000.0);

        department.setEmployees(Arrays.asList(emp1, emp2));

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        String performance = departmentService.evaluateDepartmentPerformance(1L);
        assertEquals("High Performing", performance);
    }
}
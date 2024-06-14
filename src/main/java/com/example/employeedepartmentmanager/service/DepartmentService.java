package com.example.employeedepartmentmanager.service;

import com.example.employeedepartmentmanager.entity.Department;
import com.example.employeedepartmentmanager.entity.Employee;
import com.example.employeedepartmentmanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.map(Department::getEmployees).orElse(null);
    }

    public double calculateTotalSalaryByDepartmentId(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            List<Employee> employees = department.get().getEmployees();
            if (employees != null && !employees.isEmpty()) {
                return employees.stream().mapToDouble(Employee::getSalary).sum();
            }
        }
        return 0.0;
    }

    public String evaluateDepartmentPerformance(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            double totalSalary = calculateTotalSalaryByDepartmentId(departmentId);
            if (totalSalary > 50000) {
                return "High Performing";
            } else if (totalSalary > 20000) {
                return "Average Performing";
            } else {
                return "Low Performing";
            }
        }
        return "Department Not Found";
    }
}
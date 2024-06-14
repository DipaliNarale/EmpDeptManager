package com.example.employeedepartmentmanager.controller;

import com.example.employeedepartmentmanager.entity.Department;
import com.example.employeedepartmentmanager.entity.Employee;
import com.example.employeedepartmentmanager.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable Long id) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentId(id);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/total-salary")
    public ResponseEntity<Double> calculateTotalSalaryByDepartmentId(@PathVariable Long id) {
        double totalSalary = departmentService.calculateTotalSalaryByDepartmentId(id);
        return ResponseEntity.ok(totalSalary);
    }

    @GetMapping("/{id}/performance")
    public ResponseEntity<String> evaluateDepartmentPerformance(@PathVariable Long id) {
        String performance = departmentService.evaluateDepartmentPerformance(id);
        return ResponseEntity.ok(performance);
    }
}
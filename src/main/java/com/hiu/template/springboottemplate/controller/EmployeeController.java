package com.hiu.template.springboottemplate.controller;


import com.hiu.template.springboottemplate.dto.EmployeeDto;
import com.hiu.template.springboottemplate.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Employee CRUD operations")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Create a new employee", description = "Creates a new employee with the provided information")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto created = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get an employee by ID", description = "Retrieves an employee by its unique identifier")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Update an employee", description = "Updates the information of an existing employee")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updated = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete an employee", description = "Deletes an employee by its unique identifier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

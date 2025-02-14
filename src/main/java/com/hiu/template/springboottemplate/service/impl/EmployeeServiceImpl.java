package com.hiu.template.springboottemplate.service.impl;


import com.hiu.template.springboottemplate.dto.EmployeeDto;
import com.hiu.template.springboottemplate.mapper.EmployeeMapper;
import com.hiu.template.springboottemplate.entity.Employee;
import com.hiu.template.springboottemplate.repository.EmployeeRepository;
import com.hiu.template.springboottemplate.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setFirstName(employeeDto.getFirstName());
        existing.setLastName(employeeDto.getLastName());
        existing.setEmail(employeeDto.getEmail());
        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toDto(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}

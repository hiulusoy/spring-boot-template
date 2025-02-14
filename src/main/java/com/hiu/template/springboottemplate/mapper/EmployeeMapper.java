package com.hiu.template.springboottemplate.mapper;


import com.hiu.template.springboottemplate.dto.EmployeeDto;
import com.hiu.template.springboottemplate.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);
}

package com.hiu.template.springboottemplate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Employee Data Transfer Object")
public class EmployeeDto {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the employee", example = "john.doe@example.com")
    private String email;
}

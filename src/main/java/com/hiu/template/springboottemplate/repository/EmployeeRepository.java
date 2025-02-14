package com.hiu.template.springboottemplate.repository;


import com.hiu.template.springboottemplate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // İhtiyaç halinde ek sorgu metodları eklenebilir.
}

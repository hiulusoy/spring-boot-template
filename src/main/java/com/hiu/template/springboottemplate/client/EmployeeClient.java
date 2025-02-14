package com.hiu.template.springboottemplate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "externalEmployeeService", url = "https://external-service.com/api/employees")
public interface EmployeeClient {

    @GetMapping("/ping")
    String ping();
}

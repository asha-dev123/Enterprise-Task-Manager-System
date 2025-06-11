
/*
 * Enterprise Task Manager System - Backend (Spring Boot)
 * Author: Asha Tummuru
 */

// TaskManagerApplication.java
package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}

// Employee.java
package com.company.model;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String email;
    private String department;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}

// Project.java
package com.company.model;

import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

// LeaveRequest.java
package com.company.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

// Repositories
package com.company.repository;

import com.company.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
public interface ProjectRepository extends JpaRepository<Project, Long> {}
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {}

// Services
package com.company.service;

import com.company.model.*;
import com.company.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskManagerService {
    private final EmployeeRepository employeeRepo;
    private final ProjectRepository projectRepo;
    private final LeaveRequestRepository leaveRepo;

    public TaskManagerService(EmployeeRepository e, ProjectRepository p, LeaveRequestRepository l) {
        this.employeeRepo = e;
        this.projectRepo = p;
        this.leaveRepo = l;
    }

    public List<Employee> getAllEmployees() { return employeeRepo.findAll(); }
    public Employee saveEmployee(Employee e) { return employeeRepo.save(e); }

    public List<Project> getAllProjects() { return projectRepo.findAll(); }
    public Project saveProject(Project p) { return projectRepo.save(p); }

    public List<LeaveRequest> getAllLeaveRequests() { return leaveRepo.findAll(); }
    public LeaveRequest saveLeaveRequest(LeaveRequest l) { return leaveRepo.save(l); }
}

// Controller
package com.company.controller;

import com.company.model.*;
import com.company.service.TaskManagerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskManagerController {
    private final TaskManagerService service;

    public TaskManagerController(TaskManagerService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() { return service.getAllEmployees(); }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee e) { return service.saveEmployee(e); }

    @GetMapping("/projects")
    public List<Project> getAllProjects() { return service.getAllProjects(); }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project p) { return service.saveProject(p); }

    @GetMapping("/leaves")
    public List<LeaveRequest> getAllLeaves() { return service.getAllLeaveRequests(); }

    @PostMapping("/leaves")
    public LeaveRequest createLeave(@RequestBody LeaveRequest l) { return service.saveLeaveRequest(l); }
}

// SecurityConfig.java (placeholder)
package com.company.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
}

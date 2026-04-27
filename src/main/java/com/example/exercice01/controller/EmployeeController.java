package com.example.exercice01.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercice01.model.dto.EmployeeFilter;
import com.example.exercice01.model.entity.Employee;
import com.example.exercice01.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // @GetMapping
    // public List<Employee> getMethodName() {
    // return employeeService.getAllEmployees();
    // }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // @GetMapping("/{id}")
    // public Employee getEmployeeById(@PathVariable("id") Long id) {
    // return new Employee(id, "Test User", "test@gmail.com", "IT");
    // }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();// 404 Not Found
        }
        return ResponseEntity.ok(employee);// 200 OK
    }

    @GetMapping("/search")
    public String searchEmployeeByName(@RequestParam("name") String name) {
        return "Đang tìm kiếm nhân viên tên: " + name;
    }

    @GetMapping("/filter")
    public String filterEmployeeS(@ModelAttribute EmployeeFilter filter) {
        return "Đọc từ Object - Tên: " + filter.getName() + ", Phòng: " + filter.getDepartment();
    }

    // @PostMapping
    // public Employee create(@RequestBody Employee newEmployee) {
    // return employeeService.createEmployee(newEmployee);
    // }

    // @PutMapping("/{id}")
    // public Employee update(@PathVariable("id") Long id, @RequestBody Employee
    // updatedInfo) {
    // return employeeService.updateEmployee(id, updatedInfo);
    // }

    // @DeleteMapping("/{id}")
    // public String delete(@PathVariable("id") Long id) {
    // boolean isDeleted = employeeService.deleteEmployee(id);
    // return isDeleted ? "Đã xóa thành công!" : "Không tìm thấy nhân viên để xóa!";
    // }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee newEmployee) {
        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee updateInfo) {
        Employee employee = employeeService.updateEmployee(id, updateInfo);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.noContent().build(); // 204 No Content (Không cần trả body)
    }
}

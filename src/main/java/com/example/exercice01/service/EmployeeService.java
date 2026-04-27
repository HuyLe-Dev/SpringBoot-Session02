package com.example.exercice01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exercice01.model.entity.Employee;
import com.example.exercice01.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        // Nếu không tìm thấy, trả về null (Thực tế sẽ ném ra Exception)
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedInfo) {
        Employee existingEmp = getEmployeeById(id);
        if (existingEmp != null) {
            existingEmp.setFullName(updatedInfo.getFullName());
            existingEmp.setEmail(updatedInfo.getEmail());
            existingEmp.setDepartment(updatedInfo.getDepartment());
            // RAM tự động lưu object vì reference (tham chiếu) giống nhau
        }
        return existingEmp;
    }

    public boolean deleteEmployee(Long id) {
        return employeeRepository.deleteById(id);
    }

}

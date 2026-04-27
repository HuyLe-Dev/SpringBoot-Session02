package com.example.exercice01.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.exercice01.model.entity.Employee;

@Repository
public class EmployeeRepository {
    // Database chạy bằng RAM
    private final List<Employee> db = new ArrayList<>();

    // Biến để giả lập cơ chế tự động tăng ID (Auto Increment) của Oracle DB
    private Long idCounter = 1L;

    // Khởi tạo sẵn vài dữ liệu
    public EmployeeRepository() {
        db.add(new Employee(idCounter++, "Nguyen Van A", "a@gmail.com", "IT"));
        db.add(new Employee(idCounter++, "Tran Thi B", "b@gmail.com", "HR"));
    }

    // Lấy tất cả
    public List<Employee> findAll() {
        return db;
    }

    // Tìm theo ID (Dùng Optional chuẩn Senior để tránh lỗi Null)
    public Optional<Employee> findById(Long id) {
        return db.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }

    // Thêm mới
    public Employee save(Employee employee) {
        employee.setId(idCounter++); // Tự động cấp ID mới
        db.add(employee);
        return employee;
    }

    // Xóa theo ID
    public boolean deleteById(Long id) {
        return db.removeIf(emp -> emp.getId().equals(id));
    }
}

package com.klimkin.dao;

import com.klimkin.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    void create(Employee employee);

    void delete(Integer id);

    void update(Employee employee);

    List<Employee> staffList();

    Employee findEmployeeById(Integer id);

    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeeByDepartment(Integer id);

    boolean compare(String email);
}

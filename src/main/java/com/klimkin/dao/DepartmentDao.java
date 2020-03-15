package com.klimkin.dao;

import com.klimkin.entity.Department;

import java.util.List;

public interface DepartmentDao {

    void create(Department department);

    void delete(Integer id);

    void update(Department department);

    List<Department> departmentsList();

    Department findDepartmentById(Integer id);

    Department findDepartmentByName(String departmentName);

    boolean compare(String department);
}

package com.klimkin.service;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.dao.jdbc.JdbcDepartmentDao;
import com.klimkin.entity.Department;

import javax.servlet.http.HttpServletRequest;

public class DepartmentValidation extends AbstractValidator {
    private DepartmentDao departmentDao = new JdbcDepartmentDao();

    public String validAndSaveEmployee(String departmentId, HttpServletRequest req) {
        this.newDepartment = departmentId == null;
        Department d = fillAndValidationDepartment(departmentId, req);
        if (validDepartment) {
            save(d);
            return null;
        } else {
            return fillValue(req);
        }
    }

    private String fillValue(HttpServletRequest req) {
        return req.getParameter("departmentName");
    }

    private void save(Department department) {
        if (newDepartment) {
            departmentDao.create(department);
        } else {
            departmentDao.update(department);
        }
    }

    private Department fillAndValidationDepartment(String departmentId, HttpServletRequest req) {
        Department department = new Department();
        if (!newDepartment) {
            department.setId(Integer.parseInt(departmentId));
        }
        department.setDepartmentName(validDepartmentName(req.getParameter("departmentName")));
        return department;
    }
}

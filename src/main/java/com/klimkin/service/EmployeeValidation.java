package com.klimkin.service;

import com.klimkin.dao.EmployeeDao;
import com.klimkin.dao.jdbc.JdbcEmployeeDao;
import com.klimkin.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public class EmployeeValidation extends AbstractValidator {
    private EmployeeDao employeeDao = new JdbcEmployeeDao();


    public String[] validAndSaveEmployee(String employeeId, HttpServletRequest req) {
        this.newcomer = employeeId == null;
        Employee e = fillAndValidationEmployee(employeeId, req);
        if (validEmployee) {
            save(e);
            return null;
        } else {
            return fillValue(req);
        }
    }

    private Employee fillAndValidationEmployee(String employeeId, HttpServletRequest req) {
        Employee employee = new Employee();
        if (!newcomer) {
            employee.setId(Integer.parseInt(employeeId));
        }
        employee.setFirstName(validName(req.getParameter("firstName")));
        employee.setLastName(validName(req.getParameter("lastName")));
        employee.setEmail(validEmail(req.getParameter("email")));
        employee.setBirthday(validDate(req.getParameter("birthday")));
        employee.setSalary(validNumber(req.getParameter("salary")));
        employee.setDepartment(validDepartmentForStaff(req.getParameter("department")));

        return employee;
    }


    private void save(Employee employee) {
        if (newcomer) {
            employeeDao.create(employee);
        } else {
            employeeDao.update(employee);
        }
    }

    private String[] fillValue(HttpServletRequest req) {
        String[] value= new String[5];
        value[0] = req.getParameter("firstName");
        value[1] = req.getParameter("lastName");
        value[2] = req.getParameter("email");
        value[3] = req.getParameter("birthday");
        value[4] = req.getParameter("salary");
        return value;
    }

}

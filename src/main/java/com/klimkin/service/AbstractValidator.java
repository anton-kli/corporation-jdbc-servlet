package com.klimkin.service;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.dao.EmployeeDao;
import com.klimkin.dao.jdbc.JdbcDepartmentDao;
import com.klimkin.dao.jdbc.JdbcEmployeeDao;
import com.klimkin.entity.Department;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class AbstractValidator {
    private EmployeeDao employeeDao = new JdbcEmployeeDao();
    private DepartmentDao departmentDao = new JdbcDepartmentDao();

    protected boolean newDepartment;
    protected boolean validDepartment = true;

    protected boolean newcomer;
    protected boolean validEmployee = true;

    protected String validName(String name) {
        if (isEmpty(name)) {
            name = null;
            if (newcomer) {
                validEmployee = false;
            }
        }
        if (!isName(name)) {
            validEmployee = false;
        }
        return name;
    }

    protected String validEmail(String email) {
        if (isEmpty(email)) {
            email = null;
            if (newcomer) {
                validEmployee = false;
            }
        }
        if (!checkEmail(email)) {
            validEmployee = false;
        }
        return email;
    }

    protected Date validDate(String date) {
        Date bd = null;
        if (date == null) {
            if (newcomer) {
                validEmployee = false;
            }
        } else {
            try {
                bd = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                validEmployee = false;
            }
        }
        return bd;
    }

    protected Integer validNumber(String number) {
        Integer val = null;
        if(number == null) {
            if (newcomer) {
                validEmployee = false;
            }
        } else {
            try {
                val = Integer.parseInt(number);
            } catch (Exception e) {
                validEmployee = false;
            }
        }
        return val;
    }

    protected Department validDepartmentForStaff(String number) {
        Department department = null;
        if (number == null) {
            if (newcomer) {
                validEmployee = false;
            }
        } else {
            department = new JdbcDepartmentDao().findDepartmentById(Integer.parseInt(number));
        }
        return department;
    }

    protected String validDepartmentName(String name) {
        if (isEmpty(name)) {
            name = null;
            validDepartment = false;
        } else {
            if (departmentDao.compare(name)) {
                validDepartment = false;
            }
        }
        return name;
    }


    private boolean isEmpty(String state) {
        return state == null || state.trim().equals("");
    }

    private boolean isName(String state) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(state.trim()).matches();
    }

    private boolean checkEmail(String state) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
        return pattern.matcher(state.trim()).matches() && !employeeDao.compare(state);
    }
}

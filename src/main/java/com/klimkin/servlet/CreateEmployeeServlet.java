package com.klimkin.servlet;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.dao.EmployeeDao;
import com.klimkin.dao.jdbc.JdbcDepartmentDao;
import com.klimkin.dao.jdbc.JdbcEmployeeDao;
import com.klimkin.entity.Department;
import com.klimkin.service.EmployeeValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add_employee")
public class CreateEmployeeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentDao departmentDao = new JdbcDepartmentDao();
        EmployeeDao employeeDao = new JdbcEmployeeDao();
        StringBuilder title = new StringBuilder("<h3 class=\"text-center\">");
        String idEmpl = req.getParameter("employee");
        String button;
        String submit = "";
        String back = "";

        if (idEmpl == null) {
            title.append("Create Employee").append("</h3>");
            button = "Create";
        } else {
            title.append("Update Employee #").append(idEmpl).append("</h3>");
            button = "Update";
            submit ="?employee=" + idEmpl;
            Integer dep_id = employeeDao.findEmployeeById(Integer.parseInt(idEmpl)).getDepartment().getId();
            back = "/main/staff?dep="+dep_id;
        }

        req.setAttribute("select", selectDepartment());
        req.setAttribute("back", back);
        req.setAttribute("title", title);
        req.setAttribute("button", button);
        req.setAttribute("submit", submit);
        getServletContext().getRequestDispatcher("/add_employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;
        String idEmpl = req.getParameter("employee");
        String[] value = new EmployeeValidation().validAndSaveEmployee(idEmpl, req);

        if (value == null) {
            message = "<p class=\"text-success\">Employee successfully created.</p>";
        } else {
            message = "<p class=\"text-danger\">Some fields is empty or employee with the same email already exists.</p>";
        }

        req.setAttribute("message", message);
        req.setAttribute("value", value);
        doGet(req, resp);
    }

    private String selectDepartment() {
        StringBuilder select = new StringBuilder();
        List<Department> departments = new JdbcDepartmentDao().departmentsList();
        for (Department d : departments) {
            select.append("<option value=\"").append(d.getId()).append("\">").append(d.getDepartmentName()).append("</option>\n");
        }
        return select.toString();
    }
}


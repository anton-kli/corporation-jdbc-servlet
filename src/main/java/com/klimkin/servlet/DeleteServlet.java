package com.klimkin.servlet;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.dao.EmployeeDao;
import com.klimkin.dao.jdbc.JdbcDepartmentDao;
import com.klimkin.dao.jdbc.JdbcEmployeeDao;
import com.klimkin.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    DepartmentDao departmentDao = new JdbcDepartmentDao();
    EmployeeDao employeeDao = new JdbcEmployeeDao();
    String entity;
    Integer id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.entity = req.getParameter("delete");
        if (entity != null) {
            StringBuilder title = new StringBuilder("<h3 class=\"card-title text-center\">");
            this.id = Integer.parseInt(req.getParameter("id"));
            String back;
            if (entity.equals("department")) {
                title.append("Delete department #").append(id).append("</h3>");
                back = "/main/departments";
            } else {
                Integer dep_id = employeeDao.findEmployeeById(id).getDepartment().getId();
                back = "/main/staff?dep="+dep_id;
                title.append("Delete employee #").append(id).append("</h3>");
            }

            if (req.getParameter("message") == null) {
                req.setAttribute("message", "<p class=\"text-center mt=4\">Are you sure?</p>");
            }
            req.setAttribute("title", title);
            req.setAttribute("back", back);
        }
        getServletContext().getRequestDispatcher("/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (entity != null) {
            String message;
            if (entity.equals("department")) {
                List<Employee> staff = employeeDao.findEmployeeByDepartment(id);
                for(Employee fired : staff) {
                    employeeDao.delete(fired.getId());
                }
                departmentDao.delete(id);
                message = "<p class=\"text-center text-success mt-4\">Department successfully delete.</p>";
            } else {
                employeeDao.delete(id);
                message = "<p class=\"text-center text-success mt-4\">Employee successfully delete.</p>";
            }
            req.setAttribute("message", message);
        }
        doGet(req, resp);
    }
}

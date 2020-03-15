package com.klimkin.servlet;

import com.klimkin.dao.DepartmentDao;
import com.klimkin.dao.jdbc.JdbcDepartmentDao;
import com.klimkin.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentsServlet extends HttpServlet {
    DepartmentDao departmentDao = new JdbcDepartmentDao();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> deps = departmentDao.departmentsList();

        req.setAttribute("departments", deps);
        getServletContext().getRequestDispatcher("/departments.jsp").forward(req, resp);
    }


}

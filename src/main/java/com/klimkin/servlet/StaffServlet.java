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

@WebServlet("/staff/*")
public class StaffServlet extends HttpServlet {
    EmployeeDao employeeDao = new JdbcEmployeeDao();
    DepartmentDao departmentDao = new JdbcDepartmentDao();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dep = req.getParameter("dep");
        List<Employee> staff;
        StringBuilder title = new StringBuilder("<p class = \"h1 display-4\">");
        if (dep == null) {
            title.append("All Staff").append("</p>");
            staff = employeeDao.staffList();
        } else {
            int id = Integer.parseInt(dep);
            String departmentName = departmentDao.findDepartmentById(id).getDepartmentName();
            title.append(departmentName).append("</p>");
            staff = employeeDao.findEmployeeByDepartment(id);
        }

        req.setAttribute("addButton", addButtonState());
        req.setAttribute("title", title);
        req.setAttribute("staff", staff);
        getServletContext().getRequestDispatcher("/staff.jsp").forward(req, resp);
    }

    private String addButtonState() {
        String state = "<a href=\"/main/add_employee\" class=\"btn btn-primary\">+ Add Employee</a>";

        if (departmentDao.departmentsList().size() == 0) {
            state = "<button type=\"button\" " +
                    "class=\"btn btn-secondary\" data-container=\"body\" data-toggle=\"popover\" data-placement=\"right\" " +
                    "data-content=\"You need to create some department at first/\">\n" +
                    "+ Add Employee\n" +
                    "</button>";
        }
        return state;
    }
}

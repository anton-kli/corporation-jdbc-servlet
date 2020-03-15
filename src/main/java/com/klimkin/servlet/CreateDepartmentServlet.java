package com.klimkin.servlet;

import com.klimkin.service.DepartmentValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_department")
public class CreateDepartmentServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder title = new StringBuilder("<h3 class=\"text-center\">");
        String idDep = req.getParameter("dep");
        String button;
        String submit = "";

        if (idDep == null) {
            title.append("Create Department").append("</h3>");
            button = "Create";
        } else {
            Integer id = Integer.parseInt(idDep);
            title.append("Update department #").append(id).append("</h3>");
            button = "Update";
            submit ="?dep=" + idDep;
        }

        if (req.getAttribute("value") == null) {
            req.setAttribute("value", "");
        }
        req.setAttribute("title", title);
        req.setAttribute("button", button);
        req.setAttribute("submit", submit);
        getServletContext().getRequestDispatcher("/add_department.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message;

        String idDep = req.getParameter("dep");
        String value = new DepartmentValidation().validAndSaveEmployee(idDep, req);

        if (value == null) {
            message = "<p class=\"text-success\">Department successfully created.</p>";
            value = "";
        } else {
            message = "<p class=\"text-danger\">Fields is empty or department with the same name already exists.</p>";
        }

        req.setAttribute("value", value);
        req.setAttribute("message", message);
        doGet(req, resp);
    }
}


<%@ page import="java.util.List" %>
<%@ page import="com.klimkin.entity.Department" %>
<%@ page import="com.klimkin.dao.jdbc.util.JdbcTableUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Corporation</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand mx-5" style="letter-spacing: 3px;" href="/main">CORPORATION</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/main">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main/departments">Departments</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main/staff">Staff</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container my-5">

    <%
        List<Department> departments = (List) request.getAttribute("departments");

        if (departments.size() == 0) {
            JdbcTableUtil util = new JdbcTableUtil();
            util.dropDepartmentDb();
            util.createDepartmentDb();
        }
    %>

    <p class = "h1 display-4">Departments</p>
    <hr class="hr-white my-5">
    <table class="table table-striped">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col"></th>

        </tr>
        <%
            for (Department department : departments) {
                int id = department.getId();
        %>
        <tr>
            <td scope="row"> <%=id %> </td>
            <td scope="row"> <a href="/main/staff?dep=<%=id%>"><%=department.getDepartmentName()%></a></td>
            <td scope="row">
                <a href="/main/add_department?dep=<%=id%>">
                    <img src="asserts/image/create-24px.svg" width="20" height="20" border="0" alt="Edit">
                </a>
                <a href="/main/delete?delete=department&id=<%=id%>">
                    <img class="ml-2" src="asserts/image/delete_sweep-24px.svg" width="20" height="20" border="0" alt="Delete">
                </a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <div class="container my-4">
        <a href="/main/add_department" class="btn btn-primary">+ Add Department</a>
    </div>
</div>
</body>
</html>

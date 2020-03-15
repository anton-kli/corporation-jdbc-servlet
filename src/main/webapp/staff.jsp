<%@ page import="java.util.List" %>
<%@ page import="com.klimkin.entity.Employee" %>
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
    List<Employee> staff = (List) request.getAttribute("staff");

    if (staff.size() == 0) {
        JdbcTableUtil util = new JdbcTableUtil();
        util.dropEmployeeDb();
        util.createEmployeeDb();
    }
%>
    <%=request.getAttribute("title")%>

    <hr class="hr-white my-5">
<table class="table table-striped">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
        <th scope="col">Birthday</th>
        <th scope="col">Salary</th>
        <th scope="col">Department ID</th>
        <th scope="col"></th>
    </tr>
    <%
        for (Employee employee : staff) {
            int id = employee.getId();
    %>
    <tr>
        <td> <%=id%> </td>
        <td> <%= employee.getFirstName() %> </td>
        <td> <%= employee.getLastName() %> </td>
        <td> <%= employee.getEmail() %> </td>
        <td> <%= employee.getBirthday() %> </td>
        <td> <%= employee.getSalary() %> </td>
        <td> <%= employee.getDepartment().getId() %> </td>
        <td scope="row">
            <a href="/main/add_employee?employee=<%=id%> ">
                <img src="asserts/image/create-24px.svg" width="20" height="20" border="0" alt="Edit">
            </a>
            <a href="/main/delete?delete=employee&id=<%=id%>">
                <img class="ml-2" src="asserts/image/delete_sweep-24px.svg" width="20" height="20" border="0" alt="Delete">
            </a>
        </td>
    </tr>
    <%
        }
    %>
</table>
    <%= request.getAttribute("addButton")%>
<%--<a href="/main/add_employee" class="btn btn-primary">+ Add Employee</a>--%>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>
</body>
</html>


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

<!--Form-->
<div class="container my-5">
    <form action="/main/add_employee<%=request.getAttribute("submit")%>" method="post" class="was-validation">
        <%=request.getAttribute("title")%>

        <%
            String[] value = (String[]) request.getAttribute("value");
            String message = (String) request.getAttribute("message");
            if (message == null) {
                value = new String[] {"", "", "", "", ""};
                out.print("<p class=\"text-dark\">Fill in the empty fields.</p>");
            } else {
                out.print(message);
            }
        %>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="FirstNameInput">First Name:</label>
            </div>
            <div class="col">
                <input class="form-control" type="text" name="firstName" id="FirstNameInput" value="<%=value[0]%>" placeholder="First Name">
            </div>
        </div>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="LastNameInput">Last Name:</label>
            </div>
            <div class="col">
                <input class="form-control" type="text" name="lastName" id="LastNameInput" value="<%=value[1]%>" placeholder="Last Name">
            </div>
        </div>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="EmailInput">Email:</label>
            </div>
            <div class="col">
                <input class="form-control" type="email" name="email" id="EmailInput" value="<%=value[2]%>" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="BirthdayInput">Birthday:</label>
            </div>
            <div class="col">
                <input class="form-control" type="date" name="birthday" id="BirthdayInput" value="<%=value[3]%>" placeholder="Birthday">
            </div>
        </div>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="SalaryInput">Salary:</label>
            </div>
            <div class="col">
                <input class="form-control" type="number" name="salary" id="SalaryInput" value="<%=value[4]%>" placeholder="$">
            </div>
        </div>
        <div class="form-group">
            <div>
                <label class="col-sm-6 col-form-label" for="DepartmentInput">Department:</label>
            </div>
            <div class="col">
                <select class="form-control" type="number" name="department_id" id="DepartmentInput">
                    <option selected value=""> - </option>
                    <%=request.getAttribute("select")%>>
                </select>
            </div>
        </div>

        <div class="mt-1 mx-1">
            <button type="submit" class="btn btn-primary"><%=request.getAttribute("button")%></button>
            <a href="/main/staff<%=request.getAttribute("back")%>" class="btn btn-danger">Back</a>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
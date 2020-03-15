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
    <form action="/main/add_department<%=request.getAttribute("submit")%>" method="post" class="needs-validation">
            <%=request.getAttribute("title")%>
            <%
                if (request.getAttribute("message") == null) {
                    out.print("<p class=\"text-white\">.</p>");
                } else {
                    out.print(request.getAttribute("message"));
                }

            %>
        <div class="col">
                <label for="TitleInput">Department Name:</label>
                <input class="form-control" type="text" name="departmentName" id="TitleInput" value="<%=request.getAttribute("value")%>" placeholder="Department">
        </div>

        <div class="mt-3 mx-3">
                <button type="submit" class="btn btn-primary"><%=request.getAttribute("button")%></button>
                <a href="/main/departments" class="btn btn-danger mx-2">Back</a>
            </div>

    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>
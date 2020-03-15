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
<div class="container" style="height: 25%"></div>
<%
    if (request.getParameter("delete") != null) {
%>
<div class="row justify-content-md-center">
    <div  class="card" style="width: 400px;">
        <div class="card-body">
            <form action="/main/delete" method="post">
                <%=request.getAttribute("title")%>
                <%=request.getAttribute("message")%>
                <div class="mt-1 mx-3 text-center">
                    <button type="submit" class="btn btn-danger">Delete</button>
                    <a class="btn btn-primary text-white" onclick="history.back();">Back</a>
                </div>
            </form>
        </div>
    </div>
</div>
<%
    } else {
%>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-12 mb-4 text-center my-5">
            <h3 class="text-success mb-5">Delete success</h3>
            <a href="/main/departments" class="btn btn-outline-dark border-dark text-dark">Departments</a>
            <a href="/main/staff" class="btn btn-outline-dark border-dark text-dark">Staff</a>
        </div>
    </div>
</div>
<%
    }
%>
</body>
</html>

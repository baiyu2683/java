<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 18-7-19
  Time: 上午12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>zhangheng2683's homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=basePath%>/style/bootstrap/css/bootstrap.css">
    <script src="<%=basePath%>/style/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<%=basePath%>
<div class="container-fluid">
    <h2>h2的标题</h2>
    <div class="row text-center">
        <div class="col-4 bg-success">.col-4</div>
        <div class="col-4 offset-4 bg-warning">.col-4 .offset-4</div>
    </div>
    <div class="row">
        <div class="col-4 offset-3 bg-success">.col-4</div>
        <div class="col-4 offset-4 bg-warning">.col-4 .offset-3</div>
    </div>
    <div class="row">
        <div class="col-6 offset-3 bg-success">.col-6 .offset-3</div>
    </div>
    <p>The <abbr title="World Health Organization">Who</abbr>  was founded in 1948.</p>
    <p>The <blockquote class="blockquote">Who</blockquote>  was founded in 1948.</p>
    <h1>Description Lists</h1>
    <p>The dl element indicates a description</p>
    <dl>
        <dt class="text-primary">Coffee</dt>
        <dd class="text-info">- blank hot drink</dd>
        <dt class="text-primary">Milk</dt>
        <dd class="text-info">- white cold drink</dd>
    </dl>
    <table class="table table-bordered table-hover">
        <thead>
            <tr class="table-primary">
                <th>FirshName</th>
                <th>LastName</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <tr class="table-info">
                <td>John</td>
                <td>Doe</td>
                <td>John@example.com</td>
            </tr>
            <tr class="table-info">
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
            </tr>
        </tbody>
    </table>
    <div>==========</div>
    <div class="alert alert-info">
        信息
    </div>
</div>
</body>
</html>

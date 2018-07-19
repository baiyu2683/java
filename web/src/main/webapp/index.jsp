<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 18-7-19
  Time: 上午12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
</head>
<body>
<script>
    window.location.replace("<%=basePath%>/homepage")
</script>
</body>
</html>

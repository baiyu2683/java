<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
	<title>cookie测试</title>
	<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-2.2.1.js"></script>
</head>
<body>
<button typ='button' name="请求" value="request" onclick="request();">请求</button>
<script type="text/javascript">
	function request() {
		$.ajax({
			"url":"<%=basePath%>/test/getCookie",
			"type":"get",
			"dataType":"json",
			"data":{
			},
			"success" : function(data) {
                console.log(JSON.stringify(data));
            },
            "error" : function(status, t) {
                console.log(status);
            }
		})
	}
</script>
</body>
</html>
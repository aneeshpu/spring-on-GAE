<%@page import="com.foo.MyModel"%>
<html>
<body>
	<%
		MyModel model = (MyModel)request.getAttribute("message");
	%>
	framework:<%=model.getName()%>
	<br/>
	version:<%=model.getVersion()%>
</body>
</html>
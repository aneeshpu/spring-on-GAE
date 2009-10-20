<%@page import="com.aneeshpu.gae.web.mvc.MyModel"%>
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
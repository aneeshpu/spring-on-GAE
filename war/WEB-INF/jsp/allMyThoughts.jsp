<%@page import="com.aneesh.gae.domain.QuickThought"%>
<%@page import="java.util.List"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/quickThought.css" />
</head>
<body>
	So far you have thought:
	<%
		List<QuickThought> thoughts = (List<QuickThought>)request.getAttribute("allMyThoughts");
		for(QuickThought quickThought: thoughts){
	%>
	<li><%=quickThought%>
	<br/>
	<%}%>
</body>
</html>
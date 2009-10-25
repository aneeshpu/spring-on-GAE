<%@page import="com.aneesh.gae.domain.QuickThought"%>
<%@page import="java.util.List"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/quickThought.css" />
</head>
<body>
	<div id="mind">
		<ol>
	<%
		List<QuickThought> thoughts = (List<QuickThought>)request.getAttribute("allMyThoughts");
		for(QuickThought quickThought: thoughts){
	%>
		
			<li >
				<span class="thought"><%=quickThought%></span>
				<span class="time"><%=quickThought.when() == null ? "" : quickThought.when()%></span>
			</li>
		
	<%}%>
		</ol>
	</div>
</body>
</html>
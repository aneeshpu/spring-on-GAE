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
		
			<li class="thought">
				<span>
					<%=quickThought.when()%>
					<%=quickThought%>
				</span>
			</li>
		
	<%}%>
		</ol>
	</div>
</body>
</html>
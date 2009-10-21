<%@page import="com.aneesh.gae.domain.QuickThought"%><html>
<body>
	You just thought:<%=(QuickThought)request.getAttribute("thought")%>
</body>
</html>
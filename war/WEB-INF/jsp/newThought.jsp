<%@page import="com.aneesh.gae.domain.QuickThought" %>

<%
response.setContentType("application/json");
%>

{
	newThought: '<%=request.getAttribute("thought")%>'
	,created: '<%= ((QuickThought)request.getAttribute("thought")).when()%>'
}
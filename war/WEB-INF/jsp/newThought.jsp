<%
response.setContentType("application/json");
%>
{newThought: '<%=request.getAttribute("thought")%>'}
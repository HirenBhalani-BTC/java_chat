<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="java.sql.*"%>

Messages

<c:forEach items="${sessionScope.msg_list }" var="msg">
<div style="background-color: #ddffdd; width:200px; padding: 5px;border: 3px solid #ccffcc; margin: 1px">
	<c:out value="${msg.msg }"></c:out>
	
</div>

</c:forEach>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="java.net.ServerSocket"%>
<%@page import="java.net.Socket"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.11.2.min.js"></script>
</head>
<body>

<script type="text/javascript">
var receiver_id
$(document).on('click','user', function () {
    //alert(this.id);
    $('#receiver').val(this.id);
    receiver_id = this.id
    load_message();
    $('#chat_form').load('msg_form.jsp?receiver='+this.id);
});

</script>


<c:forEach items="${sessionScope.user_list }" var="ul" >

<c:set value="${ul.iduser }" var="id"></c:set>
<c:set value="${ul.name }" var="name"></c:set>
<div style="background-color: #ddffdd; width:200px; padding: 5px; border: 3px solid #ccffcc; margin: 2px">
<user  id="${id }">
	${name }<br/>
</user>

</div>
</c:forEach>


<%  
	String name = (String)session.getAttribute("name"); 
	String id = String.valueOf(session.getAttribute("u_id"));
	out.println(name);
	out.println(id);
%>

<div style="height:100px">
</div>

<div id="message"> 
<%-- <% //@ include file="chat_messages.jsp" %> --%>
</div>


<div id="chat_form">

</div>


<div id="result"></div>

<script type="text/javascript">

function load_message(){
	setInterval(function(){
		$.ajax({
            type: "GET",
            url: "chat_controller",
            data: "receiver=" + receiver_id,
		});
		$('#message').load("chat_messages.jsp");
		},2000);
}

</script>
</body>
</html>
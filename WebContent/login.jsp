<%@page import="java.net.Inet6Address"%>
<%@page import="java.net.Inet4Address"%>
<%@page import="java.net.NetworkInterface"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%


// ServerSocket serverSocket = new ServerSocket(8000, 0, IPaddress);

//creating a client socket to accept it
//Socket clientSocket = serverSocket.accept();
/* for(int i = 0; i<5; i++){
    clientSocket = serverSocket.accept();
    // start a new thread, passing it the clientSocket as an argument
}
 */
String name = request.getParameter("name");
String password = request.getParameter("pwd");
 String ip = request.getRemoteAddr(); 
/* InetAddress ip = Inet4Address.getLocalHost();  */

		
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from user where email= '"+name+"' and password= '"+password+"' ");

out.println(rs);
if(rs.next())
{
	Statement st1 = con.createStatement();
	st1.executeUpdate("update user SET ip='"+ ip +"' WHERE iduser = "+rs.getString("iduser"));
	session.setAttribute("name", rs.getString("name"));
	session.setAttribute("id", rs.getString("iduser"));
	
	//ResultSet rsc = st.executeQuery("select * from chat_msg order by iduser DESC limit 5");
	//request.setAttribute("resultset",rsc);
	
	//session.setAttribute("msg", rsc);
	
	response.sendRedirect("chat_form.jsp");	
}
else
{
	out.println("hello");}
%>
</body>
</html>
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class chat_dao {

	public void msg_insert(chat_vo rv)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st=con.createStatement();
			st.executeUpdate("insert into chat_msg (msg, sender, receiver, status) values('"+rv.getMsg()+"','"+rv.getSender()+"','"+rv.getReceiver()+"', '')");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public List msg_search(chat_vo v)
	{
		ArrayList ls=new ArrayList();
		List rls = new ArrayList();
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from chat_msg where (receiver='"+v.getReceiver()+"' AND sender='"+v.getSender()+"') OR (receiver='"+v.getSender()+"' AND sender='"+v.getReceiver()+"') order by idchat_msg DESC limit 10");
			while(rs.next())
			{
				chat_vo rv1=new chat_vo();
				rv1.setIdchat_msg(Integer.parseInt(rs.getString("idchat_msg")));
				rv1.setMsg(rs.getString("msg"));
				rv1.setSender(Integer.parseInt(rs.getString("sender")));
				ls.add(rv1);
			}
		}
		catch(Exception e)
		{
				System.out.println("Error"+e);
		}
		 Collections.reverse(ls);
		 return ls;
	}
}

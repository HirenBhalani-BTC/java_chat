package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class login_dao {

	public List user_list(login_vo rv)
	{
		List ls = new ArrayList();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st = con.createStatement();
			ResultSet rsu = st.executeQuery("select iduser,name from user");
			
			while(rsu.next())
			{
				login_vo rv1=new login_vo();
				rv1.setName(rsu.getString("name"));
				rv1.setIduser(Integer.parseInt(rsu.getString("iduser")));
				
				ls.add(rv1);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ls;
	}
	
	public List user_login(login_vo rv)
	{
		List ls = new ArrayList();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user where email= '"+rv.getEmail()+"' and password= '"+rv.getPassword()+"' ");
			while(rs.next())
			{
				login_vo rv1=new login_vo();
				rv1.setName(rs.getString("name"));
				rv1.setIduser(Integer.parseInt(rs.getString("iduser")));
				
				ls.add(rv1);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ls;
	}
	
	public void update_ip(login_vo rv)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st=con.createStatement();
			st.executeUpdate("update user SET ip='"+ rv.getIp() +"' WHERE iduser = "+rv.getIduser());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void reg_user(login_vo rv)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat_app","root","hiren");
			Statement st=con.createStatement();
			st.executeUpdate("insert into user (name, email, password, ip) values ('"+rv.getName()+"', '"+rv.getEmail()+"', '"+rv.getPassword()+"', '"+rv.getIp()+"')");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

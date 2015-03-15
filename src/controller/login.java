package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String flag = request.getParameter("flag");
		System.out.println("aaaaaaaaaaa"+flag);
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		
		
		String ip = request.getRemoteAddr();
		
		login_vo lv = new login_vo();
		
		lv.setEmail(email);
		lv.setPassword(password);
		lv.setIp(ip);
		
		login_dao ld = new login_dao();
		System.out.println("bbbbbbbbbbb");
		if(flag.equals("login"))
		{
			/* InetAddress ip = Inet4Address.getLocalHost();  */
			
			List ls = ld.user_login(lv);
			if(ls.size()!=0)
			{
				HttpSession session = request.getSession();
			
				login_vo lvi = (login_vo)ls.get(0);
				lvi.getIduser();
				
				ld.update_ip(lvi);
				//ResultSet rsc = st.executeQuery("select * from chat_msg");
				
				session.setAttribute("user", ls);
				session.setAttribute("name", lvi.getName());
				session.setAttribute("u_id", +lvi.getIduser());
				System.out.println(lvi.getName()+"    aaa   "+lvi.getIduser());
				
				login_vo lvlist = new login_vo();
				List lslist = ld.user_list(lvlist);
				
				session.setAttribute("user_list", lslist);
				response.sendRedirect("chat_form.jsp");	
				
			}
			else
			{
				response.sendRedirect("login_form.jsp");
			}
		}
		else if(flag.equals("reg"))
		{
			System.out.println("hhhhhhhh");
			String name = request.getParameter("name");
			
			
			lv.setName(name);
			
			ld.reg_user(lv);
			
			response.sendRedirect("login_form.jsp");
		}
	}

}

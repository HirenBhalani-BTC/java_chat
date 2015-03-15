package controller;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class chat
 */
@WebServlet("/chat_controller")
public class chat_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chat_controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String sender = String.valueOf(session.getAttribute("u_id"));
		String receiver = request.getParameter("receiver");
		System.out.println("1111111111111111   "+sender);
		//String receiver = request.getParameter("receiver");
		chat_vo cv = new chat_vo();
		cv.setReceiver(Integer.parseInt(receiver));
		cv.setSender(Integer.parseInt(sender));
		
		chat_dao cd = new chat_dao();
		List msg_list = cd.msg_search(cv);
		
		session.setAttribute("msg_list", msg_list);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String msg = request.getParameter("msg");
		String receiver = request.getParameter("receiver");
		String ip = request.getRemoteAddr(); 
		System.out.println(msg+"  "+receiver+"  "+ip);
		/* InetAddress ip = Inet4Address.getLocalHost();  */
		HttpSession session = request.getSession();
		
		String sender = String.valueOf(session.getAttribute("u_id"));

		chat_vo cv = new chat_vo();
		
		cv.setMsg(msg);
		cv.setReceiver(Integer.parseInt(receiver));
		cv.setSender(Integer.parseInt(sender));
		
		chat_dao cd = new chat_dao();
		
		cd.msg_insert(cv);
		
		
		List msg_list = cd.msg_search(cv);
		
		session.setAttribute("msg_list", msg_list);
		
		response.sendRedirect("chat_form.jsp");			
		
	}

}

package com.arm.Login;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "jdbc:mysql://localhost:3301/mydata";
    String user = "root";
    String pass = "1975";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event = request.getParameter("event");
		String user1 = request.getParameter("user");
		
		
		System.out.println(event+"hello "+user1);
		HttpSession session = request.getSession();
		String uname =(String) session.getAttribute("username");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,user,pass);
			
			if (user1!=null) {
				String queryString = "delete from registrations where email = ?";
				PreparedStatement  stmt = connection.prepareStatement(queryString);
				stmt.setString(1, uname);
				System.out.println("User deleted");
				stmt.executeUpdate();
				session.removeAttribute("logged");
				response.sendRedirect("Home.html");
				
			}
			else {
			String sql = "update registrations set "+event+" = ? where email = ?";
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				
				
				statement.setBoolean(1, false);
				statement.setString(2,uname);
				
				statement.executeUpdate();
				String eventString = event.substring(0,1).toUpperCase()+event.substring(1).toLowerCase();
				System.out.println(eventString);
				session.setAttribute(eventString, false);
				response.sendRedirect("Dashboard.jsp");

			}
			catch (SQLException e) {
				response.sendRedirect("Login.html?error=Invalid credentials");
				e.printStackTrace();// TODO: handle exception
			}
			
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

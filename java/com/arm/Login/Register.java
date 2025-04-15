package com.arm.Login;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String url = "jdbc:mysql://localhost:3301/mydata";
    String user = "root";
    String pass = "1975";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String institute = request.getParameter("institute");
		String age =request.getParameter("age");
		int ageInt = Integer.parseInt(age);
		String qualification = request.getParameter("qualification");
		String category = request.getParameter("category");
		String comments = request.getParameter("comments");

		
		
		List<String> events = Arrays.asList(request.getParameterValues("events"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,user,pass);
			Statement stmt = connection.createStatement();
			
			String sql = "insert into registrations (name, email,pass, institute,age,qualification, category, dance, singing, drama, painting, comments) " +
		             "values (?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			    pstmt.setString(1, name);        // For "name"
			    pstmt.setString(2, email);       // For "email"
			    pstmt.setString(3, password);		// for "pass"
			    pstmt.setString(4, institute);   // For "institute"
			    pstmt.setInt(5, ageInt);
			    pstmt.setString(6, qualification);
			    pstmt.setString(7, category);    // For "category"
			    pstmt.setBoolean(8, events.contains("Dance"));  // Set TRUE/FALSE for "dance"
			    pstmt.setBoolean(9, events.contains("Singing")); // Set TRUE/FALSE for "singing"
			    pstmt.setBoolean(10, events.contains("Drama"));   // Set TRUE/FALSE for "drama"
			    pstmt.setBoolean(11, events.contains("Painting")); // Set TRUE/FALSE for "painting"
			    pstmt.setString(12, comments);   // For "comments"
			    int rowsInserted = pstmt.executeUpdate();
			    if (rowsInserted > 0) {
			        System.out.println("A new registration was successfully added!");
			    }
			    HttpSession session = request.getSession();
				session.setAttribute("logged", true);
				session.setAttribute("name", name);
	            session.setAttribute("username", email);
				session.setAttribute("Dance", events.contains("Dance"));
				session.setAttribute("Singing", events.contains("Singing"));
				session.setAttribute("Drama", events.contains("Drama"));
				session.setAttribute("Painting", events.contains("Painting"));
				response.sendRedirect("Dashboard.jsp");
			}
			 catch (SQLException e) {
				    e.printStackTrace();
				}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


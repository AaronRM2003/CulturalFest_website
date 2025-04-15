package com.arm.Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "jdbc:mysql://localhost:3301/mydata";
    String user = "root";
    String pass = "1975";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password =  request.getParameter("password");
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,user,pass);
			
			String queryString ="select * from registrations where email = ? and pass = ?";
			try (PreparedStatement pstmt = connection.prepareStatement(queryString)) {
			    pstmt.setString(1, username); // Correct indexing starts at 1
			    pstmt.setString(2, password);

			    try (ResultSet rs = pstmt.executeQuery()) {
			        if (rs.next()) { // Check if a record is returned
			            Boolean dance = rs.getBoolean("Dance");
			            Boolean singing = rs.getBoolean("Singing");
			            Boolean drama = rs.getBoolean("Drama");
			            Boolean painting = rs.getBoolean("Painting");
			            String name = rs.getString("name");

			            HttpSession session = request.getSession();
			            session.setAttribute("logged", true);
			            session.setAttribute("name", name);
			            session.setAttribute("username", username);
			            session.setAttribute("Dance", dance);
			            session.setAttribute("Singing", singing);
			            session.setAttribute("Drama", drama);
			            session.setAttribute("Painting", painting);

			            response.sendRedirect("Dashboard.jsp");
			        } else {
			            // Handle invalid username/password
			        	HttpSession session = request.getSession();
			        	session.setAttribute("Credential", true);
			            response.sendRedirect("Login.html?error=invalid");
			            
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace(); // For debugging purposes; remove in production
			    response.sendRedirect("Error.html");
			}
			
			
			
			
			
		
	}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}

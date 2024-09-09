package student.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateValidation
 */
public class UpdateValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var fname=request.getParameter("fname");
		var lname=request.getParameter("lname");
		var email=request.getParameter("email");
		var dept=request.getParameter("dept");
		var year=request.getParameter("year");
		var stream=request.getParameter("stream");
		var uni=request.getParameter("uni");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sahil","root","abcd");
			PreparedStatement p1=con.prepareStatement("UPDATE student_signup SET fname=?,lname=?,email=? WHERE fname=?");
			PreparedStatement p2=con.prepareStatement("UPDATE student_homepage SET dept=?,year=?,stream=?,uni=? WHERE dept=?");
			
			p1.setString(1, fname);
			p1.setString(2, lname);
			p1.setString(3, email);
			p1.setString(4, fname);
			
			p2.setString(1, dept);
			p2.setString(2, year);
			p2.setString(3, stream);
			p2.setString(4, uni);
			p2.setString(5, dept);
			
			p1.executeUpdate();
			p2.executeUpdate();
			
			response.sendRedirect("Display");
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

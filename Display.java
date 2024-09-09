package student.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Display() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sahil", "root", "abcd");

            // Begin the HTML response
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f0f2f5; margin: 0; padding: 20px; }");
            out.println("h2 { color: #333; font-size: 1.8em; margin-bottom: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            out.println("table, th, td { border: 1px solid #ccc; }");
            out.println("th, td { padding: 12px; text-align: left; }");
            out.println("th { background-color: #3498db; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("a { display: inline-block; margin: 10px 0; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s ease; }");
            out.println("a:hover { background-color: #2980b9; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h2>Student Signup Information</h2>");
            out.println("<table>");
            out.println("<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Username</th><th>Password</th></tr>");

            PreparedStatement p1 = con.prepareStatement("SELECT * FROM student_signup");
            ResultSet r1 = p1.executeQuery();

            while (r1.next()) {
                String fname = r1.getString(1);
                String lname = r1.getString(2);
                String email = r1.getString(3);
                String uname = r1.getString(4);
                String password = r1.getString(5);

                out.println("<tr>");
                out.println("<td>" + fname + "</td>"); 
                out.println("<td>" + lname + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + uname + "</td>");
                out.println("<td>" + password + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<h2>Student Homepage Information</h2>");
            out.println("<table>");
            out.println("<tr><th>Department</th><th>Year</th><th>Stream</th><th>University</th></tr>");

            PreparedStatement p2 = con.prepareStatement("SELECT * FROM student_homepage");
            ResultSet r2 = p2.executeQuery();

            while (r2.next()) {
                String dept = r2.getString(1);
                String year = r2.getString(2);
                String stream = r2.getString(3);
                String uni = r2.getString(4);

                out.println("<tr>");
                out.println("<td>" + dept + "</td>");
                out.println("<td>" + year + "</td>");
                out.println("<td>" + stream + "</td>");
                out.println("<td>" + uni + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<a href='Delete.html'>Delete Record</a><br>");
            out.println("<a href='update.html'>Update Record</a><br>");
            out.println("<a href='login.html'>Go to Login page</a><br>");
            out.println("<a href='signup.html'>Go to Signup page</a><br>");

            out.println("</body>");
            out.println("</html>");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                Class.forName("org.mariadb.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/website");
                Statement stmt = conn.createStatement();
                
                String selectUser = String.format("select count(*) from users where username = \"%s\"", username);
                ResultSet rs1 = stmt.executeQuery(selectUser);
                rs1.next();
                
                String selectPassword = String.format("select password from users where username = \"%s\"", username);
                ResultSet rs2 = stmt.executeQuery(selectPassword);
                rs2.next();
                
                
                if (rs1.getString(1).equals("1") && rs2.getString(1).equals(password)) {
                    Cookie c = new Cookie("username", username);
                    response.addCookie(c);
                    response.sendRedirect("index.html");
                    response.getWriter().println("Login successful");
                }
                else {
                    response.getWriter().println("Login failed");
                }
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

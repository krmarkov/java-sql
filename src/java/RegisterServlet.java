import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/website");
                    Statement stmt = conn.createStatement();
                    String q = String.format("INSERT INTO users VALUES (\"%s\", \"%s\", \"%s\")", username, password, email);
                    ResultSet rs = stmt.executeQuery(q);
                    conn.close();
                    
//                    while(rs.next()) {
//                        int id = rs.getInt(1);
//                        String usernameDb = rs.getString(2);
//                        response.getWriter().println(id + " " + usernameDb);
//                    }
                } 
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (SQLException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.getWriter().println("Registration successful");
            }
    }

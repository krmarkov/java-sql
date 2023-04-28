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

@WebServlet(urlPatterns = {"/ItemServlet"})
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
                String username = "";
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies)
                {
                    if (c.getName().equals("username"))
                    {
                        username = c.getValue();
                    }
                }
                String webpage = """
                                 <!DOCTYPE html>
                                 <html>
                                 <head>
                                 </head>
                                 <body>
                                       Hello %s
                                 </body>
                                 </html>
                                 """.formatted(username);
                              
                response.getWriter().println(webpage);
    }
}

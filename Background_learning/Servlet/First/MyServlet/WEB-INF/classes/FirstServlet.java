import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class FirstServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
            writer.println("<body>");
        writer.println("<H1>Welcome to the world of Servlet and most importantly understading server applications and REST api</H1>");
            writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }

}
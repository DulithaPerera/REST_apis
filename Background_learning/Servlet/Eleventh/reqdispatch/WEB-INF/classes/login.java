import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class login extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

            String password = request.getParameter("password");
            if (password.equals("Servlet") ) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome_page");
                requestDispatcher.forward(request, response);
            }
            else {
                printWriter.println("<p style='color:blue'><i>Error in password try again</i></p>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
                requestDispatcher.include(request, response);
            }

    }
}
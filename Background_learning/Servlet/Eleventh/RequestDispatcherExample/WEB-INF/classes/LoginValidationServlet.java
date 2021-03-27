import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginValidationServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        try{
            String password = request.getParameter("password");
            if (password.equals("Servlet") ) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome_page");
                requestDispatcher.forward(request, response);
            }
            else {
                printWriter.println("<p style='color:red'><i>Error in password try again</i></p>");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
                requestDispatcher.include(request, response);
            }
        }
        catch (Exception e){
            printWriter.println(e);
        }
        finally {
            printWriter.close();
        }
    }
}

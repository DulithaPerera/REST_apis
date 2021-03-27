import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SecondServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<html>" +
                "<head><title>Second Servlet</title></head>" +
                "<body>" +
                "<p><b>Second Servlet class is loaded and done dand dusted</b></p>" +
                "</body>" +
                "</html>");

        printWriter.close();
    }
}
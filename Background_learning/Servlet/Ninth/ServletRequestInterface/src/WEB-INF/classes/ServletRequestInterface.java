
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletRequestInterface extends HttpServlet {

        public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String name = request.getParameter("name");
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            printWriter.println("<html>"+
            "<head><title>Name get page</title></head>"+
            "<body>"+
            "   <h1>Name get page - ServletRequestInterface</h1>"+
                "<br>"+
                "<hr>"+
                "<p>your name is"+name+" </p>"+
            "</body>"+
            "</html>");

            printWriter.close();
        }
}
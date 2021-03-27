import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class welcome extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>\n" +
                "    <head>\n" +
                "        <title>Welcome-page</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <p>Welcome <b style='color:blue'>"+username+"</b> to the world of RequestDispatcher \n"+
                "    </body>\n" +
                "</html>");
        printWriter.close();

    }
}
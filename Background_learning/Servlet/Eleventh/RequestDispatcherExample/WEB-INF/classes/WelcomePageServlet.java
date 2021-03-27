import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class WelcomePageServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>\n" +
                "    <head>\n" +
                "        <title>Welcome-page</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Welcome Page - Request Dispatcher Example</h1>\n" +
                "        <hr>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <p>Welcome <b style='color:blue'>"+username+"</b> to the world of RequestDispatcher \n"+
                "        <br>\n" +
                "        <div>\n" +
                "            <img src=\"images/Ocean_Turtle_HD_Wallpaper.jpg\" width=\"800px\" height=\"420px\">\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>");
        printWriter.close();

    }
}
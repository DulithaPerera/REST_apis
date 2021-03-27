import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AttributesServlet extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            //getting the site page name and page title
            String web_page_name =  getServletContext().getInitParameter("web_page_name");
            String page_title = getServletConfig().getInitParameter("page_title");

            //writing page content
            printWriter.println("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>"+page_title+" | "+web_page_name+"</title>\n" +
                    "</head>");
            printWriter.println("<h1 style=\"color: darkred; background-color: darkkhaki; border: brown; border-bottom-style: solid\">"+web_page_name+"</h1>\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "    <h2 style=\"color: darkorange; alignment: center\">"+page_title+"</h2>");

            //getting the username and password attributes
            String username = (String)getServletContext().getAttribute("currentUsersName");
            String userPassword = (String) getServletContext().getAttribute("currentUsersPassword");
            printWriter.println("<br><br>");
            printWriter.println("<h2 style=\"color:green\">Welcome "+username+" to the Revision Basic site </h2>");
            printWriter.println("<p style='font-size:24px'>"+username+", your password is "+userPassword+" </>");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("attributes.html");
            requestDispatcher.include(request, response);

            printWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();

            //getting the site page name and page title
            String web_page_name =  getServletContext().getInitParameter("web_page_name");
            String page_title = getServletConfig().getInitParameter("page_title");

            //writing page content
            printWriter.println("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>"+page_title+" | "+web_page_name+"</title>\n" +
                    "</head>");
            printWriter.println("<h1 style=\"color: darkred; background-color: darkkhaki; border: brown; border-bottom-style: solid\">"+web_page_name+"</h1>\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "    <h2 style=\"color: darkorange; alignment: center\">"+page_title+"</h2>");

            //getting the username and password attributes
            String username = (String)getServletContext().getAttribute("currentUsersName");
            String userPassword = (String) getServletContext().getAttribute("currentUsersPassword");
            printWriter.println("<br><br>");
            printWriter.println("<h2 style=\"color:green\">Welcome "+username+" to the Revision Basic site </h2>");
            printWriter.println("<p style='font-size:24px'>"+username+", your password is "+userPassword+" </>");


            RequestDispatcher requestDispatcher = request.getRequestDispatcher("attributes.html");
            requestDispatcher.include(request, response);

            printWriter.close();
        }
        catch (Exception e) {
                e.printStackTrace();
        }

    }
}
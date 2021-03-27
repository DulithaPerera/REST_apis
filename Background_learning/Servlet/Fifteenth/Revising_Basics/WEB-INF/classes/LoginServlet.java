import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.regex.*;

public class LoginServlet extends HttpServlet {
//    private ServletContext servletContext = getServletContext();
    public void  doGet ( HttpServletRequest request, HttpServletResponse response) {

        try{
            //getting parameter values stored in web.xml
            //context parameters (ServletContext)
            ServletContext context = getServletContext();
            String web_page_name = context.getInitParameter("web_page_name");
            //init - parameters (servletConfig)
            String page_title = getServletConfig().getInitParameter("page_title");

            //writing to the response
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>"+page_title+" | "+web_page_name+"</title>\n" +
                    "</head>");
            printWriter.println("<h1 style=\"color: darkred; background-color: darkkhaki; border: brown; border-bottom-style: solid\">"+web_page_name+"</h1>\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "    <h2 style=\"color: darkorange; alignment: center\">"+page_title+"</h2>");
            //including the login.html page

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
            requestDispatcher.include(request, response);

            printWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            //checking for validity of password
            if ( ! Pattern.matches("^[a-zA-Z0-9 ]{5,}$", request.getParameter("password")) ) {
                printWriter.println("<h3 style=\"color:red\"><b> Password is not Valid. Try Again!!!</b></h3>");
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
//                requestDispatcher.include(request,response);
                this.doGet(request, response);

            }
            //setting name and password as attributes
            else {
                ServletContext servletContext = getServletContext();
                servletContext.setAttribute("currentUsersName", request.getParameter("username"));
                servletContext.setAttribute("currentUsersPassword", request.getParameter("password"));
//                printWriter.println("Welcome "+request.getParameter("username")+" to the Revision Basics website!!!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("attributes_page");
                requestDispatcher.forward(request, response);
            }

            printWriter.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
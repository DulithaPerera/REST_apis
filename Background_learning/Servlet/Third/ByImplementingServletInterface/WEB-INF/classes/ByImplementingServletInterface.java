import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ByImplementingServletInterface implements Servlet {
    ServletConfig config = null;

    public void init(ServletConfig config) {
        this.config = config;
    }

    public void service (ServletRequest request, ServletResponse response)  throws IOException, ServletException{
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<title>Using Servlet Interface</title>");
        printWriter.println("</head>");

        printWriter.println("<body>");
        printWriter.println("<p><i>page created using Servlet interface</i></p>");
        printWriter.println("</body>");
        printWriter.println("</html>");

        printWriter.close();

    }

    public void destroy() {
        System.out.println("Servlet is destroyed");
    }

    public ServletConfig getServletConfig () {
        return config;
    }

    public String getServletInfo() {
        return "copyright 2021 - 01 - 27";
    }
}
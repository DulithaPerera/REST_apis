import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class BioData extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<html>  <body>");
        writer.println("<h1>This shows the Bio data glimpse of dulitha</h1>");
//        writer.println("<br/>");
        writer.println("<p>name: <i>dulitha perera </i></p>");
        writer.println("<p>occupation : none</p>");
        writer.println("<p>age : very old</p>");
        writer.println("<p>interest: none</p>");
//        writer.println("<hr/>");
//        writer.println( printSequence(5) );
        writer.println("</body> </html>");

        writer.close();
    }

//    public String printSequence (int n) {
//        String s = null;
//        for (int i=0; i<n; i++){
//            s += " "+String.valueOf(i);
//        }
//        return s;
//    }
}
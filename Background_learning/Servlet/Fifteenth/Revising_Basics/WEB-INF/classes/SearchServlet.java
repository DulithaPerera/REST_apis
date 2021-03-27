import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SearchServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        //getting the list of image names in internal images folder
        File imagesFolder = new File ("/opt/tomcat/latest/webapps/Revision_Basics/Images");
        File [] imageList = imagesFolder.listFiles();
        String searchQuery = request.getParameter("search_field");
        boolean matchesFound = false;
        ArrayList<String> matchedList  = new ArrayList<>();
        for (File i: imageList) {
            if ( Pattern.matches("^.*"+searchQuery+".*$", i.getName() ) ) {
                matchesFound = true;
                matchedList.add(i.getName() );
            }
        }

        if (! matchesFound ) {
            response.sendRedirect("http://google.com/search?q="+searchQuery);
        }
        else {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            //getting the web site name and web page name
            String web_page_name = getServletContext().getInitParameter("web_page_name");
            String page_title = getServletConfig().getInitParameter("page_title");

            //writing content to the web page
            printWriter.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">");
            printWriter.println("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>"+page_title+" | "+web_page_name+"</title>\n" +
                    "</head>" +
                    "<body>");
            printWriter.println("<h1 style=\"color: darkred; background-color: darkkhaki; border: brown; border-bottom-style: solid\">"+web_page_name+"</h1>\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "    <h2 style=\"color: darkorange; alignment: center\">"+page_title+"</h2>");
            printWriter.println("<br>" +
                    "<form action=\"search_result_page\" method=\"get\" >\n" +
                    "        <input type=\"text\" name=\"search_field\"> <input style=\"color: wheat; background-color: green\" type=\"submit\" value=\"Search\">\n" +
                    "    </form>" +
                    "<br>");
            printWriter.println("<table>");
            printWriter.println("<tr>");
            int count=0;
            for (String match: matchedList) {
                if (count%3 == 0){
                    printWriter.println("</tr>");
                    printWriter.println("<tr>");
                }
                printWriter.println("<td><img src='Images/"+match+"' style='width:300px; length:300px'></td>");
                count++;
            }
            if (! (count%3 == 0) ){
                printWriter.println("</tr>");
            }
            printWriter.println("</table>");
            printWriter.println("<a href=\"index.html\" style=\"background-color:blue;color:white\">Go Home</a>" +
                    "</body>" +
                    "</html>");

            printWriter.close();
        }

    }
}
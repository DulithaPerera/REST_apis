
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class InternalSearchServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String items [] = {"deer","bear", "tiger", "lion", "horse", "tortoise", "penguin", "salmon"};

        String searchWord = request.getParameter("searchWord");
        boolean matchFoundInternally = false;
        for (String item: items){
            if (searchWord.equalsIgnoreCase(item)) {

                    response.setContentType("text/html");
                    PrintWriter printWriter = response.getWriter();
                    printWriter.println("<h1> your search word <i style='color:red'>"+searchWord+"</i> is found<h1>");
                    printWriter.println("<br><br><img src='images/the_male_african_lion-1920x1080.jpg' width='800px' height='420px' alt='image of the search resutl'>");
                    printWriter.close();
                    request.getRequestDispatcher("Search.html").include(request, response);
                    matchFoundInternally = true;
                    return;
            }
        }
        if (!matchFoundInternally) {
            response.sendRedirect("https://www.google.com/search?q="+searchWord);
        }

    }
}
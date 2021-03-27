import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletFile extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<html>" +
                                "<head><title>User Account Page</title><head>"+
                                "<body>"+
                                    "<h1>User Account Page</h1>"+
                                    " <ol>\n" +
                        "            <li>thats all folks</li>\n" +
                        "            <li>see you next time</li>\n" +
                        "            <li>bye bye</li>\n" +
                        "        </ol>"+
                                "</body>"+
                            "</html>");
//        File f = new File("WelcomeFileList/UserAccount.html");
//        FileReader fileReader = new FileReader(f);
//        int i;
//        while ((i = fileReader.read()) != -1) {
//            printWriter.print((char)i);
//        }
//        fileReader.close();
        printWriter.close();
    }
}
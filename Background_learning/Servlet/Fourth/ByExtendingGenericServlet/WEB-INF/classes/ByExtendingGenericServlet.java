import  javax.servlet.*;
import java.io.*;

public class ByExtendingGenericServlet extends GenericServlet {

    public void service (ServletRequest request, ServletResponse response) {
        try {
            response.setContentType("text/html");

            PrintWriter printWriter = response.getWriter();

            printWriter.write("<html>\n" +
                    "    <head>\n" +
                    "        <title>Servlet World</title>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <h1>Welcome to the world of Servlet</h1>\n" +
                    "        <hr>\n" +
                    "        <table>\n" +
                    "            <tr>\n" +
                    "                <td>name</td>\n" +
                    "                <td>class</td>\n" +
                    "                <td>number</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>fdfd</td>\n" +
                    "                <td>dfdfd</td>\n" +
                    "                <td>dfdfd</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>YoYOYO</td>\n" +
                    "                <td>YoYOYO</td>\n" +
                    "                <td>YoYOYO</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>fdfd</td>\n" +
                    "                <td>dfdfd</td>\n" +
                    "                <td>dfdfd</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>HHHHHH</td>\n" +
                    "                <td>BBBBBB</td>\n" +
                    "                <td>FDFDFDDFD</td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "    </body>\n" +
                    "</html>");
//            FileReader fileReader = new FileReader(new File("../../home.html"));
//            int i;
//            while ( (i = fileReader.read() ) != -1 ) {
//                printWriter.print((char)i);
//            }
            printWriter.flush();
//
//            fileReader.close();
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
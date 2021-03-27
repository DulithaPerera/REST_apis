import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.simple.*;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Base64;
import java.util.Enumeration;

public class StudentServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        //displaying user info
        try{
            //checking if Authorization header is set; if auth header is missing unauthorized access error is send
             this.isAuthorizationHeaderPresent(request, response);

            //verifying access - using token
            String authHeader = request.getHeader("Authorization");
            String authEncoded = authHeader.substring( authHeader.indexOf(" ") + 1);
            String token = new String (Base64.getDecoder().decode(authEncoded) );
            int loggedInIndexNo = TokenStore.getInstance().getIndexNoOfRelevantToken(token);  // returns indexNo if token is valid; else returns 0;

            if ( loggedInIndexNo == 0 ){ //if not valid token is available unauthorized access error is thrown
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String url  = request.getRequestURI(); //getting the uri to identify, whose details should be displayed
            int indexNo = Integer.parseInt( url.substring("/Student/".length()) );

            if ( loggedInIndexNo == indexNo) { // if loggedinIndexNo matches indexNo student details are retrieved from the db
                Student student = StudentStore.getInstance().getStudent(indexNo);

                //writing data to a jsonObject
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("indexNo", student.getIndexNo() );
                jsonObject.put("name", student.getName() );
                jsonObject.put("grade", student.getGrade() );

                //converting jsonObject to string
                StringWriter jsStringWriter = new StringWriter();
                jsonObject.writeJSONString(jsStringWriter);
                String jsonData = jsStringWriter.toString();

                //sending data to client
                response.getWriter().println(jsonData);
            }
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
//            response.getWriter().println("request received");//TESTING
        }
        catch(Exception e) {
            e.printStackTrace();

        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        //entering, deleting, or editing student info
        try{
            //checking if Authorization header is set
            this.isAuthorizationHeaderPresent(request, response);
            //verifying access - using token
            String authHeader = request.getHeader("Authorization");
            String token = new String ( Base64.getDecoder().decode( authHeader.substring(authHeader.indexOf(" ")+1) ) );
            int loggedInIndexNo = TokenStore.getInstance().getIndexNoOfRelevantToken(token); // returns indexNo if token is valid; else returns 0;

            if (loggedInIndexNo == 0) {  //if not valid token is available unauthorized access error is thrown
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            //getting request parameters
            String function = request.getParameter("function"); // which is it -> add, edit or delete
            int indexNo = Integer.parseInt( request.getParameter("indexNo") );
            String name = request.getParameter("name");
            String grade = request.getParameter("grade");
            String password = request.getParameter("password");

            //creating a Student object
            Student student = new Student(indexNo, password, name, grade);
            String reply =  null;
            switch (function) {
                case "add" :
                    reply = StudentStore.getInstance().addStudent(student);
                    break;

                case "edit" :
                    if ( loggedInIndexNo == indexNo ) {
                        reply = StudentStore.getInstance().editStudent(student);
                        break;
                    }
                    else{
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }

                case "delete" :
                    if ( loggedInIndexNo == indexNo) {
                        reply =  StudentStore.getInstance().deleteStudent(student);
                        break;
                    }
                    else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
            }
            //sending response to the client if user has access to the function
            response.getWriter().println(reply);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void isAuthorizationHeaderPresent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> headerNames = request.getHeaderNames();
        while ( headerNames.hasMoreElements() ){
            String headerName = headerNames.nextElement();
            if ( "Authorization".equalsIgnoreCase(headerName) ) {
                return;
            }
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
//        PrintWriter printWriter = response.getWriter();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            printWriter.println(headerNames.nextElement() );
//        }
//        printWriter.close();
    }
}

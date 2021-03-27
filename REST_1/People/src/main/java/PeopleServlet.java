import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;

import org.json.simple.*;

public class PeopleServlet extends HttpServlet{

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //identifying the specific person for which data should be displayed from the request url
        String requestUrl = request.getRequestURI();

//        //getting loging info
//        String authInfo = request.getHeader("Authorization");
//        String authEncoded = authInfo.substring( authInfo.indexOf(" ")+1 );
//        String authDecoded = new String( Base64.getDecoder().decode(authEncoded) );
//        String loggedInUsername = authDecoded.substring(0,authDecoded.indexOf(":") );
//        String loggedInPassword = authDecoded.substring(authDecoded.indexOf(":")+1 );

        //authentication using token
        String authHeader = request.getHeader("Authorization");
        String authDecoded = authHeader.substring(authHeader.indexOf(" ")+1);
        String token = new String( Base64.getDecoder().decode(authDecoded) );

        String name = requestUrl.substring("/People/".length() );
        //fetching the data from DataStore
        People person = DataStore.getInstance().getDataRow(name);

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        //creating a json object
        JSONObject jsonObject = new JSONObject();
//        getting user password from url (Method used if the passowrd is send with the url (authorization header is not used in this method))
//        String password = request.getParameter("password");

        //authenticating via token
        String loggedInUsername = TokenStore.getInstance().getUsername(token);
        if ( person != null  && person.getName().equals(loggedInUsername) ) {
                jsonObject.put("name", person.getName() );
                jsonObject.put("job", person.getJob() );
                jsonObject.put("age", person.getAge() );
                jsonObject.put("password", person.getPassword() );

            StringWriter stringWriter = new StringWriter();
            jsonObject.writeJSONString(stringWriter);

            String jsonString  = stringWriter.toString();
            printWriter.println(jsonString);
        }
        else {
            printWriter.println("{}");
        }
        printWriter.close();
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //getting parameters for post
        String name = request.getParameter("name");
        String job = request.getParameter("job");
        int age = Integer.parseInt(request.getParameter("age") );
        String password = request.getParameter("password");


//        //getting logging data for access validation (using Authorization header)
//        String authHeader = request.getHeader("Authorization");
//        String authEncoded = authHeader.substring( authHeader.indexOf(" ")+1 );
//        String authDecoded = new String( Base64.getDecoder().decode(authEncoded) );
//        String loggedInUsername = authDecoded.substring(0, authDecoded.indexOf(":") );
//        String loggedInPassword = authDecoded.substring(authDecoded.indexOf(":")+1 );

        //authentication using token
        String authHeader = request.getHeader("Authorization");
        String tokenEncoded = authHeader.substring(authHeader.indexOf(" ")+1 );
        String token = new String( Base64.getDecoder().decode(tokenEncoded) );
        String loggedInUsername = TokenStore.getInstance().getUsername(token);

        //checking if person has editing access (people can only edit their own data)
        People personToEdit = DataStore.getInstance().getDataRow(name);
        if (loggedInUsername == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        else if (personToEdit == null ) {  //&& personToEdit.getPassword().equals(password)

            //saving new person in the DataStore
            String reply = DataStore.getInstance().addPerson(name, job, age, password);
            //sending a response msg
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(reply);
            printWriter.println("{ name:"+name+" job:"+job+" age:"+age+" password:"+password+" }");
            printWriter.close();
        }
        else if ( personToEdit.getName().equals(loggedInUsername) ){
            //replacing person with new values
            String reply = DataStore.getInstance().editPerson(name, job, age, password);
            //sending a response msg
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(reply);
            printWriter.println("{ name:"+name+" job:"+job+" age:"+age+" password:"+password+" }");
            printWriter.close();
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

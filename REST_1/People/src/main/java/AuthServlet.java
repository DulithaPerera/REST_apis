import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.Base64;

public class AuthServlet extends HttpServlet{

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //getting authentication details via Authorization header
        String authHeader = request.getHeader("Authorization");
        String authEncoded = authHeader.substring(authHeader.indexOf(" ")+1 );
        String authDecoded = new String( Base64.getDecoder().decode(authEncoded) );
        String loggedInUsername = authDecoded.substring(0, authDecoded.indexOf(":"));
        String loggedInPassword = authDecoded.substring(authDecoded.indexOf(":")+1);

        //validating credentials
        People person = DataStore.getInstance().getDataRow(loggedInUsername);

        if ( person == null ){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        else if ( !person.getPassword().equals(loggedInPassword) ){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String token = TokenStore.getInstance().putToken(loggedInUsername);
        response.getWriter().println(token);
    }

//    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//
//        String authHeader = request.getHeader("Authorization");
//        String authEncoded = authHeader.substring(authHeader.indexOf(" ")+1 );
//        String authDecoded = new String ( Base64.getDecoder().decode(authEncoded) );
//        String loggedInUsername = authDecoded.substring(0, authDecoded.indexOf(":") );
//        String loggedInPassword = authDecoded.substring(authDecoded.indexOf(":")+1);
//
//        response.setContentType("text/html");
//        PrintWriter pw = response.getWriter();
//        pw.println("get on people/auth is a hit : authHeader: "+authHeader);
//        pw.println("authEncoded: "+authEncoded);
//        pw.println("authDecoded: "+authDecoded);
//        pw.println("uname: "+loggedInUsername);
//        pw.println("pword: "+loggedInPassword);
//        pw.close();
//    }
}

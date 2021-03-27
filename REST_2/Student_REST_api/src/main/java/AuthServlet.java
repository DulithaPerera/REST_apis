import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Base64;

import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response)  {

        try {
        //getting the Authorization header information
            String authHeader = request.getHeader("Authorization");
            String authEncoded = authHeader.substring(authHeader.indexOf(" ") + 1);
            String authDecoded = new String(Base64.getDecoder().decode(authEncoded));
            int indexNo = Integer.parseInt(authDecoded.substring(0, authDecoded.indexOf(":")));
            String hashedPassword = authDecoded.substring(authDecoded.indexOf(":") + 1);

        //getting a new token for the index
            String token = TokenStore.getInstance().getToken(indexNo, hashedPassword);

            if (token == null || token.equals("") ) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
//            else {
                PrintWriter printWriter = response.getWriter();
                printWriter.println(token);
                printWriter.close();
//            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}

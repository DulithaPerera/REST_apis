
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegistrationServlet extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            //getting the parameters send from the request
            int id = Integer.valueOf(request.getParameter("idNumber") );
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            boolean maritalStatus = Boolean.parseBoolean(request.getParameter("maritalStatus") );
            System.out.println(name+" "+id+" "+dob+" "+address+" "+maritalStatus);

            //sending obtained data to db
            CrudFunctions dbObject = new CrudFunctions();
            dbObject.insertData( id, name, dob, address, maritalStatus);

            //showing all registration data
            response.sendRedirect("allRegistrations");
        }
        catch (Exception e){
            System.out.println(e+"=> registrationServlet ");
        }


    }
}
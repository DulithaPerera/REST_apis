import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteRowServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            //deleting data from db
            int id = Integer.valueOf(request.getParameter("id") );
            CrudFunctions dbObject = new CrudFunctions();
            dbObject.deleteRow(id);

            //loading all registrations page
            response.sendRedirect("allRegistrations");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
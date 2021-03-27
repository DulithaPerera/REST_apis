import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
//import java.sql.ResultSet;

public class AllRegistrations extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        //getting all data from the db
        List<CrudFunctions> registeredList = CrudFunctions.selectAll();

        try {
            //displaying the data
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charsultset=\"UTF-8\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                    "\n" +
                    "    <title>CRUD | Registration Details Page</title>\n" +
                    "</head>\n" +
                    "<body style=\"background-color: lightgreen;width: 1800px; length:100px\">\n" +
                    "    <h1>Registration Details</h1>\n" +
                    "    <br>\n" +
                    "    <table class=\"table table-stripped table-bordered table-hover\">\n" +
                    "        <tr class='row table-info'>\n" +
                    "            <th class='col-sm-2' scope=\"col\">ID</th><th class='col-sm-2'>NAME</th><th class='col-sm-2' scope=\"col\">DATE OF BIRTH</th><th class='col-sm-2'>ADDRESS</th><th class='col-sm-2' scope=\"col\">MARITAL STATUS</th>\n" +
                    "        </tr>\n");
                //printing each row of data
                    Iterator<CrudFunctions> itr = registeredList.iterator();
                    while (itr.hasNext() ) {
                        CrudFunctions results = itr.next();
                        pw.println("<tr class='row'>" +
                                "<td class='col-sm-2'>"+results.id+"</td>" +
                                "<td class='col-sm-2'>"+results.name+"</td>" +
                                "<td class='col-sm-2'>"+results.dob+"</td>" +
                                "<td class='col-sm-2'>"+results.address+"</td>" +
                                "<td class='col-sm-2'>"+results.maritalStatus+"</td>" +
                                "<td class='col-sm-2'>" +
                                "   <a class='btn btn-warning' href='updateRow?id="+results.id+"' >Update</a>"+
                                "   <a class='btn btn-danger' href='deleteRow?id="+results.id+"' >Delete</a>"+
//                                "   <form action='updateRow' method='get'><input type='text' name='id' hidden value='"+results.id+"'><input type='submit' value='Update' class='btn btn-warning'> </form>" +
//                                "   <form action='deleteRow' method='post'><input type='text' name='id' hidden value='"+results.id+"'><input type='submit' value='Delete' class='btn btn-danger'> </form>" +
                                "</td>"+
                                "</tr>");
                    }
                    pw.println("</table>\n" +
                    "\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        }
        catch (Exception e) {
            System.out.println(e+"->all registration");
        }

    }
}
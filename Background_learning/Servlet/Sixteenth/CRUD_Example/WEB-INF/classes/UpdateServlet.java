import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UpdateServlet extends HttpServlet{

    public void doGet (HttpServletRequest request, HttpServletResponse response) {

        try {
            //retrieving row from db
            int id = Integer.valueOf(request.getParameter("id"));
            CrudFunctions dbObject = new CrudFunctions();
            dbObject = dbObject.selectRow(id);

            //displaying row info on a form for updation
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>CRUD | Update Page</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                    "</head>\n" +
                    "<body style=\"background-color: burlywood\">\n" +
                    "    <h1>REGISTRATION UPDATE PAGE</h1>\n" +
                    "    <br>\n" +
                    "\n" +
                    "    <form action=\"updateRow\" method=\"post\" style=\"background-image: url(wp3.jpg)\">\n" +
                    "        <h3> \"Registration Update Form</h3>\n" +
                    "        <br>\n" +
                    "        <div class=\"form-group row\">\n" +
                    "            <label for=\"name\"  class=\"col-sm-2 col-form-label\" >Name</label>\n" +
                    "            <div class=\"col-sm-10\">\n" +
                    "                <input type=\"text\" value='"+ dbObject.name +"' class=\"form-control\" id=\"name\" name=\"name\" placeholder=\"your name\">\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-group row\">\n" +
                    "            <label for=\"idNumber\" class=\"col-sm-2 col-form-label\">ID</label>\n" +
                    "            <div class=\"col-sm-10\">\n" +
                    "                <input type=\"text\" value='" + dbObject.id + "' class=\"form-control\" id=\"idNumber\" name=\"idNumber\" placeholder=\"ID number\" readonly>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-group row\">\n" +
                    "            <label for=\"dob\" class=\"col-sm-2 col-form-label\">Date of Birth</label>\n" +
                    "            <div class=\"col-sm-10\">\n" +
                    "                <input type=\"date\" value='" + dbObject.dob + "' class=\"form-control\" id=\"dob\" name=\"dob\" placeholder=\"Date of Birth\">\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-group row\">\n" +
                    "            <label for=\"address\" class=\"col-sm-2 col-form-label\">Address</label>\n" +
                    "            <div class=\"col-sm-10\">\n" +
                    "                <input type=\"text\" value='" + dbObject.address + "' class=\"form-control\" id=\"address\" name=\"address\" placeholder=\"Address\">\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-group row\">\n" +
                    "            <label class=\"col-sm-2 col-form-label\">Marital Status</label>\n");
            if (dbObject.maritalStatus) {
                pw.println("            <div class=\"form-check form-check-inline\">\n" +
                        "                <input class=\"form-check-input\" type=\"radio\" name=\"maritalStatus\" id=\"married\" value=\"true\" checked>\n" +
                        "                <label class=\"form-check-label\" for=\"married\">Married</label>\n" +
                        "            </div>\n" +
                        "            <div class=\"form-check form-check-inline\">\n" +
                        "                <input class=\"form-check-input\" type=\"radio\" name=\"maritalStatus\" id=\"single\" value=\"false\">\n" +
                        "                <label class=\"form-check-label\" for=\"single\">Single</label>\n" +
                        "            </div>\n");
            } else {
                pw.println("            <div class=\"form-check form-check-inline\">\n" +
                        "                <input class=\"form-check-input\" type=\"radio\" name=\"maritalStatus\" id=\"married\" value=\"true\">\n" +
                        "                <label class=\"form-check-label\" for=\"married\">Married</label>\n" +
                        "            </div>\n" +
                        "            <div class=\"form-check form-check-inline\">\n" +
                        "                <input class=\"form-check-input\" type=\"radio\" name=\"maritalStatus\" id=\"single\" value=\"false\" checked>\n" +
                        "                <label class=\"form-check-label\" for=\"single\">Single</label>\n" +
                        "            </div>\n");
            }

            pw.println("        </div>\n" +
                    "\n" +
                    "        <br><br>\n" +
                    "        <button type=\"submit\" class=\"btn btn-warning\">Update</button>\n" +
                    "        <br>\n" +
                    "        <br>\n" +
                    "    </form>\n" +
                    "\n" +
                    "    <br>\n" +
                    "    <a class=\"btn btn-primary\" href=\"allRegistrations\">Show Registered People</a>\n" +
                    "    <br>\n" +
                    "\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");

            pw.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {

        try {
            //obtaining parameter from form
            int id = Integer.valueOf(request.getParameter("idNumber"));
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            Boolean maritalStatus = Boolean.parseBoolean(request.getParameter("maritalStatus"));
            // executing udate query on db
            CrudFunctions dbObject  = new CrudFunctions();
            dbObject.updateRow(id, name, dob, address, maritalStatus);

            //redirecting show all registration
            response.sendRedirect("allRegistrations");

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
package com.example.AttributesAndServletContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

public class Attributes extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        ServletContext context =  getServletContext();
        printWriter.println("<h3 style='color:green'>Below is the second title</h3> \n"+context.getInitParameter("secondTitle"));
        printWriter.println("<h3 style='color:blue'>Below are the attributes</h3>");

        Enumeration<String> attributes = context.getAttributeNames();
        String attributeName = "";
        while (attributes.hasMoreElements() ){
            attributeName = attributes.nextElement();
            printWriter.println("<p>attribute name: "+attributeName+"|  attribute value: " +context.getAttribute(attributeName)+"</p>");
        }

        printWriter.close();
    }
}

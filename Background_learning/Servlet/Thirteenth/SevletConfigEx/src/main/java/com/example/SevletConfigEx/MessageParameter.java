package com.example.SevletConfigEx;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//import javax.servlet.annotation.*;

//@WebServlet(name = "getMessageParameter", value = "/message_parameter")
public class MessageParameter extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //initialising message value
        ServletConfig servletConfiguration = getServletConfig();
        message = servletConfiguration.getInitParameter("message");
        Enumeration<String> names = servletConfiguration.getInitParameterNames();

        // Printing the message value at response;
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> your message value at initiation is <b><i>" + message + "</i></b></h1>");
        out.println("<hr>");
        out.println("<br>");

        out.println("<ul>");
        String name = null;
        while (names.hasMoreElements() ) {
            name = (String) names.nextElement();
            out.println("<li>here is the "+name+": "+servletConfiguration.getInitParameter(name)+"</li>");
        }
        out.println("</body></html>");
        out.close();
    }

    public void destroy() {
    }
}
package com.example.AttributesAndServletContext;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ServletContextExample extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        ServletContext context = getServletContext();
        String secondTitle = context.getInitParameter("secondTitle");
        printWriter.println("<h2 style='color:red'>"+secondTitle+"</h2>");
        printWriter.println("<a href='get_attributes'>see the attributes</a>");

        context.setAttribute("honor","no honor for me bro");
        context.setAttribute("occupation","no occupation for me bro, YET!");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.include(request, response);

        printWriter.close();

    }
}

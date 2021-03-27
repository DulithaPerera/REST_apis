package com.demo.CustomURLname;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.*;

@WebServlet(name = "custom", value = "/custom-page")
public class Custom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>" +
                "<head>customURL-customPage</head>" +
                "<body>" +
                "<h1>this is the custom page of CUstom URL application</h1>" +
                "<hr/>" +
                "<br>" +
                "<p>hope ur enjoying the stay, have fun bros</p></body>" +
                "<br>" +
                "<hr>" +
                "<a href='oldMethod-page'>Click here for OldMethod</a>" +
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

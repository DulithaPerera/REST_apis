package com.demo.CustomURLname;

import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.*;

public class OldMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>" +
                "<head>oldMethod-oldMehtod-page</head>" +
                "<body>" +
                "<h1>this is the OldMethod page of OldMethod URL APPLICATION</h1>" +
                "<hr/>" +
                "<br>" +
                "<p>hope ur enjoying the direction . HAVE FUN LADS!!!</p></body>" +
                "<br>" +
                "<hr>" +
                "<h1>end of OLDMETHOD PAGE</h1>"+
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

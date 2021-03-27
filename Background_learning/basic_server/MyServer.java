package BasicServer;

import java.net.*;
import java.io.*;

public class MyServer {

    public static void main (String args [] ) {

        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();

            OutputStream outStream = s.getOutputStream();
            String message ="server is now connected";
            outStream.write(message.getBytes() );

            outStream.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }


    }
 }
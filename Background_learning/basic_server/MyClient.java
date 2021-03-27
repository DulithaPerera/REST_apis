package BasicClient;

import java.net.*;
import java.io.*;

public class MyClient {
    public static void main (String args[]) {

        try {
            Socket s = new Socket("localhost",6666);
            InputStream inStream = s.getInputStream();
            int m;
            while ( (m = inStream.read() ) != -1) {
                System.out.println((char) m);
            }
            inStream.close();
            s.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

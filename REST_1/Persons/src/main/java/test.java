import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Scanner;
import java.net.*;

public class test {
    public static void main (String args[]) throws IOException, Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("enter credentials");
        System.out.println("enter username:");
        String username = sc.nextLine().trim();
        System.out.println("enter password:");
        String password = sc.nextLine().trim();

//        String token = getAuthToken(username, password);
//        System.out.println(token);
        getAuth( username, password);

    }

    public static String getAuthToken( String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/People/auth");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            String auth = username + ":" + password;
            String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes());
            String authHeader = "Basic "+authEncoded;
            con.setRequestProperty("Authorization", authHeader);
//            con.setDoOutput(true);

            //checking if request is a hit
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                Reader inStream = new InputStreamReader(con.getInputStream());
                int data;
                String token = "";
                while ((data = inStream.read()) != -1) {
                    token += (char) data;
                }

                return token;
            } else if (responseCode == 401) {
                System.out.println("authentication failed: unauthorised access");
            } else {
                System.out.println("authentication failed:");
                System.out.println(responseCode);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void getAuth(String username, String password)  throws Exception {
        URL url = new URL("http://localhost:8080/People/auth");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        String auth = username + ":" + password;
        String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic "+authEncoded;
        con.setRequestProperty("Authorization", authHeader);

        int responseCode = con.getResponseCode();
        if (responseCode == 200 ){
            Reader reader = new InputStreamReader( con.getInputStream() );
            int data;
            while (( data = reader.read()) != -1 ){
                System.out.print( (char) data);
            }
            reader.close();
        }
        else {
            System.out.println(responseCode);
        }
    }
}

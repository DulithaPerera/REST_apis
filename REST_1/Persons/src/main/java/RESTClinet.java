import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
//import org.json.simple.*;

public class RESTClinet {
    static String token ="";
    public static void main (String args[]) {

        try {
            Scanner sc = new Scanner(System.in);


            //getting auth details to get auth token
            System.out.println("Please enter your login credentials:");
            System.out.println("enter your username:");
            String userName = sc.nextLine().trim();
            System.out.println("enter your password:");
            String userPassword = sc.nextLine().trim();

            //getting the token for authentication
            token = getAuthToken(userName, userPassword);
            if ( token == null ){
                System.exit(1);
            }
            token = token.trim();

            // choosing between getting or setting data
            System.out.print("Hello there! \n");
            System.out.println("What do you want to do? 1)retrieve data 2)set data 3)get auth token -> choose a single option number:");
            int option = sc.nextInt();
            sc.nextLine();
            if (option == 1 ) {
                System.out.println("type the name of the user of whom data must be retrieved of:");
                String name = sc.nextLine().trim();
//                System.out.println("enter the password:");
//                String password = sc.nextLine().trim();

                //getting the data as a String
                String jsonString = getPersonData(name);
                System.out.println(jsonString);

            }
            else if (option ==2 ) {
                System.out.println("ok. Lets get the details of the new person first ");
                System.out.println("enter the name:");
                String name = sc.nextLine().trim();
                System.out.println("enter the job:");
                String job = sc.nextLine().trim();
                System.out.println("enter the age:");
                int age = Integer.parseInt(sc.nextLine().trim() );
                System.out.println("enter the password:");
                String password = sc.nextLine().trim();

                //sending data to the db
                setPersonData(name, job, age, password);

            }
            else {
                System.out.println("wrong option number! try again later.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPersonData(String name) {
        String jsonString = "";
        try {
            //sending a get request
//            URL url = new URL("http://localhost:8080/People/" + URLEncoder.encode(name,"UTF-8")+"?password="+URLEncoder.encode(password, "UTF-8"));
            URL url = new URL("http://localhost:8080/People/" + URLEncoder.encode(name,"UTF-8")  );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

////            setting the Authorization header
//            String auth = name+":"+password;
//            String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes() );
//            String authHeader = "Basic "+authEncoded;
//            con.setRequestProperty("Authorization", authHeader);

            //auth using token
            String authEncoded = Base64.getEncoder().encodeToString(token.getBytes() );
            String authHeader = "Bearer "+authEncoded;
            con.setRequestProperty("Authorization", authHeader);

            //checking if request is a hit
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                Reader reader = new InputStreamReader(con.getInputStream());
                int data;
                while ( (data = reader.read()) != -1) {
                    jsonString += (char)data;
                }
                reader.close();
            }
            else if (responseCode == 401 || responseCode == 404){
                System.out.println("resource not found");
                System.out.println(responseCode);
            }
            else {
                System.out.println("GET failed");
                System.out.println(responseCode);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void setPersonData(String name, String job, int age, String password) {

        try {
            //creating a connection
            URL url = new URL("http://localhost:8080/People/" + URLEncoder.encode(name, "UTF-8") );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

//            //setting Authorization header
//            String auth = name+":"+password;
//            String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes() );
//            String authHeader = "Basic "+authEncoded;
//            con.setRequestProperty("Authorization", authHeader);

            //auth using token
            String authEncoded = Base64.getEncoder().encodeToString(token.getBytes() );
            String authHeader = "Bearer "+authEncoded;
            con.setRequestProperty("Authorization", authHeader);

            String postData = "name="+ URLEncoder.encode(name,"UTF-8");
            postData += "&job="+URLEncoder.encode(job, "UTF-8");
            postData += "&age="+URLEncoder.encode(String.valueOf(age),"UTF-8" );
            postData +="&password="+URLEncoder.encode(password,"UTF-8");

            con.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream() );
            outputStreamWriter.write(postData);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            //checking if request is a hit
            int responseCode = con.getResponseCode();
            if ( responseCode == 200 ) {
//                System.out.println("POST was successful");
                DataInputStream inStream = new DataInputStream(con.getInputStream() );
                int data;
                while ( (data = inStream.read() ) != -1 ) {
                    System.out.print( (char) data);
                }
            }
            else if (responseCode == 401) {
                System.out.println("POST action failed");
            }
            else {
                System.out.println("POST was unsuccessful");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getAuthToken( String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/People/auth");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            //setting the auth header
            String auth = username + ":" + password;
            String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
            con.setRequestProperty("Authorization", authHeader);

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
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


}

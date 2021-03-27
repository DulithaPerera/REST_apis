import java.nio.Buffer;
import java.util.Scanner;
import java.util.Base64;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException;

//import java.io.Console;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import org.json.simple.*;

public class StudentClient {
    static String token = null;
    static int indexNo = 0;

    public static void main (String args[]) {

        Scanner sc = new Scanner(System.in);

        while (token == null) {
            System.out.println("Hello there!!");
            System.out.println("Do you wish to continue this program (type 'yes' or 'no'):");
            String continueProgram = sc.nextLine().trim();
            if (continueProgram.equalsIgnoreCase("no") ){
                System.out.println("Exiting program.... Have a good day!");
                System.exit(0);
            }
            System.out.println("Please enter your Login Credentials");
            System.out.println("index Number:");
            int loginIndexNo = sc.nextInt();

            sc.nextLine();
            System.out.println("Password:");
            //console not working with IDE
//        Console console = System.console();
//            String password = new String(console.readPassword());
//            char [] passwordbytes = console.readPassword();
//            String password = passwordbytes.toString();
            String password = sc.nextLine().trim();
            String hashedPassword = getHashedPassword(password);
            //getting a authentication token
            token = login(loginIndexNo, hashedPassword);
        }

            while (token != null) {
               //requesting user the type of action that needs to be done
                System.out.println("------------------------------------------------");
               System.out.println("What action do you want to perform. Please choose the correct number of the action");
               System.out.println("1->view Student details \t 2->add new Student");
               System.out.println("3->edit Student details \t 4->delete a Student");
               System.out.println("5-> exit application");
               int action = sc.nextInt();
               sc.nextLine();
               switch (action) {
                   case 1 :
                       getStudentInfo();
                       break;
                   case 2 :
                       addNewStudent();
                       break;
                   case 3 :
                       editStudent();
                       break;
                   case 4 :
                       deleteStudent();
                       break;
                   case 5 :
                       System.out.println("Exiting program.... Have a good day!");
                       System.exit(0);
                   default :
                       System.out.println("wrong action number please try again");
               }
            }
    }

    private static String getHashedPassword (String password) {

        try {
            //getting the sha-256 value
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte [] sha256BytesArray =  md.digest(password.getBytes() );

            //converting bytes array to BigInteger signum representation
            BigInteger bigInt =  new BigInteger(1, sha256BytesArray);
            //converting bigInt to hexadecimal
            StringBuilder hexa = new StringBuilder( bigInt.toString(16) );
            //pad with leading zeros
            while (hexa.length() < 32){
                hexa.insert(0,"0");
            }
            //converting to String
            String hashedPassword = hexa.toString();
//            System.out.println(hashedPassword); //TESTING

            return hashedPassword;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String login(int indexNo, String hashedPassword) {

        try {
            //creating the Authorization header
            String auth = indexNo + ":" + hashedPassword;
//            System.out.println("auth sent by client: "+auth); // TESTING
            String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes());
            String authHeader = "Basic " + authEncoded;
//            System.out.println(authHeader); //TESTING

            //establishing connection with server url
            URL url = new URL("http://localhost:8080/Student/auth");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Authorization", authHeader);

            //receiving the responseCode from server
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                int data;
                String token = "";
                while ( (data = bufferedReader.read()) != -1) {
                    token += (char) data;
                }
                token = token.trim();
                System.out.println("you successfully logged in.");
                System.out.println("your session TOKEN is :"+token);
                return token;
            }
            else if ( responseCode == 401 ){
                System.out.println("login failed with : unaauthorized access: 401");
            }
            else {
                System.out.println("login failed with : "+responseCode);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void getStudentInfo(){
        try {
            Scanner sc = new Scanner(System.in);
            //getting the name of the student to be viewed
            System.out.println("Enter the index Number of the student:");
            indexNo = sc.nextInt();
            sc.nextLine();

            //creating Authorization header
            String tokenEncoded = Base64.getEncoder().encodeToString(token.getBytes() );
            String authHeader = "Bearer "+tokenEncoded;
//            System.out.println("authHeader: "+authHeader); //TESTING

            //establishing connection with the server url
            URL url = new URL("http://localhost:8080/Student/" + indexNo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", authHeader);

            System.out.println("retrieving student data. please wait"); //TESTING
            //getting the response
            int responseCode = connection.getResponseCode();
//            System.out.println("response code: "+responseCode); //TESTING
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
                //if response is a hit; getting the data sent by the server
                String data;
                String jsonString = "";
                while ((data = bufferedReader.readLine()) != null ){
//                    System.out.println("if this onl appears. data is empty"); //TESTING
                    jsonString += data;
//                    System.out.println(data); //TESTING
                }
                bufferedReader.close();

//                System.out.println(jsonString); //TESTING
                //presenting the data -> getting the json Object
                Object object = JSONValue.parse(jsonString);
                JSONObject jsonObject = (JSONObject) object;
//                System.out.println(jsonObject); //TESTING

                System.out.println("index no: "+jsonObject.get("indexNo"));
                System.out.println("name: "+jsonObject.get("name") );
                System.out.println("grade: "+jsonObject.get("grade") );

            }
            else if (responseCode == 401) {
                System.out.println(" unaauthorized access: 401");
            }
            else {
                System.out.println("reading Student data failed with error code: "+responseCode);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addNewStudent(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new Student's indexNo:");
            indexNo = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter new Student's name:");
            String name = sc.nextLine().trim();
            System.out.println("Enter new Student's grade:");
            String grade = sc.nextLine().trim();
            System.out.println("Enter a new Student password:");
            String password = sc.nextLine().trim();
            String hashedPassword = getHashedPassword(password);

            //building the Authorization Header with token
            String tokenEncoded = Base64.getEncoder().encodeToString(token.getBytes());
            String authHeader = "Bearer " + tokenEncoded;

            //establishing connection with server url
            URL url = new URL("http://localhost:8080/Student/" + indexNo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", authHeader);

            //sending parameters
            String sendPost = "function=add";
            sendPost += "&indexNo="+indexNo;
            sendPost += "&name="+name;
            sendPost += "&grade="+grade;
            sendPost += "&password="+hashedPassword;
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(connection.getOutputStream()) );
            bw.write(sendPost);
            bw.close();

            //getting response displying the response
            int responseCode = connection.getResponseCode();
            System.out.println("response code: "+responseCode); //TESTING
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
                String data = "";
                while ( (data = br.readLine()) != null ){
                    System.out.println(data);
                }
            }
            else  {
                System.out.println("error executing the request: error code: "+responseCode);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void editStudent(){
        //first displaying the current info of the student
        getStudentInfo();
        try {
            //requesting if editing is needed
            Scanner sc = new Scanner(System.in);
            System.out.println("do you wish to change the information of student " + indexNo);
            String answer = sc.nextLine().trim();
            if ("n".equalsIgnoreCase(answer)) {
                return;
            }

            //getting the new detatils of the student
            System.out.println("Enter the new name of the student:");
            String name = sc.nextLine().trim();
            System.out.println("Enter the new grade of the student:");
            String grade = sc.nextLine().trim();
            System.out.println("Enter the password of the student:");
            String password = sc.nextLine().trim();
            String hashedPassword = getHashedPassword(password);

            //creating the Authorization header
            String tokenEncoded = Base64.getEncoder().encodeToString(token.getBytes());
            String authHeader = "Bearer " + tokenEncoded;

            //creating connection to server url
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/Student/" + indexNo).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization",authHeader);

            String sendPost = "function=edit";
            sendPost += "&indexNo="+indexNo;
            sendPost += "&name="+name;
            sendPost += "&grade="+grade;
            sendPost += "&password="+hashedPassword;

            //sending the parameter values to the server
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(connection.getOutputStream()) );
            bw.write(sendPost);
            bw.flush();
            bw.close();

            //getting and displaying the result
            int responseCode = connection.getResponseCode();
            System.out.println("response code: "+responseCode); //TESTING
            if (responseCode == 200) {
                String data = "";
                BufferedReader  br = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
                while ( (data = br.readLine()) != null ) {
                    System.out.println(data);
                }
            }
            else {
                System.out.println("error executing the request: error code: "+responseCode);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new Student's indexNo:");
            indexNo = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter new Student's name:");
            String name = sc.nextLine().trim();
            System.out.println("Enter new Student's grade:");
            String grade = sc.nextLine().trim();
            System.out.println("Enter a new Student password:");
            String password = sc.nextLine().trim();
            String hashedPassword = getHashedPassword(password);

            //building the Authorization Header with token
            String tokenEncoded = Base64.getEncoder().encodeToString(token.getBytes());
            String authHeader = "Bearer " + tokenEncoded;

            //establishing connection with server url
            URL url = new URL("http://localhost:8080/Student/" + indexNo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", authHeader);

            String sendPost = "function=delete";
            sendPost += "&indexNo="+indexNo;
            sendPost += "&name="+name;
            sendPost += "&grade="+grade;
            sendPost += "&password="+hashedPassword;

            //sending the parameter values to the server
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(connection.getOutputStream()) );
            bw.write(sendPost);
            bw.flush();
            bw.close();

            //getting and displaying the result
            int responseCode = connection.getResponseCode();
            System.out.println("response code: "+responseCode); //TESTING
            if (responseCode == 200) {
                String data = "";
                BufferedReader  br = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
                while ( (data = br.readLine()) != null ) {
                    System.out.println(data);
                }
            }
            else {
                System.out.println("error executing the request: error code: "+responseCode);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



}

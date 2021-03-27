import java.sql.*;
import java.util.*;

public class CrudFunctions {
    public int id;
    public String name, dob, address;
    public boolean maritalStatus;

    public CrudFunctions(){

    }

    public CrudFunctions(int id, String name, String dob, String address, boolean maritalStatus) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.maritalStatus = maritalStatus;
    }

    public static Connection getCon() {
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PeopleRegistration","root","");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }

    //data insertion function
    public void insertData( int id, String name, String dob, String address, boolean maritalStatus) {

        Connection con = getCon();
        try {
            PreparedStatement prepStmt = con.prepareStatement("insert into RegistrationDetails values(?,?,?,?,?)");
            prepStmt.setInt(1, id);
            prepStmt.setString(2, name);
            prepStmt.setString(3, dob);
            prepStmt.setString(4, address);
            prepStmt.setBoolean(5, maritalStatus);
            int results = prepStmt.executeUpdate();
            con.close();
        }
        catch (Exception e){
            System.out.println(e+"=>crud insertion");
        }

    }

    //selecting * data of all rows
    public static List<CrudFunctions> selectAll() {
            List<CrudFunctions> registeredList = null;
            Connection con = getCon();
        try{
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("select * from RegistrationDetails");
            //soring data in CRUDFunctions objects and an arraylist
            registeredList = new ArrayList<CrudFunctions>();
            while (results.next() ) {
               registeredList.add( new CrudFunctions(results.getInt("id"), results.getString("name"), results.getString("dob"), results.getString("address"), (results.getString("maritalStatus").equals("1"))?true:false ) );
            }
        }
        catch (Exception e){
            System.out.println(e+"=>crud * selection");
        }

        return registeredList;

    }

    //selecting data in a single row
    public CrudFunctions selectRow(int id){
            Connection con = getCon();
        try {
            PreparedStatement prepStmt = con.prepareStatement("Select * from RegistrationDetails where id=?");
            prepStmt.setInt(1,id);
            ResultSet results = prepStmt.executeQuery();

            //assigning to the CrudFunctions object
            while (results.next() ) {
                this.id = id;
                this.name = results.getString("name");
                this.dob = results.getString("dob");
                this.address = results.getString("address");
                this.maritalStatus = (results.getString("maritalStatus").equals("1"))?true:false;
            }
            con.close();
        }
        catch (Exception e){
            System.out.println(e+"=>crud single selection");
        }
        return this;
    }

    //updating a single row of data
    public void updateRow(int id, String name, String dob, String address, boolean maritalStatus) {

            Connection con = getCon();
        try {
            PreparedStatement prepStmt = con.prepareStatement("update RegistrationDetails set name=?, dob=?, address=?, maritalStatus=? where id=?");

            prepStmt.setString(1, name);
            prepStmt.setString(2, dob);
            prepStmt.setString(3, address);
            prepStmt.setBoolean(4, maritalStatus);
            prepStmt.setInt(5, id);
            int results = prepStmt.executeUpdate();
            con.close();
        }
        catch (Exception e){
            System.out.println(e+"=>crud updation");
        }
    }

    //deleting a data row
    public void deleteRow(int id) {

            Connection con = getCon();
        try{
            PreparedStatement prepStmt = con.prepareStatement("delete from RegistrationDetails where id=?");
            prepStmt.setInt(1,id);

            int result = prepStmt.executeUpdate();
            con.close();
        }
        catch (Exception e){
            System.out.println(e+"=>crud deletion");
        }
    }

}

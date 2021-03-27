
import java.sql.*;

//this is a singleton class
public class StudentStore {
    private static StudentStore instance = new StudentStore();
    private Student student;

    private StudentStore() { }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/REST_api_College","root","");

        return connection;
    }

    public static StudentStore getInstance() {
        return instance;
    }

    public String addStudent(Student student) throws SQLException,ClassNotFoundException{

        int indexNo = student.getIndexNo();
        //checking if index number already exist in the db
        if ( this.getStudent(indexNo) == null ){
            Connection connection = this.getConnection();

            Statement insertStatement = connection.createStatement();
            String statement = "insert into Student values ('"+ student.getIndexNo()+"','"+student.getName()+"','"+ student.getGrade()+"','"+ student.getPassword()+"')";
            int result =  insertStatement.executeUpdate(statement);

            connection.close();

            return "Student added successfully!";
        }
        else {
            return "this index number already exists in the db";
        }
    }

    public String editStudent(Student student) throws SQLException, ClassNotFoundException{
        int indexNo = student.getIndexNo();
        //checking if student exist
        if ( this.getStudent(indexNo) != null) {
            Connection connection = this.getConnection();

            PreparedStatement editStatement = connection.prepareStatement("update Student set name=?, grade=? where indexNo=?");
            editStatement.setString(1, student.getName() );
            editStatement.setString(2, student.getGrade() );
            editStatement.setInt(3, student.getIndexNo() );
            int result = editStatement.executeUpdate();

            connection.close();

            return "updated "+indexNo+" successfully!";
        }
        else {
            return "updating "+indexNo+" failed!; no student with the entered index no!";
        }
    }

    public Student getStudent(int indexNo) throws SQLException, ClassNotFoundException{
        Connection connection = this.getConnection();

        Statement selectStatement = connection.createStatement();
        String statement = "select * from Student where indexNo="+indexNo;
        ResultSet result = selectStatement.executeQuery(statement);
        Student student = null;
        if (result.next() ) {
            student = new Student(result.getInt("indexNo"), result.getString("password"), result.getString("name"), result.getString("grade") );
        }
        connection.close();

        return student;
    }

    public String deleteStudent(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = this.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement("delete from Student where indexNo=?");
        deleteStatement.setInt(1, student.getIndexNo() );
        int result = deleteStatement.executeUpdate();

        connection.close();

        return "deleted "+student.getIndexNo()+ " successfully";
    }

}

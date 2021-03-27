import java.util.Map;
import java.util.UUID;
import java.sql.*;

public class TokenStore {
    private static TokenStore instance = new TokenStore();
//    private static Map<String,Token> tokenMap;

    private TokenStore () {}

    public static TokenStore getInstance() {
        return instance;
    }

    private Connection getConnection () throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/REST_api_College", "root", "");

        return connection;
    }

    public String getToken(int indexNo, String password) throws SQLException, ClassNotFoundException{
        Student student = StudentStore.getInstance().getStudent(indexNo);

        //checking if a student exist
        if ( student != null && student.getPassword().equals(password)) {

            //generating a token
            String token = UUID.randomUUID().toString();
            Token authToken  = new Token(student.getIndexNo() );

            //saving in db
            Connection connection = this.getConnection();
            String statement = "insert into Auth_token values ('"+token+"','"+authToken.indexNo+"','"+authToken.expiryTime+"')";
            Statement insertStatement = connection.createStatement();
            int result = insertStatement.executeUpdate(statement);

            connection.close();

            return token;
        }
        else {
            return null;
        }
    }

    public int getIndexNoOfRelevantToken( String token) throws SQLException, ClassNotFoundException {
        //intializing the return value - indexNo
        int indexNo = 0;

        Connection connection = this.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("select * from Auth_token where token=?");
        selectStatement.setString(1, token);
        ResultSet result = selectStatement.executeQuery();

        //checking if no match is found
        if ( !result.next() ){
            return indexNo;
        }
        // if a match is found Token object is created
        Token auth_token;
        do {
            auth_token = new Token(result.getInt("indexNo"), result.getLong("expirytime") );
        }
        while ( result.next() );
        selectStatement.close(); // closing the above select statement after its usage
        //checking if expirytime is up
        if (auth_token.expiryTime > System.currentTimeMillis() ){
            indexNo = auth_token.indexNo;
        }
        else {
            PreparedStatement deleteStatement = connection.prepareStatement("delete from Auth_token where token=?");
            deleteStatement.setString(1, token);
            int deleteResult = deleteStatement.executeUpdate();
        }

        connection.close();

        return indexNo;
    }

    //inner class Token, inside TokenStore class
    public class Token {
        int indexNo;
        long expiryTime;

        public Token (int indexNo){
            this.indexNo = indexNo;
            this.expiryTime = System.currentTimeMillis() + 1*60*1000;
        }

        public Token (int indexNo, long expiryTime){
            this.indexNo = indexNo;
            this.expiryTime = expiryTime;
        }
    }
}

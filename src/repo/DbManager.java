package repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class DbManager{
    ArrayList<String> sqlData = new ArrayList<String>();
    String driverName = "com.mysql.jdbc.Driver";
    String dbName = "testdb";
    String dbUsername = "root";
    String dbPassword = "";
    String dbURL = "jdbc:mysql://localhost/" + dbName;
    Connection connection;

    public ArrayList<String> getData(){
        String query = "Select * from account";
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                sqlData.add(result.getString("name"));
                sqlData.add(result.getString("age"));
                sqlData.add(result.getString("username"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sqlData;
    }

    public void updateData(String name, String newname, String newage, String username){
        String query ="UPDATE account SET name=?, age=? WHERE name=? AND username=?";
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, newname);
            pst.setString(2, newage);
            pst.setString(3, name);
            pst.setString(4, username);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void removeData(String name, String username){
        String query = "Delete from account where name=? AND username=?";
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
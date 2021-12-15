package repo;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.*;

public class dbConnection  {
    public Connection c;
    public Statement s;

    public dbConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int insert(String name, String age, String username, String password){
        String query = "INSERT INTO account (name, age, username, password) VALUES (?, ?, ?, ?)";
        String check = "Select username from account where username=?";
        String dname = name;
        String dage = age;
        String dusername = username;
        String dpassword = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

            PreparedStatement checkStmnt = c.prepareStatement(check);
            checkStmnt.setString(1, dusername);
            ResultSet result = checkStmnt.executeQuery();
            if(result.next()){
                return 0;
            }
            else{
                PreparedStatement pst = c.prepareStatement(query);
                pst.setString(1, dname);
                pst.setString(2, dage);
                pst.setString(3, dusername);
                pst.setString(4, dpassword);
                pst.executeUpdate();
                return 1;
            }

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        }
    }

    public void remove(String name, String username){
        String query = "DELETE FROM account WHERE name=? AND username=? ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");
            PreparedStatement pst = c.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "This "+username+" is deleted.", "Success", -1);
            c.close();
        } catch (Exception a) {
            System.out.println(a);
        }
    }
}
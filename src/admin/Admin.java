package admin;
import repo.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
public class Admin implements Manager{
    private String name;
    private String age;
    private String username;
    dbConnection con;
    User obj;

    public Admin(){
    }
    public Admin(String name, String age, String username){
        this.name = name;
        this.age = age;
        this.username = username;
    }

    public int insertUser(User obj){
        String name = obj.getName();
        String age = obj.getAge();
        String username = obj.getUserName();
        String password = obj.getPassword();
        try{
            con = new dbConnection();
            int i = con.insert(name, age, username, password);
            return i;
        } catch(Exception e){
            System.out.println(e);
            return 2;
        }
        }

    public void removeUser(String name, String username){
        try {
            con = new dbConnection();
            con.remove(name, username);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
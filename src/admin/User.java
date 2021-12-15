package admin;
public class User {
    private String name;
    private String age;
    private String username;
    private String password;
    

    public User(){
        
    }
    public User(String name, String age, String username, String password){
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }
    public String getAge(){
        return this.age;
    }
    public String getUserName(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
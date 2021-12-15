package admin;
public interface Manager {
    int insertUser(User obj);
    void removeUser(String name, String username);
}
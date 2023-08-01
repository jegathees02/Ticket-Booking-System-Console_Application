package main.java.model;

public class User {
    private String user_id;
    private String name;
    private String email;
    private String phone;
    private String password;
    public User() {
    }
    public User(String user_id, String name, String email, String phone,String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
    
}

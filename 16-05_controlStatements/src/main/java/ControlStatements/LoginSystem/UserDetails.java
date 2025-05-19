package ControlStatements.LoginSystem;

public class UserDetails {
    private String userName;
    private String password;

    public UserDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean authentication(String username,String pwd){
        return username.equalsIgnoreCase(userName) && password.equalsIgnoreCase(pwd);
    }
}

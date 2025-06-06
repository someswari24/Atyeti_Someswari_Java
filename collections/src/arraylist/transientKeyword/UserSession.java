package arraylist.transientKeyword;

import java.io.Serializable;

public class UserSession implements Serializable {
    private String userName;
    private transient String password;

    public UserSession(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

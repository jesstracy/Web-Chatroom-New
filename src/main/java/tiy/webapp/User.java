package tiy.webapp;

/**
 * Created by Brice on 9/14/16.
 */
public class User {
    String userName;
    String fullName;
    int userID;

    public User() {
    }

    public User(String userName, String fullName, int userID) {
        this.userName = userName;
        this.fullName = fullName;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

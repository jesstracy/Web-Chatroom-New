package tiy.webapp;

/**
 * Created by Brice on 9/14/16.
 */
public class Message {
    String messageText;
    int messageID;
    int userID;

    public Message(String messageText, int messageID, int userID) {
        this.messageText = messageText;
        this.messageID = messageID;
        this.userID = userID;
    }

    public Message() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

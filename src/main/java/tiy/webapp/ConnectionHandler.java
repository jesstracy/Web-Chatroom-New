package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Brice on 8/25/16.
 */

public class ConnectionHandler implements Runnable {

    Socket connection = null;
    ArrayList<String> messageHistory;
    ChatDatabase myDatabase;

    public ConnectionHandler (Socket incomingConnection, ChatDatabase myDatabase) {
        this.connection = incomingConnection;
//        this.messageHistory = messageHistory;
        this.myDatabase = myDatabase;
    }

    public ConnectionHandler () {
    }

    public void run() {
        try {
            handleIncomingConnection(connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void handleIncomingConnection (Socket clientSocket) throws Exception {
        System.out.println("Connection accepted");

        // this is how we read from the client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // this is how we write back to the client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine = inputFromClient.readLine();
        if (!inputLine.equals("return:history")) {
            myDatabase.postMessage(myDatabase.conn, inputLine);
        }

        messageHistory = myDatabase.chatHistory(myDatabase.conn);
        for (String currentString : messageHistory) {
            outputToClient.println(currentString);
        }
        outputToClient.println("end:history");
    }
}

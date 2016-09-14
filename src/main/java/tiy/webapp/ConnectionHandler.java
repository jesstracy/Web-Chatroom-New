package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Brice on 8/25/16.
 */

public class ConnectionHandler implements Runnable {

    Socket connection = null;
    ArrayList<String> messageHistory;

    public ConnectionHandler (Socket incomingConnection) {
        this.connection = incomingConnection;
        this.messageHistory = messageHistory;
    }

    public ConnectionHandler () {
    }

    public void run() {
        try {
            handleIncomingConnection(connection);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void handleIncomingConnection (Socket clientSocket) throws IOException {
        System.out.println("Connection accepted");

        // this is how we read from the client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // this is how we write back to the client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine = inputFromClient.readLine();
        System.out.println(inputLine);
        outputToClient.println(inputLine);

        System.out.println(inputLine);

        outputToClient.println("Echo:" + inputLine);
//        }

    }

}

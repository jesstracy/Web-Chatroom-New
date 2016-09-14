package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
     * Created by fenji on 9/13/2016.
     */
public class Client {

    static final String HOST_ADDRESS = "localhost";
    static final int PORT_NUMBER = 8088;
    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;


    public String sendMessage(String userName, String message){
        String serverResponse = null;
        ArrayList<String> messageHistory = null;
        try {
            clientSocket = new Socket(HOST_ADDRESS, PORT_NUMBER);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(userName + ": " + message);
            //get initial response
            serverResponse = in.readLine();

            //loop to accept the entire chat history into a String Arraylist
            messageHistory = new ArrayList<String>();
            while (!serverResponse.equalsIgnoreCase("end:history")){
                messageHistory.add(serverResponse);
                serverResponse = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reverse the arrayList into a String with a newline between each message
        StringBuilder historyBuilder = new StringBuilder();
        for (int count = messageHistory.size(); count > 0; count--){
            if (count == 1){
                historyBuilder.append(messageHistory.get(count - 1).toCharArray());
            } else{
                historyBuilder.append(messageHistory.get(count - 1).toCharArray());
                historyBuilder.append('\n');
            }
        }
        String chatHistory = historyBuilder.toString();
        return chatHistory;
    }

    public String refresh(){
        String serverResponse = null;
        ArrayList<String> messageHistory = null;
        try {
            clientSocket = new Socket(HOST_ADDRESS, PORT_NUMBER);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("return:history");
            //get initial response
            serverResponse = in.readLine();

            //loop to accept the entire chat history into a String Arraylist
            messageHistory = new ArrayList<String>();
            while (!serverResponse.equalsIgnoreCase("end:history")){
                messageHistory.add(serverResponse);
                serverResponse = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reverse the arrayList into a String with a newline between each message
        StringBuilder historyBuilder = new StringBuilder();
        for (int count = messageHistory.size(); count > 0; count--){
            if (count == 1){
                historyBuilder.append(messageHistory.get(count - 1).toCharArray());
            } else{
                historyBuilder.append(messageHistory.get(count - 1).toCharArray());
                historyBuilder.append('\n');
            }
        }
        String chatHistory = historyBuilder.toString();
        return chatHistory;
    }
}

package tiy.webapp;

/**
 * Created by Brice on 9/14/16.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Brice on 8/25/16.
 */
public class Server {

    ChatDatabase myDatabase = new ChatDatabase();

    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.kickThingsOff();
    }

    public void kickThingsOff() {
        try {
            System.out.println("Running...");

//            ChatDatabase myDatabase = new ChatDatabase();
            myDatabase.init();

            startServer();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    Socket connection = null;

    public Server () {}

    public Server (Socket connection) {this.connection = connection;}

    public void startServer() {
        try {
            System.out.println("Starting Server");
            ServerSocket listener = new ServerSocket(8088);

            while(true) {
                Socket incConnection = listener.accept();
                ConnectionHandler handler = new ConnectionHandler(incConnection, myDatabase);
                Thread multiThreadServer = new Thread(handler);

                multiThreadServer.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

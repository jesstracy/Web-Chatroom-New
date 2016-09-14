package tiy.webapp;

import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Brett on 9/14/16.
 */
public class ChatDatabase {

	public final static String DB_URL = "jdbc:h2:./main";

	public static void main(String[] args) throws SQLException{
		ChatDatabase myDatabase = new ChatDatabase();
		myDatabase.init();
	}

	public void init() throws SQLException {
		Server.createWebServer().start();
		Connection conn = DriverManager.getConnection(DB_URL);
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, fullname VARCHAR, username VARCHAR)");
		stmt.execute("CREATE TABLE IF NOT EXISTS messages (id IDENTITY, message VARCHAR, user_id INT)");

	}

	public int insertUser(Connection conn, String fullName, String userName) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
		stmt.setString(1, fullName);
		stmt.setString(2, userName);
		stmt.execute();

		stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
		stmt.setString(1, userName);
		ResultSet result = stmt.executeQuery();
		result.next();

		return result.getInt("id");
	}

	public void deleteUser(Connection conn, String userName) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
		stmt.setString(1, userName);
		stmt.execute();
	}

	public void postMessage (Connection conn, String message, int userID) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO messages VALUES (NULL, ?, ?)");
		stmt.setString(1, message);
		stmt.setInt(2, userID);
		stmt.execute();
	}

	public void deleteMessage(Connection conn, String message) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM messages WHERE message = ?");
		stmt.setString(1, message);
		stmt.execute();
	}

	public ArrayList<String> chatHistory(Connection conn) throws SQLException {
		ArrayList<String> messageHistory = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages");
		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			String text = result.getString("message");
			messageHistory.add(text);
		}

		return messageHistory;
	}


}

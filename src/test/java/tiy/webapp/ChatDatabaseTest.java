package tiy.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Brett on 9/14/16.
 */
public class ChatDatabaseTest {

	ChatDatabase testDatabase;

	@Before
	public void setUp() throws Exception {
		if(testDatabase == null) {
			testDatabase = new ChatDatabase();
			testDatabase.init();
		}
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void insertUser() throws Exception {
		Connection conn = DriverManager.getConnection(testDatabase.DB_URL);
		String fullName = "Brett Gordon";
		String userName = "theNinjew";

		int userID = testDatabase.insertUser(conn, fullName, userName);

		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
		stmt.setInt(1, userID);
		ResultSet result = stmt.executeQuery();
		result.next();
		assertNotNull(result.getString("username"));

		testDatabase.deleteUser(conn, userName);

	}

	@Test
	public void addMessage() throws Exception {
		Connection conn = DriverManager.getConnection(testDatabase.DB_URL);
		String fullName = "Brett Gordon";
		String userName = "theNinjew";

		int userID = testDatabase.insertUser(conn, fullName, userName);

		String message = "This is a test message, we should never, EVER see it.";

		testDatabase.postMessage(conn, message, userID);

		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM messages WHERE user_id = ?");
		stmt.setInt(1, userID);
		ResultSet result = stmt.executeQuery();
		result.next();
		assertEquals("This is a test message, we should never, EVER see it.", result.getString("message"));

		testDatabase.deleteMessage(conn, message);

		testDatabase.deleteUser(conn, userName);


	}

	@Test
	public void history() throws Exception {
		Connection conn = DriverManager.getConnection(testDatabase.DB_URL);
		String fullName = "Brett Gordon";
		String userName = "theNinjew";

		int userID = testDatabase.insertUser(conn, fullName, userName);

		String message = "This is a test message, we should never, EVER see it.";
		testDatabase.postMessage(conn, message, userID);
		message = "This is another test message, we want to see this.";
		testDatabase.postMessage(conn, message, userID);
		message = "And we want to see ALL of them.";
		testDatabase.postMessage(conn, message, userID);

		ArrayList<String> testArray = testDatabase.chatHistory(conn);

		for (String item : testArray) {
			System.out.println(item);
		}

		assertEquals(3, testArray.size());

		testDatabase.deleteMessage(conn, message);

		testDatabase.deleteUser(conn, userName);
	}

}
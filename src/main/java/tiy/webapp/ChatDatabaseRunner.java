package tiy.webapp;

import java.sql.SQLException;

/**
 * Created by Brett on 9/14/16.
 */
public class ChatDatabaseRunner {

	public static void main(String[] args) throws SQLException {
		ChatDatabase allMyBase = new ChatDatabase();
		allMyBase.init();
	}

}

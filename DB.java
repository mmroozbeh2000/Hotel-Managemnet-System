package Hotell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	

	public static void connection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/hotell?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "Roozbeh12345";
		Statement ST = null;
		try {
			Connection cn = DriverManager.getConnection(url, username, password);

			ST = cn.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}

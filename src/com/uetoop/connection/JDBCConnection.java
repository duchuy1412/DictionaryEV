/*
@Date Oct 7, 2018
*/
package com.uetoop.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	public static Connection getJDBCConnection() throws ClassNotFoundException, SQLException {
		final String url = "jdbc:sqlite:database/dict.db";
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection(url);
		
	}
}

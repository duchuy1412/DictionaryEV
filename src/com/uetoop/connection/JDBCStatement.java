/*
@Date Oct 7, 2018
*/
package com.uetoop.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uetoop.model.Word;

public class JDBCStatement {

	public static ArrayList<Word> getData() {
		ArrayList<Word> listWord = new ArrayList();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			
			String sql = "SELECT * FROM DictionaryEV";
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
		         String  word = rs.getString(2);
		         String  detail = rs.getString(3);
		         listWord.add(new Word(word, detail));
		         rs.next();
		    }
			
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listWord;
	}

}

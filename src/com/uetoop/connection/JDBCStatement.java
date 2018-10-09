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
	
	//get RECENT WORD
	public static ArrayList<Word> getRecent() {
		ArrayList<Word> listRecent = new ArrayList();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			
			String sql = "SELECT * FROM RECENT";
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
		         String  word = rs.getString(2);
		         String  detail = rs.getString(3);
		         listRecent.add(new Word(word, detail));
		         rs.next();
		    }
			
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listRecent;
	}
	
	
	//get STARRED WORD
	public static ArrayList<Word> getStarred() {
		ArrayList<Word> listStarred = new ArrayList();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			
			String sql = "SELECT * FROM RECENT";
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
		         String  word = rs.getString(2);
		         String  detail = rs.getString(3);
		         listStarred.add(new Word(word, detail));
		         rs.next();
		    }
			
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listStarred;
	}
	
	
	public void addWord(String word, String detail) {
		Connection con;
		String sql = "INSERT INTO DictionaryEV(word,detail) VALUES(?,?)";
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setString(2, detail);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addRecent() {
		
	}

}

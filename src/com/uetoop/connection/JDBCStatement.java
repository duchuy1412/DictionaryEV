/*
@Date Oct 7, 2018
*/
package com.uetoop.connection;

import java.beans.Statement;
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
	public static ArrayList<String> getRecent() {
		ArrayList<String> listRecent = new ArrayList();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			
			String sql = "SELECT * FROM Recent";
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
		         String  word = rs.getString(1);
		         listRecent.add(word);
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
	public static ArrayList<String> getStarred() {
		ArrayList<String> listStarred = new ArrayList();
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			
			String sql = "SELECT * FROM STAR";
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
		         String  word = rs.getString(2);
		         listStarred.add(word);
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
	
	
	public static void addWord(String word, String detail) {
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
	
	//Order
	public static void sortData() {
		Connection con;
		String sql = "SELECT * FROM DictionaryEV ORDER BY word ASC";
		try {
			con = JDBCConnection.getJDBCConnection();
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Add to RECENT table
	public static void addWordRecent(String word) {
		Connection con;
		String sql = "INSERT INTO Recent(word) VALUES(?)";
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Add to STAR table
	public static void addWordMark(String word) {
		Connection con;
		String sql = "INSERT INTO STAR(word) VALUES(?)";
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateWord(String word, String detail) {
		Connection con;
		String sql = "UPDATE DictionaryEV SET detail='" + detail + "' WHERE word='" + word + "'";
		try {
			con = JDBCConnection.getJDBCConnection();
			java.sql.Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteWord(String word) {
		Connection con;
		String sql = "DELETE FROM DictionaryEV WHERE word='" + word + "'";
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteWordMark(String word) {
		Connection con;
		String sql = "DELETE FROM STAR WHERE word='" + word +"'";
//		System.out.println(sql);
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteWordRecent(String word) {
		Connection con;
		String sql = "DELETE FROM Recent WHERE word='" + word +"'";
//		System.out.println(sql);
		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

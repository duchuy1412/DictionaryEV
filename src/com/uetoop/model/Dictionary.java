/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.util.ArrayList;

import com.uetoop.connection.JDBCStatement;

public class Dictionary {
	public ArrayList<Word> getData() {
		return JDBCStatement.getData();
	}
	
	public ArrayList<String> getRecent(){
		return JDBCStatement.getRecent();
	}
	
	public ArrayList<String> getMark(){
		return JDBCStatement.getStarred();
	}
}

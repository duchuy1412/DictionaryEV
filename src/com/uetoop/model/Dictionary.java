/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.util.ArrayList;

import com.uetoop.connection.JDBCStatement;

public class Dictionary {
	private static ArrayList<Word> data = JDBCStatement.getData();

	public ArrayList<Word> getData() {
		return data;
	}
}

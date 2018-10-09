/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.uetoop.connection.JDBCStatement;

public class DictionaryManagement {
	private Dictionary dictionaries = new Dictionary();
	
	public Dictionary getDictionaries() {
		return dictionaries;
	}
	
}

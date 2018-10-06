/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement {
	private Dictionary dictionaries = new Dictionary();
	
	public Dictionary getDictionaries() {
		return dictionaries;
	}

	public void insertFromFile() {
		try {
			Scanner inFile = new Scanner(new FileInputStream("res/dictionaries.txt"));
			String wordTarget, wordExplain;
			while(inFile.hasNextLine()) {
				String oneLine = inFile.nextLine();
				wordTarget = oneLine.substring(0, oneLine.indexOf('\t'));
				wordExplain = oneLine.substring(oneLine.indexOf('\t')+1, oneLine.length());
				this.getDictionaries().getData().add(new Word(wordTarget, wordExplain));
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found!!!");
		}
	}
	
	public void editWord() {
		
	}
	
	public void deleteWord() {

	}
}

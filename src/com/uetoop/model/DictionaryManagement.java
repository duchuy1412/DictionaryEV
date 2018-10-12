/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;

import com.uetoop.connection.JDBCStatement;

public class DictionaryManagement {
	private Dictionary dictionaries = new Dictionary();
	
	public Dictionary getDictionaries() {
		return dictionaries;
	}
	
	public int searchToAdd(DefaultListModel<Word> wordList, String str, int head, int tail) {
        if(wordList.get(head).getWord_target().compareTo(str) > 0)
        	return 0;
        else if(wordList.get(tail).getWord_target().compareTo(str)<0)
        	return tail;
        if(wordList.get((head+tail)/2).getWord_target().compareTo(str)==0 || wordList.get((head+tail)/2+1 ).getWord_target().compareTo(str)==0)	return -1;
        if(wordList.get((head+tail)/2).getWord_target().compareTo(str)< 0 && wordList.get((head+tail)/2+1).getWord_target().compareTo(str)>0 ) return (head+tail)/2+1;
        else if(wordList.get((head+tail)/2).getWord_target().compareTo(str) > 0)
        	return searchToAdd(wordList,str,head,(head+tail)/2);
        else
        	return searchToAdd(wordList,str,(head+tail)/2,tail);	
	}	

}
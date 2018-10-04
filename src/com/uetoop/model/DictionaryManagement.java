/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryManagement {
	private Dictionary dictionaries = new Dictionary();
	
	public Dictionary getDictionaries() {
		return dictionaries;
	}

	// Insert from Command Line
	public void insertFromCommandline() {
		Scanner sc = new Scanner(System.in);
		int numsOfWords;
		String wordTarget, wordExplain;
		System.out.println("[Import] Enter number of words: ");
		//enter number of words
		numsOfWords = sc.nextInt(); 
		sc.nextLine(); //remove character "\n"
	
		while(numsOfWords>0) {
			wordTarget = sc.nextLine();
			wordExplain = sc.nextLine();
			Word word = new Word(wordTarget, wordExplain);
			this.getDictionaries().getData().add(word);
			--numsOfWords;
		}
	}
	
	// Insert from File
	public void insertFromFile() {
		try {
			Scanner inFile = new Scanner(new FileInputStream("src/dictionaries.txt"));
			String wordTarget, wordExplain;
			while(inFile.hasNextLine()) {
				String oneLine = inFile.nextLine();
				wordTarget = oneLine.substring(0, oneLine.indexOf('\t'));
				wordExplain = oneLine.substring(oneLine.indexOf('\t')+1, oneLine.length());
				Word word = new Word(wordTarget, wordExplain);
				this.getDictionaries().getData().add(word);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found!!!");
		}
	}
	
	// Export Dictionary to file
	public void dictionaryExportToFile() {
		try{
			FileWriter writer = new FileWriter("src/dictionaries.txt"); 
			for(Word word: getDictionaries().getData()) {
				  writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace ();
		}
		System.out.println("Lưu thành công!");
	}
	
	// Look up dictionary 
	public void dictionaryLookup() {
		String keyword;
		Scanner sc = new Scanner(System.in);
		System.out.println("[Search] Enter keyword you want to search: ");
		keyword = sc.nextLine();
		
		int indexKeyword = searchBinary(getDictionaries().getData(), keyword, 0, getDictionaries().getData().size());
		
		if(indexKeyword == -1) {
			System.out.println("Không thấy từ: " + keyword);
		}else {
			System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
			System.out.println("-\t| " + getDictionaries().getData().get(indexKeyword).getWord_target() + "\t\t| " + getDictionaries().getData().get(indexKeyword).getWord_explain() + "\t\t\t\t\n");
		}
	}
	
	// Add a Word
	public void addAWord() {
		int location;	
		Scanner sc = new Scanner(System.in);
		String wordTarget, wordExplain;
		wordTarget = sc.nextLine();
		wordExplain = sc.nextLine();
		Word word = new Word(wordTarget, wordExplain);
		
		if(this.getDictionaries().getData().get(0).getWord_target().compareTo(wordTarget)>0)
			location = 0;
		else if(this.getDictionaries().getData().get(this.getDictionaries().getData().size()-1).getWord_target().compareTo(wordTarget)<0)
			location = this.getDictionaries().getData().size();
		else {
			location = this.searchToAdd(this.dictionaries.getData(),wordTarget,0,this.getDictionaries().getData().size()-1);
		}
		if(location==-1)
			System.out.println("Từ này đã tồn tại");
		else
			this.getDictionaries().getData().add(location+1, word);	// them vao sau vi tri tra ve
		}
			
	// Search position to add new Words 
	public int searchToAdd(ArrayList<Word> wordList, String str, int head, int tail) {
		if(wordList.get((head+tail)/2).getWord_target().compareTo(str)==0 || wordList.get((head+tail)/2+1 ).getWord_target().compareTo(str)==0)	return -1;
		if(wordList.get((head+tail)/2).getWord_target().compareTo(str)< 0 && wordList.get((head+tail)/2+1).getWord_target().compareTo(str)>0 ) return (head+tail)/2;
		else if(wordList.get((head+tail)/2).getWord_target().compareTo(str) > 0)
				return searchToAdd(wordList,str,head,(head+tail)/2);
		else
			return searchToAdd(wordList,str,(head+tail)/2,tail);
	}
	
	// Edit word
	public void editWord() {
		Scanner sc = new Scanner(System.in);
		String keyword;
		System.out.println("Nhập từ cần sửa (English): ");
		keyword = sc.nextLine();
		
		int indexKeyword = searchBinary(getDictionaries().getData(), keyword, 0, getDictionaries().getData().size());
		
		if(indexKeyword == -1) {
			System.out.println("Không thấy từ: " + keyword);
		}else {
			System.out.println("Nhập từ mới (English): ");
			String word1 = sc.nextLine();
			System.out.println("Nhập nghĩa của từ mới (Vietnamese): ");
			String word2 = sc.nextLine();
			getDictionaries().getData().set(indexKeyword, new Word(word1, word2));
		}
	}
	
	// Delete Word
	public void deleteWord() {
		Scanner sc = new Scanner(System.in);
		String deleteWord;
		System.out.println("Nhập từ cần xóa: ");
		deleteWord = sc.nextLine();
		
		int indexKeyword = searchBinary(getDictionaries().getData(), deleteWord, 0, getDictionaries().getData().size());
		this.getDictionaries().getData().remove(indexKeyword);
	}
	
	// Search Binary
	public int searchBinary(ArrayList<Word> wordList, String str, int head, int tail) {
		if(tail < head + 2)	
			return -1;
		else
			if(wordList.get((head+tail)/2).getWord_target().compareTo(str)==0)	return (head+tail)/2;
		else 
			if((wordList.get((head+tail)/2).getWord_target().compareTo(str)>0))
				return searchBinary(wordList,str,head,(head+tail)/2);
		else
			return searchBinary(wordList,str,(head+tail)/2,tail);
		
	}	
	
	// Option 
	public void selectOption(DictionaryCommandLine dictionaryCommandLine) {
		int userOption;
		Scanner sc = new Scanner(System.in);
		userOption = sc.nextInt();
		switch (userOption) {
		case 1:
			//Them tu
			addAWord();
			System.out.println("Thêm từ thành công!!\n");
			break;
			
		case 2:
			// Sua tu
			editWord();
			break;
			
		case 3:
			// Xoa tu
			deleteWord();
			break;
		
		case 4:
			//Tim kiem
			this.dictionaryLookup();
//			dictionaryCommandLine.dictionarySearcher();
			break;
			
		case 5:
			//Xem toan bo
			dictionaryCommandLine.showAllWords(this.getDictionaries());
			break;
		
		case 6:
			//export to file
			dictionaryExportToFile();
			break;
			
		default:
			break;
		}
	}
}

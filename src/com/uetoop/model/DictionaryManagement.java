/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
	public Dictionary dictionaries = new Dictionary();
	
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
			FileWriter writer = new FileWriter("src/export.txt"); 
			for(Word word: getDictionaries().getData()) {
				  writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
			}
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace ();
		}
		System.out.println("Xuất file thành công!");
	}
	
	//look up dictionary 
	public void dictionaryLookup() {
		String keyword;
		Scanner sc = new Scanner(System.in);
		System.out.println("[Search] Enter keyword you want to search: ");
		keyword = sc.nextLine();
		int isFinded = 0;
		
		for(Word word : this.getDictionaries().getData()) {
			if(word.getWord_target().equals(keyword)) {
				isFinded = 1;
				System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
				System.out.println("-\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain() + "\t\t\t\t\n");
			}
		}
		if(isFinded == 0) {
			System.out.println("Không thấy từ: " + keyword);
		}
	}
	
	// Edit word
	public void editWord() {
		Scanner sc = new Scanner(System.in);
		String deleteWord;
		System.out.println("Nhập từ cần sửa (English): ");
		deleteWord = sc.nextLine();
		for (Word word : this.getDictionaries().getData()) {
			if(word.getWord_target().equals(deleteWord)) {
				System.out.println("Nhập từ mới (English): ");
				word.setWord_target(sc.nextLine());
				System.out.println("Nhập nghĩa của từ mới (Vietnamese): ");
				word.setWord_explain(sc.nextLine());
				break;
			}
		}
	}
	
	// Delete Word
	public void deleteWord() {
		Scanner sc = new Scanner(System.in);
		String deleteWord;
		System.out.println("Nhập từ cần xóa: ");
		deleteWord = sc.nextLine();
		for (Word word : this.getDictionaries().getData()) {
			if(word.getWord_target().equals(deleteWord)) {
				this.getDictionaries().getData().remove(word);
				break;
			}
		}
	}
	
	// Option 
	public void selectOption(DictionaryCommandLine dictionaryCommandLine) {
		int userOption;
		Scanner sc = new Scanner(System.in);
		userOption = sc.nextInt();
		switch (userOption) {
		case 1:
			//Them tu
			insertFromCommandline();
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
//			this.dictionaryLookup();
			dictionaryCommandLine.dictionarySearcher();
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

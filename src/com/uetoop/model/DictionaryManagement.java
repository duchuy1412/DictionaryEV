/*
@Date Sep 18, 2018
*/
package com.uetoop.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
		System.out.println("[Thêm từ] Số lượng từ cần thêm: ");
		//enter number of words
		numsOfWords = sc.nextInt(); 
		sc.nextLine(); //remove character "\n"
	
		for(int i=1; i<= numsOfWords; i++){
			System.out.println("Từ thứ :"+i);
			this.addAWord();
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
		System.out.println("Đã lưu!");
	}
	
	// Look up dictionary 
	public void dictionaryLookup() {
		String keyword;
		Scanner sc = new Scanner(System.in);
		System.out.println("[Tìm kiếm] Từ muốn tìm kiếm: ");
		keyword = sc.nextLine();
		
		int location = this.approximateSearch(this.dictionaries.getData(),keyword,0,this.getDictionaries().getData().size()-1);
		if(location == -1) {
			System.out.println("Không tìm thấy: " + keyword);
		}
		else {
			System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
			for(int i=1; i<this.getDictionaries().getData().size();i++){
				if(location+i-1 < this.getDictionaries().getData().size() && this.getDictionaries().getData().get(location+i-1).getWord_target().indexOf(keyword) == 0) {
					
					System.out.println(i+ "\t| " + this.getDictionaries().getData().get(location+i-1).getWord_target() + "\t\t| " + this.getDictionaries().getData().get(location+i-1).getWord_explain() + "\t\t\t\t\n");
				}
				else break;
			}
		}
	}
			
	// Edit word
	public void editWord() {
		Scanner sc = new Scanner(System.in);
		String editWord;
		System.out.println("Nhập từ cần sửa (English): ");
		editWord = sc.nextLine();
		int location = this.search(this.dictionaries.getData(),editWord,0,this.getDictionaries().getData().size()-1);
		if(location == -1) {
			System.out.println("Không tìm thấy: " + editWord);
		}
		else {
			this.getDictionaries().getData().remove(location);
			this.addAWord();
		}
	}
	
	// Delete Word
	public void deleteWord() {
		Scanner sc = new Scanner(System.in);
		String deleteWord;
		System.out.println("Nhập từ cần xóa: ");
		deleteWord = sc.nextLine();
		
		int location = this.search(this.dictionaries.getData(),deleteWord,0,this.getDictionaries().getData().size()-1);
		if(location == -1)
			System.out.println("Không tìm thấy: " + deleteWord);
		else 
			this.getDictionaries().getData().remove(location);
	}
	
	// Sort Words: Sắp xếp 
	public void sortWordList(ArrayList<Word> wordList) {
		Collections.sort(wordList, new Comparator<Word>() {
	           public int compare(Word word1, Word word2) {
	               return (word1.getWord_target().compareTo(word2.getWord_target()));
	               
	           }
	       });
	}
		
	//search : Tìm kiếm đúng tuyệt đối
	public int search(ArrayList<Word> wordList, String str, int head, int tail) {
		if(tail < head + 2)	return -1;
		if(wordList.get(0).getWord_target().compareTo(str)==0)	return 0;
		else if(wordList.get(tail).getWord_target().compareTo(str)==0)	return tail;
		if(wordList.get((head+tail)/2).getWord_target().compareTo(str)==0)	return (head+tail)/2;
		else if((wordList.get((head+tail)/2).getWord_target().compareTo(str)>0))
				return search(wordList,str,head,(head+tail)/2);
		else
			return search(wordList,str,(head+tail)/2,tail);
		
	}
		
	// approximate search : Tìm kiếm các vị trí đầu tiền có các từ bắt đầu giống từ khóa
	public int approximateSearch(ArrayList<Word> wordList, String str, int head, int tail) {
		if(wordList.get(head).getWord_target().indexOf(str)==0)	return head;
		else if(wordList.get(tail).getWord_target().indexOf(str)==0)	return tail;
		if(tail-head <2)	return -1;
		String word = wordList.get((head+tail)/2).getWord_target();
		if(word.length()<str.length())
			return approximateSearch(wordList,str,head+1,tail);
		word = wordList.get((head+tail)/2).getWord_target().substring(0, str.length());
		if(word.compareTo(str)==0) {
			for(int i = (head+tail)/2 ; i >=0 ; i--) {
				if(wordList.get(i).getWord_target().substring(0, str.length()).compareTo(str)!=0)
					return i+1;
			}
			return -1;
		}
		else if(word.compareTo(str)>0)
				return approximateSearch(wordList,str,head,(head+tail)/2);
		else
			return approximateSearch(wordList,str,(head+tail)/2,tail);
	}
	
	// Add a Word
	public void addAWord() {
		Scanner sc = new Scanner(System.in);
		String wordTarget, wordExplain;
		System.out.print("Từ mới: ");
		wordTarget = sc.nextLine();
		System.out.print("Từ nghĩa của từ: ");
		wordExplain = sc.nextLine();
		int location;
		Word word = new Word(wordTarget, wordExplain);
		if(this.getDictionaries().getData().get(0).getWord_target().compareTo(wordTarget)>0)
			location = 0;
		else if(this.getDictionaries().getData().get(this.getDictionaries().getData().size()-1).getWord_target().compareTo(wordTarget)<0)
			location = this.getDictionaries().getData().size();
		else {
			location = this.searchToAdd(this.dictionaries.getData(),wordTarget,0,this.getDictionaries().getData().size()-1)+1;
		}
		if(location==-1)
			System.out.println("Từ này đã tồn tại");
		else
			this.getDictionaries().getData().add(location, word);	// them vao sau vi tri tra ve
	}
	
	// Find a position to add new Words 
		public int searchToAdd(ArrayList<Word> wordList, String str, int head, int tail) {
			if(wordList.get((head+tail)/2).getWord_target().compareTo(str)==0 || wordList.get((head+tail)/2+1 ).getWord_target().compareTo(str)==0)	return -1;
			if(wordList.get((head+tail)/2).getWord_target().compareTo(str)< 0 && wordList.get((head+tail)/2+1).getWord_target().compareTo(str)>0 ) return (head+tail)/2;
			else if(wordList.get((head+tail)/2).getWord_target().compareTo(str) > 0)
					return searchToAdd(wordList,str,head,(head+tail)/2);
			else
				return searchToAdd(wordList,str,(head+tail)/2,tail);
		}
		
	// Option 
	public void selectOption(DictionaryCommandLine dictionaryCommandLine, int choice) {
		switch (choice) {
		case 1:
			//Them tu
			insertFromCommandline();
//			addAWord();
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
		
		case 0:
			dictionaryExportToFile(); //Tự lưu trước khi thoát
			System.out.println("Đã thoát chương trình");
			System.exit(0);
		default:
			System.out.println("Thao tác không hợp lệ");
			break;
		}
	}
}

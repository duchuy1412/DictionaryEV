/*
@Date Sep 19, 2018
*/
package com.uetoop.model;

import java.util.Scanner;

public class DictionaryCommandLine {
		private DictionaryManagement dictionaryManagement = new DictionaryManagement();
		
		private void showOption() {
			System.out.println("\n********DICTIONARY OPTION********\n");
			System.out.println("*\t1. Thêm từ\t\t*\n");
			System.out.println("*\t2. Sửa từ\t\t*\n");
			System.out.println("*\t3. Xóa từ\t\t*\n");
			System.out.println("*\t4. Tìm kiếm từ\t\t*\n");
			System.out.println("*\t5. Xem toàn bộ từ\t*\n");
			System.out.println("*\t6. Export to File\t*\n");
			
			System.out.println("****\tEnter your option: ");
			dictionaryManagement.selectOption(this);
			showOption();
		}
		
		public void showWord(Word word) {
			System.out.println("\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain() + "\t\t\t\t\n");
		}
		
		//Show all words (since v1.0)
		public void showAllWords(Dictionary dictionaries) {
			System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
			int no = 0;
			for (Word word : dictionaries.getData()) {
				System.out.println(++no + "\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain() + "\t\t\t\t\n");
			}
		}
		
		public void dictionarySearcher(){
			String keyword;
			Scanner sc = new Scanner(System.in);
			System.out.println("[Search] Enter keyword you want to search: ");
			keyword = sc.nextLine();
			int isFinded = 0;
			int no = 0;
			
			for(Word word : dictionaryManagement.getDictionaries().getData()) {
				if(word.getWord_target().contains(keyword) && word.getWord_target().indexOf(keyword) == 0) {
					++no;
					isFinded = 1;
					if (no == 1) System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
					System.out.println(no + "\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain() + "\t\t\t\t\n");
				}
			}
			if(isFinded == 0) {
				System.out.println("Không thấy kết quả nào");
			}
		}
		
		//Dictionary Basic (since v1.0)
		public void dictionaryBasic() {
			dictionaryManagement.insertFromCommandline();
			this.showAllWords(dictionaryManagement.dictionaries);
		}
		
		//Dictionary Advanced (since v1.1)
		public void dictionaryAdvanced() {
			dictionaryManagement.insertFromFile();
			this.showAllWords(dictionaryManagement.dictionaries);
			this.showOption();
		}
}

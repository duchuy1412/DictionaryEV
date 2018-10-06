/*
@Date Sep 19, 2018
*/
package com.uetoop.model;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DictionaryCommandLine {
		private DictionaryManagement dictionaryManagement = new DictionaryManagement();
		
		// Menu Option 
		private void showOption() {
			Scanner sc = new Scanner(System.in);
			int choice;
				do {
					System.out.println("\n********BẢNG ĐIỀU KHIỂN********\n");
					System.out.println("*\t1. Thêm  từ\t\t*\n");
					System.out.println("*\t2. Sửa từ\t\t*\n");
					System.out.println("*\t3. Xóa từ\t\t*\n");
					System.out.println("*\t4. Tìm kiếm\t\t*\n");
					System.out.println("*\t5. Xem toàn bộ\t\t*\n");
					System.out.println("*\t6. Export to File\t*\n");
					System.out.println("*\t0. Thoát chương trình\t*\n");
					System.out.println("\tLựa chọn thao tác: ");
					
					choice = sc.nextInt();
					dictionaryManagement.selectOption(this,choice);
					//Delay to show result
				}while(choice!=0);
		}
		
		// Show All Words
		public void showAllWords(Dictionary dictionaries) {
			System.out.println(" No\t| English\t\t| Vietnamese\t\t\t\n");
			int no = 0;
			for (Word word : dictionaries.getData()) {
				System.out.println(++no + "\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain() + "\t\t\t\t\n");
			}
		}
		
		// Dictionary Searcher
		public void dictionarySearcher(){
			dictionaryManagement.dictionaryLookup();
		}
		
		// Dictionary Basic
		public void dictionaryBasic() {
			dictionaryManagement.insertFromCommandline();
			this.showAllWords(dictionaryManagement.getDictionaries());
		}
		
		// Dictionary Advanced
		public void dictionaryAdvanced() {
			dictionaryManagement.insertFromFile();
			this.showAllWords(dictionaryManagement.getDictionaries());
			this.showOption();
		}
}

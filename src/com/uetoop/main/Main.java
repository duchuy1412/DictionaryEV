/*
@Date Sep 18, 2018
*/
package com.uetoop.main;

import javax.swing.SwingUtilities;
import com.uetoop.gui.DictionaryApplication;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				DictionaryApplication dictionaryApplication = new DictionaryApplication();
			}
		});
		
	}

}

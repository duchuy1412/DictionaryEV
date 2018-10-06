/*
@Date Sep 20, 2018
*/
package com.uetoop.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class AppFrame extends JFrame {
	private final int WIDTH_WINDOW = 900;
	private final int HEIGHT_WINDOW = 700;
	private DictionaryPanel dictionaryPanel;
	
	protected JSplitPane split;
	
	public AppFrame() {
		    super("Dictionary EV ");
		    setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
		    setLocationRelativeTo(null);
		    getContentPane().setLayout(new BorderLayout());
		    
		    //add panel to frame
		    dictionaryPanel = new DictionaryPanel();
		    getContentPane().add(dictionaryPanel, BorderLayout.CENTER);
		    
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		    setResizable(false);
		    setVisible(true);
	}
}

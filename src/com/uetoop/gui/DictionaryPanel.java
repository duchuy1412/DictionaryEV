/*
@Date Sep 22, 2018
*/
package com.uetoop.gui;

import java.awt.Color;

import javax.swing.*;

public class DictionaryPanel extends JPanel{
	private DictionaryLeftPanel leftPanel;
	private DictionaryRightPanel rightPanel;
	
	public DictionaryPanel() {
		setLayout(null);
		//create a left panel
		leftPanel = new DictionaryLeftPanel();
		add(leftPanel);
		
		
		//right panel
		rightPanel = new DictionaryRightPanel();
		add(rightPanel);
		
		setBackground(Color.CYAN);
        
	}
}

/*
@Date Oct 1, 2018
*/
package com.uetoop.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.uetoop.model.DictionaryManagement;
import com.uetoop.model.Word;
import com.uetoop.utils.IconItem;

public class DictionaryLeftPanel extends JPanel implements DocumentListener, ActionListener{
	//Components
	private JList list;
	private JLabel lblSearchBar, lblLogo;
	private JPanel logoPanel;
	private JScrollPane scrollPane, scrollPaneDict, scrollPaneRencent, scrollPaneMark;
	private JFormattedTextField txtSearchBar;
	private JButton btnSearch, btnClear, btnUp, btnDown;
	private JTabbedPane tabbedPane;
	private ArrayList<String> listWord;
	
	//Dictionary
	private DictionaryManagement dictionaryManagement;
	
	
	public DictionaryLeftPanel() {
		// Construct
		dictionaryManagement = new DictionaryManagement();
		listWord = new ArrayList<String>();
		
		// import data
		dictionaryManagement.insertFromFile();
		for (Word w : dictionaryManagement.getDictionaries().getData()) {
			listWord.add(w.getWord_target());
		}
		
		//Logo Panel
		logoPanel = new JPanel();
		logoPanel.setBounds(0, 0, 290, 50);
		logoPanel.setBackground(Color.GRAY);
		logoPanel.setLayout(null);
		add(logoPanel);
		
		//Label Logo
		lblLogo = new JLabel("Từ điển Anh-Việt");
		lblLogo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		lblLogo.setBounds(60, 10, 250, 30);
		lblLogo.setForeground(Color.LIGHT_GRAY);
		logoPanel.add(lblLogo);
				
		// Label Searcher
		lblSearchBar = new JLabel("Tìm kiếm:");
		lblSearchBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblSearchBar.setBounds(10, 60, 180, 20);
		add(lblSearchBar);
		
		//create search bar
		txtSearchBar = new JFormattedTextField();
		txtSearchBar.setColumns(10);
		txtSearchBar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.BOLD, 14));
		txtSearchBar.getDocument().addDocumentListener(this);
		txtSearchBar.setBounds(40, 85, 190, 25);
		txtSearchBar.setToolTipText("Nhập từ cần tìm");
		add(txtSearchBar);
		
		// Button searcher
		btnSearch = new JButton();
		btnSearch.setBounds(235, 85, 45, 25);
		btnSearch.setToolTipText("Tra từ");
		btnSearch.setIcon(IconItem.iconSearch);
		add(btnSearch);
		
		// Button Clear
		btnClear = new JButton();
		btnClear.setBounds(10, 85, 25, 25);
		btnClear.setIcon(IconItem.iconRefresh);
		btnClear.setToolTipText("Làm mới thanh tìm kiếm");
		btnClear.addActionListener(this);
		add(btnClear);
		
		//Button Up
		btnUp = new JButton();
		btnUp.setIcon(IconItem.iconUp);
		btnUp.setToolTipText("Lên trên");
		btnUp.setBounds(10, 170, 25, 25);
		btnUp.setContentAreaFilled(false);
		btnUp.setBorderPainted(false);
		btnUp.addActionListener(this);
		add(btnUp);
		
		//Button Down
		btnDown = new JButton();
		btnDown.setBounds(10, 200, 25, 25);
		btnDown.setIcon(IconItem.iconDown);
		btnDown.setToolTipText("Xuống dưới");
		btnDown.setContentAreaFilled(false);
		btnDown.setBorderPainted(false);
		btnDown.addActionListener(this);
		add(btnDown);
		
		// Create ScrollPane from List
		scrollPaneDict = creatScrollPaneWithList(listWord.toArray());
		scrollPaneRencent = creatScrollPaneWithList(listWord.toArray());
		scrollPaneMark = creatScrollPaneWithList(listWord.toArray());
		
        // Create Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(40, 140, 240, 500);
        
        // Add Tab 1
        tabbedPane.addTab("", IconItem.iconList, scrollPaneDict, "Toàn bộ");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        // Add Tab 2
        tabbedPane.addTab("", IconItem.iconClock, scrollPaneRencent, "Những từ học gần đây");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        // Add Tab 3
        tabbedPane.addTab("", IconItem.iconFavourites, scrollPaneMark, "Những từ được gắn dấu sao");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        // Array title of tabs
        String[] tabMenu = {"", "Từ điển", "Gần đây", "Gắn sao"};
        
        // Set title for default selected tab	
        tabbedPane.setSelectedIndex(0);
        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex()+1]);
        
        // Show title for a selected tab
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex()+1]);
				for(int i = 0; i < tabbedPane.getTabCount(); i++) {
					if(i != tabbedPane.getSelectedIndex()) {
						tabbedPane.setTitleAt(i, tabMenu[0]);
					}
				}
			}
		});
        
        // Add to Panel
        add(tabbedPane);
        //Scrolling tabs
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        //Left Panel
        setBackground(Color.LIGHT_GRAY);
        setBounds(0, 0, 290, 700);
        setLayout(null);
	}
	
	//Create a Scroll Pane 
	public JScrollPane creatScrollPaneWithList(Object[] Obj) {
		// Create a list
		list = new JList(Obj);
		list.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //single selection
		list.setSelectedIndex(0);
        scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBar(null);
        return scrollPane;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		searchKeyword(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		searchKeyword(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		searchKeyword(e);
	}
	
	private void searchKeyword(DocumentEvent e) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println(txtSearchBar.getText());
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnClear)) {
			this.txtSearchBar.setText(null);
		}
	}
	
}

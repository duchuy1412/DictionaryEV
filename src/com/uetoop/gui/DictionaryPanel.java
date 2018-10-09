/*
@Date Sep 22, 2018
*/
package com.uetoop.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import com.uetoop.model.DictionaryManagement;
import com.uetoop.model.Word;
import com.uetoop.utils.IconItem;

public class DictionaryPanel extends JPanel
		implements ListSelectionListener, DocumentListener, ActionListener, KeyListener {
	// Components Left
	private JList<String> listWord, listRecent, listMark;
	private JLabel lblSearchBar, lblLogo;
	private JPanel pnlLeftTop, pnlRightTop, pnlLeftBottom, pnlRightBottom;
	private JScrollPane scrollPaneDict, scrollPaneRecent, scrollPaneMark;
	private JFormattedTextField txtSearchBar;
	private JButton btnSearch, btnClear, btnUp, btnDown;
	private JTabbedPane tabbedPane;
	private DefaultListModel<String> listModelWord, listModelRecent, listModelMark, resultList;
	private ArrayList<Word> listDict;
	// Components Right
	private JTextPane tpDetail;
	private JLabel lblDetail;
	private JButton btnAdd, btnEdit, btnDelete, btnMark, btnPronounce;
	private JScrollPane scrollPane;
	private Document doc;
	private SimpleAttributeSet set;

	// Dictionary
	private DictionaryManagement dictionaryManagement;

	public DictionaryPanel() {
		dictionaryManagement = new DictionaryManagement();
		listDict = dictionaryManagement.getDictionaries().getData();

		listModelWord = new DefaultListModel<String>();
		listModelRecent = new DefaultListModel<String>();
		listModelMark = new DefaultListModel<String>();

		// import list of WORD_TARGET
		for (int i = 0; i < listDict.size(); i++) {
			listModelWord.addElement(listDict.get(i).getWord_target());
		}

		listModelMark.addElement("abcsas");
		listModelRecent.addElement("Rencently");

		// this
		setLayout(null);
		setBackground(Color.CYAN);

		// Panel Left-Top
		pnlLeftTop = new JPanel();
		pnlLeftTop.setBounds(0, 0, 290, 50);
		pnlLeftTop.setBackground(new Color(6, 67, 165));
		pnlLeftTop.setLayout(null);
		add(pnlLeftTop);

		// Add components to Panel Left-Top
		addToLeftTop();

		// Panel Left-Bottom
		pnlLeftBottom = new JPanel();
		pnlLeftBottom.setBounds(0, 0, 290, 700);
		pnlLeftBottom.setBackground(new Color(214, 215, 216));
		pnlLeftBottom.setLayout(null);
		add(pnlLeftBottom);

		// Add components to Panel Left-Bottom
		addToLeftBottom();

		// Panel Right-Top
		pnlRightTop = new JPanel();
		pnlRightTop.setBounds(290, 0, 600, 50);
		pnlRightTop.setBackground(new Color(180, 186, 193));
		pnlRightTop.setLayout(null);
		add(pnlRightTop);

		// Add components to Right-Top
		addToRightTop();

		// Panel Right-Bottom
		pnlRightBottom = new JPanel();
		pnlRightBottom.setBounds(290, 0, 600, 700);
		pnlRightBottom.setBackground(new Color(242, 242, 242));
		pnlRightBottom.setLayout(null);
		add(pnlRightBottom);

		// Add components to Right-Bottom
		addToRightBottom();

	}

	private void addToLeftTop() {
		// Label Logo
		lblLogo = new JLabel("Từ điển Anh-Việt");
		lblLogo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		lblLogo.setBounds(60, 10, 250, 30);
		lblLogo.setForeground(Color.LIGHT_GRAY);
		pnlLeftTop.add(lblLogo);
	}

	private void addToLeftBottom() {
		// Label Searcher
		lblSearchBar = new JLabel("Tìm kiếm:");
		lblSearchBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblSearchBar.setBounds(10, 60, 180, 20);
		pnlLeftBottom.add(lblSearchBar);

		// create search bar
		txtSearchBar = new JFormattedTextField();
		txtSearchBar.setColumns(10);
		txtSearchBar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.BOLD, 14));
		txtSearchBar.getDocument().addDocumentListener(this);
		txtSearchBar.setBounds(40, 85, 190, 25);
		txtSearchBar.setToolTipText("Nhập từ cần tìm");
		txtSearchBar.addKeyListener(this);
		pnlLeftBottom.add(txtSearchBar);

		// Button searcher
		btnSearch = new JButton();
		btnSearch.setBounds(235, 85, 45, 25);
		btnSearch.setToolTipText("Tra từ");
		btnSearch.setIcon(IconItem.iconSearch);
		pnlLeftBottom.add(btnSearch);

		// Button Clear
		btnClear = new JButton();
		btnClear.setBounds(10, 85, 25, 25);
		btnClear.setIcon(IconItem.iconRefresh);
		btnClear.setToolTipText("Làm mới thanh tìm kiếm");
		btnClear.addActionListener(this);
		pnlLeftBottom.add(btnClear);

		// Button Up
		btnUp = new JButton();
		btnUp.setIcon(IconItem.iconUp);
		btnUp.setToolTipText("Lên trên");
		btnUp.setBounds(10, 170, 25, 25);
		btnUp.setContentAreaFilled(false);
		btnUp.setBorderPainted(false);
		btnUp.addActionListener(this);
		pnlLeftBottom.add(btnUp);

		// Button Down
		btnDown = new JButton();
		btnDown.setBounds(10, 200, 25, 25);
		btnDown.setIcon(IconItem.iconDown);
		btnDown.setToolTipText("Xuống dưới");
		btnDown.setContentAreaFilled(false);
		btnDown.setBorderPainted(false);
		btnDown.addActionListener(this);
		pnlLeftBottom.add(btnDown);

		// Create JList
		listWord = new JList<String>();
		listWord.setModel(listModelWord);
		listWord.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
		listWord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single selection
		listWord.addListSelectionListener(this);
		scrollPaneDict = new JScrollPane();
		scrollPaneDict.setViewportView(listWord);
		scrollPaneDict.setHorizontalScrollBar(null);

		listRecent = new JList<String>(listModelRecent);
		listRecent.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
		listRecent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single selection
		listRecent.addListSelectionListener(this);
		scrollPaneRecent = new JScrollPane();
		scrollPaneRecent.setViewportView(listRecent);
		scrollPaneRecent.setHorizontalScrollBar(null);

		listMark = new JList<String>(listModelMark);
		listMark.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
		listMark.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single selection
		listMark.addListSelectionListener(this);
		scrollPaneMark = new JScrollPane();
		scrollPaneMark.setViewportView(listMark);
		scrollPaneMark.setHorizontalScrollBar(null);

		// Create Tabbed Pane
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(40, 140, 240, 500);

		// Add Tab 1
		tabbedPane.addTab("", IconItem.iconList, scrollPaneDict, "Toàn bộ");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		// Add Tab 2
		tabbedPane.addTab("", IconItem.iconClock, scrollPaneRecent, "Những từ học gần đây");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		// Add Tab 3
		tabbedPane.addTab("", IconItem.iconStarred, scrollPaneMark, "Những từ được gắn dấu sao");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		// Array title of tabs
		String[] tabMenu = { "", "Từ điển", "Gần đây", "Gắn sao" };

		// Set title for default selected tab
		tabbedPane.setSelectedIndex(0);
		tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex() + 1]);

		// Show title for a selected tab
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex() + 1]);
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					if (i != tabbedPane.getSelectedIndex()) {
						tabbedPane.setTitleAt(i, tabMenu[0]);
					}
				}
			}
		});

		// Add to Panel
		pnlLeftBottom.add(tabbedPane);
		// Scrolling tabs
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	}

	private void addToRightTop() {
		// Button Add
		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(IconItem.iconAdd);
		btnAdd.setBounds(250, 10, 90, 25);
		btnAdd.setToolTipText("Thêm từ vào từ điển này");
		btnAdd.setContentAreaFilled(false);
		btnAdd.addActionListener(this);
		pnlRightTop.add(btnAdd);

		// Button Modify
		btnEdit = new JButton("Sửa");
		btnEdit.setIcon(IconItem.iconModify);
		btnEdit.setBounds(350, 10, 90, 25);
		btnEdit.setToolTipText("Sửa từ");
		btnEdit.setContentAreaFilled(false);
		btnEdit.addActionListener(this);
		btnEdit.setEnabled(false);
		pnlRightTop.add(btnEdit);

		// Button Delete
		btnDelete = new JButton("Xóa");
		btnDelete.setIcon(IconItem.iconDelete);
		btnDelete.setBounds(450, 10, 90, 25);
		btnDelete.setToolTipText("Xóa từ");
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(this);
		btnDelete.setEnabled(false);
		pnlRightTop.add(btnDelete);

		// Button Starred
		btnMark = new JButton();
		btnMark.setIcon(IconItem.iconStar);
		btnMark.setBounds(550, 0, 40, 40);
		btnMark.setToolTipText("Đánh dấu từ này");
		btnMark.setContentAreaFilled(false);
		btnMark.setBorderPainted(false);
		btnMark.setEnabled(false);
		btnMark.addActionListener(this);
		pnlRightTop.add(btnMark);

		// Button Pronounce
		btnPronounce = new JButton();
		btnPronounce.setIcon(IconItem.iconSpeaker);
		btnPronounce.setBounds(10, 5, 40, 40);
		btnPronounce.setToolTipText("Phát âm từ này");
		btnPronounce.setContentAreaFilled(false);
		btnPronounce.setBorderPainted(false);
		btnPronounce.setEnabled(false);
		btnPronounce.addActionListener(this);
		pnlRightTop.add(btnPronounce);
	}

	private void addToRightBottom() {
		// Label Detail
		lblDetail = new JLabel("Nghĩa của từ: ");
		lblDetail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblDetail.setBounds(10, 60, 180, 20);
		pnlRightBottom.add(lblDetail);

		// Detail
		tpDetail = new JTextPane();
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		tpDetail.setBorder(
				BorderFactory.createCompoundBorder(border, BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
//		doc = tpDetail.getStyledDocument();
//		set = new SimpleAttributeSet();
		// Set the attributes before adding text
//	    tpDetail.setCharacterAttributes(set, true);
	    
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tpDetail);
		scrollPane.setBounds(10, 90, 570, 550);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlRightBottom.add(scrollPane);
	}
	
	public void showDetailOfWord(ArrayList<Word> list, String str) {
		for(Word w : list) {
			if(w.getWord_target().equals(str)) {
				String detail = w.getWord_explain();

				detail = detail.replace("<C><F><I><N><Q>", "");
				detail = detail.replace("</Q></N></I></F></C>", "");
				detail = detail.replace("<br />-", "\n\t-");
				detail = detail.replace("<br />=", "\n\t\t=");
				detail = detail.replace("<br />*", "\n*");
				tpDetail.setText(detail);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			if (listWord.getSelectedIndex() == -1) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnPronounce.setEnabled(false);
				btnMark.setEnabled(false);
			} else {
				showDetailOfWord(listDict, listWord.getSelectedValue());
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnPronounce.setEnabled(true);
				btnMark.setEnabled(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnClear)) {
			this.txtSearchBar.setText(null);
		} else if (e.getSource().equals(btnAdd)) {
			System.out.println("Add");
		} else if (e.getSource().equals(btnEdit)) {

		} else if (e.getSource().equals(btnDelete)) {
			if (this.listWord.getSelectedIndex() != -1) {
				if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa từ này?", "Xóa từ",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					this.listModelWord.remove(this.listWord.getSelectedIndex());
				}
			}
		} else if (e.getSource().equals(btnUp)) {
			listWord.setSelectedIndex(listWord.getSelectedIndex()-1);
		} else if (e.getSource().equals(btnDown)) {
			listWord.setSelectedIndex(listWord.getSelectedIndex()+1);
		} else if (e.getSource().equals(btnPronounce)) {

		} else if (e.getSource().equals(btnSearch)) {

		}

		else if (e.getSource().equals(btnMark)) {
			if (btnMark.getIcon().equals(IconItem.iconStarred)) {
				btnMark.setIcon(IconItem.iconStar);
			} else {
				btnMark.setIcon(IconItem.iconStarred);
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		searchKeyword(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		searchKeyword(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		searchKeyword(e);
	}

	private void searchKeyword(DocumentEvent e) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				resultList = resultSearch(txtSearchBar.getText(), listModelWord);
				listWord.setModel(resultList);
				listWord.setSelectedIndex(0);
				showDetailOfWord(listDict, listWord.getSelectedValue());
			}
		});
	}

	public DefaultListModel<String> resultSearch(String keyword, DefaultListModel<String> obj) {
		DefaultListModel<String> rsList = new DefaultListModel<String>();
		for (int i = 0; i < obj.size(); i++) {
			if (obj.get(i).indexOf(keyword) == 0) {
				rsList.addElement(obj.get(i));
			}
		}
		return rsList;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTest(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void keyTest(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			txtSearchBar.setText(listWord.getSelectedValue());
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			listWord.setSelectedIndex(listWord.getSelectedIndex()-1);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			listWord.setSelectedIndex(listWord.getSelectedIndex()+1);
		}
	}
	
}

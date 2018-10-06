/*
@Date Oct 4, 2018
*/
package com.uetoop.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.uetoop.utils.IconItem;

public class DictionaryRightPanel extends JPanel implements ActionListener {
	JTextArea txtAreaDetail;
	private JPanel boxToolPanel;
	private JLabel lblDetail;
	private JButton btnAdd, btnEdit, btnDelete, btnPronounce;
	public DictionaryRightPanel() {
		//Box Tool
		boxToolPanel = new JPanel();
		boxToolPanel.setBounds(0, 0, 600, 50);
		boxToolPanel.setBackground(new Color(220, 227, 237));
		boxToolPanel.setLayout(null);
		add(boxToolPanel);
		
		// Button Add
		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(IconItem.iconAdd);
		btnAdd.setBounds(10, 10, 90, 25);
		btnAdd.setToolTipText("Thêm từ vào từ điển này");
		btnAdd.setContentAreaFilled(false);
		btnAdd.addActionListener(this);
		boxToolPanel.add(btnAdd);
		
		// Button Modify
		btnEdit = new JButton("Sửa");
		btnEdit.setIcon(IconItem.iconModify);
		btnEdit.setBounds(110, 10, 90, 25);
		btnEdit.setToolTipText("Sửa từ");
		btnEdit.setContentAreaFilled(false);
		btnEdit.addActionListener(this);
		boxToolPanel.add(btnEdit);
				
		// Button Delete
		btnDelete = new JButton("Xóa");
		btnDelete.setIcon(IconItem.iconDelete);
		btnDelete.setBounds(210, 10, 90, 25);
		btnDelete.setToolTipText("Xóa từ");
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(this);
		boxToolPanel.add(btnDelete);
		
		// Button Pronounce
		btnPronounce = new JButton("Phát âm");
		btnPronounce.setIcon(IconItem.iconDelete);
		btnPronounce.setBounds(350, 10, 1, 25);
		btnPronounce.setToolTipText("Phát âm từ này");
		btnPronounce.setContentAreaFilled(false);
		btnPronounce.addActionListener(this);
		boxToolPanel.add(btnPronounce);
		
		// Label Detail
		lblDetail = new JLabel("Nghĩa của từ: ");
		lblDetail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblDetail.setBounds(10, 60, 180, 20);
		add(lblDetail);
		
		//	Detail 
		txtAreaDetail = new JTextArea("Welcome to DictionaryEV!");
		txtAreaDetail.setBounds(10, 90, 570, 550);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
	    txtAreaDetail.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
	    txtAreaDetail.setEditable(false);
		add(txtAreaDetail);
		
		
		setBounds(290, 0, 600, 700);
		setBackground(Color.WHITE);
		setLayout(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAdd)) {
			System.out.println("Add");
		}
	}
}

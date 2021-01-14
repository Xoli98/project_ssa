package gui;

import src.database;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.JButton;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.util.*;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


;public class main_window_ui extends JFrame {
	
	List<String> features;
	double[][] theta;
	Statement stmt = null;
	Connection conn = null;
	
	String[] arr_categories; 
	String[] arr_query;
	JPanel contentPane;
	JList listCategories;
	JList listInbox;
	
	static database db;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_window_ui frame = new main_window_ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_window_ui() {
		
		//retrieve values to be displayed from database
		db = database.get_instance();
		arr_categories = db.select_categories();
		arr_query = db.select_queries(null);
		if(arr_query.length==0) arr_query = new String[] {"Nothing to show. "};
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JButton btnAddCategory = new JButton("Reset");
		btnAddCategory.setBounds(40, 335, 128, 23);
		contentPane.add(btnAddCategory);
		
		
		
		listInbox = new JList();
		listInbox.setBounds(218, 99, 416, 264);
		listInbox.setBorder(new LineBorder(new Color(0, 0, 0)));
		listInbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//contentPane.add(new JScrollPane(listInbox));
		listInbox.setModel(new AbstractListModel() {
			String[] values = arr_query;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		listInbox.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            
		            String query = arr_query[index];
		            
		            //search for query_id in database;
		            
		            //send id to edit category ui
		            System.out.println(index);
		            
		        }
		    }
		});

		contentPane.add(listInbox);
		
		listCategories = new JList();
		listCategories.setBounds(50, 102, 112, 149);
		listCategories.setModel(new AbstractListModel() {
			String[] values = arr_categories;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		listCategories.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            String category = arr_categories[index];
	            	arr_query = db.select_queries(category);
		            System.out.println("index: "+ index+", class: "+arr_categories[index]);
		        }
		    }
		});
		listCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(listCategories);
		
		JLabel lblNewLabel = new JLabel("- MAIL CLASSIFYER -");
		lblNewLabel.setBounds(118, 21, 386, 28);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 81, 155, 170);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Inbox");
		lblNewLabel_1.setBounds(266, 60, 112, 23);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(218, 81, 416, 170);
		contentPane.add(separator_1);

		JLabel lblNewLabel_2 = new JLabel("Categories");
		lblNewLabel_2.setBounds(66, 56, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnPerformance = new JButton("Performance");
		btnPerformance.setBounds(40, 301, 131, 23);
		contentPane.add(btnPerformance);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(26, 281, 155, 9);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_3 = new JLabel("Model");
		lblNewLabel_3.setBounds(78, 262, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		
		setContentPane(contentPane);
		
		
	}
	
	public void set_categories(String[] categories) {
		arr_categories = categories;
		System.out.println("set category.....");
		listInbox.setModel(new AbstractListModel() {
			String[] values = arr_categories;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	public void set_messages(String[] querries) {
		arr_query = querries;
		System.out.println("set message...");
		listInbox.setModel(new AbstractListModel() {
			String[] values = arr_query;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
}

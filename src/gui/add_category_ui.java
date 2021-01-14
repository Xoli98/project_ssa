package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

public class add_category_ui extends JFrame {

	JPanel contentPane;
	JTextField txtCategoryName;
	JTextArea txtDescription;
	JLabel lblCategoryNo;
	public JButton btnAdd; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_category_ui frame = new add_category_ui();
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
	public add_category_ui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(36, 81, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCategoryName = new JTextField();
		txtCategoryName.setBounds(92, 78, 235, 20);
		contentPane.add(txtCategoryName);
		txtCategoryName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setBounds(36, 108, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(36, 117, 517, 105);
		contentPane.add(txtDescription);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(464, 244, 89, 23);
		contentPane.add(btnAdd);
		
		lblCategoryNo = new JLabel("Category 1");
		lblCategoryNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoryNo.setBounds(36, 40, 84, 30);
		contentPane.add(lblCategoryNo);
		
		JLabel lblNewLabel_2 = new JLabel("Add Category");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		lblNewLabel_2.setBounds(163, 11, 155, 44);
		contentPane.add(lblNewLabel_2);
	}
	
	
	public void setCategoryNo(String str) {
		lblCategoryNo.setText(str);
	}
	
	public String get_categoryName(){
		
		try {
			return txtCategoryName.getText();
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public String get_examplaryDescription(){
		
		try {
			return txtDescription.getText();
		}catch(Exception e) {
			return null;
		}
		
	}
}

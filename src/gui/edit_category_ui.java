package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class edit_category_ui extends JFrame {

	private JPanel contentPane;
	private static String query;
	private static String category;
	private static edit_category_ui instance;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_category_ui frame = new edit_category_ui();
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
	
	
	//we actually just wanna display and 
	public edit_category_ui get_instance() {
		
		return instance;
	}
	
	
	
	
	public edit_category_ui(int category_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		contentPane.setLayout(null);
		
		JTextArea txtQuery = new JTextArea();
		txtQuery.setLineWrap(true);
		txtQuery.setEditable(false);
		txtQuery.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtQuery.setBounds(21, 81, 441, 230);
		contentPane.add(txtQuery);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(60, 312, 164, -50);
		
		contentPane.add(list);
		
		
		//handle this 
		JComboBox cmbCategories = new JComboBox();
		cmbCategories.setModel(new DefaultComboBoxModel(new String[] {"**select category**", "class 1", "class 2", "class 3"}));
		cmbCategories.setBounds(101, 335, 164, 20);
		contentPane.add(cmbCategories);
		
		JLabel lblNewLabel = new JLabel("Edit Category");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(94, 11, 234, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnChange = new JButton("Change");
		btnChange.setBackground(Color.GREEN);
		btnChange.setBounds(284, 335, 89, 23);
		contentPane.add(btnChange);
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Category: ");
		lblNewLabel_1.setBounds(21, 56, 52, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCategoryName = new JLabel("Class 1");
		lblCategoryName.setBounds(83, 56, 114, 14);
		contentPane.add(lblCategoryName);
	}
	
	
	

}

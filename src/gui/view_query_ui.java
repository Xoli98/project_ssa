package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class view_query_ui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_query_ui frame = new view_query_ui();
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
	public view_query_ui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblPress = new JLabel("Press");
		lblPress.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblPress.setForeground(Color.BLUE);
		lblPress.setBackground(Color.GREEN);
		lblPress.setBounds(225, 52, 32, 14);
		contentPane.add(lblPress);
		
		JLabel lblIfQueryIncorrectly = new JLabel("if query incorrectly classified.");
		lblIfQueryIncorrectly.setBackground(Color.GREEN);
		lblIfQueryIncorrectly.setBounds(261, 52, 146, 14);
		contentPane.add(lblIfQueryIncorrectly);
		
		JTextArea txtQuery = new JTextArea();
		txtQuery.setBounds(21, 77, 467, 177);
		contentPane.add(txtQuery);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(300, 265, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setBackground(Color.GREEN);
		btnOkay.setSelected(true);
		btnOkay.setForeground(Color.BLACK);
		btnOkay.setBounds(399, 265, 89, 23);
		contentPane.add(btnOkay);
		
		JLabel lblNewLabel_1 = new JLabel("Message UI");
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(108, 11, 220, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Category:");
		lblNewLabel_2.setBounds(21, 52, 49, 14);
		contentPane.add(lblNewLabel_2);
		
		
		setContentPane(contentPane);
		
		JLabel lblCategoryName = new JLabel("Class 1");
		lblCategoryName.setBounds(78, 52, 94, 14);
		contentPane.add(lblCategoryName);
	}

}

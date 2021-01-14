package gui;


import src.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.util.*;

import java.net.*;

public class send_query_ui extends JFrame {

	private JPanel contentPane;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					send_query_ui frame = new send_query_ui();
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
	public send_query_ui() {
		
		
		//vocab = file_handler.read_from("vocab.txt");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(300, 300));
		contentPane.setPreferredSize(new Dimension(8, 8));
		contentPane.setBounds(new Rectangle(5, 5, 5, 5));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MAIL SENDER UI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setPreferredSize(new Dimension(80, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(126, 11, 148, 36);
		contentPane.add(lblNewLabel);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtArea.setBounds(10, 58, 414, 140);
		contentPane.add(txtArea);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//push to file 
				
				String str = txtArea.getText();
				if(str != null) {
					//option pain message sent
					//predict class and send to database
					predict(str);
					
				}
			
			}
		});
		
		

		btnSend.setBackground(Color.GREEN);
		btnSend.setBounds(335, 209, 89, 23);
		contentPane.add(btnSend);
		
	}
	
	
	private String predict(String str){
		
		String temp = preprocessor.preprocess(str);
		double[] onehot = feature_selection.transform(temp);
		double pred[][] = multinomial_regressor.predict(onehot);
		matrix.toString(pred);
		
		return null;
	}

}

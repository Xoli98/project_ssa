package src;

import src.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

import gui.*;

public class Main {
	static Hashtable<String, String> train_data;
	static List<String> query_class; 
	static int no_categories;
	static database db;
	private static multinomial_regressor model;
	
	
	
	public static void main(String[] args) {
		db = database.get_instance();
		model = multinomial_regressor.get_instance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					if(!model.isTrained()) train();
					//display windows
					
					
					
					//two concurrent methods 
					main_window_ui server = new main_window_ui(); //client 
					send_query_ui client = new send_query_ui(); //server
					client.setVisible(true);
					server.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	
	private static double[][] transform_targets(List<String> t) {
		
		double[][] Y = new double[no_categories][no_categories];
		for(int i=0; i < query_class.size(); i++) {
			Y[i][i] = 1;
		}
		return Y;
	}
	
	
	public static void train() {
		
		
		//get training data
		train_data = get_traindata();
		//transform train data to vectors
		double[][] X = transform(train_data);
		//covert enumeration type to list
		query_class = Collections.list(train_data.keys());
		
		//transform targets to vectors 
		double[][] Y = transform_targets(query_class);
		//save targets(category) to database
		
		//fit transformed trained model to model
		fit(X,Y);
		
	}
	
	
	private static void fit(double[][] X, double[][] Y) {
		model.fit(X, Y);
		model.serialize();
		db.insert_categories(query_class);
	
	}

	
	public static double[][] transform(Hashtable<String, String> train_data) {
		//preprocess
		List<String> values = new ArrayList<String>();
		System.out.println(train_data.toString());
		System.out.println("categories retrieved..."); 
		for(String value: train_data.values()) {
			String temp = preprocessor.preprocess(value);
			values.add(temp);
		}
		System.out.println(values.toString());
		System.out.println("exemplary text preprocessed..."); 
		double[][] X = feature_selection.fit_transform(values);
		System.out.println("features selected ..."); 
		return X;
	}
	
	public static Hashtable<String, String> get_traindata() {
		//number of categories 
		no_categories = get_no_categories();
		//k- category, v-query
		
		Hashtable<String, String> train_data = new Hashtable<String, String>();
		String error ;
		for(int i=0; i<no_categories; i++) {
			error = "";
			String category_name, exemplary_text;
			
			while(true) {
				category_name = JOptionPane.showInputDialog(error+"Name of message group "+ i+ " . (n.b. text must only be 10 characters long.).");
				if(category_name == null) continue; error = "ERR: Null input. \n ";
				category_name = filter_characters(category_name, 10);
				if(train_data.containsKey(category_name)) continue; error="ERR: Key exists. Pick another key.";
				error = "";
				break;
			}
			
			
			while(true) {
				exemplary_text = JOptionPane.showInputDialog(error+"Input exemplary text for group '" + category_name+ "' . (n.b. text must only be 100 characters long.).");
				if(exemplary_text == null) continue; error = "ERR: Null input. \n ";
				break;
			}
			
			exemplary_text = filter_characters(exemplary_text, 100);
			train_data.put(category_name.toLowerCase(), exemplary_text.toLowerCase());
			
		}
		return train_data; 
	}
	
	private static int get_no_categories() {
		int intCategories;
		String error = "";
		while(true) {
			String responce = JOptionPane.showInputDialog(error+"Pick number of categories. i.e. number between 2 and 5.");
			try {
				intCategories = Integer.parseInt(responce);
				
			}catch(NumberFormatException e) {
				error = "Err: numerical inputs only. \n";
				continue;
			}
			if(intCategories >= 2 && intCategories <= 5) break;
			error = "Err: input outside range. \n";
			
		}
		return intCategories;
	}

	private static String filter_characters(String str, int limit) {
		
		if (str == null) return null;
		char[] arr = str.toCharArray();
		
		if( arr.length > limit) {
			char[] temp = new char[limit];
			for(int i=0; i<limit; i++) {
				temp[i] = arr[i];
			}
			arr = temp;
		}
		return String.valueOf(arr);
		
	}

}

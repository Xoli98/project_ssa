package src;

import java.io.*;
import java.util.*;
public class file_handler {

	static List<String> content; 
	static File file;
	static PrintWriter printwriter;
	public static boolean write_to(String filename, List<String> content, boolean append) {
		file = new File(filename);
		try {
			if(!file.exists()) file.createNewFile();
			printwriter = new PrintWriter(new FileWriter(file, false));
			for(String line: content) {
				printwriter.write(line+"\n");
				printwriter.flush();
			}
			printwriter.close();
			return true;
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static List<String> read_from(String filename) {
		file = new File(filename);
		if(!file.exists()) return null;
		content = new ArrayList<String>(); 
		Scanner sc;
		try {
			sc = new Scanner(file);
			while(sc.hasNext()) {
				String line = sc.nextLine();
				content.add(line);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return content;
	}
	
	public static void serialize(Object obj, String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(obj);
			objectOut.close();
			System.out.println("The Object  was succesfully written to a file '" +filename+"'.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Object deserialise(String filename) {
		ObjectInputStream objectinputstream = null;
		Object obj = null;
		try {
		    FileInputStream streamIn = new FileInputStream(filename);
		    objectinputstream = new ObjectInputStream(streamIn);
		    System.out.println("object read!!!");
		    obj = objectinputstream.readObject();
		    
		    
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
		    if(objectinputstream != null){
		        try {
					objectinputstream .close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		    } 
		    return obj;
		}
	}
}

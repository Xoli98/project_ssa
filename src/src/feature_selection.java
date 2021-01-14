package src;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



public class feature_selection {
	
	static Hashtable<String,Integer> feature_frequency;
	static List<String> features;
	public static List<String> vocab; 
	
	
	
	
	
	public static double[][] fit_transform(List<String> values) { //fit_transform()
		if(create_features(values)) {
			//select features 
			double[][] arr = new double[values.size()][features.size()];
			for(int i=0; i<values.size(); i++) {
				arr[i] = transform(values.get(i));
			}
			return arr;
			
		}else {
			return null;
		}
		
	}
	
	private static boolean create_features(List<String> values){
		//create features 
		if(values == null) return false;
		feature_frequency = get_feature_frequency(values);
		features = filter_frequency(0.15);
		file_handler.write_to("features.txt", features, false);
		System.out.println("features created....");
		return true;
	}
	
	private static Hashtable<String, Integer> get_feature_frequency(List<String> values){
		
		feature_frequency= new Hashtable<String,Integer>();
		for(String val: values) {
			
			String[] tokens = val.split(" ");
			for(int i=0; i < tokens.length; i++) {
				
				if(feature_frequency.containsKey(tokens[i])) {
					int temp = feature_frequency.get(tokens[i]);
					temp += 1;
					feature_frequency.replace(tokens[i], temp);
				}else {
					feature_frequency.put(tokens[i], 1);
				}
			}
		}
		return feature_frequency;
	}
	
	private static List<String> filter_frequency(double threshold){
		threshold *= feature_frequency.size();
		List<String> keys = Collections.list(feature_frequency.keys());
		for( String key: keys) {
			if(threshold < feature_frequency.get(key)) {
				feature_frequency.remove(key);
			}
		} 
		return keys;
	}
	
	
	
	
	
	public static Hashtable<String, Integer> get_word_frequency(List<String> values){
		
		//
		Hashtable<String,Integer> v= new Hashtable<String,Integer>();
		for(String val: values) {
			
			String[] tokens = val.split(" ");
			for(int i=0; i < tokens.length; i++) {
				
				if(v.containsKey(tokens[i])) {
					int temp = v.get(tokens[i]);
					temp += 1;
					v.replace(tokens[i], temp);
				}else {
					v.put(tokens[i], 1);
				}
			}
		}
		
		
		//remove the frequency threshold
		double threshold = v.size() * 0.15;
		
		List<String> keys = Collections.list(v.keys());
		for( String key: keys) {
			if(threshold < v.get(key)) {
				v.remove(key);
			}
		} 
		
		
		//vocab
		List<String> vocabulary = Collections.list(v.keys());
		
		Hashtable<String, Integer> vocab = new Hashtable<String,Integer>();
		for(int i=0; i < v.size();i++) {
			vocab.put(vocabulary.get(i), i);
		}
		
		// save to file.
		

		try {
			PrintWriter pw = new PrintWriter("vocab.txt");
			for(String w: vocabulary) {
				pw.write(w + "\n");
			}
			
			pw.flush();
			pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		return v;
	}
	
	public static double[] transform(List<String> features, String str){
		
		double[] X = new double[features.size()];
		String[] tokens = str.split(" ");
		for(int i=0; i<tokens.length; i++) {
			if(features.contains(tokens[i])) X[i] = 1;
		}
		return X;
	}
	
	public static double[] transform(String str) {
		
		
		System.out.println(str);
		List<String> vocab = file_handler.read_from("features.txt");
		System.out.println("vocab:" + vocab.toString());
		String[] tokens = str.split(" ");
	    HashMap<String, Integer> myWordsCount = new HashMap<String, Integer>();
	    for (String t : tokens){
	        if (myWordsCount.containsKey(t)) myWordsCount.replace(t, myWordsCount.get(t) + 1);
	        else myWordsCount.put(t, 1);
	    }
	    
	    System.out.println("count:" + myWordsCount.toString());
	    double vector[] = new double[vocab.size()];
	    for(String key: myWordsCount.keySet()) {
	    	//word
	    	if(vocab.contains(key)) {
	    		vector[vocab.indexOf(key)] = 1.0;
	    	}
	    }
	    
		return vector;
	}
	
	
	
	public static double[] get_onehotvector(List<String> lst, String str) {
		double[] vector = new double[lst.size()];
		for(int i=0; i<lst.size(); i++) {
			
			if(lst.get(i) == str) vector[i] = 1;
			else vector[i] = 0;
		}
		return vector;
	}
	
	
	private static List<String> get_vocab_from_file() {
		
		
		Scanner sc = new Scanner("vocab.txt");
		List<String> vocab = new ArrayList<String>();
		while(sc.hasNext()) {
			vocab.add(sc.nextLine().trim());
			
		}
		
		return vocab;
		
	}
	
	
	
	
	

}

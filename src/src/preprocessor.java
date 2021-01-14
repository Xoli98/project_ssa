package src;

import java.util.*;
import src.stemmer;
//create interface for this class 



public class preprocessor {
	
	
	List<String> stopwords = new ArrayList<>(Arrays.asList("a","the","by",
			"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", 
			"yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
			"her", "hers", "herself", "it", "its", "itself", "they", "them", "their", 
			"theirs", "themselves", "what", "which", "who", "whom", "this", "that", 
			"these", "those", "am", "is", "are", "was", "were", "be", "been", "being", 
			"have", "has", "had", "having", "do", "does", "did","didn", "doing", "a", "an", "the", 
			"and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", 
			"for", "with", "about", "against", "between", "into", "through", "during", 
			"before", "after", "above", "below", "to", "from", "up", "down", "in", "out", 
			"on", "off", "over", "under", "again", "further", "then", "once", "here", 
			"there", "when", "where", "why", "how", "all", "any", "both", "each", "few", 
			"more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", 
			"same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", 
			"should", "now"));
	
	
	public static String preprocess(String str){
		str = str.toLowerCase().trim();
		str = removePunctuation(str);
		str = applyStem(str);
		
		return str;
	}
	
	private static String removePunctuation(String str) {
		
		String[] tokens = str.split("\\W+");
		String results = "";
		for(int i=0; i < tokens.length; i++) results += tokens[i]+" ";
		
		return results.trim(); 
		
	}
	private String removeStopwords(String str){
		
		String[] tokens = str.split(" ");
		String results = "";
		for(String token: tokens) {
			if(!stopwords.contains(token)) {
				results += tokens + " ";
			}
		}
		return results.trim();
	}
	
	
	private static String applyStem(String str) {
		String[] tokens = str.split(" ");
		String results = "";
		for(int i=0; i<tokens.length; i++) {
			results += stemmer.stem(tokens[i]) + " ";
		}
		return results.trim();
	}
	
	
	
}

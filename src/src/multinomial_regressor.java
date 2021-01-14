package src;

import java.io.*;
import java.util.*;
import src.matrix;

public class multinomial_regressor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private final String MODELFILE = "model.obj";
	private final double LEARNING_RATE = 0.001;
	private final int EPOCHS = 200000;
	private int n_classes;
	private int n_features;
	private double[][] theta = null;
	private static multinomial_regressor instance = null;
	

	
	public static multinomial_regressor get_instance() {
		if(instance == null) {
			instance = new multinomial_regressor();
			File file = new File(instance.MODELFILE);
			if(file.exists()) 
				instance = (multinomial_regressor) file_handler.deserialise(instance.MODELFILE);
		}
		return instance;
	}
	
	
	
	public static void fit(double[][] X, double[][] Y ) {
		
		System.out.println("X");
		matrix.toString(X);
		
		System.out.println("Y");
		matrix.toString(Y);
		
		int n_rows = X.length;
		int n_features = X[0].length;
		int n_classes = Y[0].length;
		instance.theta = matrix.zeros(n_classes, n_features);
		System.out.println("theta");
		matrix.toString(instance.theta);
		double[][] lineq, delta, cost, m1, m2, m3 = null;
		
		
		List<double[]> costs = new ArrayList<>();
		for (int i = 0; i < instance.EPOCHS; i++) {
			//predict
			
			
			lineq = _predict(X);
			
			//mean error
			//cost = -1/m * ((Y * log(h)) + ((1-Y) * log(1-h)))
			//delta = (learning_rate/m) * dot((h-Y).T, X)
			
			//-1/m * ((Y * np.log(h))
			
			
			//mean error
			
			double[][] mean_error, y; 
			y = matrix.subtract(lineq, Y);
			mean_error = matrix.dot(matrix.transpose(X), y);
			mean_error = matrix.multiply(mean_error, instance.LEARNING_RATE/(double)n_rows);
			
			double[][] a,b,c;
			
			a = matrix.log(lineq);
			a = matrix.multiply(Y, a);
			

			//np.log(1-h)
			b = matrix.multiply(lineq, -1.0);
			b = matrix.add(b, 1);
			b = matrix.log(b);

			//(1-Y)
			c = matrix.multiply(Y, -1.0);
			c = matrix.add(Y, 1);
			b = matrix.multiply(c, b);
			
			//((1-Y) * np.log(1-h))
			
			cost = matrix.add(a, b);
			cost = matrix.multiply(cost, -1/(double)n_rows);
			
			double[] m = matrix.sum(cost);
			
			//change cost 
			costs.add(m);
			a = matrix.subtract(lineq, Y);
			a = matrix.transpose(a);
			a = matrix.dot(a, X);
			
			double no = instance.LEARNING_RATE/(double)n_rows;
			delta = matrix.multiply(a, no);
			instance.theta = matrix.subtract(instance.theta, delta);
			System.out.println("epoch= " +i + " ["+m[0]+","+m[1]+","+m[2]+"]");
			
		}
	
		
	}
	
	
	
	
	
	public void serialize() {
		if(instance==null) return;
		file_handler.serialize(instance, instance.MODELFILE);
	}
	
	
	private static void print_dimensions(double[][] a) {
		
		System.out.println("r: "+a.length+ ", c: " +a[0].length);
	}
	private static double[][] sigmoid(double[][] lineq) {
		
		//1 / (1 + np.exp(-z))
		
		lineq = matrix.multiply(lineq, -1.0);
		lineq = matrix.exp(lineq);
		lineq = matrix.add(lineq, 1);
		//divide for normalisation
		return matrix.divide(lineq, 1);
	}
	
	
	private static double[][] _predict(double[][] X){
		double[][] t = matrix.transpose(instance.theta);
		X = matrix.dot(X,t);
		System.out.println("dot");
		matrix.toString(X);
		return sigmoid(X);
	}
	
	public static double[][] predict(double[] X ) {
		double[][] x = new double[1][];
		x[0] = X;
		if(instance.theta == null) return null;
		
		double[][] prediction = _predict(x);
		System.out.println("prediction: r:" +prediction.length+ "; c:"+prediction[0].length);
		matrix.toString(prediction);
		for(int i=0; i < prediction[0].length; i++) prediction[0][i] = Math.round(prediction[0][i]);
		return prediction;
	}
	
	
	public boolean isTrained() {
		System.out.print("is trained?");
		if(instance.theta==null) {
			System.out.print("no"+"\n");
			return false;
		} 
		
		System.out.print("yes"+"\n");
		return true;
	}
}

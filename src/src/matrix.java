package src;

import java.util.Random;
import java.io.*;


public class matrix {
public static double[][] transpose(double[][] a) {
		
	
		int row = a.length;
		int col = a[0].length;
		double[][] temp = new double[col][row];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j< col; j++) {
				temp[j][i] = a[i][j];
			}
		}
		return temp;
		
	}
	
	public static double[][] random(int row, int col) {
		Random rand = new Random();
		double[][] temp = new double[row][col];
		for(int i = 0;i < temp.length; i++) {
			for(int j = 0; j<temp[0].length; j++) {
				temp[i][j] = rand.nextDouble();
			}
		}
		return temp;
	}
	
	public static double[][] zeros(int row, int col) {
		double[][] temp = new double[row][col];
		for(int i = 0;i < temp.length; i++) {
			for(int j = 0; j<temp[0].length; j++) {
				temp[i][j] = 0;
			}
		}
		return temp;
	}
	
	
	public static double[][] multiply(double[][] a, double b) {
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] *= b;
			}
		}
		return a;
	}
	
	public static double[][] multiply(double[][] a, double[][] b) {
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] *= b[j][i];
			}
		}
		
		return a;
	}
	
	
	public static double[][] dot(double[][] a, double[][] b) {
		
		
		
		int r1 = a.length;
		int c1 = a[0].length;
		int r2 = b.length;
		int c2 = b[0].length;
		if(c1 != r2) return null;
		
		double[][] temp = new double[r1][c2];
		for(int i = 0;i < r1; i++) {
			for(int j = 0; j< c2; j++) {
				for(int k = 0; k < c1; k++ ) {
					temp[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return temp;
	}
	
	
	
	 public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int r1, int c1, int c2) {
	        
			int[][] product = new int[r1][c2];
			for(int i = 0; i < r1; i++) {
			    for (int j = 0; j < c2; j++) {
			        for (int k = 0; k < c1; k++) {
			            product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
			        }
			    }
			}
			return product;
	    }
	 
	 
	 
	public static double[] dot(double[] a, double[][] b) {
		
		
		if(a.length != b.length) return null;
		
		int rows = a.length;
		int cols = b.length;
		
		
		double[] temp = new double[rows];

		for(int i = 0;i < rows; i++) {
			for(int j = 0; j< cols; j++) {
				temp[i] += a[i] * b[i][j];
			}
		}
		return temp;
	}
	
	
	
	
	public double[][] subtract(double[][] a, double b) {
		
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] -= b;
			}
		}
		return a;
	}
	
	
	public static double[] sum( double[][] a ) {
		
		
		
		double[] sum = new double[a.length];
		for(int i=0; i < a.length; i++) {
			
			double total = 0.0;
			for(int j=0; j < a[0].length; j++) {
				total += a[i][j];
			}
			sum[i] = total;
		}
		
		return sum;
	}
	
	public static double[][] add(double[][] a, double[][] b) {	
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] += b[i][j];
			}
		}
		return a;
	}
	
	public static double[][] subtract(double[][] a, double[][] b) {	
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] -= b[i][j];
			}
		}
		return a;
	}
	
	public static double[][] add(double[][] a, double b) {
		
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] += b;
			}
		}
		return a;
	}
	
	public double[][] divide(double[][] a, double[][] b) {
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] /= b[i][j];
			}
		}
		return a;
	}
	
	public static double[][] divide(double[][] a, double b) {
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] = b/a[i][j];
			}
		}
		return a;
	}
	
	public static double[][] exp(double[][] a) {
	
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] = Math.pow(Math.E, a[i][j]);
			}
		}
		return a;
	}
	
	public static double[][] log(double[][] a) {
		
		for(int i = 0;i < a.length; i++) {
			for(int j = 0; j<a[0].length; j++) {
				a[i][j] = Math.log(a[i][j]);
			}
		}
		return a;
	}
	
	
	public void save(double[][] arr, String name) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < arr.length; i++)//for each row
		{
		   for(int j = 0; j < arr.length; j++)//for each column
		   {
		      builder.append(arr[i][j]+"");//append to the output string
		      if(j < arr.length - 1)//if this is not the last row element
		         builder.append(",");//then add comma (if you don't like commas you can use spaces)
		   }
		   builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(name));
			writer.write(builder.toString());//save the string representation of the board
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void retrieve(String name) {
		int[][] board = new int[9][9];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(name));
			String line = "";
			int row = 0;
			while((line = reader.readLine()) != null)
			{
			   String[] cols = line.split(","); 
			   int col = 0;
			   for(String  c : cols)
			   {
			      board[row][col] = Integer.parseInt(c);
			      col++;
			   }
			   row++;
			}
			reader.close();
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void toString(double[][] a) {
		
		int r = a.length;
		int c = a[0].length;
		
		System.out.println("-----------------------");
		System.out.println("{");
		for(int i=0; i< r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(" " +a[i][j] + " ;");
			}
			System.out.print("\n");
		}
		System.out.println("}");
		
		System.out.println("-----------------------");
	}
}

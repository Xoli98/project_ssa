package src;

import java.sql.*;
import java.util.*;


public class database {
	   
	  private static final String DB_URL = "jdbc:mysql://localhost/ssa";
	  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  private static final String USER = "root";
	  private static final String PASS = "12345";
	  private static Connection conn = null;
	  private static ResultSet result_set = null;
	  private static Statement stmt = null;
	
   
	  //singleton design pattern
	  private static database instance = null;
	  public static database get_instance() {
		  if(instance==null) instance = new database();
		  return instance;
	  }
	  private database() {
		   try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				
				System.out.println("connection and statement created");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   
	   }
	   
	   
	   
	   public static void initialise(Statement stmt) throws SQLException {
		   System.out.println(stmt.toString());
		   String sql = null;
		   
		    sql = "CREATE TABLE IF NOT EXISTS CATEGORY " +
		       "(category_id INTEGER NOT NULL AUTO_INCREMENT, " +
		       " name VARCHAR(10) NOT NULL, " + 
		       " PRIMARY KEY ( category_id )" +
		       ")";
		stmt.executeUpdate(sql);
		
    sql = "CREATE TABLE IF NOT EXISTS QUERY " +
		       "(query_id INTEGER NOT NULL AUTO_INCREMENT, " +
		       " value VARCHAR(100) NOT NULL, " + 
		       " category_name VARCHAR(10) NOT NULL, " + 
		       " is_seen BIT DEFAULT 0, " +
		       " is_fixed BIT DEFAULT 0, " +
		       " PRIMARY KEY ( query_id ),"+
		       " FOREIGN KEY (category_name) REFERENCES CATEGORY(name))";
	stmt.executeUpdate(sql);
	System.out.println("Database created successfully...");
   }
   
   
   public static boolean insert_categories(List<String> c) {

	   if(c == null) return false;
	   try {
			for(String i: c) {
			   String sql = "INSERT INTO category(category_name) " +
                   "VALUES ('"+i+"')";
			   stmt.executeUpdate(sql);
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	   return true;
   }
   
   
   public static String[] select_categories() {
	   result_set = null;
	   String sql = "select category_name from category";
	   String[] results = null;
	   try {
		   result_set = stmt.executeQuery(sql);
			results = toList(result_set, "category_name");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return results;
	   }
   
   
   private static String[] toList(ResultSet rs, String columns){
	   List<String> results;
	   results = new ArrayList<String>();
	   try {
		   while(result_set.next()) {
			   results.add(result_set.getString(columns));
		   }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	   String[] temp = new String[results.size()];
	   for(int i=0; i<results.size(); i++) temp[i] = results.get(i);
	   return temp;
   }
   
   public static String[] select_queries(String category) {
	   result_set = null;
	   String[] results = null;
	   String sql = null;
	   try {
		   
		   if(category==null) {
			   sql = "select txt_query from query";
			   result_set = stmt.executeQuery(sql);
	   }else {
		   sql = "select txt_query from query "
			   		+ "where category_name = "+category+"";
		   result_set = stmt.executeQuery(sql);
	   }
	   results = toList(result_set, "value");
	   }catch(SQLException e1) {
		   e1.printStackTrace();
	   }  
	   return results;
   }
   
   
   public static void insert_query(String query, String category_name, String cn) {
	   try {
		   int category_id;
		   category_id = select_category_id(category_name);
		   String sql = "INSERT INTO query(txt_query, category_id) " +
               "VALUES ('"+query+","+category_id+")";
		   stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	  
   }
   
   public static void update_query(String q, String c) {
	   try {
		   String sql = "INSERT INTO query " +
               "VALUES ('"+q+","+c+",?')";
		   stmt.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	  
   }
   
   public static String[] del_query(String[] c) {
	   return null;
   }
   
   
   public static int select_category_id(String name) {
	   ResultSet rs = null;
	   int category_id = -1;
	   String sql = "select category_id from category where category_name="+name+"";
	   String[] results = null;
	   try {
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt("category_id");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return category_id;
	   }
	   
   
   private static void close(Connection conn, Statement stmt) {
	   try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
   }
}

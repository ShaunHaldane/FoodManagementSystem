package application;

import java.awt.Checkbox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConnectToDatabase {
	
	//Variable for storing date
	static Date today = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	static String inputDate=sdf.format(today);

	
	//Connect to database
	public static Connection getConnection() {
		Connection conn=null;		
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\W10 User\\Documents\\DataBases\\FoodControlAppDB");
				System.out.println("connected to db");
				return conn;
			}
			catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Counts rows in database
    public static int countRows(){
        String sql = "SELECT item FROM items";
        int rowCount = 0;
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	rowCount++;
            }
        } 
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return rowCount;
    }
	
////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Inserts data into database
    public void insert(int id, String item, String expiryDate, String price) {
        String sql = "INSERT INTO items(id, item, expiryDate, price) VALUES(?,?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setInt(1, id);
        	pstmt.setString(2, item);
            pstmt.setString(3, expiryDate);
            pstmt.setString(4, price);
            pstmt.executeUpdate();
            System.out.println("Items inserted to db");
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
////////////////////////////////////////////////////////////////////////////////////////////
    
    //Returns all the rows from database as an Arraylist of strings.
    public static ArrayList selectAllItems(){
        String sql = "SELECT item, expiryDate FROM items";
        ArrayList<String> finalList = new ArrayList<String>();
        ArrayList<Checkbox> checkBoxList = new ArrayList<Checkbox>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	
            	String dayExpiryCount = rs.getString("expiryDate").substring(0, 2);
            	String monthExpiryCount = rs.getString("expiryDate").substring(3, 5);
            	String yearExpiryCount = rs.getString("expiryDate").substring(6, 10);
            	
            	int dayExpiry = Integer.parseInt(dayExpiryCount);
            	int monthExpiry = Integer.parseInt(monthExpiryCount);
            	int yearExpiry = Integer.parseInt(yearExpiryCount);
            	
            	int dateTodayIntDay = Integer.parseInt(inputDate.substring(0, 2));
            	int dateTodayIntMonth = Integer.parseInt(inputDate.substring(3, 5));
            	int dateTodayIntYear = Integer.parseInt(inputDate.substring(6, 10));
            	
            	int daysTillExpiry = dayExpiry - dateTodayIntDay;
            	int monthsTillExpiry = monthExpiry - dateTodayIntMonth;
            	int yearsTillExpiry =  yearExpiry - dateTodayIntYear;
            	
            	
            	if(daysTillExpiry > 1 && monthsTillExpiry > 1) {
                	finalList.add(rs.getString("item") + " has " + Integer.toString(monthsTillExpiry) + " months  " + "and " + Integer.toString(daysTillExpiry) + " days till expired" );
            	}
            	else if(daysTillExpiry> 1 && monthsTillExpiry == 0) {
                	finalList.add(rs.getString("item") + " has  " + Integer.toString(daysTillExpiry) + " days till expired" );
            	}
            	else if(daysTillExpiry == 1 && monthsTillExpiry > 1) {
                	finalList.add(rs.getString("item") + " has  " + Integer.toString(monthsTillExpiry) + " months  " + "and " + Integer.toString(daysTillExpiry) + " day till expired" );
            	}
            	else if(daysTillExpiry == 1 && monthsTillExpiry == 0) {
                	finalList.add(rs.getString("item") + " has  " + Integer.toString(daysTillExpiry) + " day till expired" );
            	}
            	else if(daysTillExpiry == 0 && monthsTillExpiry > 1) {
                	finalList.add(rs.getString("item") + " has  " + Integer.toString(monthsTillExpiry) + " months till expired ");
            	}
            	else if(daysTillExpiry == 0 && monthsTillExpiry == 1) {
                	finalList.add(rs.getString("item") + " has  " + Integer.toString(monthsTillExpiry) + " month till expired ");
            	}
            	else if(daysTillExpiry == 1 && monthsTillExpiry == 1) {
                	finalList.add(rs.getString("item") +  " has 1 month and 1 day till expired ");
            	}
            	else if(daysTillExpiry == 0 && monthsTillExpiry == 0) {
            		finalList.add("You need to eat " + rs.getString("item") + " today");
            	}
            	else if(daysTillExpiry < 0 && monthsTillExpiry <= 0 && yearsTillExpiry <= 0) {
            		finalList.add(rs.getString("item") + " has expired, throw it out");           	
            	}
            	else {
            		finalList.add("empty");
            	}

//            	expiryDateArray.add(exp);
//            	pricesArray.add(Double.parseDouble(rs.getString("price")));	 	
            }
            
            System.out.print(finalList);
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return finalList; 

    }
    
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//  
  //Delete a row from database
  public static void delete(int id) {
      String sql = "DELETE FROM items WHERE id = ?";
      System.out.println("in delete");

      try (Connection conn = getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
      	pstmt.setInt(1, id);
          pstmt.executeUpdate();
          System.out.println("Item " + id + " Deleted");

      } 
      catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }
  
  
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //information for alerts section
  //Returns all the rows from database as an Arraylist of strings.
  public static ArrayList getAlerts(){
      String sql = "SELECT item, expiryDate FROM items";
      ArrayList<String> alertsList = new ArrayList<String>();
      
      try (Connection conn = getConnection();
           Statement stmt  = conn.createStatement();
           ResultSet rs    = stmt.executeQuery(sql)){
          
          // loop through the result set
          while (rs.next()) {
          	
          	String dayExpiryCount = rs.getString("expiryDate").substring(0, 2);
          	String monthExpiryCount = rs.getString("expiryDate").substring(3, 5);
          	String yearExpiryCount = rs.getString("expiryDate").substring(6, 10);
          	
          	int dayExpiry = Integer.parseInt(dayExpiryCount);
          	int monthExpiry = Integer.parseInt(monthExpiryCount);
          	int yearExpiry = Integer.parseInt(yearExpiryCount);
          	
          	int dateTodayIntDay = Integer.parseInt(inputDate.substring(0, 2));
          	int dateTodayIntMonth = Integer.parseInt(inputDate.substring(3, 5));
          	int dateTodayIntYear = Integer.parseInt(inputDate.substring(6, 10));
          	
          	int daysTillExpiry = dayExpiry - dateTodayIntDay;
          	int monthsTillExpiry = monthExpiry - dateTodayIntMonth;
          	int yearsTillExpiry =  yearExpiry - dateTodayIntYear;
          	
          	if(daysTillExpiry < 0 && monthsTillExpiry <= 0 && yearsTillExpiry <= 0) {
          		alertsList.add(rs.getString("item") + " has expired, throw it out");           	
          	}
          	else if(daysTillExpiry == 0 && monthsTillExpiry == 0) {
          		alertsList.add("You need to eat " + rs.getString("item") + " today");
          	}
          	else if(daysTillExpiry == 1 && monthsTillExpiry == 0) {
          		alertsList.add(rs.getString("item") + " will expire tomorrow");
          	}          
          	if(daysTillExpiry < 0 && monthsTillExpiry <= 0 && yearsTillExpiry <= 0) {
          		alertsList.add(rs.getString("item") + " has expired, throw it out");           	
          	}
          	else {
          		System.out.println("nothing");
          	}
          	
 	
          }
          
          ArrayList<Double> thisWeekSpendArray = ConnectToHistoryDatabase.thisWeekSpendArray();
  		ArrayList<Double> thisMonthSpendArray = ConnectToHistoryDatabase.thisMonthSpendArray();
  		
  		double thisWeek = 0;
  		for(int i = 0; i < thisWeekSpendArray.size(); i++) {
  			thisWeek += thisWeekSpendArray.get(i);
  		}
  		
  		double thisMonth = 0;
  		for(int i = 0; i < thisMonthSpendArray.size(); i++) {
  			thisMonth += thisMonthSpendArray.get(i);
  		}
  		
  		if(thisWeek > 150) {
  			alertsList.add("You have spent " + thisWeek + " this week");
  		}
  		
  		if(thisMonth > 600) {
  			alertsList.add("You have spent " + thisMonth + " this month");
  		}  
          
      }catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      return alertsList; 

  }
}
    

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

import javafx.scene.control.Label;

public class ConnectToHistoryDatabase {
	
	//Connect to database
	public static Connection getConnection() {
		Connection conn=null;		
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\W10 User\\Documents\\DataBases\\FoodControlHistoryDB");
				System.out.println("connected to db");
				return conn;
			}
			catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	
////////////////////////////////////////////////////////////////////////////////////////////
	
//Inserts data into database
	public void insertToHistoryDB(String item, String date, String price) {
	String sql = "INSERT INTO itemsHistory(item, date, price) VALUES(?,?,?)";
	
	try (Connection conn = getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	pstmt.setString(1, item);
	pstmt.setString(2, date);
	pstmt.setString(3, price);
	pstmt.executeUpdate();
	System.out.println("Items inserted to history db");
	} 
	catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	}

////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Returns all the rows from database as an Arraylist of strings.
    public static ArrayList selectAllItemsInHistory(){
        String sql = "SELECT item, date FROM itemsHistory";
        ArrayList<String> itemsStringList = new ArrayList<String>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	itemsStringList.add(rs.getString("item") + "          " + rs.getString("date"));
            	
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return itemsStringList;
         
}
  
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //Returns array of prices from history database
    public static ArrayList returnPriceDataFromHistory(){
        String sql = "SELECT price FROM itemsHistory";
        ArrayList<Integer> priceList = new ArrayList<Integer>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	priceList.add(Integer.parseInt(rs.getString("price")));
            	
            }
            System.out.println(priceList); 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return priceList;
         
}
    
    //Returns array of items from history database
    public static ArrayList returnItemDataFromHistory(){
        String sql = "SELECT item FROM itemsHistory";
        ArrayList<Integer> priceList = new ArrayList<Integer>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	priceList.add(Integer.parseInt(rs.getString("item")));
            	
            }
            System.out.println(priceList); 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return priceList;
         
}
    
    //Returns array of input dates from history database
    public static ArrayList returnInputDatesFromHistory(){
        String sql = "SELECT date FROM itemsHistory";
        ArrayList<String> inputDatesList = new ArrayList<String>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	inputDatesList.add(rs.getString("date"));
            	
            }
            System.out.println(inputDatesList); 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return inputDatesList ;
         
}  
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //Returns array of this weeks spend
    public static ArrayList thisWeekSpendArray(){
        String sql = "SELECT date, price FROM itemsHistory";
        ArrayList<String> inputDatesAndPriceStrings = new ArrayList<String>();
        ArrayList<Integer> inputDateInts = new ArrayList<Integer>();
        ArrayList<Double> priceDoubles = new ArrayList<Double>();
        ArrayList<Double> weekPrices = new ArrayList<Double>();
        
        
        double day1 = 0;
        double day2 = 0;
        double day3 = 0;
        double day4 = 0;
        double day5 = 0;
        double day6 = 0;
        double day7 = 0;
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	inputDatesAndPriceStrings.add(rs.getString("date") + " " + rs.getString("price"));
            	inputDateInts.add(Integer.parseInt(rs.getString("date").substring(0, 2)));
            	priceDoubles.add(Double.parseDouble(rs.getString("price")));
            	System.out.println(inputDateInts);
            	System.out.println(priceDoubles);
            }
            
            double dayPrice1 = 0;
            double dayPrice2 = 0;
            double dayPrice3 = 0;
            double dayPrice4 = 0;
            double dayPrice5 = 0;
            double dayPrice6 = 0;
            double dayPrice7 = 0;
            double dayPrice8 = 0;
            double dayPrice9 = 0;
            double dayPrice10 = 0;
            double dayPrice11 = 0;
            double dayPrice12 = 0;
            double dayPrice13 = 0;
            double dayPrice14 = 0;
            double dayPrice15 = 0;
            double dayPrice16 = 0;
            double dayPrice17 = 0;
            double dayPrice18 = 0;
            double dayPrice19 = 0;
            double dayPrice20 = 0;
            double dayPrice21 = 0;
            double dayPrice22 = 0;
            double dayPrice23 = 0;
            double dayPrice24 = 0;
            double dayPrice25 = 0;
            double dayPrice26 = 0;
            double dayPrice27 = 0;
            double dayPrice28 = 0;
            double dayPrice29 = 0;
            double dayPrice30 = 0;
            double dayPrice31 = 0;
            
            
            for(int i = 0; i < inputDateInts.size(); i++) {
            	if(inputDateInts.get(i) == 1) {
            		dayPrice1 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 2) {
            		dayPrice2 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 3) {
            		dayPrice3 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 4) {
            		dayPrice4 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 5) {
            		dayPrice5 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 6) {
            		dayPrice6 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 7) {
            		dayPrice7 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 8) {
            		dayPrice8 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 9) {
            		dayPrice9 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 10) {
            		dayPrice10 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 11) {
            		dayPrice11 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 12) {
            		dayPrice12 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 13) {
            		dayPrice13 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 14) {
            		dayPrice14 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 15) {
            		dayPrice15 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 16) {
            		dayPrice16 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 17) {
            		dayPrice17 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 18) {
            		dayPrice18 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 19) {
            		dayPrice19 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 20) {
            		dayPrice20 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 21) {
            		dayPrice21 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 22) {
            		dayPrice22 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 23) {
            		dayPrice23 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 24) {
            		dayPrice24 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 25) {
            		dayPrice25 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 26) {
            		dayPrice26 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 27) {
            		dayPrice27 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 28) {
            		dayPrice28 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 29) {
            		dayPrice29 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 30) {
            		dayPrice30 += priceDoubles.get(i);
            	}
            	else if(inputDateInts.get(i) == 31) {
            		dayPrice31 += priceDoubles.get(i);
            	}
            	else {
            		System.out.println("Date is out of bounds");
            	}
            }
            
            ArrayList<Double> totalSpentPerDayWeek1 = new ArrayList<Double>();
            ArrayList<Double> totalSpentPerDayWeek2 = new ArrayList<Double>();
            ArrayList<Double> totalSpentPerDayWeek3 = new ArrayList<Double>();
            ArrayList<Double> totalSpentPerDayWeek4 = new ArrayList<Double>();
            ArrayList<Double> totalSpentPerDayWeek5 = new ArrayList<Double>();
            
            //Variable for storing date
          	Date today = new Date();
          	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
          	String inputDate=sdf.format(today);
          	
          	int variableToFindWeek = Integer.parseInt(inputDate.substring(0,2));
              
              if(variableToFindWeek == 1 || variableToFindWeek <= 7) {
            	  totalSpentPerDayWeek1.add(dayPrice1);
            	  totalSpentPerDayWeek1.add(dayPrice2);
            	  totalSpentPerDayWeek1.add(dayPrice3);
            	  totalSpentPerDayWeek1.add(dayPrice4);
            	  totalSpentPerDayWeek1.add(dayPrice5);
            	  totalSpentPerDayWeek1.add(dayPrice6);
            	  totalSpentPerDayWeek1.add(dayPrice7);
            	  weekPrices = totalSpentPerDayWeek1;
              }
              else if(variableToFindWeek == 8 || variableToFindWeek <= 14) {
            	  totalSpentPerDayWeek2.add(dayPrice8);
            	  totalSpentPerDayWeek2.add(dayPrice9);
            	  totalSpentPerDayWeek2.add(dayPrice10);
            	  totalSpentPerDayWeek2.add(dayPrice11);
            	  totalSpentPerDayWeek2.add(dayPrice12);
            	  totalSpentPerDayWeek2.add(dayPrice13);
            	  totalSpentPerDayWeek2.add(dayPrice14);
            	  weekPrices = totalSpentPerDayWeek2;
              }
              else if(variableToFindWeek == 15 || variableToFindWeek <= 21) {
            	  totalSpentPerDayWeek3.add(dayPrice15);
            	  totalSpentPerDayWeek3.add(dayPrice16);
            	  totalSpentPerDayWeek3.add(dayPrice17);
            	  totalSpentPerDayWeek3.add(dayPrice18);
            	  totalSpentPerDayWeek3.add(dayPrice19);
            	  totalSpentPerDayWeek3.add(dayPrice20);
            	  totalSpentPerDayWeek3.add(dayPrice21);
            	  weekPrices = totalSpentPerDayWeek3;
              }
              else if(variableToFindWeek == 22 || variableToFindWeek <= 28) {
            	  totalSpentPerDayWeek4.add(dayPrice22);
            	  totalSpentPerDayWeek4.add(dayPrice23);
            	  totalSpentPerDayWeek4.add(dayPrice24);
            	  totalSpentPerDayWeek4.add(dayPrice25);
            	  totalSpentPerDayWeek4.add(dayPrice26);
            	  totalSpentPerDayWeek4.add(dayPrice27);
            	  totalSpentPerDayWeek4.add(dayPrice28);
            	  weekPrices = totalSpentPerDayWeek4;
              }
              else if(variableToFindWeek == 29 || variableToFindWeek <= 31) {
            	  totalSpentPerDayWeek5.add(dayPrice29);
            	  totalSpentPerDayWeek5.add(dayPrice30);
            	  totalSpentPerDayWeek5.add(dayPrice31);
            	  weekPrices = totalSpentPerDayWeek5;
              }
             

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return weekPrices;
         
}
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//Returns array of this months spend
    public static ArrayList<Double> thisMonthSpendArray(){
    	String sql = "SELECT date, price FROM itemsHistory";
    	ArrayList<String> inputDatesAndPriceStrings = new ArrayList<String>();
    	ArrayList<Integer> inputDateInts = new ArrayList<Integer>();
    	ArrayList<Double> priceDoubles = new ArrayList<Double>();
    	ArrayList<Double> weekPrices = new ArrayList<Double>();
    	ArrayList<Double> totalSpentPerMonth = new ArrayList<Double>();

    	try (Connection conn = getConnection();
    			Statement stmt  = conn.createStatement();
    			ResultSet rs    = stmt.executeQuery(sql)){

    		// loop through the result set
    		while (rs.next()) {
    			inputDatesAndPriceStrings.add(rs.getString("date") + " " + rs.getString("price"));
    			inputDateInts.add(Integer.parseInt(rs.getString("date").substring(0, 2)));
    			priceDoubles.add(Double.parseDouble(rs.getString("price")));
    			
    		}

	double dayPrice1 = 0;
	double dayPrice2 = 0;
	double dayPrice3 = 0;
	double dayPrice4 = 0;
	double dayPrice5 = 0;
	double dayPrice6 = 0;
	double dayPrice7 = 0;
	double dayPrice8 = 0;
	double dayPrice9 = 0;
	double dayPrice10 = 0;
	double dayPrice11 = 0;
	double dayPrice12 = 0;
	double dayPrice13 = 0;
	double dayPrice14 = 0;
	double dayPrice15 = 0;
	double dayPrice16 = 0;
	double dayPrice17 = 0;
	double dayPrice18 = 0;
	double dayPrice19 = 0;
	double dayPrice20 = 0;
	double dayPrice21 = 0;
	double dayPrice22 = 0;
	double dayPrice23 = 0;
	double dayPrice24 = 0;
	double dayPrice25 = 0;
	double dayPrice26 = 0;
	double dayPrice27 = 0;
	double dayPrice28 = 0;
	double dayPrice29 = 0;
	double dayPrice30 = 0;
	double dayPrice31 = 0;


	for(int i = 0; i < inputDateInts.size(); i++) {
		if(inputDateInts.get(i) == 1) {
			dayPrice1 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 2) {
			dayPrice2 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 3) {
			dayPrice3 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 4) {
			dayPrice4 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 5) {
			dayPrice5 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 6) {
			dayPrice6 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 7) {
			dayPrice7 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 8) {
			dayPrice8 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 9) {
			dayPrice9 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 10) {
			dayPrice10 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 11) {
			dayPrice11 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 12) {
			dayPrice12 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 13) {
			dayPrice13 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 14) {
			dayPrice14 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 15) {
			dayPrice15 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 16) {
			dayPrice16 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 17) {
			dayPrice17 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 18) {
			dayPrice18 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 19) {
			dayPrice19 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 20) {
			dayPrice20 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 21) {
			dayPrice21 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 22) {
			dayPrice22 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 23) {
			dayPrice23 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 24) {
			dayPrice24 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 25) {
			dayPrice25 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 26) {
			dayPrice26 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 27) {
			dayPrice27 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 28) {
			dayPrice28 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 29) {
			dayPrice29 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 30) {
			dayPrice30 += priceDoubles.get(i);
		}
		else if(inputDateInts.get(i) == 31) {
			dayPrice31 += priceDoubles.get(i);
		}
		else {
			System.out.println("Date is out of bounds");
		}
	}


	totalSpentPerMonth.add(dayPrice1);
	totalSpentPerMonth.add(dayPrice2);
	totalSpentPerMonth.add(dayPrice3);
	totalSpentPerMonth.add(dayPrice4);
	totalSpentPerMonth.add(dayPrice5);
	totalSpentPerMonth.add(dayPrice6);
	totalSpentPerMonth.add(dayPrice7);
	totalSpentPerMonth.add(dayPrice8);
	totalSpentPerMonth.add(dayPrice9);
	totalSpentPerMonth.add(dayPrice10);
	totalSpentPerMonth.add(dayPrice11);
	totalSpentPerMonth.add(dayPrice12);
	totalSpentPerMonth.add(dayPrice13);
	totalSpentPerMonth.add(dayPrice14);
	totalSpentPerMonth.add(dayPrice15);
	totalSpentPerMonth.add(dayPrice16);
	totalSpentPerMonth.add(dayPrice17);
	totalSpentPerMonth.add(dayPrice18);
	totalSpentPerMonth.add(dayPrice19);
	totalSpentPerMonth.add(dayPrice20);
	totalSpentPerMonth.add(dayPrice21);
	totalSpentPerMonth.add(dayPrice22);
	totalSpentPerMonth.add(dayPrice23);
	totalSpentPerMonth.add(dayPrice24);
	totalSpentPerMonth.add(dayPrice25);
	totalSpentPerMonth.add(dayPrice26);
	totalSpentPerMonth.add(dayPrice27);
	totalSpentPerMonth.add(dayPrice28);
	totalSpentPerMonth.add(dayPrice29);
	totalSpentPerMonth.add(dayPrice30);
	totalSpentPerMonth.add(dayPrice31);


    	}catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return totalSpentPerMonth;
    }
     
}
    







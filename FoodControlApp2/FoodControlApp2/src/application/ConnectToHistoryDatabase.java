package application;

import java.awt.Checkbox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public void insertToHistoryDB(String item, String date) {
	String sql = "INSERT INTO itemsHistory(item, date) VALUES(?,?)";
	
	try (Connection conn = getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	pstmt.setString(1, item);
	pstmt.setString(2, date);
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
        ArrayList<Label> itemsLabelList  = new ArrayList<Label>();
        
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	itemsStringList.add(rs.getString("item") + "          " + rs.getString("date"));
            	
            }
            System.out.println(itemsLabelList); 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return itemsStringList;
         
}
}

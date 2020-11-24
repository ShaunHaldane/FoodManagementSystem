package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConnectToWasteDatabase {
	
	
	//Connect to database
	public static Connection getConnection() {
		Connection conn=null;		
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\W10 User\\Documents\\DataBases\\WastedItemsDB");
				System.out.println("connected to db");
				return conn;
			}
			catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Inserts data into database
	public void insertToWasteDB(String item, String date, String price) {
		String sql = "INSERT INTO wastedItems(item, date, price) VALUES(?,?,?)";

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, item);
			pstmt.setString(2, date);
			pstmt.setString(3, price);
			pstmt.executeUpdate();
			System.out.println("Items inserted to waste db");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	//Returns array of this weeks waste
	public static ArrayList thisWeekWasteArray(){
		String sql = "SELECT date, price FROM wastedItems";
		ArrayList<String> expiryDatesAndPriceStrings = new ArrayList<String>();
		ArrayList<Integer> expiryDateInts = new ArrayList<Integer>();
		ArrayList<Double> priceDoubles = new ArrayList<Double>();
		ArrayList<Double> weekPrices = new ArrayList<Double>();

		try (Connection conn = getConnection();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				expiryDatesAndPriceStrings.add(rs.getString("date") + " " + rs.getString("price"));
				System.out.println("returned from this week waste array " + rs.getString("date"));
				expiryDateInts.add(Integer.parseInt(rs.getString("date").substring(0, 2)));
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


			for(int i = 0; i < expiryDateInts.size(); i++) {
				if(expiryDateInts.get(i) == 1) {
					dayPrice1 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 2) {
					dayPrice2 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 3) {
					dayPrice3 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 4) {
					dayPrice4 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 5) {
					dayPrice5 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 6) {
					dayPrice6 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 7) {
					dayPrice7 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 8) {
					dayPrice8 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 9) {
					dayPrice9 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 10) {
					dayPrice10 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 11) {
					dayPrice11 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 12) {
					dayPrice12 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 13) {
					dayPrice13 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 14) {
					dayPrice14 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 15) {
					dayPrice15 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 16) {
					dayPrice16 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 17) {
					dayPrice17 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 18) {
					dayPrice18 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 19) {
					dayPrice19 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 20) {
					dayPrice20 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 21) {
					dayPrice21 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 22) {
					dayPrice22 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 23) {
					dayPrice23 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 24) {
					dayPrice24 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 25) {
					dayPrice25 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 26) {
					dayPrice26 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 27) {
					dayPrice27 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 28) {
					dayPrice28 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 29) {
					dayPrice29 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 30) {
					dayPrice30 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 31) {
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
    
//Returns array of this months waste
	public static ArrayList<Double> thisMonthWasteArray(){
		String sql = "SELECT date, price FROM wastedItems";
		ArrayList<Integer> expiryDateInts = new ArrayList<Integer>();
		ArrayList<Double> priceDoubles = new ArrayList<Double>();
		ArrayList<Double> totalWastedThisMonth = new ArrayList<Double>();

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
		String inputDate=sdf.format(today);

		int variableToFindMonth = Integer.parseInt(inputDate.substring(3,5));
		int variableToFindYear = Integer.parseInt(inputDate.substring(6,10));

		try (Connection conn = getConnection();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				if(variableToFindMonth == Integer.parseInt(rs.getString("date").substring(3, 5)) && variableToFindYear == Integer.parseInt(rs.getString("date").substring(6, 10))) {
					expiryDateInts.add(Integer.parseInt(rs.getString("date").substring(0, 2)));
					priceDoubles.add(Double.parseDouble(rs.getString("price")));
					}
				else {
					System.out.println("date not found");
				}
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


			for(int i = 0; i < expiryDateInts.size(); i++) {
				if(expiryDateInts.get(i) == 1) {
					dayPrice1 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 2) {
					dayPrice2 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 3) {
					dayPrice3 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 4) {
					dayPrice4 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 5) {
					dayPrice5 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 6) {
					dayPrice6 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 7) {
					dayPrice7 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 8) {
					dayPrice8 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 9) {
					dayPrice9 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 10) {
					dayPrice10 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 11) {
					dayPrice11 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 12) {
					dayPrice12 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 13) {
					dayPrice13 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 14) {
					dayPrice14 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 15) {
					dayPrice15 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 16) {
					dayPrice16 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 17) {
					dayPrice17 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 18) {
					dayPrice18 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 19) {
					dayPrice19 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 20) {
					dayPrice20 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 21) {
					dayPrice21 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 22) {
					dayPrice22 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 23) {
					dayPrice23 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 24) {
					dayPrice24 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 25) {
					dayPrice25 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 26) {
					dayPrice26 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 27) {
					dayPrice27 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 28) {
					dayPrice28 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 29) {
					dayPrice29 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 30) {
					dayPrice30 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 31) {
					dayPrice31 += priceDoubles.get(i);
				}
				else {
					System.out.println("Date is out of bounds");
				}
			}


			totalWastedThisMonth.add(dayPrice1);
			totalWastedThisMonth.add(dayPrice2);
			totalWastedThisMonth.add(dayPrice3);
			totalWastedThisMonth.add(dayPrice4);
			totalWastedThisMonth.add(dayPrice5);
			totalWastedThisMonth.add(dayPrice6);
			totalWastedThisMonth.add(dayPrice7);
			totalWastedThisMonth.add(dayPrice8);
			totalWastedThisMonth.add(dayPrice9);
			totalWastedThisMonth.add(dayPrice10);
			totalWastedThisMonth.add(dayPrice11);
			totalWastedThisMonth.add(dayPrice12);
			totalWastedThisMonth.add(dayPrice13);
			totalWastedThisMonth.add(dayPrice14);
			totalWastedThisMonth.add(dayPrice15);
			totalWastedThisMonth.add(dayPrice16);
			totalWastedThisMonth.add(dayPrice17);
			totalWastedThisMonth.add(dayPrice18);
			totalWastedThisMonth.add(dayPrice19);
			totalWastedThisMonth.add(dayPrice20);
			totalWastedThisMonth.add(dayPrice21);
			totalWastedThisMonth.add(dayPrice22);
			totalWastedThisMonth.add(dayPrice23);
			totalWastedThisMonth.add(dayPrice24);
			totalWastedThisMonth.add(dayPrice25);
			totalWastedThisMonth.add(dayPrice26);
			totalWastedThisMonth.add(dayPrice27);
			totalWastedThisMonth.add(dayPrice28);
			totalWastedThisMonth.add(dayPrice29);
			totalWastedThisMonth.add(dayPrice30);
			totalWastedThisMonth.add(dayPrice31);


		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return totalWastedThisMonth;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//Returns array of this years waste
	public static ArrayList<Double> thisYearWasteArray(){
		String sql = "SELECT date, price FROM wastedItems";
		ArrayList<Integer> expiryDateInts = new ArrayList<Integer>();
		ArrayList<Double> priceDoubles = new ArrayList<Double>();
		ArrayList<Double> totalWastedThisYear = new ArrayList<Double>();

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
		String inputDate=sdf.format(today);

		int variableToFindYear = Integer.parseInt(inputDate.substring(6,10));

		try (Connection conn = getConnection();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				if(variableToFindYear == Integer.parseInt(rs.getString("date").substring(6, 10))) {
					expiryDateInts.add(Integer.parseInt(rs.getString("date").substring(3, 5)));
					priceDoubles.add(Double.parseDouble(rs.getString("price")));
					}
				else {
					System.out.println("out of bounds");
				}
			}

			double monthPrice1 = 0;
			double monthPrice2 = 0;
			double monthPrice3 = 0;
			double monthPrice4 = 0;
			double monthPrice5 = 0;
			double monthPrice6 = 0;
			double monthPrice7 = 0;
			double monthPrice8 = 0;
			double monthPrice9 = 0;
			double monthPrice10 = 0;
			double monthPrice11 = 0;
			double monthPrice12 = 0;


			for(int i = 0; i < expiryDateInts.size(); i++) {
				if(expiryDateInts.get(i) == 1) {
					monthPrice1 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 2) {
					monthPrice2 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 3) {
					monthPrice3 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 4) {
					monthPrice4 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 5) {
					monthPrice5 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 6) {
					monthPrice6 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 7) {
					monthPrice7 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 8) {
					monthPrice8 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 9) {
					monthPrice9 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 10) {
					monthPrice10 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 11) {
					monthPrice11 += priceDoubles.get(i);
				}
				else if(expiryDateInts.get(i) == 12) {
					monthPrice12 += priceDoubles.get(i);
				}
				else {
					System.out.println("Date is out of bounds");
				}
			}

			totalWastedThisYear.add(monthPrice1);
			totalWastedThisYear.add(monthPrice2);
			totalWastedThisYear.add(monthPrice3);
			totalWastedThisYear.add(monthPrice4);
			totalWastedThisYear.add(monthPrice5);
			totalWastedThisYear.add(monthPrice6);
			totalWastedThisYear.add(monthPrice7);
			totalWastedThisYear.add(monthPrice8);
			totalWastedThisYear.add(monthPrice9);
			totalWastedThisYear.add(monthPrice10);
			totalWastedThisYear.add(monthPrice11);
			totalWastedThisYear.add(monthPrice12);

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return totalWastedThisYear;
	}	

}

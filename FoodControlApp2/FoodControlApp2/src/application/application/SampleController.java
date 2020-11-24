package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SampleController implements Initializable{
	
	//Items Input window
	@FXML
	TextField itemInputField;
	@FXML
	TextField expiryDateInputField;
	@FXML
	TextField priceInputField;
	@FXML
	Label itemsAddedLabel;
	int addItemCount = 0;
	@FXML 
	Label moneySpentInWeekLabel;
	@FXML 
	Label moneySpentInMonthLabel;
	@FXML 
	Label moneySpentInYearLabel;
	@FXML 
	Label moneyWastedInWeekLabel;
	@FXML 
	Label moneyWastedInMonthLabel;
	@FXML 
	Label moneyWastedInYearLabel;
	@FXML 
	Label alertsListLabel1;
	@FXML 
	Label alertsListLabel2;
	@FXML 
	Label alertsListLabel3;
	@FXML 
	Label alertsListLabel4;
	@FXML 
	Label alertsListLabel5;
	
	@FXML
	LineChart<String, Number> budgetLineChart;
	
	//Variables for getting and formatting the date
	static Date today = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	static String inputDate=sdf.format(today);
	
	static String item;
	String expiryDate;
	static String price;
	int id;

	private static VBox root;
	
	
///////////////////////////////////////////////////////////////////////////////////////////	
	//These 2 functions switches to a new scene, it closes the original window and opens a new one.
	//use this if you want to return to the original screen after the add item button is clicked.
	//This may be the better choice for android. The user can only enter one item at a time
	
//	public void addItemsButtonClicked(ActionEvent event) throws IOException {
//		AnchorPane itemsInputSceneRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("itemsInputWindow.fxml"));
//		Scene itemsInputScene = new Scene(itemsInputSceneRoot);
//		
//		//this line gets the stage information
//		Stage itemsInputWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//		itemsInputWindow.setScene(itemsInputScene);
//		itemsInputWindow.show();
//	}
	
	
	//function to return to window after button clicked
//	public void addItemButtonClicked(ActionEvent event) throws IOException {
//	AnchorPane itemsInputSceneRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
//	Scene itemsInputScene = new Scene(itemsInputSceneRoot);
//	
//	//this line gets the stage information
//	Stage itemsInputWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//	itemsInputWindow.setScene(itemsInputScene);
//	itemsInputWindow.show();
//}
	
	//Function to open new items input window.
	public void addItemsButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader addItemsfxmlLoader = new FXMLLoader();
		addItemsfxmlLoader.setLocation(getClass().getResource("ItemsInputWindow.fxml"));
        Scene itemsInputScene = new Scene(addItemsfxmlLoader.load());
        Stage itemsInputWindow = new Stage();
        itemsInputWindow.setScene(itemsInputScene);
        itemsInputWindow.show();
	}
	
////////////////////////////////////////////////////////////////////////////////////////	
	
	//Function to add items to database from items input window.
	public void addItemButtonClicked() {
		
		//Variables to store data from the input fields.
		item = itemInputField.getText().toLowerCase();
		expiryDate = expiryDateInputField.getText();
		price = priceInputField.getText();
		
		id = ConnectToDatabase.countRows();
		
		//Create instance to verify correct inputs.
		itemsInputVerificationMethods verifyInput = new itemsInputVerificationMethods();
		
		//Logic to ensure the user fills in the input fields correctly.
		if(verifyInput.ItemsInputVerificationMethods(item, expiryDate, price) == 0) {
			addItemCount++;
			id += addItemCount;
			if(addItemCount < 2) {
				itemsAddedLabel.setText(addItemCount + " Item Added.");
				id += 1;
				System.out.println("returned from failurecode " + verifyInput.ItemsInputVerificationMethods(item, expiryDate, price));
			}else {
				itemsAddedLabel.setText(addItemCount + " Items Added.");
				id += addItemCount;
			}
		}else if(verifyInput.ItemsInputVerificationMethods(item, expiryDate, price) == 1){
			Alert alert = new Alert(AlertType.WARNING, "Fill in the item input field ", ButtonType.OK);
			alert.showAndWait();
			itemsAddedLabel.setText(" ");
		}else if(verifyInput.ItemsInputVerificationMethods(item, expiryDate, price) == 2){
			Alert alert = new Alert(AlertType.WARNING, "Put the date in the form dd/mm/yyyy in the expiry date input field ", ButtonType.OK);
			alert.showAndWait();
			itemsAddedLabel.setText(" ");
		}else if(verifyInput.ItemsInputVerificationMethods(item, expiryDate, price) == 3){
			Alert alert = new Alert(AlertType.WARNING, "Put a number in the form of 0.00 in the price field ", ButtonType.OK);
			alert.showAndWait();
			itemsAddedLabel.setText(" ");
		}
		
		//Create instances to insert data into current and history database
		ConnectToDatabase addItems = new ConnectToDatabase();
		ConnectToHistoryDatabase addItemsToHistory = new ConnectToHistoryDatabase();
		addItems.insert(id, item, expiryDate, price);
		addItemsToHistory.insertToHistoryDB(item, inputDate, price);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////
	
	//Function to open new window to allow the user to delete items.
	public void deleteItemsButtonClicked(ActionEvent event) throws Exception {
		DeleteItemsWindow openDeleteItemsWindow = new DeleteItemsWindow();
		Stage deleteItemsWindow = new Stage();
		openDeleteItemsWindow.start(deleteItemsWindow);

	}

	
	
///////////////////////////////////////////////////////////////////////////////////////////

	//Function to open common items window
	public void commonItemsButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader commonItemsfxmlLoader = new FXMLLoader();
		commonItemsfxmlLoader.setLocation(getClass().getResource("CommonItemsWindow.fxml"));
        Scene commonItemsScene = new Scene(commonItemsfxmlLoader.load());
        Stage commonItemsWindow = new Stage();
        commonItemsWindow.setScene(commonItemsScene);
        commonItemsWindow.show();
        
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML
	private void commonItemsThisWeekButtonClicked(ActionEvent event) throws Exception{
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML
	private void commonItemsThisMonthButtonClicked(ActionEvent event) throws Exception{
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	
	@FXML
	private void commonItemsThisYearButtonClicked(ActionEvent event) throws Exception{
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////

	//Function to open budget window
	public void budgetButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader budgetfxmlLoader = new FXMLLoader();
		budgetfxmlLoader.setLocation(getClass().getResource("BudgetWindow.fxml"));
        Scene budgetScene = new Scene(budgetfxmlLoader.load());
        Stage budgetWindow = new Stage();
        budgetWindow.setScene(budgetScene);
        budgetWindow.show();
        
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Function to display money spent this week on graph
	@FXML
    private void thisWeekChartButtonClicked(ActionEvent event) {
		ArrayList<Double> thisWeeksSpend = ConnectToHistoryDatabase.thisWeekSpendArray();
		int week = 0;
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        String inputDate=sdf.format(today);
          	
        int variableToFindWeek = Integer.parseInt(inputDate.substring(0,2));
              
        if(variableToFindWeek == 1 || variableToFindWeek <= 7) {
        	week = 1;
        	}
        else if(variableToFindWeek == 8 || variableToFindWeek <= 14) {
        	week = 2;
        	}
        else if(variableToFindWeek == 15 || variableToFindWeek <= 21) {
        	week = 3;
         	}
        else if(variableToFindWeek == 22 || variableToFindWeek <= 28) {
        	week = 4;
          	}
        else if(variableToFindWeek == 29 || variableToFindWeek <= 31) {
        	week = 5;
            }
		
		if(week == 1) {
			budgetLineChart.setTitle("01-" + inputDate.substring(3,10) + "  to  " + "07-" + inputDate.substring(3,10));
		}
		if(week == 2) {
			budgetLineChart.setTitle("08-" + inputDate.substring(3,10) + "  to  " + "14-" + inputDate.substring(3,10));
		}
		if(week == 3) {
			budgetLineChart.setTitle("15-" + inputDate.substring(3,10)+ "  to  " +"21-" + inputDate.substring(3,10));
		}
		if(week == 4) {
			budgetLineChart.setTitle("22-" + inputDate.substring(3,10)+ "  to  " +"28-" + inputDate.substring(3,10));
		}
		if(week == 5) {
			budgetLineChart.setTitle("29-" + inputDate.substring(3,10)+ "  to  " +"31-" + inputDate.substring(3,10));
		}
		
		
		budgetLineChart.getData().clear();
    	XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
    	budgetLineChart.setLegendVisible(false);
    	
          series.getData().add(new XYChart.Data<String, Number>("Day 1", thisWeeksSpend.get(0)));
          series.getData().add(new XYChart.Data<String, Number>("Day 2", thisWeeksSpend.get(1)));
          series.getData().add(new XYChart.Data<String, Number>("Day 3", thisWeeksSpend.get(2)));
          series.getData().add(new XYChart.Data<String, Number>("Day 4", thisWeeksSpend.get(3)));
          series.getData().add(new XYChart.Data<String, Number>("Day 5", thisWeeksSpend.get(4)));
          series.getData().add(new XYChart.Data<String, Number>("Day 6", thisWeeksSpend.get(5)));
          series.getData().add(new XYChart.Data<String, Number>("Day 7", thisWeeksSpend.get(6)));
    
        budgetLineChart.getData().add(series);
    }
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Function to display money spent this month on graph
	@FXML
    private void thisMonthChartButtonClicked(ActionEvent event) {
		ArrayList<Double> thisMonthSpend = ConnectToHistoryDatabase.thisMonthSpendArray();
		budgetLineChart.getData().clear();
    	XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
    	budgetLineChart.setLegendVisible(false);
    	
    	int month = Integer.parseInt(inputDate.substring(3,5));
    	
    	if(month == 1) {
			budgetLineChart.setTitle("January " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 2) {
			budgetLineChart.setTitle("February " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 3) {
			budgetLineChart.setTitle("March " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 4) {
			budgetLineChart.setTitle("April " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 5) {
			budgetLineChart.setTitle("May " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 5) {
			budgetLineChart.setTitle("June " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 7) {
			budgetLineChart.setTitle("July " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 8) {
			budgetLineChart.setTitle("August " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 9) {
			budgetLineChart.setTitle("Sptember " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 10) {
			budgetLineChart.setTitle("October " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 11) {
			budgetLineChart.setTitle("November " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        budgetLineChart.getData().add(series);
		}
    	
    	if(month == 12) {
			budgetLineChart.setTitle("December " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthSpend.get(0)));
	        series.getData().add(new XYChart.Data<String, Number>("2", thisMonthSpend.get(1)));
	        series.getData().add(new XYChart.Data<String, Number>("3", thisMonthSpend.get(2)));
	        series.getData().add(new XYChart.Data<String, Number>("4", thisMonthSpend.get(3)));
	        series.getData().add(new XYChart.Data<String, Number>("5", thisMonthSpend.get(4)));
	        series.getData().add(new XYChart.Data<String, Number>("6", thisMonthSpend.get(5)));
	        series.getData().add(new XYChart.Data<String, Number>("7", thisMonthSpend.get(6)));
	        series.getData().add(new XYChart.Data<String, Number>("8", thisMonthSpend.get(7)));
	        series.getData().add(new XYChart.Data<String, Number>("9", thisMonthSpend.get(8)));
	        series.getData().add(new XYChart.Data<String, Number>("10", thisMonthSpend.get(9)));
	        series.getData().add(new XYChart.Data<String, Number>("11", thisMonthSpend.get(10)));
	        series.getData().add(new XYChart.Data<String, Number>("12", thisMonthSpend.get(11)));
	        series.getData().add(new XYChart.Data<String, Number>("13", thisMonthSpend.get(12)));
	        series.getData().add(new XYChart.Data<String, Number>("14", thisMonthSpend.get(13)));
	        series.getData().add(new XYChart.Data<String, Number>("15", thisMonthSpend.get(14)));
	        series.getData().add(new XYChart.Data<String, Number>("16", thisMonthSpend.get(15)));
	        series.getData().add(new XYChart.Data<String, Number>("17", thisMonthSpend.get(16)));
	        series.getData().add(new XYChart.Data<String, Number>("18", thisMonthSpend.get(17)));
	        series.getData().add(new XYChart.Data<String, Number>("19", thisMonthSpend.get(18)));
	        series.getData().add(new XYChart.Data<String, Number>("20", thisMonthSpend.get(19)));
	        series.getData().add(new XYChart.Data<String, Number>("21", thisMonthSpend.get(20)));
	        series.getData().add(new XYChart.Data<String, Number>("22", thisMonthSpend.get(21)));
	        series.getData().add(new XYChart.Data<String, Number>("23", thisMonthSpend.get(22)));
	        series.getData().add(new XYChart.Data<String, Number>("24", thisMonthSpend.get(23)));
	        series.getData().add(new XYChart.Data<String, Number>("25", thisMonthSpend.get(24)));
	        series.getData().add(new XYChart.Data<String, Number>("26", thisMonthSpend.get(25)));
	        series.getData().add(new XYChart.Data<String, Number>("27", thisMonthSpend.get(26)));
	        series.getData().add(new XYChart.Data<String, Number>("28", thisMonthSpend.get(27)));
	        series.getData().add(new XYChart.Data<String, Number>("29", thisMonthSpend.get(28)));
	        series.getData().add(new XYChart.Data<String, Number>("30", thisMonthSpend.get(29)));
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
                     
    }
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//Function to display money spent this year on graph	
	@FXML
    private void thisYearChartButtonClicked(ActionEvent event) {
		ArrayList<Double> thisYearSpend = ConnectToHistoryDatabase.thisYearSpendArray();
		budgetLineChart.getData().clear();
    	XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
    	budgetLineChart.setLegendVisible(false);
    	
    	int year = Integer.parseInt(inputDate.substring(6,10));
    	
    	
		budgetLineChart.setTitle(inputDate.substring(6,10));
		series.getData().add(new XYChart.Data<String, Number>("Jan", thisYearSpend.get(0)));
		series.getData().add(new XYChart.Data<String, Number>("Feb", thisYearSpend.get(1)));
		series.getData().add(new XYChart.Data<String, Number>("Mar", thisYearSpend.get(2)));
		series.getData().add(new XYChart.Data<String, Number>("Apr", thisYearSpend.get(3)));
		series.getData().add(new XYChart.Data<String, Number>("May", thisYearSpend.get(4)));
		series.getData().add(new XYChart.Data<String, Number>("June", thisYearSpend.get(5)));
		series.getData().add(new XYChart.Data<String, Number>("July", thisYearSpend.get(6)));
		series.getData().add(new XYChart.Data<String, Number>("Aug", thisYearSpend.get(7)));
		series.getData().add(new XYChart.Data<String, Number>("Sept", thisYearSpend.get(8)));
		series.getData().add(new XYChart.Data<String, Number>("Oct", thisYearSpend.get(9)));
		series.getData().add(new XYChart.Data<String, Number>("Nov", thisYearSpend.get(10)));
		series.getData().add(new XYChart.Data<String, Number>("Dec", thisYearSpend.get(11)));

	        
		budgetLineChart.getData().add(series);
	               
    }
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//Function to display money wasted this week on graph
	@FXML
    private void thisWeekWastedChartButtonClicked(ActionEvent event) {
		System.out.println("wasted button");
		ArrayList<Double> thisWeeksWaste = ConnectToWasteDatabase.thisWeekWasteArray();
		int week = 0;
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        String inputDate=sdf.format(today);
          	
        int variableToFindWeek = Integer.parseInt(inputDate.substring(0,2));
              
        if(variableToFindWeek == 1 || variableToFindWeek <= 7) {
        	week = 1;
        	}
        else if(variableToFindWeek == 8 || variableToFindWeek <= 14) {
        	week = 2;
        	}
        else if(variableToFindWeek == 15 || variableToFindWeek <= 21) {
        	week = 3;
         	}
        else if(variableToFindWeek == 22 || variableToFindWeek <= 28) {
        	week = 4;
          	}
        else if(variableToFindWeek == 29 || variableToFindWeek <= 31) {
        	week = 5;
            }
		
		if(week == 1) {
			budgetLineChart.setTitle("01-" + inputDate.substring(3,10) + "  to  " + "07-" + inputDate.substring(3,10));
		}
		if(week == 2) {
			budgetLineChart.setTitle("08-" + inputDate.substring(3,10) + "  to  " + "14-" + inputDate.substring(3,10));
		}
		if(week == 3) {
			budgetLineChart.setTitle("15-" + inputDate.substring(3,10)+ "  to  " +"21-" + inputDate.substring(3,10));
		}
		if(week == 4) {
			budgetLineChart.setTitle("22-" + inputDate.substring(3,10)+ "  to  " +"28-" + inputDate.substring(3,10));
		}
		if(week == 5) {
			budgetLineChart.setTitle("29-" + inputDate.substring(3,10)+ "  to  " +"31-" + inputDate.substring(3,10));
		}
		
		
		budgetLineChart.getData().clear();
    	XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
    	budgetLineChart.setLegendVisible(false);
    	
          series.getData().add(new XYChart.Data<String, Number>("Day 1", thisWeeksWaste.get(0)));
          series.getData().add(new XYChart.Data<String, Number>("Day 2", thisWeeksWaste.get(1)));
          series.getData().add(new XYChart.Data<String, Number>("Day 3", thisWeeksWaste.get(2)));
          series.getData().add(new XYChart.Data<String, Number>("Day 4", thisWeeksWaste.get(3)));
          series.getData().add(new XYChart.Data<String, Number>("Day 5", thisWeeksWaste.get(4)));
          series.getData().add(new XYChart.Data<String, Number>("Day 6", thisWeeksWaste.get(5)));
          series.getData().add(new XYChart.Data<String, Number>("Day 7", thisWeeksWaste.get(6)));
    
        budgetLineChart.getData().add(series);
    }
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Function to display money wasted this month on graph
	@FXML
	private void thisMonthWastedChartButtonClicked(ActionEvent event) {
		ArrayList<Double> thisMonthWaste = ConnectToWasteDatabase.thisMonthWasteArray();
		budgetLineChart.getData().clear();
		XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
		budgetLineChart.setLegendVisible(false);

		int month = Integer.parseInt(inputDate.substring(3,5));

		if(month == 1) {
			budgetLineChart.setTitle("January " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 2) {
			budgetLineChart.setTitle("February " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			budgetLineChart.getData().add(series);
			}

		if(month == 3) {
			budgetLineChart.setTitle("March " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 4) {
			budgetLineChart.setTitle("April " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			budgetLineChart.getData().add(series);
		}

		if(month == 5) {
			budgetLineChart.setTitle("May " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 5) {
			budgetLineChart.setTitle("June " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			budgetLineChart.getData().add(series);
			}

		if(month == 7) {
			budgetLineChart.setTitle("July " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 8) {
			budgetLineChart.setTitle("August " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 9) {
			budgetLineChart.setTitle("Sptember " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			budgetLineChart.getData().add(series);
			}

		if(month == 10) {
			budgetLineChart.setTitle("October " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

		if(month == 11) {
			budgetLineChart.setTitle("November " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			budgetLineChart.getData().add(series);
			}

		if(month == 12) {
			budgetLineChart.setTitle("December " + inputDate.substring(6,10));
			series.getData().add(new XYChart.Data<String, Number>("1", thisMonthWaste.get(0)));
			series.getData().add(new XYChart.Data<String, Number>("2", thisMonthWaste.get(1)));
			series.getData().add(new XYChart.Data<String, Number>("3", thisMonthWaste.get(2)));
			series.getData().add(new XYChart.Data<String, Number>("4", thisMonthWaste.get(3)));
			series.getData().add(new XYChart.Data<String, Number>("5", thisMonthWaste.get(4)));
			series.getData().add(new XYChart.Data<String, Number>("6", thisMonthWaste.get(5)));
			series.getData().add(new XYChart.Data<String, Number>("7", thisMonthWaste.get(6)));
			series.getData().add(new XYChart.Data<String, Number>("8", thisMonthWaste.get(7)));
			series.getData().add(new XYChart.Data<String, Number>("9", thisMonthWaste.get(8)));
			series.getData().add(new XYChart.Data<String, Number>("10", thisMonthWaste.get(9)));
			series.getData().add(new XYChart.Data<String, Number>("11", thisMonthWaste.get(10)));
			series.getData().add(new XYChart.Data<String, Number>("12", thisMonthWaste.get(11)));
			series.getData().add(new XYChart.Data<String, Number>("13", thisMonthWaste.get(12)));
			series.getData().add(new XYChart.Data<String, Number>("14", thisMonthWaste.get(13)));
			series.getData().add(new XYChart.Data<String, Number>("15", thisMonthWaste.get(14)));
			series.getData().add(new XYChart.Data<String, Number>("16", thisMonthWaste.get(15)));
			series.getData().add(new XYChart.Data<String, Number>("17", thisMonthWaste.get(16)));
			series.getData().add(new XYChart.Data<String, Number>("18", thisMonthWaste.get(17)));
			series.getData().add(new XYChart.Data<String, Number>("19", thisMonthWaste.get(18)));
			series.getData().add(new XYChart.Data<String, Number>("20", thisMonthWaste.get(19)));
			series.getData().add(new XYChart.Data<String, Number>("21", thisMonthWaste.get(20)));
			series.getData().add(new XYChart.Data<String, Number>("22", thisMonthWaste.get(21)));
			series.getData().add(new XYChart.Data<String, Number>("23", thisMonthWaste.get(22)));
			series.getData().add(new XYChart.Data<String, Number>("24", thisMonthWaste.get(23)));
			series.getData().add(new XYChart.Data<String, Number>("25", thisMonthWaste.get(24)));
			series.getData().add(new XYChart.Data<String, Number>("26", thisMonthWaste.get(25)));
			series.getData().add(new XYChart.Data<String, Number>("27", thisMonthWaste.get(26)));
			series.getData().add(new XYChart.Data<String, Number>("28", thisMonthWaste.get(27)));
			series.getData().add(new XYChart.Data<String, Number>("29", thisMonthWaste.get(28)));
			series.getData().add(new XYChart.Data<String, Number>("30", thisMonthWaste.get(29)));
			series.getData().add(new XYChart.Data<String, Number>("31", thisMonthWaste.get(30)));
			budgetLineChart.getData().add(series);
			}

	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Function to display money wasted this year on graph	
	@FXML
	private void thisYearWastedChartButtonClicked(ActionEvent event) {
		ArrayList<Double> thisYearWaste = ConnectToWasteDatabase.thisYearWasteArray();
		budgetLineChart.getData().clear();
		XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
		budgetLineChart.setLegendVisible(false);

		int year = Integer.parseInt(inputDate.substring(6,10));

		budgetLineChart.setTitle(inputDate.substring(6,10));
		series.getData().add(new XYChart.Data<String, Number>("Jan", thisYearWaste.get(0)));
		series.getData().add(new XYChart.Data<String, Number>("Feb", thisYearWaste.get(1)));
		series.getData().add(new XYChart.Data<String, Number>("Mar", thisYearWaste.get(2)));
		series.getData().add(new XYChart.Data<String, Number>("Apr", thisYearWaste.get(3)));
		series.getData().add(new XYChart.Data<String, Number>("May", thisYearWaste.get(4)));
		series.getData().add(new XYChart.Data<String, Number>("June", thisYearWaste.get(5)));
		series.getData().add(new XYChart.Data<String, Number>("July", thisYearWaste.get(6)));
		series.getData().add(new XYChart.Data<String, Number>("Aug", thisYearWaste.get(7)));
		series.getData().add(new XYChart.Data<String, Number>("Sept", thisYearWaste.get(8)));
		series.getData().add(new XYChart.Data<String, Number>("Oct", thisYearWaste.get(9)));
		series.getData().add(new XYChart.Data<String, Number>("Nov", thisYearWaste.get(10)));
		series.getData().add(new XYChart.Data<String, Number>("Dec", thisYearWaste.get(11)));

		budgetLineChart.getData().add(series);

	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Initialize method
	//Function to display figures in the money spent part of the main screen and alerts.

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//////////////////////////Get money spent data///////////////////
		
		ArrayList<Double> thisWeekSpendArray = ConnectToHistoryDatabase.thisWeekSpendArray();
		ArrayList<Double> thisMonthSpendArray = ConnectToHistoryDatabase.thisMonthSpendArray();
		ArrayList<Double> thisYearSpendArray = ConnectToHistoryDatabase.thisYearSpendArray();
		
		double thisWeek = 0;
		for(int i = 0; i < thisWeekSpendArray.size(); i++) {
			thisWeek += thisWeekSpendArray.get(i);
		}
		
		double thisMonth = 0;
		for(int i = 0; i < thisMonthSpendArray.size(); i++) {
			thisMonth += thisMonthSpendArray.get(i);
		}
		
		double thisYear = 0;
		for(int i = 0; i < thisYearSpendArray.size(); i++) {
			thisYear += thisYearSpendArray.get(i);
		}
		System.out.println(thisMonthSpendArray);
		
		try {
			DecimalFormat df2 = new DecimalFormat("#.##");
			moneySpentInWeekLabel.setText("  € " + df2.format(thisWeek));
			moneySpentInMonthLabel.setText("  € " + df2.format(thisMonth));
			moneySpentInYearLabel.setText("  € " + df2.format(thisYear));
		}catch(Exception e) {
			System.out.println("Initialize has been triggered");
		}
		
//////////////////////////Get money wasted data///////////////////
		
		ArrayList<Double> thisWeekWastedArray = ConnectToWasteDatabase.thisWeekWasteArray();
		ArrayList<Double> thisMonthWastedArray = ConnectToWasteDatabase.thisMonthWasteArray();
		ArrayList<Double> thisYearWastedArray = ConnectToWasteDatabase.thisYearWasteArray();

		double thisWeekWaste = 0;
		for(int i = 0; i < thisWeekWastedArray.size(); i++) {
			thisWeekWaste += thisWeekWastedArray.get(i);
			System.out.println("wasted "+thisWeekWastedArray.get(i));
		}

		double thisMonthWaste = 0;
		for(int i = 0; i < thisMonthWastedArray.size(); i++) {
			thisMonthWaste += thisMonthWastedArray.get(i);
		}

		double thisYearWaste = 0;
		for(int i = 0; i < thisYearWastedArray.size(); i++) {
			thisYearWaste += thisYearWastedArray.get(i);
		}

		try {
			DecimalFormat df2 = new DecimalFormat("#.##");
			moneyWastedInWeekLabel.setText("  € " + df2.format(thisWeekWaste));
			moneyWastedInMonthLabel.setText("  € " + df2.format(thisMonthWaste));
			moneyWastedInYearLabel.setText("  € " + df2.format(thisYearWaste));
		}catch(Exception e) {
			System.out.println("Initialize has been triggered");
		}
		
		
		
		///////////////////////////////get alerts data//////////////////////////////
		
    	ArrayList<String> alertList = new ArrayList<String>();
		 alertList= ConnectToDatabase.getAlerts();

		try {
			alertsListLabel1.setText("1. " + alertList.get(0));
			alertsListLabel2.setText("2. " + alertList.get(1));
			alertsListLabel3.setText("3. " + alertList.get(2));
			alertsListLabel4.setText("4. " + alertList.get(3));
			alertsListLabel5.setText("5. " + alertList.get(4));
			
		}catch(Exception e) {
			System.out.println("");
		}
		
		
		
		
	}
	
}
	

	
	
	



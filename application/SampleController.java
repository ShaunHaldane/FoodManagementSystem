package application;

import java.io.IOException;
import java.net.URL;
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
			if(addItemCount < 2) {
				itemsAddedLabel.setText(addItemCount + " Item Added.");
				System.out.println("returned from failurecode " + verifyInput.ItemsInputVerificationMethods(item, expiryDate, price));
			}else {
				itemsAddedLabel.setText(addItemCount + " Items Added.");
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

	//Function to open items history window
	public void foodHistoryButtonClicked(ActionEvent event) throws Exception {
		ItemsHistoryWindow openItemsHistoryWindow = new ItemsHistoryWindow();
		Stage ItemsHistoryWindow = new Stage();
		openItemsHistoryWindow.start(ItemsHistoryWindow);
		

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
			budgetLineChart.setTitle("From 01-" + inputDate.substring(3,10) + "  till  " + "07-" + inputDate.substring(3,10));
		}
		if(week == 2) {
			budgetLineChart.setTitle("From 08-" + inputDate.substring(3,10) + "  till  " + "14-" + inputDate.substring(3,10));
		}
		if(week == 3) {
			budgetLineChart.setTitle("From 15-" + inputDate.substring(3,10)+ "  till  " +"21-" + inputDate.substring(3,10));
		}
		if(week == 4) {
			budgetLineChart.setTitle("From 22-" + inputDate.substring(3,10)+ "  till  " +"28-" + inputDate.substring(3,10));
		}
		if(week == 5) {
			budgetLineChart.setTitle("From: 29-" + inputDate.substring(3,10)+ "  till  " +"31-" + inputDate.substring(3,10));
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
	        series.getData().add(new XYChart.Data<String, Number>("31", thisMonthSpend.get(30)));
	        budgetLineChart.getData().add(series);
		}
                     
    }
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Initialize method
	//Function to display figures in the money spent part of the main screen and alerts after login button is clicked

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//////////////////////////Get money spent data///////////////////
		
		System.out.println("initialised");
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
		System.out.println(thisMonthSpendArray);
		
		try {
			moneySpentInWeekLabel.setText("  € " + Double.toString(thisWeek));
			moneySpentInMonthLabel.setText("  € " + Double.toString(thisMonth));
			moneySpentInYearLabel.setText("  € " + Double.toString(thisMonth));
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
	

	
	
	



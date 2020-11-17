package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class SampleController {
	
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
	
	//Variables for getting and formatting the date
	static Date today = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
	static String inputDate=sdf.format(today);
	
	static String item;
	String expiryDate;
	static String price;
	int id;
	
	
	
	
	private static VBox root;

    private static CheckBox box=new CheckBox("Apple");

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
		addItemsToHistory.insertToHistoryDB(item, inputDate);
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
	
	
	
}
	

	
	
	



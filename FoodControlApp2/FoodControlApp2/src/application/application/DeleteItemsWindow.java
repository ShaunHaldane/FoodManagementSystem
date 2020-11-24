package application;

import java.util.ArrayList;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * @author gaurav salvi
 *
 */
public class DeleteItemsWindow extends Application{

    private static VBox root;
    int itemsUsedCount = 0;
    int itemsWastedCount = 0;

    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	ArrayList<String> boxes = new ArrayList<String>();
    	boxes = ConnectToDatabase.selectAllItems();
    	
    	CheckBox box1=new CheckBox();
    	CheckBox box2=new CheckBox();
    	CheckBox box3=new CheckBox();
    	CheckBox box4=new CheckBox();
    	CheckBox box5=new CheckBox();
    	CheckBox box6=new CheckBox();
    	CheckBox box7=new CheckBox();
    	CheckBox box8=new CheckBox();
    	CheckBox box9=new CheckBox();
    	CheckBox box10=new CheckBox();
    	
    	
    	Label title = new Label();
    	title.setText("Stock Information");
    	title.setFont(Font.font ("System", FontWeight.BOLD, 18));
    	title.setTranslateY(10);
    	title.setTranslateX(10);
    	title.setUnderline(true);
    	
        root=new VBox();
        
        root.getChildren().add(title);

        if(boxes.size() == 1) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
            
        }
        else if(boxes.size() == 2) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	
        }
        else if(boxes.size() == 3) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        }
        else if(boxes.size() == 4) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        }
        else if(boxes.size() == 5) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        }
        else if(boxes.size() == 6) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        	box6.setText(boxes.get(5));
        	box6.setTranslateY(45);
            box6.setTranslateX(10);
            root.getChildren().add(box6);
        }
        else if(boxes.size() == 7) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        	box6.setText(boxes.get(5));
        	box6.setTranslateY(45);
            box6.setTranslateX(10);
            root.getChildren().add(box6);
        	box7.setText(boxes.get(6));
        	box7.setTranslateY(50);
            box7.setTranslateX(10);
            root.getChildren().add(box7);
        }
        else if(boxes.size() == 8) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        	box6.setText(boxes.get(5));
        	box6.setTranslateY(45);
            box6.setTranslateX(10);
            root.getChildren().add(box6);
        	box7.setText(boxes.get(6));
        	box7.setTranslateY(50);
            box7.setTranslateX(10);
            root.getChildren().add(box7);
        	box8.setText(boxes.get(7));
        	box8.setTranslateY(55);
            box8.setTranslateX(10);
            root.getChildren().add(box8);
        }
        else if(boxes.size() == 9) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        	box6.setText(boxes.get(5));
        	box6.setTranslateY(45);
            box6.setTranslateX(10);
            root.getChildren().add(box6);
        	box7.setText(boxes.get(6));
        	box7.setTranslateY(50);
            box7.setTranslateX(10);
            root.getChildren().add(box7);
        	box8.setText(boxes.get(7));
        	box8.setTranslateY(55);
            box8.setTranslateX(10);
            root.getChildren().add(box8);
        	box9.setText(boxes.get(8));
        	box9.setTranslateY(60);
            box9.setTranslateX(10);
            root.getChildren().add(box9);
        }
        else if(boxes.size() == 10) {
        	box1.setText(boxes.get(0));
        	box1.setTranslateY(20);
            box1.setTranslateX(10);
            root.getChildren().add(box1);
        	box2.setText(boxes.get(1));
            box2.setTranslateY(25);
            box2.setTranslateX(10);
            root.getChildren().add(box2);
        	box3.setText(boxes.get(2));
        	box3.setTranslateY(30);
            box3.setTranslateX(10);
            root.getChildren().add(box3);
        	box4.setText(boxes.get(3));
        	box4.setTranslateY(35);
            box4.setTranslateX(10);
            root.getChildren().add(box4);
        	box5.setText(boxes.get(4));
        	box5.setTranslateY(40);
            box5.setTranslateX(10);
            root.getChildren().add(box5);
        	box6.setText(boxes.get(5));
        	box6.setTranslateY(45);
            box6.setTranslateX(10);
            root.getChildren().add(box6);
        	box7.setText(boxes.get(6));
        	box7.setTranslateY(50);
            box7.setTranslateX(10);
            root.getChildren().add(box7);
        	box8.setText(boxes.get(7));
        	box8.setTranslateY(55);
            box8.setTranslateX(10);
            root.getChildren().add(box8);
        	box9.setText(boxes.get(8));
        	box9.setTranslateY(60);
            box9.setTranslateX(10);
            root.getChildren().add(box9);
        	box10.setText(boxes.get(9));
        	box10.setTranslateY(65);
            box10.setTranslateX(10);
            root.getChildren().add(box10);
        } 
        else {
        	System.out.println("you need to add more checkboxes");
        }
                
        Label itemsDeletedSuccessfullyLabel = new Label();
    	itemsDeletedSuccessfullyLabel.setText("");
    	itemsDeletedSuccessfullyLabel.setTranslateY(70);
    	itemsDeletedSuccessfullyLabel.setTranslateX(10);
        
    	Button usedItemsButton = new Button();
    	usedItemsButton.setText("I ate these!");
    	usedItemsButton.setFont(Font.font ("System", FontWeight.BOLD, 12));
    	usedItemsButton.setTranslateY(50);
    	usedItemsButton.setTranslateX(150);
    	usedItemsButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {	
            	stage.getScene().getWindow().setWidth(stage.getScene().getWidth() + 0.001);
            	ArrayList<String> findItemArray = ConnectToDatabase.returnArrayListOfAllItems();
        		ArrayList<String> findExpiryDateArray = ConnectToDatabase.returnArrayListOfAllExpiryDates();
            	if(box1.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(0), findExpiryDateArray.get(0));
            		itemsUsedCount++;
            	}
            	if(box2.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(1), findExpiryDateArray.get(1));
            		itemsUsedCount++;
            	}
            	if(box3.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(2), findExpiryDateArray.get(2));
            		itemsUsedCount++;
            	}
            	if(box4.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(3), findExpiryDateArray.get(3));
            		itemsUsedCount++;
            	}
            	if(box5.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(4), findExpiryDateArray.get(4));
            		itemsUsedCount++;
            	}
            	if(box6.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(5), findExpiryDateArray.get(5));
            		itemsUsedCount++;
            	}
            	if(box7.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(6), findExpiryDateArray.get(6));
            		itemsUsedCount++;
            	}
            	if(box8.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(7), findExpiryDateArray.get(7));
            		itemsUsedCount++;
            	}
            	if(box9.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(8), findExpiryDateArray.get(8));
            		itemsUsedCount++;
            	}
            	if(box10.isSelected()) {
            		ConnectToDatabase.delete(findItemArray.get(9), findExpiryDateArray.get(9));
            		itemsUsedCount++;
            	}
            	
            	itemsDeletedSuccessfullyLabel.setText(itemsUsedCount + " Items deleted");
//            	
            }
    	});
    	
    	Button wastedItemsButton = new Button();
    	wastedItemsButton.setText("I threw these out!");
    	wastedItemsButton.setFont(Font.font ("System", FontWeight.BOLD, 12));
    	wastedItemsButton.setTranslateY(25);
    	wastedItemsButton.setTranslateX(10);
    	wastedItemsButton.setOnAction(new EventHandler<ActionEvent>(){
    		ConnectToWasteDatabase insertToWasteDatabase = new ConnectToWasteDatabase();
            public void handle(ActionEvent e) {	
            	ArrayList<String> findItemArray = ConnectToDatabase.returnArrayListOfAllItems();
        		ArrayList<String> findExpiryDateArray = ConnectToDatabase.returnArrayListOfAllExpiryDates();
            	if(box1.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(0), findExpiryDateArray.get(0));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(0), findExpiryDateArray.get(0));
            		itemsWastedCount++;
            	}
            	if(box2.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(1), findExpiryDateArray.get(1));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(1), findExpiryDateArray.get(1));
            		itemsWastedCount++;
            	}
            	if(box3.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(2), findExpiryDateArray.get(2));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(2), findExpiryDateArray.get(2));
            		itemsWastedCount++;
            	}
            	if(box4.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(3), findExpiryDateArray.get(3));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(3), findExpiryDateArray.get(3));
            		itemsWastedCount++;
            	}
            	if(box5.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(4), findExpiryDateArray.get(4));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(4), findExpiryDateArray.get(4));
            		itemsWastedCount++;
            	}
            	if(box6.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(5), findExpiryDateArray.get(5));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(5), findExpiryDateArray.get(5));
            		itemsWastedCount++;
            	}
            	if(box7.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(6), findExpiryDateArray.get(6));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(6), findExpiryDateArray.get(6));
            		itemsWastedCount++;
            	}
            	if(box8.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(7), findExpiryDateArray.get(7));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(7), findExpiryDateArray.get(7));
            		itemsWastedCount++;
            	}
            	if(box9.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(8), findExpiryDateArray.get(8));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(8), findExpiryDateArray.get(8));
            		itemsWastedCount++;
            	}
            	if(box10.isSelected()) {
            		String[] selectARowResultForWaste = ConnectToDatabase.selectARow(findItemArray.get(9), findExpiryDateArray.get(9));
            		insertToWasteDatabase.insertToWasteDB(selectARowResultForWaste[0], selectARowResultForWaste[1], selectARowResultForWaste[2]);
            		System.out.println("items put into waste: "+selectARowResultForWaste[0] + selectARowResultForWaste[1] + selectARowResultForWaste[2]);
            		ConnectToDatabase.delete(findItemArray.get(9), findExpiryDateArray.get(9));
            		itemsWastedCount++;
            	}
            	
            	itemsDeletedSuccessfullyLabel.setText(itemsWastedCount + " Items deleted");
            	
            }
    	});
    	
    	
    	root.getChildren().add(itemsDeletedSuccessfullyLabel);
        root.getChildren().add(usedItemsButton);
        root.getChildren().add(wastedItemsButton);
        
        Scene value=new Scene(root,400,400);

        stage.setScene(value);

        stage.show();

    }
}



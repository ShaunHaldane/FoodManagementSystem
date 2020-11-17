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
    int itemsDeletedCount = 0;

    
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
    	title.setText("Which Items Would You Like To Delete?");
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
        
    	Button deleteSelectedItemsButton = new Button();
    	deleteSelectedItemsButton.setText("Delete Items");
    	deleteSelectedItemsButton.setFont(Font.font ("System", FontWeight.BOLD, 12));
    	deleteSelectedItemsButton.setTranslateY(50);
    	deleteSelectedItemsButton.setTranslateX(275);
    	deleteSelectedItemsButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {	
            	if(box1.isSelected()) {
            		ConnectToDatabase.delete(0);
            		itemsDeletedCount++;
            	}
            	if(box2.isSelected()) {
            		ConnectToDatabase.delete(1);
            		itemsDeletedCount++;
            	}
            	if(box3.isSelected()) {
            		ConnectToDatabase.delete(2);
            		itemsDeletedCount++;
            	}
            	if(box4.isSelected()) {
            		ConnectToDatabase.delete(3);
            		itemsDeletedCount++;
            	}
            	if(box5.isSelected()) {
            		ConnectToDatabase.delete(4);
            		itemsDeletedCount++;
            	}
            	if(box6.isSelected()) {
            		ConnectToDatabase.delete(5);
            		itemsDeletedCount++;
            	}
            	if(box7.isSelected()) {
            		ConnectToDatabase.delete(6);
            		itemsDeletedCount++;
            	}
            	if(box8.isSelected()) {
            		ConnectToDatabase.delete(7);
            		itemsDeletedCount++;
            	}
            	if(box9.isSelected()) {
            		ConnectToDatabase.delete(8);
            		itemsDeletedCount++;
            	}
            	if(box10.isSelected()) {
            		ConnectToDatabase.delete(9);
            		itemsDeletedCount++;
            	}
            	
            	itemsDeletedSuccessfullyLabel.setText(itemsDeletedCount + " Items deleted");
            	
            }
    	});
    	
    	root.getChildren().add(itemsDeletedSuccessfullyLabel);
        root.getChildren().add(deleteSelectedItemsButton);
        
        Scene value=new Scene(root,400,400);

        stage.setScene(value);

        stage.show();

    }
}



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
public class ItemsHistoryWindow extends Application{

    private static VBox root;
    int itemsDeletedCount = 0;

    
    @Override
    public void start(Stage stage) throws Exception {
    	
    	ArrayList<String> labels = new ArrayList<String>();
    	labels = ConnectToHistoryDatabase.selectAllItemsInHistory();
    	System.out.println(labels);
    	
    	Label label1 = new Label();
    	Label label2 = new Label();
    	Label label3 = new Label();
    	Label label4 = new Label();
    	Label label5 = new Label();
    	Label label6 = new Label();
    	Label label7 = new Label();
    	Label label8 = new Label();
    	Label label9 = new Label();
    	Label label10 = new Label();
    	Label label11 = new Label();
    	Label label12 = new Label();
    	Label label13 = new Label();
    	Label label14 = new Label();
    	Label label15 = new Label();
    	Label label16 = new Label();
    	Label label17 = new Label();
    	Label label18 = new Label();
    	Label label19 = new Label();
    	Label label20 = new Label();
    	
    	
    	Label title = new Label();
    	title.setText("Items History");
    	title.setFont(Font.font ("System", FontWeight.BOLD, 18));
    	title.setTranslateY(10);
    	title.setTranslateX(10);
    	title.setUnderline(true);
    	
    	Label itemsTitle = new Label();
    	itemsTitle.setText("Item          Date Added");
    	itemsTitle.setFont(Font.font ("System", FontWeight.BOLD, 12));
    	itemsTitle.setTranslateY(20);
    	itemsTitle.setTranslateX(10);
    	
        root=new VBox();
        
        root.getChildren().add(title);
        root.getChildren().add(itemsTitle);

        if(labels.size() == 1) {
        	label1.setText(labels.get(0));
        	label1.setTranslateY(25);
        	label1.setTranslateX(10);
            root.getChildren().add(label1);            
        }
        else if(labels.size() == 2) {
        	label1.setText(labels.get(0));
        	label1.setTranslateY(25);
        	label1.setTranslateX(10);
            root.getChildren().add(label1);
            label2.setText(labels.get(1));
            label2.setTranslateY(30);
            label2.setTranslateX(10);
            root.getChildren().add(label2);	
        }
        else if(labels.size() == 3) {
        	label1.setText(labels.get(0));
        	label1.setTranslateY(25);
        	label1.setTranslateX(10);
            root.getChildren().add(label1);
            label2.setText(labels.get(1));
            label2.setTranslateY(30);
            label2.setTranslateX(10);
            root.getChildren().add(label2);
            label3.setText(labels.get(2));
            label3.setTranslateY(35);
            label3.setTranslateX(10);
            root.getChildren().add(label3);
        }
        else if(labels.size() == 4) {
        	label1.setText(labels.get(0));
        	label1.setTranslateY(25);
        	label1.setTranslateX(10);
            root.getChildren().add(label1);
            label2.setText(labels.get(1));
            label2.setTranslateY(30);
            label2.setTranslateX(10);
            root.getChildren().add(label2);
            label3.setText(labels.get(2));
            label3.setTranslateY(35);
            label3.setTranslateX(10);
            root.getChildren().add(label3);
            label4.setText(labels.get(3));
            label4.setTranslateY(40);
            label4.setTranslateX(10);
            root.getChildren().add(label4);	
        }
        
        else {
        	System.out.println("you need to add more labels");
        }
        
        Scene value=new Scene(root,400,400);

        stage.setScene(value);

        stage.show();

    }
}

package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class itemsInputVerificationMethods {
	
	int parsedExpiryDate;
	int failureCode;
	
	public int ItemsInputVerificationMethods(String item, String expiryDate, String price) {
		System.out.println("in itemsInputVerificationMethods");
		if(item.length() <= 2) {
			failureCode = 1;
		}else {
			try {
				parsedExpiryDate =  Integer.parseInt(expiryDate.substring(0, 2) + expiryDate.substring(3, 5) + expiryDate.substring(6, 10));
				
				try {
					double priceVerify = Double.parseDouble(price);
					failureCode = 0;
				}catch(Exception e){
					failureCode = 3;
				}
			}catch(Exception e){
				failureCode = 2;
			}
		}
		
		return failureCode;
		
	}

}

package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class itemsInputVerificationMethods {
	
	int parsedExpiryDate;
	String ExpiryDateExample = "16/11/2020";
	int failureCode;
	
	public int ItemsInputVerificationMethods(String item, String expiryDate, String price) {
		System.out.println("in itemsInputVerificationMethods");
		if(item.length() <= 2) {
			failureCode = 1;
		}else {
			try {
				int parsedExpiryDate =  Integer.parseInt(expiryDate.substring(0, 2) + expiryDate.substring(3, 5) + expiryDate.substring(6, 10));
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
		
//    	int expiry = Integer.parseInt(exp);
//    	int dateTodayInt = Integer.parseInt(inputDate.substring(3, 5) + inputDate.substring(0, 2));
//    	int daysTillExpiry = expiry - dateTodayInt;
//    	daysTillExpiryArray.add(daysTillExpiry);
//    	
//    	finalList.add(rs.getString("item") + " " + Integer.toString(daysTillExpiry));
		
	}

}

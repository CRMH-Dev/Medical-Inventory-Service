package org.tallymed.ui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommonUtil {
	
	public static void showErrorPopup(Stage stage, String errorMessage, String errorHeader){
		Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(stage);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error Message");
        alert.setHeaderText(errorHeader);
        alert.setContentText(errorMessage);
        alert.showAndWait();
	}
	
	public static void showInfoPopup(Stage stage, String infoMessage, String infoHeader){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(stage);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info Message");
        alert.setHeaderText(infoHeader);
        alert.setContentText(infoMessage);
        alert.showAndWait();
	}
	
	public static void showWarningPopup(Stage stage, String message, String header){
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(stage);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning Message");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
	}
	
	public static Alert showConfirmationPopup(Stage stage, String message, String header){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(stage);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Are you confirm?");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
	}
	
}

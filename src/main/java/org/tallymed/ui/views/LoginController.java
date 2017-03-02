package org.tallymed.ui.views;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.tallymed.service.clientserv.op.LoginOperation;
import org.tallymed.service.clientserv.type.OperationType;
import org.tallymed.ui.util.CommonUtil;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	public LoginController(){
		
	}
	public static Stage loginStage;
	@FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;
	@FXML
	private void handleLogin(){
		log.info("Inside handleLogin() with Username: "+userNameField.getText());
		boolean isInputValid = isValidInput();
		if(isInputValid){
			final String uri = "http://localhost:8080/login";
			LoginOperation loginOperation = new LoginOperation();
			loginOperation.setUsername(userNameField.getText());
			loginOperation.setPassword(passwordField.getText());
			loginOperation.setOperationType(OperationType.SEARCH);
		    RestTemplate restTemplate = new RestTemplate();
		   
		    LoginOperation result = restTemplate.postForObject(uri, loginOperation, LoginOperation.class);
		    if(result != null){
		    	RootlayoutController rootlayoutController = new RootlayoutController();
		    	rootlayoutController.initRootLayout(loginStage);
		    	rootlayoutController.showHomeLayout();
		    }
		    else{
		    	CommonUtil.showErrorPopup(loginStage, "Invalid Username or Password!!", "Invalid credentials!!");
		    }
		}
	}

	private boolean isValidInput() {
		String errorMessage = "";
		if (userNameField.getText() == null || userNameField.getText().length() == 0) {
            errorMessage += "Username can't be empty!\n"; 
        }
		else if(userNameField.getText().length() > 15){
			errorMessage += "Username length should be less then 16";
		}
		if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Password can't be empty!\n";
        }
		else if(passwordField.getText().length() > 15){
			errorMessage += "Username length should be less then 16";
		}
		if (errorMessage.length() == 0) {
            return true;
        } else {
            CommonUtil.showErrorPopup(loginStage, errorMessage, errorMessage);
            return false;
        }	
	}
	@FXML
	private void handleClose(){
		Platform.exit();
		System.exit(0);
	}
}

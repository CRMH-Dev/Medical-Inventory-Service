package org.tallymed.ui.views;

import org.springframework.web.client.RestTemplate;
import org.tallymed.service.clientserv.op.DealerOperation;
import org.tallymed.service.clientserv.type.OperationType;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DealerAddController {
	@FXML
	private JFXTextField dealerName;
	
	@FXML
	private JFXTextField dealerCompany;
	
	@FXML
	private JFXTextField contactNo;
	
	@FXML
	private JFXButton cancelButton;
	
	@FXML
	private void handleDealerSave(){
		DealerOperation dealerOperation = new DealerOperation();
		dealerOperation.setContactNo(contactNo.getText());
		dealerOperation.setDealerCompany(dealerCompany.getText());
		dealerOperation.setOperationType(OperationType.SAVE);
		dealerOperation.setDelaerName(dealerName.getText());
		
		boolean isInputValid = isValidInput();
		if(isInputValid){
			final String uri = "http://localhost:8080/dealerOperation";
			RestTemplate restTemplate = new RestTemplate();
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			DealerOperation result = restTemplate.postForObject(uri, dealerOperation, DealerOperation.class);
			stage.hide();
		}
	}
	
	private boolean isValidInput() {
		return true;
	}

	@FXML
	private void handleDealerCancel(){
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}

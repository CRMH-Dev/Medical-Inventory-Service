package org.tallymed.service.clientserv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tallymed.service.clientserv.ClientService;
import org.tallymed.service.clientserv.op.DealerOperation;
import org.tallymed.service.clientserv.op.LoginOperation;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;

@RestController
public class ClientWebService implements ClientService {
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	@Override
	@RequestMapping(value="/login",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LoginOperation> loginOperation(@RequestBody LoginOperation loginOperation) {
		ResponseEntity<LoginOperation> loginOperationRes = null;
		switch (loginOperation.getOperationType()) {
		case SEARCH:
			loginOperationRes = clientServiceImpl.loginSearch(loginOperation);
			break;

		default:
			loginOperationRes = new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
			break;
		}
		return loginOperationRes;
		
	}
	public ClientServiceImpl getClientServiceImpl() {
		return this.clientServiceImpl;
	}
	public void setClientServiceImpl(ClientServiceImpl clientServiceImpl) {
		this.clientServiceImpl = clientServiceImpl;
	}
	
	@Override
	@RequestMapping(value="/productInventory",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<ProductInventoryOperation> inventoryOperation(
			@RequestBody ProductInventoryOperation inventoryOperationRequest) {
		ResponseEntity<ProductInventoryOperation> inventoryOperationResponse = null;
		switch(inventoryOperationRequest.getProductOperationType()) {
		
		case PRODUCT:
			switch(inventoryOperationRequest.getOperationType()){
			case DELETE:
				break;
			case SAVE:
				break;
			case SEARCH:
				inventoryOperationResponse = clientServiceImpl.searchProductByProductName(inventoryOperationRequest);
				break;
			case UPDATE:
				break;
			default:
				break;
			
			}
			break;
			
			
		case PRODUCT_INVENTORY:
			switch(inventoryOperationRequest.getOperationType()){
			case DELETE:
				break;
			case SAVE:
				inventoryOperationResponse = clientServiceImpl.productInventorySave(inventoryOperationRequest);
				break;
			case SEARCH:
				inventoryOperationResponse = clientServiceImpl.productInvetorySearch(inventoryOperationRequest);
				break;
			case UPDATE:
				break;
			case SEARCHALL:
				inventoryOperationResponse = clientServiceImpl.productInvetorySearchAll(inventoryOperationRequest);
				break;
			default:
				break;
			
			}
			break;
			
		default:
			break;
		}
		
		return inventoryOperationResponse;
	}
	@Override
	@RequestMapping(value="/dealerOperation",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<DealerOperation> dealerOperation(@RequestBody DealerOperation dealerOperation) {
		switch(dealerOperation.getOperationType()){
		case DELETE:
			break;
		case SAVE:
			clientServiceImpl.saveDealer(dealerOperation);
			return new ResponseEntity<DealerOperation>(HttpStatus.OK);
		case SEARCH:
			break;
		case UPDATE:
			break;
		case SEARCHALL:
			return new ResponseEntity<DealerOperation>(clientServiceImpl.getAllDealers(dealerOperation), HttpStatus.OK);
		default:
			break;
		
		}
		return null;
	}
	
}

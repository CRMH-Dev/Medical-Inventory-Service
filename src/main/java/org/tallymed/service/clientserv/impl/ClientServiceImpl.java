package org.tallymed.service.clientserv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.tallymed.service.clientserv.op.DealerOperation;
import org.tallymed.service.clientserv.op.LoginOperation;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;
import org.tallymed.service.clientserv.op.Products;
import org.tallymed.service.model.DealerInfo;
import org.tallymed.service.model.LoginInformation;
import org.tallymed.service.model.MfgCompany;
import org.tallymed.service.model.Product;
import org.tallymed.service.model.ProductInventory;
import org.tallymed.service.model.UnitOfMeasurement;
import org.tallymed.service.provider.DealerInfoService;
import org.tallymed.service.provider.LoginInformationService;
import org.tallymed.service.provider.MfgCompanyService;
import org.tallymed.service.provider.ProductInventoryService;
import org.tallymed.service.provider.ProductService;
import org.tallymed.service.provider.UOMService;

@Repository("customerServiceImpl")
public class ClientServiceImpl {
    @Autowired
	private LoginInformationService loginInformationService;
    @Autowired
    private ProductInventoryService productInventoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UOMService uomService;
    @Autowired
    MfgCompanyService mfgCompanyService;
    @Autowired
    private DealerInfoService dealerInfoService;

	
	public ResponseEntity<LoginOperation> loginOperation(LoginOperation loginOperation) {	
		return new ResponseEntity<LoginOperation>(loginOperation,HttpStatus.OK);
	}

	public ResponseEntity<LoginOperation> loginSearch(LoginOperation loginOperation) {
		LoginOperation loginOperationRes = null;
		LoginInformation loginInformation = new LoginInformation();
		loginInformation.setUsername(loginOperation.getUsername());
		loginInformation.setPassword(loginOperation.getPassword());
		List<LoginInformation> loginInformations = loginInformationService.validateLogin(loginInformation);
		if(loginInformations != null && !loginInformations.isEmpty()){
			loginInformation = loginInformations.get(0);
			loginOperationRes = new LoginOperation();
			loginOperationRes.setAdmin(loginInformation.isAdmin());
			loginOperationRes.setContactNo(loginInformation.getContactNo());
			loginOperationRes.setFname(loginInformation.getFname());
			loginOperationRes.setLname(loginInformation.getLname());
			loginOperationRes.setUsername(loginInformation.getUsername());
		}		
		if(loginOperationRes == null)
			return null;
		else
			return new ResponseEntity<LoginOperation>(loginOperationRes,HttpStatus.OK);
	}
	public ResponseEntity<ProductInventoryOperation> productInventorySave(
			ProductInventoryOperation inventoryOperationRequest) {
		ProductInventoryOperation inventoryOperationResponse;
		
		DealerInfo dealerInfo = findDealerInfo(inventoryOperationRequest.getDealerName());
		
		if(inventoryOperationRequest.getProducts() != null && !inventoryOperationRequest.getProducts().isEmpty()){
			for(Products product : inventoryOperationRequest.getProducts()){
				UnitOfMeasurement uom = findOrCreateUOM(product.getUomType(),product.getUomQuantity());
				
			}
		}
		/*Set<ProductInventory> productInventory = new HashSet<ProductInventory();
        Product product = new Product();
        
        Set<ProductInventory> productInventories = new HashSet<ProductInventory>();
        Set<Product> products = new HashSet<Product>();
        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
        MfgCompany mfgCompany = new MfgCompany();
        
        unitOfMeasurement.setUnitQuantity(inventoryOperationRequest.getUomQuantity());
        unitOfMeasurement.setUnitType(inventoryOperationRequest.getUomType());
        
        mfgCompany.setAddress(inventoryOperationRequest.getCompanyAddr());
        mfgCompany.setCompanyName(inventoryOperationRequest.getCompanyName());
        mfgCompany.setCompanyShortName(inventoryOperationRequest.getCompanyShortName());
        
        product.setProductComposition(inventoryOperationRequest.getProductComposition());
        product.setProductName(inventoryOperationRequest.getProductName());
        
        productInventory.setBatchId(inventoryOperationRequest.getBatchId());
        productInventory.setCurrentStock(inventoryOperationRequest.getCurrentStock());
        productInventory.setDateOfPurchase(inventoryOperationRequest.getDateOfPurchase());
        productInventory.setEpiryDate(inventoryOperationRequest.getExpDate());
        productInventory.setMfgDate(inventoryOperationRequest.getMfgDate());
        productInventory.setSellPrice(inventoryOperationRequest.getSellingPrice());
        productInventory.setPurchasePrice(inventoryOperationRequest.getPurchasePrice());
        
        productInventories.add(productInventory);
        product.setProductInventorys(productInventories);
        products.add(product);
        unitOfMeasurement.setProducts(products);
        mfgCompany.setProducts(products);
        mfgCompanyService.save(mfgCompany);
        uomService.save(unitOfMeasurement);
        productService.save(product);
        productInventoryService.save(productInventory);*/
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private UnitOfMeasurement findOrCreateUOM(String uomType, int i) {
		UnitOfMeasurement uom = new UnitOfMeasurement();
		uom.setUnitQuantity(i);
		uom.setUnitType(uomType);
		List<UnitOfMeasurement> uoms = uomService.findById(uom);
		if(uoms == null || uoms.isEmpty()){
			return uom;
		}
		return uoms.get(0);
	}

	private DealerInfo findDealerInfo(String dealerName) {
		DealerInfo dealerInfo = new DealerInfo();
		dealerInfo.setDealerName(dealerName);
		List<DealerInfo> dealerInfos = dealerInfoService.findDealerInfoByName(dealerInfo);
		if(dealerInfos != null && !dealerInfos.isEmpty()){
			return dealerInfos.get(0);
		}
		return null;
	}

	public void saveDealer(DealerOperation dealerOperation) {
		DealerInfo dealerInfo = new DealerInfo();
		dealerInfo.setContactNo(dealerOperation.getContactNo());
		dealerInfo.setDealerCompanyName(dealerOperation.getDealerCompany());
		dealerInfo.setDealerName(dealerOperation.getDelaerName());
		List<DealerInfo> dealerInfos = dealerInfoService.findDealerInfo(dealerInfo);
		if(dealerInfos == null || dealerInfos.isEmpty())
			dealerInfoService.save(dealerInfo);
		else
			System.out.println("Already exists!");
	}

	public DealerInfoService getDealerInfoService() {
		return dealerInfoService;
	}

	public void setDealerInfoService(DealerInfoService dealerInfoService) {
		this.dealerInfoService = dealerInfoService;
	}

	public DealerOperation getAllDealers(DealerOperation dealerOperation) {
		DealerInfo dealerInfo = new DealerInfo();
		DealerOperation dealerOperationResponse = new DealerOperation();
		dealerInfo.setContactNo(dealerOperation.getContactNo());
		dealerInfo.setDealerCompanyName(dealerOperation.getDealerCompany());
		dealerInfo.setDealerName(dealerOperation.getDelaerName());
		List<DealerInfo> dealerInfos = dealerInfoService.findAll(dealerInfo);
		List<String> dealerNames = new ArrayList<String>();
		for(DealerInfo dInfo : dealerInfos){
			dealerNames.add(dInfo.getDealerName());
		}
		dealerOperationResponse.setDealersName(dealerNames);
		return dealerOperationResponse;		
	}
	
}

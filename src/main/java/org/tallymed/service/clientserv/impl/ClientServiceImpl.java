package org.tallymed.service.clientserv.impl;

import java.util.ArrayList;
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
import org.tallymed.service.model.DealerPayment;
import org.tallymed.service.model.LoginInformation;
import org.tallymed.service.model.MfgCompany;
import org.tallymed.service.model.OrderPurchase;
import org.tallymed.service.model.OrderPurchaseProduct;
import org.tallymed.service.model.Product;
import org.tallymed.service.model.ProductInventory;
import org.tallymed.service.model.UnitOfMeasurement;
import org.tallymed.service.provider.DealerInfoService;
import org.tallymed.service.provider.LoginInformationService;
import org.tallymed.service.provider.MfgCompanyService;
import org.tallymed.service.provider.OrderPurchaseProductService;
import org.tallymed.service.provider.OrderPurchaseService;
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
	@Autowired
	private OrderPurchaseProductService orderPurchaseProductService;
	@Autowired
	private OrderPurchaseService orderPurchaseService;

	public ResponseEntity<LoginOperation> loginOperation(LoginOperation loginOperation) {
		return new ResponseEntity<LoginOperation>(loginOperation, HttpStatus.OK);
	}

	public ResponseEntity<LoginOperation> loginSearch(LoginOperation loginOperation) {
		LoginOperation loginOperationRes = null;
		LoginInformation loginInformation = new LoginInformation();
		loginInformation.setUsername(loginOperation.getUsername());
		loginInformation.setPassword(loginOperation.getPassword());
		List<LoginInformation> loginInformations = loginInformationService.validateLogin(loginInformation);
		if (loginInformations != null && !loginInformations.isEmpty()) {
			loginInformation = loginInformations.get(0);
			loginOperationRes = new LoginOperation();
			loginOperationRes.setAdmin(loginInformation.isAdmin());
			loginOperationRes.setContactNo(loginInformation.getContactNo());
			loginOperationRes.setFname(loginInformation.getFname());
			loginOperationRes.setLname(loginInformation.getLname());
			loginOperationRes.setUsername(loginInformation.getUsername());
		}
		if (loginOperationRes == null)
			return null;
		else
			return new ResponseEntity<LoginOperation>(loginOperationRes, HttpStatus.OK);
	}

	public ResponseEntity<ProductInventoryOperation> productInventorySave(
			ProductInventoryOperation inventoryOperationRequest) {
		ProductInventoryOperation inventoryOperationResponse;

		DealerInfo dealerInfo = findDealerInfo(inventoryOperationRequest.getDealerName());
		OrderPurchase orderPurchase = createOrderPurchase(dealerInfo, inventoryOperationRequest);
		DealerPayment dealerPayment = createDealerPayment(dealerInfo, orderPurchase.getOrderTotal());
		
		// save dealer payment pending
		dealerInfo.setTotalPurchase(dealerInfo.getTotalPurchase() + orderPurchase.getOrderTotal());
		Set<OrderPurchaseProduct> orderPurchaseProducts = new HashSet<OrderPurchaseProduct>();
		if (inventoryOperationRequest.getProducts() != null && !inventoryOperationRequest.getProducts().isEmpty()) {
			for (Products products : inventoryOperationRequest.getProducts()) {
				Product product = findOrCreateProduct(products);
				ProductInventory productInventory = findOrCreateProductInventory(products);
				if (productInventory.getProduct() == null) {
					productInventory.setProduct(product);
				}
				if (product.getProductId() == 0) {
					productService.save(product);
				}
				OrderPurchaseProduct orderPurchaseProduct = new OrderPurchaseProduct();
				orderPurchaseProduct.setOrderPurchase(orderPurchase);
				orderPurchaseProduct.setProductInventory(productInventory);
				orderPurchaseProducts.add(orderPurchaseProduct);
				productInventoryService.save(productInventory);
				orderPurchaseProductService.save(orderPurchaseProduct);
			}
			orderPurchase.setOrderPurchaseProducts(orderPurchaseProducts);
			orderPurchaseService.update(orderPurchase);
			dealerInfoService.update(dealerInfo);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private DealerPayment createDealerPayment(DealerInfo dealerInfo, float orderTotal) {
		DealerPayment dealerPayment = new DealerPayment();
		dealerPayment.setAmount(orderTotal);
		dealerPayment.setPayment(false);
		dealerPayment.setDealerInfo(dealerInfo);
		return dealerPayment;
	}

	private OrderPurchase createOrderPurchase(DealerInfo dealerInfo,
			ProductInventoryOperation inventoryOperationRequest) {
		OrderPurchase orderPurchase = new OrderPurchase();
		orderPurchase.setDealerId(dealerInfo);
		orderPurchase.setOrderDate(inventoryOperationRequest.getDateOfPurchase());
		orderPurchase.setInvoiceId(inventoryOperationRequest.getInvoiceID());
		Float totalPrice = 0.0f;
		if (inventoryOperationRequest.getProducts() != null) {
			for (Products products : inventoryOperationRequest.getProducts()) {
				totalPrice += (Float.valueOf(products.getCurrentStock()) * Float.valueOf(products.getPurchasePrice()));
			}
		}
		orderPurchase.setOrderTotal(totalPrice);
		orderPurchaseService.save(orderPurchase);
		return orderPurchase;
	}

	private ProductInventory findOrCreateProductInventory(Products products) {
		ProductInventory productInventory = new ProductInventory();
		productInventory.setBatchId(products.getBatchId());
		productInventory.setDateOfPurchase(null);
		productInventory.setEpiryDate(products.getExpDate());
		productInventory.setMfgDate(products.getMfgDate());
		productInventory.setPurchasePrice(products.getPurchasePrice());
		productInventory.setSellPrice(products.getSellingPrice());
		List<ProductInventory> productInventories = productInventoryService.findById(productInventory);
		if (productInventories == null || productInventories.isEmpty()) {
			productInventory.setCurrentStock(products.getCurrentStock());
			return productInventory;
		} else {
			ProductInventory productInventory2 = productInventories.get(0);
			productInventory2.setCurrentStock(productInventory2.getCurrentStock() + products.getCurrentStock());
			return productInventory2;
		}
	}

	private Product findOrCreateProduct(Products products) {
		UnitOfMeasurement uom = findOrCreateUOM(products.getUomType(), products.getUomQuantity());
		MfgCompany mfgCompany = findOrCreateMFGCompany(products.getCompanyShortName(), products.getCompanyName());
		Product productToSearch = new Product();
		productToSearch.setMfgCompany(mfgCompany);
		productToSearch.setProductComposition(products.getProductComposition());
		productToSearch.setProductName(products.getProductName());
		productToSearch.setUnitOfMeasurement(uom);
		List<Product> prodsSearched = productService.findById(productToSearch);
		if (prodsSearched == null || prodsSearched.isEmpty()) {
			return productToSearch;
		}
		return prodsSearched.get(0);
	}

	private MfgCompany findOrCreateMFGCompany(String companyShortName, String companyName) {
		MfgCompany mfgCompany = new MfgCompany();
		mfgCompany.setCompanyName(companyName);
		mfgCompany.setCompanyShortName(companyShortName);
		List<MfgCompany> mfgCompanies = mfgCompanyService.findById(mfgCompany);
		if (mfgCompanies == null || mfgCompanies.isEmpty()) {
			mfgCompanyService.save(mfgCompany);
			return mfgCompany;
		}
		return mfgCompanies.get(0);
	}

	private UnitOfMeasurement findOrCreateUOM(String uomType, int i) {
		UnitOfMeasurement uom = new UnitOfMeasurement();
		uom.setUnitQuantity(i);
		uom.setUnitType(uomType);
		List<UnitOfMeasurement> uoms = uomService.findById(uom);
		if (uoms == null || uoms.isEmpty()) {
			uomService.save(uom);
			return uom;
		}
		return uoms.get(0);
	}

	private DealerInfo findDealerInfo(String dealerName) {
		DealerInfo dealerInfo = new DealerInfo();
		dealerInfo.setDealerName(dealerName);
		List<DealerInfo> dealerInfos = dealerInfoService.findDealerInfoByName(dealerInfo);
		if (dealerInfos != null && !dealerInfos.isEmpty()) {
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
		if (dealerInfos == null || dealerInfos.isEmpty())
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
		for (DealerInfo dInfo : dealerInfos) {
			dealerNames.add(dInfo.getDealerName());
		}
		dealerOperationResponse.setDealersName(dealerNames);
		return dealerOperationResponse;
	}

	public ResponseEntity<ProductInventoryOperation> productInvetorySearch(
			ProductInventoryOperation inventoryOperationRequest) {
		if (inventoryOperationRequest != null) {
			ProductInventoryOperation pioRes = new ProductInventoryOperation();
			pioRes.setProducts(new ArrayList<Products>());
			for (Products products : inventoryOperationRequest.getProducts()) {
				String batchId = products.getBatchId();
				if (batchId != null) {
					ProductInventory productInventoryReq = new ProductInventory();
					productInventoryReq.setBatchId(batchId);
					List<ProductInventory> productInventorys = productInventoryService.findById(productInventoryReq);
					for (ProductInventory productInventory : productInventorys) {
						Products productRes = new Products();
						productRes.setProductComposition(productInventory.getProduct().getProductComposition());
						productRes.setProductName(productInventory.getProduct().getProductName());
						productRes.setBatchId(productInventory.getBatchId());
						productRes.setCompanyName(productInventory.getProduct().getMfgCompany().getCompanyName());
						productRes.setCompanyShortName(
								productInventory.getProduct().getMfgCompany().getCompanyShortName());
						productRes.setExpDate(productInventory.getEpiryDate());
						productRes
								.setUomQuantity(productInventory.getProduct().getUnitOfMeasurement().getUnitQuantity());
						productRes.setUomType(productInventory.getProduct().getUnitOfMeasurement().getUnitType());
						productRes.setPurchasePrice(productInventory.getPurchasePrice());
						productRes.setSellingPrice(productInventory.getSellPrice());
						productRes.setMfgDate(productInventory.getMfgDate());
						pioRes.getProducts().add(productRes);
						break;
					}
					break;
				}
				break;
			}
			return new ResponseEntity<ProductInventoryOperation>(pioRes, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ProductInventoryOperation> searchProductByProductName(
			ProductInventoryOperation inventoryOperationRequest) {
		ProductInventoryOperation pioRes = new ProductInventoryOperation();
		for (Products products : inventoryOperationRequest.getProducts()) {
			if (products.getProductName() != null) {
				pioRes.setProducts(new ArrayList<Products>());
				Product productReq = new Product();
				productReq.setProductName(products.getProductName());
				List<Product> productList = productService.findById(productReq);
				for (Product product : productList) {
					Products pRes = new Products();
					pRes.setProductName(product.getProductName());
					pRes.setProductComposition(product.getProductComposition());
					pRes.setCompanyName(product.getMfgCompany().getCompanyName());
					pRes.setCompanyShortName(product.getMfgCompany().getCompanyShortName());
					pRes.setUomQuantity(product.getUnitOfMeasurement().getUnitQuantity());
					pRes.setUomType(product.getUnitOfMeasurement().getUnitType());

					pioRes.getProducts().add(pRes);
				}
			}
			return new ResponseEntity<ProductInventoryOperation>(pioRes, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ProductInventoryOperation> productInvetorySearchAll(
			ProductInventoryOperation inventoryOperationRequest) {
		if (inventoryOperationRequest != null) {
			ProductInventoryOperation pioRes = new ProductInventoryOperation();
			pioRes.setProducts(new ArrayList<Products>());
			ProductInventory productInventoryReq = new ProductInventory();
			List<ProductInventory> productInventorys = productInventoryService.findAll(productInventoryReq);
			for (ProductInventory productInventory : productInventorys) {
				Products productRes = new Products();
				productRes.setProductComposition(productInventory.getProduct().getProductComposition());
				productRes.setProductName(productInventory.getProduct().getProductName());
				productRes.setBatchId(productInventory.getBatchId());
				productRes.setCompanyName(productInventory.getProduct().getMfgCompany().getCompanyName());
				productRes.setCompanyShortName(productInventory.getProduct().getMfgCompany().getCompanyShortName());
				productRes.setExpDate(productInventory.getEpiryDate());
				productRes.setUomQuantity(productInventory.getProduct().getUnitOfMeasurement().getUnitQuantity());
				productRes.setUomType(productInventory.getProduct().getUnitOfMeasurement().getUnitType());
				productRes.setPurchasePrice(productInventory.getPurchasePrice());
				productRes.setSellingPrice(productInventory.getSellPrice());
				productRes.setMfgDate(productInventory.getMfgDate());
				productRes.setCurrentStock(productInventory.getCurrentStock());
				pioRes.getProducts().add(productRes);
			}
			return new ResponseEntity<ProductInventoryOperation>(pioRes, HttpStatus.OK);
		}
		return null;
	}

}

package org.tallymed.ui.views;

import java.io.IOException;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.web.client.RestTemplate;
import org.tallymed.service.clientserv.op.DealerOperation;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;
import org.tallymed.service.clientserv.op.Products;
import org.tallymed.service.clientserv.type.OperationType;
import org.tallymed.service.clientserv.type.ProductOperationType;
import org.tallymed.ui.util.CommonUtil;
import org.tallymed.ui.views.forms.InventoryProduct;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
public class InventoryAddController implements Initializable {

	Map<String, InventoryProduct> inventoryProductMap = null;

	@FXML
	private VBox inventoryPane;

	@FXML
	private ComboBox<String> dealer;

	@FXML
	private TextField batchId;

	@FXML
	TextField productName;

	@FXML
	TextField productComposition;

	@FXML
	private TextField mfgCompanyName;

	@FXML
	private TextField mfgShortName;

	@FXML
	private ComboBox<String> unitType;

	@FXML
	private TextField mrp;

	@FXML
	private TextField purchasePrice;

	@FXML
	private TextField quantity;

	@FXML
	private DatePicker mfgDate;

	@FXML
	private DatePicker expDate;

	/*@FXML
	private JFXTextField purchaseDate;
	*/
	@FXML
	private DatePicker purchaseDate;
	
	@FXML
	private TextField unitQuantity;

	@FXML
	private TextField invoiceID;

	@FXML
	private Button addDealerInvoice;

	@FXML
	private TreeTableView<InventoryProduct> treeView;
	
	@FXML
	private Button saveAllButton;
	
	@FXML
	private Button editButton;
	
	@FXML
	private Button deleteButton;
	
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private Label dealerPayLabel;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resetAllFields();
		//scrollPane2.setVisible(false);
		priceLabel.setVisible(false);
		dealerPayLabel.setVisible(false);
		ObservableList<String> dealers = FXCollections.observableArrayList();
		dealers.add("Add New");
		DealerOperation dealerOperation = findDealers();
		for(String dealerName : dealerOperation.getDealersName()){
			dealers.add(dealerName);
		}
		dealer.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null && newValue.equalsIgnoreCase("Add New")) {
					try {
						Stage popupStage = new Stage(StageStyle.TRANSPARENT);
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(RootlayoutController.class.getResource("DealerAddPopup.fxml"));
						AnchorPane root = (AnchorPane) loader.load();
						popupStage.initOwner(dealer.getScene().getWindow());
						popupStage.initModality(Modality.WINDOW_MODAL);
						popupStage.setScene(new Scene(root, 330, 300));
						popupStage.show();
						popupStage.setOnHidden(event -> {
							DealerOperation dealerOperation = findDealers();
							for(String dealerName : dealerOperation.getDealersName()){
								if(!dealers.contains(dealerName))
									dealers.add(dealerName);
							}
							dealer.setItems(dealers);
							dealer.getSelectionModel().clearSelection();
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		dealer.setItems(dealers);

		ObservableList<String> units = FXCollections.observableArrayList();
		unitType.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)){
				unitType.setValue(unitType.getEditor().getText());
			}
		});
		
		units.add("STRIP");
		units.add("SYRUP");
		units.add("INJECTION");
		unitType.setItems(units);
		
		batchId.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)){
				inventoryPane.setDisable(true);
				ProductInventoryOperation productInventoryOperation = new ProductInventoryOperation();
				productInventoryOperation.setProducts(new ArrayList<Products>());
				Products products = new Products();
				products.setBatchId(batchId.getText());
				productInventoryOperation.getProducts().add(products);
				productInventoryOperation.setProductOperationType(ProductOperationType.PRODUCT_INVENTORY);
				productInventoryOperation.setOperationType(OperationType.SEARCH);
				String uri1 = "http://localhost:8080/productInventory";
				RestTemplate restTemplate = new RestTemplate();
				ProductInventoryOperation pioRes = restTemplate.postForObject(uri1, productInventoryOperation, ProductInventoryOperation.class);
				if(pioRes != null && pioRes.getProducts() != null && !pioRes.getProducts().isEmpty()){
					populateFieldsBasedOnBatchID(pioRes);
				}
				else{
					productName.setDisable(false);
					productName.setText(null);
				}
				inventoryPane.setDisable(false);
			}
		});
		
		productName.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)){
				inventoryPane.setDisable(true);
				ProductInventoryOperation productInventoryOperation = new ProductInventoryOperation();
				productInventoryOperation.setProducts(new ArrayList<Products>());
				Products products = new Products();
				products.setProductName(productName.getText());
				productInventoryOperation.getProducts().add(products);
				productInventoryOperation.setProductOperationType(ProductOperationType.PRODUCT);
				productInventoryOperation.setOperationType(OperationType.SEARCH);
				String uri1 = "http://localhost:8080/productInventory";
				RestTemplate restTemplate = new RestTemplate();
				ProductInventoryOperation pioRes = restTemplate.postForObject(uri1, productInventoryOperation, ProductInventoryOperation.class);
				if(pioRes != null && pioRes.getProducts() != null && !pioRes.getProducts().isEmpty()){
					populateFieldsBasedOnBatchID(pioRes);
				}
				else{
					productComposition.setDisable(false);
					productComposition.setText(null);
					mfgCompanyName.setDisable(false);
					mfgCompanyName.setText(null);
					mfgShortName.setDisable(false);
					mfgShortName.setText(null);
					unitQuantity.setDisable(false);
					unitQuantity.setText(null);
					unitType.getSelectionModel().clearSelection();
					unitType.setDisable(false);
					quantity.setDisable(false);
					quantity.setText(null);
					purchasePrice.setDisable(false);
					purchasePrice.setText(null);
					mrp.setDisable(false);
					mrp.setText(null);
					mfgDate.setDisable(false);
					mfgDate.setValue(null);
					expDate.setDisable(false);
					expDate.setValue(null);
				}
				inventoryPane.setDisable(false);
			}
		});
		
		if (inventoryProductMap == null || inventoryProductMap.isEmpty()) {
			//treeView.setVisible(false);
			saveAllButton.setVisible(false);
			editButton.setVisible(false);
			deleteButton.setVisible(false);
			priceLabel.setVisible(false);
			dealerPayLabel.setVisible(false);
			refreshTableView();
			
		} else {
			treeView.setVisible(true);
			saveAllButton.setVisible(true);
			editButton.setVisible(true);
			deleteButton.setVisible(true);
			priceLabel.setVisible(true);
			dealerPayLabel.setVisible(true);
			refreshTableView();
		}
	}

	private void populateFieldsBasedOnBatchID(ProductInventoryOperation pioRes) {
		if(pioRes.getProducts() != null && !pioRes.getProducts().isEmpty()){
			Products product = pioRes.getProducts().get(0);
			productName.setDisable(false);
			productName.setText(product.getProductName());
			productComposition.setDisable(false);
			productComposition.setText(product.getProductComposition());
			mfgCompanyName.setDisable(false);
			mfgCompanyName.setText(product.getCompanyName());
			mfgShortName.setDisable(false);
			mfgShortName.setText(product.getCompanyShortName());
			unitQuantity.setDisable(false);
			unitQuantity.setText(String.valueOf(product.getUomQuantity()));
			unitType.getSelectionModel().clearSelection();
			unitType.setValue(product.getUomType());
			unitType.getEditor().setText(product.getUomType());
			unitType.setDisable(false);
			quantity.setDisable(false);
			quantity.setText(null);
			purchasePrice.setDisable(false);
			purchasePrice.setText(String.valueOf(product.getPurchasePrice()));
			mrp.setDisable(false);
			mrp.setText(String.valueOf(product.getSellingPrice()));
			mfgDate.setDisable(false);
			if(product.getMfgDate() != null)
				mfgDate.setValue(product.getMfgDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			expDate.setDisable(false);
			if(product.getExpDate() != null)
				expDate.setValue(product.getExpDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
	} 

	private DealerOperation findDealers() {
		DealerOperation dealerOperation = new DealerOperation();
		dealerOperation.setOperationType(OperationType.SEARCHALL);
		final String uri = "http://localhost:8080/dealerOperation";
		RestTemplate restTemplate = new RestTemplate();
		DealerOperation dealerOperations = restTemplate.postForObject(uri, dealerOperation, DealerOperation.class);
		return dealerOperations;
	}

	@SuppressWarnings("unchecked")
	private void refreshTableView() {
		TreeTableColumn<InventoryProduct, String> batchName = new TreeTableColumn<>("Batch ID");
		batchName.setPrefWidth(100);
		batchName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		batchName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getBatchId();
					}
				});
		TreeTableColumn<InventoryProduct, String> productName = new TreeTableColumn<>("Product Name");
		productName.setPrefWidth(150);
		productName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		productName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getProductName();
					}
				});
		TreeTableColumn<InventoryProduct, String> companyShortName = new TreeTableColumn<>("Company Code");
		companyShortName.setPrefWidth(100);
		companyShortName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		companyShortName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getMfgShortName();
					}
				});
		TreeTableColumn<InventoryProduct, String> orderQuantityName = new TreeTableColumn<>("Ordered Quantity");
		orderQuantityName.setPrefWidth(100);
		orderQuantityName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		orderQuantityName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						int unitQ = Integer.parseInt(param.getValue().getValue().getUnitQuantity().get());
						int quntity = Integer.parseInt(param.getValue().getValue().getQuantity().get());
						int resQ = (unitQ * quntity);
						String string = "(" + unitQ + " X " + quntity + ") = "+resQ;
						return new SimpleStringProperty(string);
					}
				});
		TreeTableColumn<InventoryProduct, String> purchasePrice = new TreeTableColumn<>("Purchase Price");
		purchasePrice.setPrefWidth(100);
		purchasePrice.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		purchasePrice.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getPurchasePrice();
					}
				});
		TreeTableColumn<InventoryProduct, String> sellPrice = new TreeTableColumn<>("Sell Price");
		sellPrice.setPrefWidth(100);
		sellPrice.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		sellPrice.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getMrp();
					}
				});
		TreeTableColumn<InventoryProduct, String> mfgName = new TreeTableColumn<>("MFG Date");
		mfgName.setPrefWidth(100);
		mfgName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		mfgName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getMfgDate();
					}
				});
		TreeTableColumn<InventoryProduct, String> expName = new TreeTableColumn<>("Expiry Date");
		expName.setPrefWidth(100);
		expName.setStyle("-fx-border-color: #bbb;-fx-border-style: solid;");
		expName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getExpDate();
					}
				});
		if (inventoryProductMap != null && !inventoryProductMap.isEmpty()) {
			ObservableList<InventoryProduct> inventoryProductsObservable = FXCollections.observableArrayList();
			Set<String> batchKeys = inventoryProductMap.keySet();
			Float totalPurchasePrice = 0.0f;
			Float totalSellPrice = 0.0f;
			for(String key : batchKeys){
				InventoryProduct inventoryProduct = inventoryProductMap.get(key);
				totalPurchasePrice += (Float.valueOf(inventoryProduct.getQuantity().get()) * Float.valueOf(inventoryProduct.getPurchasePrice().get()));
				totalSellPrice += (Float.valueOf(inventoryProduct.getQuantity().get()) * Float.valueOf(inventoryProduct.getMrp().get()));
				inventoryProductsObservable.add(inventoryProduct);
			}
			priceLabel.setText(totalPurchasePrice.toString());
			final TreeItem<InventoryProduct> root = new RecursiveTreeItem<InventoryProduct>(inventoryProductsObservable,
					RecursiveTreeObject::getChildren);
			treeView.getColumns().setAll(batchName, productName, companyShortName, orderQuantityName, purchasePrice, sellPrice, mfgName, expName);
			treeView.setRoot(root);
			treeView.setShowRoot(false);
		}
	}

	@FXML
	private void handleDealerComboClick() {
		String message = validateDealerInvoice();
		if (message == "") {
			addDealerInvoice.setDisable(true);
			dealer.setDisable(true);
			invoiceID.setDisable(true);
			purchaseDate.setDisable(true);
			addDealerInvoice.setStyle("-fx-background-color:#777777;");
			inventoryProductMap = new HashMap<String, InventoryProduct>();
			batchId.setDisable(false);
		} else {
			CommonUtil.showErrorPopup(null, message, "Please enter the Mandatory fields");
		}
	}

	@FXML
	private void handleAdd() {
		
		if (inventoryProductMap != null) {
			LoadingController loadingController = LoadingController.getInstance(dealer.getScene().getWindow());
			loadingController.show();
			if (validProduct()) {
				String unitType1 = unitType.getValue();
				if(unitType1 == null){
					unitType1 = unitType.getEditor().getText();
				}
				InventoryProduct inventoryProduct = new InventoryProduct(dealer.getValue(), batchId.getText(),
						productName.getText(), productComposition.getText(), mfgCompanyName.getText(),
						mfgShortName.getText(), unitType1, mrp.getText(), purchasePrice.getText(),
						quantity.getText(), mfgDate.getValue().toString(), expDate.getValue().toString(), unitQuantity.getText());
				inventoryProductMap.put(batchId.getText(), inventoryProduct);
				treeView.setVisible(true);
				saveAllButton.setVisible(true);
				editButton.setVisible(true);
				deleteButton.setVisible(true);
				priceLabel.setVisible(true);
				dealerPayLabel.setVisible(true);
				refreshTableView();
			}
			loadingController.hide();
		} else {
			CommonUtil.showErrorPopup(null, "Please provide dealer invoice details!!", "An error has been occured!");
		}
	}
	
	@FXML
	private void handleDelete(){
		if(treeView != null && treeView.getSelectionModel() != null){
			TreeTableViewSelectionModel<InventoryProduct> treeTableViewSelectionModel = treeView.getSelectionModel();
			if(!treeTableViewSelectionModel.getSelectedCells().isEmpty()){
				StringProperty batchProperty = treeTableViewSelectionModel.getSelectedCells().get(0).getTreeItem().getValue().getBatchId();
				String batchKey = batchProperty.getValue();
				if(inventoryProductMap != null && !inventoryProductMap.isEmpty()){
					inventoryProductMap.remove(batchKey);
				}
				if(inventoryProductMap != null && !inventoryProductMap.isEmpty()){
					refreshTableView();
				}
				else{
					refreshTableView();
					treeView.setVisible(false);
					saveAllButton.setVisible(false);
					editButton.setVisible(false);
					deleteButton.setVisible(false);
					priceLabel.setVisible(false);
					dealerPayLabel.setVisible(false);
				}
			}
			else{
				CommonUtil.showWarningPopup(null, "Please select a row to delete!!", "Warning!!!");
			}
		}
	}
	
	@FXML
	private void handleEdit(){
		
	}

	@FXML
	private void handleSaveAll(){
		LoadingController loadingController = LoadingController.getInstance(dealer.getScene().getWindow());
		loadingController.show();
		ProductInventoryOperation productInventoryOperation = new ProductInventoryOperation();
		Set<String> inventoryKeySet = inventoryProductMap.keySet();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			productInventoryOperation.setDateOfPurchase(formatter.parse(purchaseDate.getValue().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		productInventoryOperation.setDealerName(dealer.getValue());
		productInventoryOperation.setInvoiceID(invoiceID.getText());
		productInventoryOperation.setOperationType(OperationType.SAVE);
		productInventoryOperation.setProducts(new ArrayList<Products>());
		productInventoryOperation.setProductOperationType(ProductOperationType.PRODUCT_INVENTORY);
		for(String inventoryKey : inventoryKeySet){
			InventoryProduct inventoryProduct = inventoryProductMap.get(inventoryKey);
			Products product = new Products();
			product.setBatchId(inventoryProduct.getBatchId().get());
			product.setCompanyName(inventoryProduct.getMfgCompanyName().get());
			product.setCompanyShortName(inventoryProduct.getMfgShortName().get());
			product.setCurrentStock(Integer.parseInt(inventoryProduct.getQuantity().get()));
			try {
				product.setExpDate(formatter.parse(inventoryProduct.getExpDate().get()));
				product.setMfgDate(formatter.parse(inventoryProduct.getMfgDate().get()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			product.setProductComposition(inventoryProduct.getProductComposition().get());
			product.setProductName(inventoryProduct.getProductName().get());
			product.setPurchasePrice(Float.valueOf(inventoryProduct.getPurchasePrice().get()));
			product.setSellingPrice(Float.valueOf(inventoryProduct.getMrp().get()));
			product.setUomQuantity(Integer.valueOf(inventoryProduct.getUnitQuantity().get()));
			product.setUomType(inventoryProduct.getUnitType().get());
			productInventoryOperation.getProducts().add(product);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String uri1 = "http://localhost:8080/productInventory";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(uri1, productInventoryOperation, ProductInventoryOperation.class);
		inventoryProductMap.clear();
		inventoryProductMap = null;
		loadingController.hide();
		CommonUtil.showInfoPopup(null, "All Data saved successfully", "success");
		refreshTableView();
	}
	
	private boolean validProduct() {
		String message = "";
		if(batchId  == null || batchId.getText() == null){
			message += "Batch Id is Mandatory!!\n";
		}
		if(productName == null || productName.getText() == null){
			message += "Product Name is Mandatory!!\n";
		}
		if(mfgShortName == null || mfgShortName.getText() == null){
			message += "MFG Company short name is Mandatory!!\n";
		}
		if(unitType == null || unitType.getValue() == null || unitType.getEditor().getText() == null){
			message += "Unit Type is Mandatory!!\n";
		}
		if(unitQuantity == null || unitQuantity.getText() == null){
			message += "Unit quantity is Mandatory!!\n";
		}
		if(quantity == null || quantity.getText() == null){
			message += "Stock quantity is Mandatory!!\n";
		}
		if(mfgDate == null || mfgDate.getValue() == null){
			message += "MFG Date is Mandatory!!\n";
		}
		if(expDate == null || expDate.getValue() == null){
			message += "EXP Date is Mandatory!!\n";
		}
		if(message.equals("")){
			return true;
		}
		else{
			CommonUtil.showErrorPopup(null, message, "Mandatory data missing");
			return false;
		}
	}

	private String validateDealerInvoice() {
		String message = "";
		if (dealer == null || dealer.getValue() == null) {
			message += "Dealer selection in Mandatory!!\n";
		}
		if (invoiceID == null || invoiceID.getText() == null || invoiceID.getText().equals("")) {
			message += "Dealer Invoice is Mandatory!!\n";
		}
		if (purchaseDate == null || purchaseDate.getValue() == null || purchaseDate.getValue().equals("")) {
			message += "Purchase date is Mandatory!!\n";
		}
		return message;
	}
	
	@FXML
	private void resetButtonAction(){
		resetAllFields();
	}
	
	private void resetAllFields(){
		dealer.getSelectionModel().clearSelection();
		dealer.setDisable(false);
		invoiceID.setDisable(false);
		invoiceID.setText(null);
		purchaseDate.setDisable(false);
		purchaseDate.setValue(null);
		addDealerInvoice.setDisable(false);
		batchId.setDisable(true);
		batchId.setText(null);
		productName.setDisable(true);
		productName.setText(null);
		productComposition.setDisable(true);
		productComposition.setText(null);
		mfgCompanyName.setDisable(true);
		mfgCompanyName.setText(null);
		mfgShortName.setDisable(true);
		mfgShortName.setText(null);
		unitQuantity.setDisable(true);
		unitQuantity.setText(null);
		unitType.getSelectionModel().clearSelection();
		unitType.setDisable(true);
		quantity.setDisable(true);
		quantity.setText(null);
		purchasePrice.setDisable(true);
		purchasePrice.setText(null);
		mrp.setDisable(true);
		mrp.setText(null);
		mfgDate.setDisable(true);
		mfgDate.setValue(null);
		expDate.setDisable(true);
		expDate.setValue(null);
	}
	
}

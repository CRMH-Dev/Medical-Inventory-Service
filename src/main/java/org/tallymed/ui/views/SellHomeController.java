package org.tallymed.ui.views;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;
import org.tallymed.service.clientserv.op.Products;
import org.tallymed.service.clientserv.type.OperationType;
import org.tallymed.service.clientserv.type.ProductOperationType;
import org.tallymed.ui.util.CommonUtil;
import org.tallymed.ui.views.forms.InventoryProduct;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class SellHomeController implements Initializable {

	Map<String, InventoryProduct> inventoryProductMap = null;
	
	Map<String, InventoryProduct> inventoryProductMapForBill = null;

	@SuppressWarnings("restriction")
	@FXML
	private FlowPane productFlowPane;
	
	@FXML
	private VBox sellVBox;

	@SuppressWarnings("restriction")
	@FXML
	private TreeTableView<InventoryProduct> treeView;
	
	@FXML
	private TreeTableView<InventoryProduct> treeViewBill;
	
	@FXML
	private TextField searchField;

	@SuppressWarnings({ "unchecked", "restriction" })
	private void refreshTableView(Map<String, InventoryProduct> inventoryProductMapForTreeTable) {
		TreeTableColumn<InventoryProduct, String> batchName = new TreeTableColumn<>("Batch ID");
		batchName.setPrefWidth(100);
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
		companyShortName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getMfgShortName();
					}
				});
		TreeTableColumn<InventoryProduct, String> orderQuantityName = new TreeTableColumn<>("Stock Available (Pcs)");
		orderQuantityName.setPrefWidth(120);
		orderQuantityName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						//int unitQ = Integer.parseInt(param.getValue().getValue().getUnitQuantity().get());
						int quntity = Integer.parseInt(param.getValue().getValue().getQuantity().get());
						String string = String.valueOf(quntity);
						return new SimpleStringProperty(string);
					}
				});
		TreeTableColumn<InventoryProduct, String> sellPrice = new TreeTableColumn<>("Price per Piece");
		sellPrice.setPrefWidth(100);
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
		expName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getExpDate();
					}
				});
		if (inventoryProductMapForTreeTable != null && !inventoryProductMapForTreeTable.isEmpty()) {
			ObservableList<InventoryProduct> inventoryProductsObservable = FXCollections.observableArrayList();
			Set<String> batchKeys = inventoryProductMapForTreeTable.keySet();
			Float totalSellPrice = 0.0f;
			for (String key : batchKeys) {
				InventoryProduct inventoryProduct = inventoryProductMapForTreeTable.get(key);
				totalSellPrice += (Float.valueOf(inventoryProduct.getQuantity().get())
						* Float.valueOf(inventoryProduct.getMrp().get()));
				inventoryProductsObservable.add(inventoryProduct);
			}
			final TreeItem<InventoryProduct> root = new RecursiveTreeItem<InventoryProduct>(inventoryProductsObservable,
					RecursiveTreeObject::getChildren);
			treeView.getColumns().setAll(batchName, productName, companyShortName, orderQuantityName,
					sellPrice, mfgName, expName);
			treeView.setRoot(root);
			treeView.setShowRoot(false);
		}
		else{
			treeView.setRoot(null);
		}
	}

	@SuppressWarnings("restriction")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateTreeView();
		refreshTableView(inventoryProductMap);
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<InventoryProduct>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<InventoryProduct>> observable,
					TreeItem<InventoryProduct> oldProduct, TreeItem<InventoryProduct> newProduct) {
				if(newProduct != null && newProduct.getValue() != null){
					if(inventoryProductMapForBill == null){
						inventoryProductMapForBill = new LinkedHashMap<String, InventoryProduct>();
					}
					InventoryProduct inventoryProductForBill = newProduct.getValue();
					inventoryProductForBill.setQuantity(new SimpleStringProperty(String.valueOf(0)));
					inventoryProductMapForBill.put(inventoryProductForBill.getBatchId().getValue(), inventoryProductForBill);
					refreshTableViewForBill(inventoryProductMapForBill);
				}
			}
		});
	}

	private void populateTreeView() {
		if (inventoryProductMap == null) {
			inventoryProductMap = new TreeMap<String, InventoryProduct>();
		}
		ProductInventoryOperation productInventoryOperation = new ProductInventoryOperation();
		productInventoryOperation.setProductOperationType(ProductOperationType.PRODUCT_INVENTORY);
		productInventoryOperation.setOperationType(OperationType.SEARCHALL);
		String uri1 = "http://localhost:9080/productInventory";
		RestTemplate restTemplate = new RestTemplate();
		ProductInventoryOperation pioRes = restTemplate.postForObject(uri1, productInventoryOperation,
				ProductInventoryOperation.class);
		if (pioRes != null && pioRes.getProducts() != null && !pioRes.getProducts().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			for (Products products : pioRes.getProducts()) {
				String expDate = sdf.format(products.getExpDate());
				String mfgDate = sdf.format(products.getMfgDate());
				InventoryProduct inventoryProduct = new InventoryProduct(null, products.getBatchId(),
						products.getProductName(), products.getProductComposition(), products.getCompanyName(),
						products.getCompanyShortName(), products.getUomType(),
						String.valueOf(products.getSellingPrice()), String.valueOf(products.getPurchasePrice()),
						String.valueOf(products.getCurrentStock()), mfgDate,
						expDate, String.valueOf(products.getUomQuantity()));
				inventoryProductMap.put(products.getBatchId(), inventoryProduct);
			}
		}
	}
	@FXML
	public void filterTableData(){
		Map<String, InventoryProduct> inventoryProductMapTemp;
		if(searchField.getText() != null || searchField.getText().equalsIgnoreCase("")){
			inventoryProductMapTemp = inventoryProductMap
										.entrySet()
										.parallelStream()
										.filter(i -> i.getValue().getBatchId().getValue().contains(searchField.getText())
													|| i.getValue().getBatchId().getValue().contains(searchField.getText().toUpperCase())
													|| i.getValue().getProductName().getValue().contains(searchField.getText())
													|| i.getValue().getProductName().getValue().contains(searchField.getText().toUpperCase())
													|| i.getValue().getProductComposition().getValue().contains(searchField.getText())
													|| i.getValue().getProductComposition().getValue().contains(searchField.getText().toUpperCase())
												)
										.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		}
		else{
			inventoryProductMapTemp = inventoryProductMap;
		}
		refreshTableView(inventoryProductMapTemp);
	}
	
	@FXML
	public void handleGenerateBill(){
		sellVBox.setDisable(true);
		if(inventoryProductMapForBill == null || inventoryProductMapForBill.isEmpty()){
			CommonUtil.showErrorPopup(null, "Please add medicines before proceed to generate bill!!", "Error occured");
		}
		else{
			String errorMessage = validateInventoryProductForBill(inventoryProductMapForBill);
			if(StringUtils.isEmpty(errorMessage)){
				
			}
			else{
				CommonUtil.showErrorPopup(null, errorMessage, "Validation Failure");
			}
		}
		sellVBox.setDisable(false);
	}
	
	private String validateInventoryProductForBill(Map<String, InventoryProduct> inventoryProductMapForBill2) {
		String errorMessage = "";
		for(Entry<String, InventoryProduct> entry : inventoryProductMapForBill2.entrySet()){
			if(entry.getValue().getQuantity().getValue().equalsIgnoreCase("0")){
				errorMessage = errorMessage.concat("Quantity can't be 0, Please add a valid quantity before proceed!!\n"
						+ "Double click on quantity column for each row for updating it's value!!");
				break;
			}
		}		
		return errorMessage;
	}

	@FXML
	public void handleCancelCurrentBill(){
		inventoryProductMapForBill = null;
		refreshTableViewForBill(inventoryProductMapForBill);
	}

	@SuppressWarnings({ "restriction", "unchecked" })
	private void refreshTableViewForBill(Map<String, InventoryProduct> inventoryProductMapForTreeTable) {
		TreeTableColumn<InventoryProduct, String> batchName = new TreeTableColumn<>("Batch ID");
		batchName.setPrefWidth(100);
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
		companyShortName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return param.getValue().getValue().getMfgShortName();
					}
				});
		TreeTableColumn<InventoryProduct, String> orderQuantityName = new TreeTableColumn<>("Quantity");
		orderQuantityName.setPrefWidth(100);
		orderQuantityName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						return new SimpleStringProperty(param.getValue().getValue().getQuantity().getValue().toString());
					}
				});
		orderQuantityName.setEditable(true);
		
		orderQuantityName.setCellFactory(new Callback<TreeTableColumn<InventoryProduct,String>, TreeTableCell<InventoryProduct,String>>() {
			@Override
			public TreeTableCell<InventoryProduct, String> call(TreeTableColumn<InventoryProduct, String> param) {
				TreeTableCell<InventoryProduct, String> treeTableCell = new TextFieldTreeTableCell<InventoryProduct, String>(new StringConverter() {

					@Override
					public Object fromString(String string) {
						return string;
					}

					@Override
					public String toString(Object object) {
						if(object != null)
							return object.toString();
						return null;
					}
				});
				
				return treeTableCell;
			}
		});
		orderQuantityName.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<InventoryProduct,String>>() {
			@Override
			public void handle(CellEditEvent<InventoryProduct, String> event) {
				
				if(!StringUtils.isEmpty(event.getNewValue())){
					orderQuantityName.setUserData(event.getNewValue());
					InventoryProduct value = event.getRowValue().getValue();
					value.setQuantity(new SimpleStringProperty(event.getNewValue()));
					inventoryProductMapForBill.put(value.getBatchId().getValue(), value);
					refreshTableViewForBill(inventoryProductMapForBill);
				}
				else {
					CommonUtil.showWarningPopup(null, event.getNewValue(), "Enter a valid quantity for billing...");
				}
			}
		});
		
		TreeTableColumn<InventoryProduct, String> sellPrice = new TreeTableColumn<>("Price per piece");
		sellPrice.setPrefWidth(120);
		sellPrice.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						Double priceMrp = Double.parseDouble(param.getValue().getValue().getMrp().getValue());
						Double unitQuantity = Double.parseDouble(param.getValue().getValue().getUnitQuantity().getValue());
						DecimalFormat df = new DecimalFormat("#.00"); 
						Double pricePerPc = priceMrp/unitQuantity;
						return new SimpleStringProperty(df.format(pricePerPc));
					}
				});
		TreeTableColumn<InventoryProduct, String> currentTotal = new TreeTableColumn<>("Current Total");
		currentTotal.setPrefWidth(100);
		currentTotal.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						Double priceMrp = Double.parseDouble(param.getValue().getValue().getMrp().getValue());
						Double unitQuantity = Double.parseDouble(param.getValue().getValue().getUnitQuantity().getValue());
						Double pricePerPc = priceMrp/unitQuantity;
						Double quantity = Double.parseDouble(param.getValue().getValue().getQuantity().getValue());
						Double currentTotal = pricePerPc * quantity;
						DecimalFormat df = new DecimalFormat("#.00"); 
						return new SimpleStringProperty(df.format(currentTotal));
					}
				});
		if(inventoryProductMapForBill != null && !inventoryProductMapForBill.isEmpty()){
			ObservableList<InventoryProduct> inventoryProductsObservable = FXCollections.observableArrayList();
			Set<String> batchKeys = inventoryProductMapForTreeTable.keySet();
			Float totalSellPrice = 0.0f;
			for (String key : batchKeys) {
				InventoryProduct inventoryProduct = inventoryProductMapForTreeTable.get(key);
				totalSellPrice += (Float.valueOf(inventoryProduct.getQuantity().get())
						* Float.valueOf(inventoryProduct.getMrp().get()));
				inventoryProductsObservable.add(inventoryProduct);
			}
			final TreeItem<InventoryProduct> root = new RecursiveTreeItem<InventoryProduct>(inventoryProductsObservable,
					RecursiveTreeObject::getChildren);
			treeViewBill.getColumns().setAll(batchName, productName, sellPrice, orderQuantityName, currentTotal);
			treeViewBill.setRoot(root);
			treeViewBill.setEditable(true);
			treeViewBill.setShowRoot(false);
		}
		else{
			treeViewBill.setRoot(null);
		}
	}
}
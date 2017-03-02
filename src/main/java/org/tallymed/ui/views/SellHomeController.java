package org.tallymed.ui.views;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.web.client.RestTemplate;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;
import org.tallymed.service.clientserv.op.Products;
import org.tallymed.service.clientserv.type.OperationType;
import org.tallymed.service.clientserv.type.ProductOperationType;
import org.tallymed.ui.views.forms.InventoryProduct;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class SellHomeController implements Initializable {

	Map<String, InventoryProduct> inventoryProductMap = null;

	@FXML
	private FlowPane productFlowPane;

	@FXML
	private TreeTableView<InventoryProduct> treeView;

	@SuppressWarnings("unchecked")
	private void refreshTableView() {
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
		TreeTableColumn<InventoryProduct, String> orderQuantityName = new TreeTableColumn<>("Ordered Quantity");
		orderQuantityName.setPrefWidth(100);
		orderQuantityName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<InventoryProduct, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<InventoryProduct, String> param) {
						int unitQ = Integer.parseInt(param.getValue().getValue().getUnitQuantity().get());
						int quntity = Integer.parseInt(param.getValue().getValue().getQuantity().get());
						int resQ = (unitQ * quntity);
						String string = "(" + unitQ + " X " + quntity + ") = " + resQ;
						return new SimpleStringProperty(string);
					}
				});
		TreeTableColumn<InventoryProduct, String> purchasePrice = new TreeTableColumn<>("Purchase Price");
		purchasePrice.setPrefWidth(100);
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
		if (inventoryProductMap != null && !inventoryProductMap.isEmpty()) {
			ObservableList<InventoryProduct> inventoryProductsObservable = FXCollections.observableArrayList();
			Set<String> batchKeys = inventoryProductMap.keySet();
			Float totalPurchasePrice = 0.0f;
			Float totalSellPrice = 0.0f;
			for (String key : batchKeys) {
				InventoryProduct inventoryProduct = inventoryProductMap.get(key);
				totalPurchasePrice += (Float.valueOf(inventoryProduct.getQuantity().get())
						* Float.valueOf(inventoryProduct.getPurchasePrice().get()));
				totalSellPrice += (Float.valueOf(inventoryProduct.getQuantity().get())
						* Float.valueOf(inventoryProduct.getMrp().get()));
				inventoryProductsObservable.add(inventoryProduct);
			}
			final TreeItem<InventoryProduct> root = new RecursiveTreeItem<InventoryProduct>(inventoryProductsObservable,
					RecursiveTreeObject::getChildren);
			treeView.getColumns().setAll(batchName, productName, companyShortName, orderQuantityName, purchasePrice,
					sellPrice, mfgName, expName);
			treeView.setRoot(root);
			treeView.setShowRoot(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateTreeView();
		refreshTableView();
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<InventoryProduct>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<InventoryProduct>> observable,
					TreeItem<InventoryProduct> oldProduct, TreeItem<InventoryProduct> newProduct) {
				System.out.println(newProduct.getValue().getProductName());
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
		String uri1 = "http://localhost:8080/productInventory";
		RestTemplate restTemplate = new RestTemplate();
		ProductInventoryOperation pioRes = restTemplate.postForObject(uri1, productInventoryOperation,
				ProductInventoryOperation.class);
		if (pioRes != null && pioRes.getProducts() != null && !pioRes.getProducts().isEmpty()) {
			for (Products products : pioRes.getProducts()) {
				InventoryProduct inventoryProduct = new InventoryProduct(null, products.getBatchId(),
						products.getProductName(), products.getProductComposition(), products.getCompanyName(),
						products.getCompanyShortName(), products.getUomType(),
						String.valueOf(products.getSellingPrice()), String.valueOf(products.getPurchasePrice()),
						String.valueOf(products.getCurrentStock()), products.getMfgDate().toString(),
						products.getExpDate().toString(), String.valueOf(products.getUomQuantity()));
				inventoryProductMap.put(products.getBatchId(), inventoryProduct);
			}
		}
	}
}
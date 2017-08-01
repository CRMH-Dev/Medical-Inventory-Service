package org.tallymed.ui.views;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;

public class RootlayoutController {
	private static BorderPane rootLayout;

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout(Stage loginStage) {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Image img = new Image("file:resources/img/logo.png");
			Stage mainStage = new Stage();
			mainStage.setScene(scene);
			mainStage.initStyle(StageStyle.DECORATED);
			mainStage.setTitle("..: tallyMED :: Home :..");
			mainStage.getIcons().add(img);
			mainStage.setMaximized(true);
			loginStage.close();
			mainStage.setOnHidden(event -> Platform.exit());
			mainStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHomeLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Home.fxml"));
			ScrollPane homeInitiate = (ScrollPane) loader.load();
			rootLayout.setCenter(homeInitiate);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleClose() {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	private void handleInventoryManageMenu() {

	}

	@FXML
	private void handleAddInventory() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/InventoryAdd.fxml"));
			VBox homeInitiate = (VBox) loader.load();
			rootLayout.setCenter(homeInitiate);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleSellHome() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/SellHome.fxml"));
			VBox homeInitiate = (VBox) loader.load();
			rootLayout.setCenter(homeInitiate);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

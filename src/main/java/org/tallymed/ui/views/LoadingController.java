package org.tallymed.ui.views;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class LoadingController {
	private static LoadingController loadingController = null;
	Stage popupStage = null;

	private LoadingController() {

	}

	public static LoadingController getInstance(Window window) {
		if (loadingController == null) {
			loadingController = new LoadingController(window);
		}
		return loadingController;
	}

	private LoadingController(Window window) {
		try {
			popupStage = new Stage(StageStyle.TRANSPARENT);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Loading.fxml"));
			AnchorPane root;

			root = (AnchorPane) loader.load();
			popupStage.initOwner(window);
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.setScene(new Scene(root, 40, 40));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void show() {
		if (popupStage != null)
			popupStage.show();
	}

	public void hide() {
		if (popupStage != null)
			popupStage.hide();
	}

	public void close() {
		if (popupStage != null)
			popupStage.close();
	}
}

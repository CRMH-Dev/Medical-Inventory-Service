package org.tallymed.ui;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.tallymed.ui.views.LoginController;

import com.jfoenix.controls.JFXProgressBar;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath*:applicationContext.xml")
public class RestApplication extends Application{
    /*public static void main(String[] args) {
       // SpringApplication.run(RestApplication.class, args);
    	startRestAPI(args);
    }*/
    public static void startRestAPI(String[] args) {
    	SpringApplication.run(RestApplication.class, args);
	}
	
	private static BorderPane rootLayout;
    public static final String SPLASH_IMAGE = "file:resources/img/SplashLogo.png";
    private static Stage loginStage;
    private Pane splashLayout;
    private JFXProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 450;
    private static final int SPLASH_HEIGHT = 150;
    private static String[] arguments;
    private Stage stage;
        /**
     * Returns the main stage.
     * @return
     */
    public static void main(String[] args) {
    	arguments = args;
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	//log.info("Starting the RestApplication : start() method");
        primaryStage.setTitle("Inventory Home");
        Image img = new Image("file:resources/img/logo.png");
        primaryStage.getIcons().add(img);
        //log.info("before initLoginProcess() method");
    	final Task<Boolean> startRestTask = new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				updateMessage("Application is starting the services . . . ");
				startRestAPI(arguments);
				updateMessage("All services started successfully . . . ");
				return true;
			}
		};
		showSplash(
                primaryStage,
                startRestTask,
                () -> initLoginProcess()
        );
        new Thread(startRestTask).start();        
    }
    
    
    private void initLoginProcess() {
    	try {
    		loginStage = new Stage(StageStyle.DECORATED);
    		Image img = new Image("file:resources/img/logo.png");
    		LoginController.loginStage = loginStage;
    		loginStage.getIcons().add(img);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/LoginLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            Platform.setImplicitExit(false);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            Scene loginScene = new Scene(personOverview);
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(RestApplication.class.getResource("views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load(getClass().getResourceAsStream("/views/RootLayout.fxml"));
            Scene scene = new Scene(rootLayout);
            Image img = new Image("file:resources/img/logo.png");
            Stage mainStage = new Stage();
            this.stage = mainStage;
            mainStage.setScene(scene);
            mainStage.initStyle(StageStyle.DECORATED); 
            mainStage.setTitle("..: tallyMED :: Home :..");
            mainStage.getIcons().add(img);
            mainStage.setMaximized(true);
            loginStage.close();
            mainStage.setOnHidden(event -> Platform.exit());
            mainStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    
    
    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(getClass().getResourceAsStream("/img/SplashLogo.png"))); //new ImageView(new Image(SPLASH_IMAGE));
        loadProgress = new JFXProgressBar();
        
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("Loading services . . . ");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.TOP_RIGHT);
        splashLayout.setStyle(
                "-fx-padding: 3; " +
                "-fx-background-color: white; " +
                "-fx-border-width:3; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "green, " +
                        "derive(green, 60%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }
    
    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.show();
    }

    public interface InitCompletionHandler {
        public void complete();
    }
}

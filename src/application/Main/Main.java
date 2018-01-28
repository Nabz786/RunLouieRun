package application.Main;
import application.Game.Game;
 
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**********************************************************************
 * This is the main class, here the program will be launched placing
 * the user at the main menu screen. 
 *
 *********************************************************************/
public class Main extends Application {
	
	/** Width of the program window **/
	private static final int WIDTH = 500; //transition to 600
	 
	/** height of the program window **/
	private static final int HEIGHT = 600; //transition to 400
	
	/** Stage for the primary stage which will be the main-window **/
	private static Stage mainWindow;
	
	/** The first scene that loads upon running the program **/
	private Scene mainMenuScene;
	
	/** Button to start the game**/
	private Button startButton;
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		mainWindow = primaryStage;
		startButton = new Button("Start");
		startButton.setPrefSize(70, 50);
			
		VBox startMenuLayout = new VBox();
		startMenuLayout.setAlignment(Pos.CENTER);
		startMenuLayout.getChildren().add(startButton); //When adding multiple children use .addAll
			
		mainMenuScene = new Scene(startMenuLayout, WIDTH, HEIGHT);
		mainWindow.setScene(mainMenuScene);
		mainWindow.setTitle("Run Louie Run");
		mainWindow.show();
		mainWindow.setResizable(true);
			
		startButton.setOnAction(event ->{
			Game game = null;
			try {
					game = new Game();
				} catch (IOException e) {
					e.printStackTrace();
				}
			Scene gameScene = game.getGameScene();
			mainWindow.setScene(gameScene);
			});
	}
	
	public static Stage getCurrentStage() {
		return mainWindow;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

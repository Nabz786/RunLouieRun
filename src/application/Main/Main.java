package application.Main;
import application.Game.Game;
 
import java.io.IOException;

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
	private static final int WIDTH = 650;
	
	/** height of the program window **/
	private static final int HEIGHT = 400;
	
	/** Stage for the primary stage which will be the main-window **/
	private Stage mainWindow;
	
	/** The first scene that loads upon running the program **/
	private Scene mainMenuScene;
	
	/** Button to start the game**/
	private Button startButton;
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		//try {
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
			mainWindow.setResizable(false);
			
			Parent root = FXMLLoader.load(getClass().getResource("MainMenuStyle.fxml"));

			startButton.setOnAction(event ->{
				mainWindow.setScene(new Scene(root, WIDTH, HEIGHT));
				Game game = new Game();
			});
			
			
			
			
//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		
		
		//create a start button, then within the event listener create the animation timer
		//thus when start is clicked we create a new timer for all the animations.
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application.Main;

import java.io.IOException;

import application.Game.AssetLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This is the main class, here the program will be launched placing 
 * the user at the main menu screen.
 * @author Nabeel Vali
 * @author Kehlsey Lewis
 * @author Andrew Freiman
 */
public class Main extends Application {

	/** Manage game assets. **/
	AssetLoader assetLoader;

	/** Stage for the primary stage which will be the main-window. **/
	public static Stage mainWindow;

	/** The first scene that loads upon running the program. **/
	private Scene mainMenuScene;
	
	
	/**
    * This method starts the game.
    * @param primaryStage - the graphics context for the panel
    */
	@Override
	public final void start(final Stage primaryStage) throws IOException {
		assetLoader = new AssetLoader();
		//Loads main menu setup for main menu
		Parent root = FXMLLoader.load(
				getClass().getResource("MainMenuStyle.fxml"));
		//Main window options
		setMainWindow(primaryStage);
		mainMenuScene = new Scene(root, 
				assetLoader.getWinWidth(), assetLoader.getWinHeight());
		mainWindow.setScene(mainMenuScene);
		mainWindow.setTitle("Run Louie Run");
		mainWindow.show();
		mainWindow.setResizable(true);
	}
	
	/**
	 * Sets the primary stage to the mainwindow.
	 * @param stage to be set
	 */
	private void setMainWindow(final Stage stage) {
		mainWindow = stage;
	}
			
	/**
	 * Updates the current scene with the passed scene.
	 * @param scene Scene to update
	 */
	public static void setScene(final Scene scene) {
		mainWindow.setScene(scene);
	}

	/*****************************************************************
	* This method is the main method that will launch the application.
	* @param args -
    *****************************************************************/
	public static void main(final String[] args) {
		launch(args);
	}
}

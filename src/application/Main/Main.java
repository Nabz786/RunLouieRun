package application.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import application.Game.Game;
import application.Game.SoundManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


/**********************************************************************
 * This is the main class, here the program will be launched placing 
 * the user at the main menu screen.
 * @author Nabeel Vali
 * @author Kehlsey Lewis
 * @author Andrew Freiman
 *********************************************************************/
public class Main extends Application {

	/** Width of the program window **/
	private static final int WIDTH = 600; 

	/** height of the program window **/
	private static final int HEIGHT = 400; 

	/** Stage for the primary stage which will be the main-window **/
	public static Stage mainWindow;

	/** The first scene that loads upon running the program **/
	private Scene mainMenuScene;
	
	
	/*****************************************************************
    * This method starts the game.
    * @param primaryStage - the graphics context for the panel
    *****************************************************************/
	@Override
	public final void start(final Stage primaryStage) throws IOException {
	
		//Loads main menu setup for main menu
		Parent root = FXMLLoader.load(getClass().getResource("MainMenuStyle.fxml"));

		//Main window options
		mainWindow = primaryStage;
		mainMenuScene = new Scene(root, WIDTH, HEIGHT);
		mainWindow.setScene(mainMenuScene);
		mainWindow.setTitle("Run Louie Run");
		mainWindow.show();
		mainWindow.setResizable(true);
		
	}
			
	/*****************************************************************
	 * This method returns the current stage setup when called.
	 * @return mainWindow - the current stage
	 *****************************************************************/
	public static Stage getCurrentStage() {
		return mainWindow;
	}
	
	/*****************************************************************
    * This method will set the scene for the current window.
    * @param scene - the scene for the window to be set to
    * @return mainWindow - the window with the new scene
    *****************************************************************/
	public static void setScene(Scene scene){
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

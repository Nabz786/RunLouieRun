package application.Main;

import java.io.File;
import java.io.IOException;

import application.Game.Game;
import application.Game.SoundManager;
//import application.Help;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**********************************************************************
 * This is the fx:controller class which links the scene builder members from
 * the fxml file to code within this class
 * 
 * @author Nabeel Vali
 * @author Khelsey Lewis
 *********************************************************************/

public class MainMenuController {

	/** Buttons to start and exit the game **/
	@FXML
	private Button startButton, exitButton, helpButton;

	/** Sound manager to manage the start menu sound **/
	private SoundManager soundManager;

	/** Width of the program window **/
	private static final int WIDTH = 600;

	/** height of the program window **/
	private static final int HEIGHT = 400;

	@FXML
	/******************************************************************
	 * This method is called after all @FXML annotated members have been
	 * injected.
	 *****************************************************************/
	private void initialize() {
		soundManager = new SoundManager();
		soundManager.playSound(SoundManager.Sounds.MainMenu);

		startButton.setOnAction(e -> {

			Game game = null;
			try {
				game = new Game();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Main.setScene(game.getGameScene());
			soundManager.stopSound();
		});

		helpButton.setOnAction(e -> {

			try {
				Parent root = FXMLLoader.load(getClass().getResource("helpMenu.fxml"));

				Scene mainMenuScene = new Scene(root, 600, 400);
				Main.setScene(mainMenuScene);
				soundManager.stopSound();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}
}

package application.Main;

import java.io.IOException;

import application.Game.Game;
import application.Game.SoundManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**********************************************************************
 * This is the fx:controller class which links the scene builder
 * members from the fxml file to code within this class
 * @author Nabeel Vali
 *********************************************************************/

public class MainMenuController {

			
	@FXML
	private Button startButton;
	private Button exitButton;
	
	@FXML
	private Label testLabel;
	private Stage mainWindow;
	private SoundManager soundManager;
	private Scene mainMenuScene;
	private Main main;
	
	/** Width of the program window **/
	private static final int WIDTH = 600; // transition to 600

	/** height of the program window **/
	private static final int HEIGHT = 400; // transition to 400

	//This method is called after all @FXML annotated members have been injected
	@FXML
	private void initialize() {
		soundManager = new SoundManager();
		soundManager.playSound(SoundManager.Sounds.MainMenu);
		
		startButton.setOnAction(e -> {
			
			//System.out.println("test");
			Game game = null;
			try {
					game = new Game();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			//mainWindow.setScene(game.getGameScene());
			Main.setScene(game.getGameScene());
			soundManager.stopSound();
			
		});

	}
	
	
	
	
	
}

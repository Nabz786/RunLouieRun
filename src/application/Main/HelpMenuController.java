package application.Main;

import java.io.IOException;

import application.Game.Game;
import application.Game.SoundManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class HelpMenuController {

		
		/**Button to return to main menu **/
		@FXML
		private Button backButton;
		
		/** Sound manager to manage the start menu sound **/
		private SoundManager soundManager;
		
		/** Width of the program window **/
		private static final int WIDTH = 600;

		/** height of the program window **/
		private static final int HEIGHT = 400;

		@FXML
		/****************************************************************** 
		 * This method is called after all @FXML annotated members have
		 * been injected. 
		 *****************************************************************/
		private void initialize() {	
			
			soundManager = new SoundManager();
			soundManager.playSound(SoundManager.Sounds.MainMenu);
			
			backButton.setOnAction(e -> {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("MainMenuStyle.fxml"));
					Scene mainMenuScene = new Scene(root, 600, 400);
					Main.setScene(mainMenuScene);
					soundManager.stopSound();
					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			});
			
			
		}
	}


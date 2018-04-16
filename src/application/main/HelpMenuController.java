package application.main;
import java.io.IOException;

import application.game.AssetLoader;
import application.game.SoundManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
* This is the fx:controller class which links the scene builder members from
* the helpMenu.fxml file to code within this class.
*
* @author Kehlsey Lewis
* @author Nabeel Vali
* @author Andrew Freiman
* @version Winter 2018
*/

public class HelpMenuController {

		
		/**Button to return to main menu. **/
		@FXML
		private Button backButton;
		
		/** Sound manager to manage the start menu sound. **/
		private SoundManager soundManager;
		
		/** Asset Manager.**/
		private AssetLoader assetLoader;
		
		/**
	    * This method is called after all @FXML annotated members have
		* been injected. 
	    */
		@FXML
		private void initialize() {	
			
			//start playing music
			soundManager = new SoundManager();
			assetLoader = new AssetLoader();
			soundManager.playSound(SoundManager.Sounds.MainMenu);
			
			//if the back button is clicked return back to main menu
			backButton.setOnAction(e -> {
				try {
					
					//Loads the main menu to return back to
					Parent root = FXMLLoader.load(
							getClass().getResource(
							 "MainMenuStyle.fxml"));
					Scene mainMenuScene = new Scene(
						root, assetLoader.getWinWidth(), 
						assetLoader.getWinHeight());
					Main.setScene(mainMenuScene);
					
					soundManager.stopSound();
					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			});
		}
	}


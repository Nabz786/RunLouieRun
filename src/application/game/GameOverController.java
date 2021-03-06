package application.game;
import java.io.IOException;

import application.main.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;

/**
 * This is the fx:controller class which links the scene builder 
 * members from the GameOverScreen.fxml file to code within this class.
 * @author Nabeel Vali
 * @author Khelsey Lewis
 * @author Andrew Freiman
 * @version Winter 2018
 */

public class GameOverController {
   /** Buttons to go back to main menu and restart the game. **/
   @FXML
   private Button mainMenuButton, restartButton;

   /** Sound manager to manage the game over menu sound. **/
   private SoundManager soundManager;

   /** Width of the program window. **/
   private AssetLoader assetLoader;
   
   /** Label for distance traveled.**/
   @FXML
   private Label distanceLabel;
   
   /** Labels for anchors earned.**/
   @FXML
   private Label anchorLabel;

   
   /**
    * This method is called after all @FXML annotated members have been
    * injected.
    */
   @FXML
   private void initialize() {
	  distanceLabel.setText("You made it: " + Game.getDistance() + "m");
	  anchorLabel.setText("You Earned: " + Game.getNumCoins() + " anchors");
	  
      soundManager = new SoundManager();
      assetLoader = new AssetLoader();
      soundManager.playSound(SoundManager.Sounds.Death);
      //If the restart button is hit will start new game
         restartButton.setOnAction(e -> {
         Game game = new Game();
         Main.setScene(game.getGameScene());
      });

      //If the main menu button is hit will take back to main menu
      mainMenuButton.setOnAction(e -> {
         try {
            //Loads the main menu to return back to
            Parent root = FXMLLoader.load(
            		Main.class.getResource("MainMenuStyle.fxml"));
            Scene mainMenuScene = new Scene(root, 
            		assetLoader.getWinWidth(), assetLoader.getWinHeight());
            Main.setScene(mainMenuScene);
         } catch (IOException e1) {
            e1.printStackTrace();
         }
      }); 
   }
}

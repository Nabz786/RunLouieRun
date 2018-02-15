package application.Main;

import java.io.IOException;
import application.Game.Game;
import application.Game.SoundManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**********************************************************************
 * This is the main class, here the program will be launched placing the user at
 * the main menu screen.
 * 
 * @author Nabeel Vali
 *********************************************************************/
public class Main extends Application {

	/** Width of the program window **/
	private static final int WIDTH = 600; // transition to 600

	/** height of the program window **/
	private static final int HEIGHT = 400; // transition to 400

	/** Stage for the primary stage which will be the main-window **/
	private static Stage mainWindow;

	/** The first scene that loads upon running the program **/
	private Scene mainMenuScene;

	/** Button to start the game **/
	private Button startButton;
	
	/** Manager for all game sounds **/
	private SoundManager soundManager;
	

	@Override
	public final void start(final Stage primaryStage) throws IOException {
	
		
		soundManager = new SoundManager();
		soundManager.playSound(SoundManager.Sounds.MainMenu);
		
		
		mainWindow = primaryStage;

		//I changed from vbox to pane because I found more tutorials on pane, seemed easier to work with :)
		Pane startMenuLayout = new Pane();
		
		//Commented out my file locations, you guys do the same for yours so we don't have to keep changing them as we move the files around
		// for not at least until we find a different way
		
//		ImageView background = new ImageView(new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/background.png"));
//		ImageView title = new ImageView(new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/title.png"));
//		ImageView startButton = new ImageView(new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/start_text.png"));
//		ImageView exitButton = new ImageView(new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/exit_text.png"));
//		ImageView background = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/background.png"));
//		ImageView title = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/title.png"));
//		ImageView startButton = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/start_text.png"));
//		ImageView exitButton = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/exit_text.png"));
		ImageView background = new ImageView(new Image("file:///C:/Users/Andy/Documents/GitHub/RunLouieRun/src/application/Resources/Images/background.png"));
		ImageView title = new ImageView(new Image("file:///C:/Users/Andy/Documents/GitHub/RunLouieRun/src/application/Resources/Images/title.png"));
		ImageView startButton = new ImageView(new Image("file:///C:/Users/Andy/Documents/GitHub/RunLouieRun/src/application/Resources/Images/start_text.png"));
		ImageView exitButton = new ImageView(new Image("file:///C:/Users/Andy/Documents/GitHub/RunLouieRun/src/application/Resources/Images/exit_text.png"));
		
		startButton.setLayoutX(400);
		startButton.setLayoutY(150);
		
		exitButton.setLayoutX(400);
		exitButton.setLayoutY(200);
		
		background.setLayoutX(0);
		background.setLayoutY(0);
		
		title.setLayoutX(150);
		title.setLayoutY(0);
				
		//adding a listener to the image to act as a button
		startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			
			//code to start game goes here
			//when the start button is clicked we create a new instance of game and load it to the current stage
						Game game = null;
						try {
								game = new Game();
							} catch (IOException e) {
								e.printStackTrace();
							}
						mainWindow.setScene(game.getGameScene());
						soundManager.stopSound();
						soundManager.playSound(SoundManager.Sounds.Running);
						
						

			//this tells the handler that the event is over
	         event.consume();	
		});

		//closes the window
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			primaryStage.close();				
		});
		
		//adding all of the menu components
		startMenuLayout.getChildren().addAll(background,title,exitButton,startButton);
		

		//Main window options
		mainMenuScene = new Scene(startMenuLayout, WIDTH, HEIGHT);
		mainWindow.setScene(mainMenuScene);
		mainWindow.setTitle("Run Louie Run");
		mainWindow.show();
		mainWindow.setResizable(true);
		

	}
			
	// returns the current stage
	public static Stage getCurrentStage() {
		return mainWindow;
	}

	public static void main(final String[] args) {
		launch(args);
	}
}

package application.Main;

import application.Game.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**********************************************************************
 * This is the main class, here the program will be launched placing the user at
 * the main menu screen.
 * 
 * @author Nabeel Vali
 *********************************************************************/
public class Main extends Application {

	/** Boolean to start/stop music play**/
	private volatile boolean musicplaying = false;
	
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

	@Override
	public final void start(final Stage primaryStage) throws IOException {
		
		mainWindow = primaryStage;

		//I changed from vbox to pane because I found more tutorials on pane, seemed easier to work with :)
		Pane startMenuLayout = new Pane();
		ImageView background = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/background.png"));
		ImageView title = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/title.png"));
		ImageView startButton = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/start_text.png"));
		ImageView exitButton = new ImageView(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/exit_text.png"));
		
		startButton.setLayoutX(400);
		startButton.setLayoutY(150);
		
		exitButton.setLayoutX(400);
		exitButton.setLayoutY(200);
		
		background.setLayoutX(0);
		background.setLayoutY(0);
		
		title.setLayoutX(150);
		title.setLayoutY(0);
		
		//Begin playing music
		playMusic("C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Sounds/MainThemeFinal.wav");
		
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
						
			//Stop menu theme music and begin running music
			musicplaying = false;
			playMusic("C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Sounds/RunningTheme.wav");
						
			//this tells the handler that the event is over
	         event.consume();	
		});

		//closes the window
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			primaryStage.close();	
			//Stop music
			musicplaying = false;
			
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

	//Plays the main menu theme on loop

	
			
	
	public void playMusic(String filepath){
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				musicplaying = true;
				File file= new File(filepath);
				try{
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(file));
				while(musicplaying){
						clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				clip.stop();
				clip.close();
				}catch(Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
		t.start();
	}
	
	
	// returns the current stage
	public static Stage getCurrentStage() {
		return mainWindow;
	}

	public static void main(final String[] args) {
		launch(args);
	}
}

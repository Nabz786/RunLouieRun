package application.Main;
import application.Game.Game;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
import javafx.scene.layout.VBox;

/**********************************************************************
 * This is the main class, here the program will be launched placing
 * the user at the main menu screen.
 * @author Nabeel Vali
 *********************************************************************/
public class Main extends Application {
	
	/** Width of the program window **/
	private static final int WIDTH = 600; //transition to 600
	 
	/** height of the program window **/
	private static final int HEIGHT = 400; //transition to 400
	
	/** Stage for the primary stage which will be the main-window **/
	private static Stage mainWindow;
	
	/** The first scene that loads upon running the program **/
	private Scene mainMenuScene;
	
	/** Button to start the game**/
	private Button startButton;
	
	@Override
	public final void start(final Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("MainMenuStyle.fxml"));
		
		mainWindow = primaryStage;
		//startButton = new Button("Start");
		//startButton.setPrefSize(70, 50);
		
		//we are using a vbox layout for the mainmenu, we declare it here then add its children
		//VBox startMenuLayout = new VBox();
		//startMenuLayout.setAlignment(Pos.CENTER);
		//startMenuLayout.getChildren().add(startButton); //When adding multiple children use .addAll
			
		ImageView imageView = new ImageView();
		imageView.setX(200);
		imageView.setY(200);
		imageView.setFitHeight(128);
		imageView.setFitWidth(128);
		List<Image> images = new ArrayList<>();
		images.add(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie1.png"));
		images.add(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie2.png"));
		images.add(new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie3.png"));

			imageView.setImage(images.get(0));
			int index = 0;
			
			IntegerProperty count = new SimpleIntegerProperty(0);
			
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/6), ev -> {
				if(count.get() < 2) count.set(count.get() +1);
				else count.set(0);
				
				
				imageView.setImage(images.get(count.get()));
		        
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			
//		AnimationTimer timer = new AnimationTimer() {
//			int index = 0;
//
//			@Override
//			public void handle(long arg0) {
//				if(index != images.size()) {
//				imageView.setImage(images.get(index++));
//				}
//				else {
//					index = 0;
//				}
//			}
//			
//		};
//		timer.start();
		//startMenuLayout.getChildren().add(imageView);

		
		
		
		//Main window options
		mainMenuScene = new Scene(root, WIDTH, HEIGHT);
		mainWindow.setScene(mainMenuScene);
		mainWindow.setTitle("Run Louie Run");
		mainWindow.show();
		mainWindow.setResizable(true);
			
		//when the start button is clicked we create a new instance of game and load it to the current stage
//		startButton.setOnAction(event ->{
//			Game game = null;
//			try {
//					game = new Game();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			mainWindow.setScene(game.getGameScene());
//			});
//		
		
		
	}
	
	//returns the current stage
	public static Stage getCurrentStage() {
		return mainWindow;
	}
	
	public static void main(final String[] args) {
		launch(args);
	}
}

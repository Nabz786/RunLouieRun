package application.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/********************************************************************** 
 * This class will hold all the relevant material related to the game
 * and game states 
 *@author Nabeel Vali
 *********************************************************************/
public class Game extends Application implements EventHandler<KeyEvent> {
		
	/** The scene that is represented within this class**/
	public Scene gameScene;
	
	/** For now we will use a pane as the Parent node **/
	private Pane root;
	
	/** Width of the game window**/
	private final int WIDTH = 600;
	
	/** Height of the game window  **/
	private final int HEIGHT = 400;
	
	/** Image view to be used for holding louie in this release**/
	ImageView imageView;
	
	/** The x value of the imageview that holds louie**/
	private int xPos = 55;
	
	/** The y value of the imageview that holds louie **/
	private int yPos = 243;
	
	
	/**
	 * Constructor creates an instance of game as well as created a circle
	 * @throws IOException - if the css stylesheet was not found
	 */
	public Game() throws IOException{
		root = new Pane();
		createGameInstance();
		showLouie();
		setTempNode();
	}
	
	public void showLouie() {
		
		imageView = new ImageView();
		imageView.setX(xPos);
		imageView.setY(yPos);
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
		root.getChildren().add(imageView);
	}
	
	public void createGameInstance() throws IOException {
		gameScene = new Scene(root, WIDTH, HEIGHT);
		root.setId("test");
		gameScene.getStylesheets().add(Game.class.getResource("GameDesign.css").toExternalForm());
	}
	
	private void setTempNode() {
		final Box keyBoardNode = new Box();
		keyBoardNode.setFocusTraversable(true);
		keyBoardNode.requestFocus();
		keyBoardNode.setOnKeyPressed(this);
		root.getChildren().add(keyBoardNode);
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
	
	@Override
	public void start(Stage gameStage) throws Exception {}

	public void rebound() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
					if(imageView.getY() < 243) {
						yPos++;
						imageView.setY(yPos);
					}else {
						yPos = 241;
						imageView.setY(yPos);
					}
				}	
		};
		timer.start();
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.SPACE) {
			yPos = yPos - 40;
			imageView.setY(yPos);
			rebound();
		}
	}
}	


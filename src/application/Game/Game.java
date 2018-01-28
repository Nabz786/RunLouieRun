package application.Game;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/********************************************************************** 
 * This class will hold all the relevant material related to the game
 * and game states 
 *
 *********************************************************************/
public class Game extends Application {
	
	/** The scene that is represented within this class**/
	public Scene gameScene;
	
	/** For now we will use a pane as the Parent node **/
	private Pane root;
	
	/** Width of the game window**/
	private final int WIDTH = 600;
	
	/** Height of the game window  **/
	private final int HEIGHT = 400;
	
	/** Temporary circle for trying out graphics**/
	private Circle c;
	
	//just trying to play around with movement
	//this will not be here in release 1, its temporary
	double ballRadius = 20;
	double ballX = 100;
	double ballY = 200;
	double deltaX = 4;
	double deltaY = 4;
	
	/**
	 * Constructor creates an instance of game as well as created a circle
	 * @throws IOException - if the css stylesheet was not found
	 */
	public Game() throws IOException{
		timer.start();
		root = new Pane();
		createGameInstance();
		createCircle();
	}
	
	//we will be using an animation timer to update the scene and all relevant game movements 
	AnimationTimer timer = new AnimationTimer() {
		
		@Override
		public void handle(long timerTick) {
			//code within handle is just temporary and will be removed once sprite class is finished
			ballX += deltaX;
			ballY += deltaY;
			
			if (ballX + ballRadius >= WIDTH)
            {
                ballX = WIDTH - ballRadius;
                deltaX *= -1;
            } 
            else if (ballX - ballRadius < 0) 
            {
                ballX = 0 + ballRadius;
                deltaX *= -1;
            }
            else if(ballY + ballRadius >= HEIGHT) {
            	ballY = HEIGHT - ballRadius;
            	deltaY *= -1;
            }
            else if(ballY - ballRadius < 0) {
            	ballY = 0 + ballRadius;
            	deltaY *= -1;
            }
			
			c.setCenterX(ballX);
			c.setCenterY(ballY);
		}
	};
	
	public void createCircle() {
		c = new Circle();
		c.setCenterX(ballX);
		c.setCenterY(ballY);
		c.setRadius(ballRadius);
		c.setFill(Color.GREEN);
		root.getChildren().add(c);
	}
	
	public void createGameInstance() throws IOException {
		gameScene = new Scene(root, WIDTH, HEIGHT);
		root.setId("test");
		gameScene.getStylesheets().add(Game.class.getResource("GameDesign.css").toExternalForm());
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
	
	@Override
	public void start(Stage gameStage) throws Exception {}
}	


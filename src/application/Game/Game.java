package application.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import application.Main.Main;
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
import javafx.scene.canvas.GraphicsContext;


/********************************************************************** 
 * This class will hold all the relevant material related to the game
 * engine and game states. 
 *@author Nabeel Vali
 *********************************************************************/
public class Game extends Application {
		
	/** The scene that is represented within this class.**/
	private Scene gameScene;
	
	/** For now we will use a pane as the Parent node.**/
	private Group root;
	
	/** Width of the game window.**/
	public static final int windowWidth = 600;
	
	/** Height of the game window.**/
	public static final int windowHeight = 400;
	
	/** Graphics canvas where objects are drawn.**/
	private Canvas canvas;
	
	/** Graphics engine.**/
	private GraphicsContext gc;
	
	/** Background scenery image.**/
	private Image background;
	
	/** Main character louie sprite.**/
	private Louie louie;
	
	/** Random instance to randomize spawning.**/
	private Random random;
	
	/** Boolean value to identify whether game has been started.**/
	private boolean gameStarted = false;
		
	/** Arraylist holding all key events.**/
	private ArrayList<String> input;
	
	/** Arraylist holding all spawned enemies.**/
	private ArrayList<EvilExam> enemyList;
	
	/******************************************************************
	 * Constructor for game class.
	 * Calls another method to initialize the game engine and draw all
	 * required game assets to the screen
	 *****************************************************************/
	public Game() {	
		createGameInstance();
	}
	
	/******************************************************************
	 * Instantiates all required members, and loads backgrounds
	 *****************************************************************/
	private void loadAssets()  {
		canvas = new Canvas(700, Game.windowHeight);
		root.getChildren().add(canvas);
		
		random = new Random();
		
		gc = canvas.getGraphicsContext2D();
		
		enemyList = new ArrayList<EvilExam>();
				
		//background = new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/background.png");
		background = new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/background.png");
	}
	
	private void loadLouie() {		
		louie = new Louie(32, 243);
		
		Image[] louieFrames = new Image[3];
		for(int i = 1; i < 4; i++) 
			//louieFrames[i- 1] = new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie" + i + ".png");
			louieFrames[i- 1] = new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie" + i + ".png");
		louie.frames = louieFrames;
		louie.dispayDuration = 0.100;
	}
	
	private void spawnEnemy() {
		if(gameStarted) {
			EvilExam enemy = new EvilExam(enemyList.get(enemyList.size()- 1).positionX + 350, 275);
			enemyList.add(enemy);
		}else {
			EvilExam enemy = new EvilExam(windowWidth + enemyList.size() * 250, 275);
			enemyList.add(enemy);
		}
	}
	
	private void initListener() {
		input = new ArrayList<String>();

        gameScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });

        gameScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
	}
	
	private void createGameInstance() {
		root = new Group();
		gameScene = new Scene(root, windowWidth, windowHeight);
		loadAssets();
		loadLouie();
		initListener();
		spawnEnemy();
		spawnEnemy();
		
		gameStarted = true;
		
		final long startingTime = System.nanoTime();
		
		new AnimationTimer() {

			@Override
			public void handle(long currentDeltaTime) {
				double deltaDifference = (currentDeltaTime - startingTime) /  1000000000.0;
                gc.clearRect(0, 0, 700, windowHeight);
                
                
                if(louie.onGround()) {
                	louie.canJump = true;
                	if(input.contains("SPACE")) {
                		louie.jump();
                	}
                } else {
                	louie.canJump = false;
                	louie.rebound();
                }
                
                gc.drawImage(background, 0, 0);
        
				louie.render(gc, deltaDifference);
				
				for(int i = 0; i < enemyList.size(); i++) {
					EvilExam enemy = enemyList.get(i);
					enemy.render(gc, deltaDifference, 96, 96);
                	enemy.chargeLeft();
                	
                	if(enemy.getPositionX() + 96 < 0) {
                		enemyList.remove(enemy);
                		spawnEnemy();
                	}
                }

			}
		}.start();
	}
			
	public Scene getGameScene() {
		return gameScene;
	}
	
	@Override
	public void start(Stage gameStage) {}
}
	



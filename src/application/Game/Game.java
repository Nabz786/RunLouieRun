package application.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import application.Main.Main;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 * This class will hold all the relevant material related to the game
 * engine and game states. 
 *@author Nabeel Vali
 */
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
	
	/** Boolean value to identify whether game has been started.**/
	private boolean gameStarted = false;
		
	/** Arraylist holding all key events.**/
	private ArrayList<String> input;
	
	/** Arraylist holding all spawned enemies.**/
	private ArrayList<EvilExam> enemyList;
	
	/**
	 * Constructor for game class.
	 * Calls another method to initialize the game engine and draw all
	 * required game assets to the screen
	 **/
	public Game() {	
		createGameInstance();
	}
	
	/**
	 * Instantiates all required members, and loads backgrounds.
	 **/
	private void loadAssets()  {
		canvas = new Canvas(700, Game.windowHeight);
		root.getChildren().add(canvas);
				
		gc = canvas.getGraphicsContext2D();
		
		enemyList = new ArrayList<EvilExam>();
				
		background = new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/background.png");
		//background = new Image("file:///C:/Users/Kehlsey/workspace/RunLouieRun/src/application/Resources/Images/background.png");
	}
	
	/**
	 * Creates a new louie sprite and loads all frames into array
	 * to create the animation.
	 */
	private void loadLouie() {		
		louie = new Louie(32, 243);
		
		Image[] louieFrames = new Image[3];

		for (int i = 1; i < 4; i++) {
			louieFrames[i- 1] = new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Finished_Louie" + i + ".png");
		}
		louie.setFrames(louieFrames);
		louie.setFrameDuration(0.100);
	}
	
	/**
	 * Creates a new louie sprite and loads all frames into array
	 * to create the animation.
	 */
	private void spawnEnemy() {
		if (gameStarted) {
			EvilExam enemy = new EvilExam(enemyList.get(
					        enemyList.size() - 1).getPositionX() 
					           + 350, 275);
			enemyList.add(enemy);
		} else {
			EvilExam enemy = new EvilExam(windowWidth 
					+ enemyList.size() * 250, 275);
			enemyList.add(enemy);
		}
	}
	
	/*
	 * Initializes a key listener to record key presses and then pass
	 * each press into an arraylist to mitigate key spamming.
	 */
	private void initListener() {
		input = new ArrayList<String>();

        gameScene.setOnKeyPressed(
            new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent e) {
                
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
            });

        gameScene.setOnKeyReleased(
            new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent e) {
                    String code = e.getCode().toString();
                    input.remove(code);
                }
            });
	}
	
	/**
	 * Initializes many aspects of the game including the gamescene and
	 * root node, this method also loads all game assets and spawns two
	 * starting enemies, and serves as the game engine to render all
	 * assets.
	 */
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
			public void handle(final long currentDeltaTime) {
				double deltaDifference = (currentDeltaTime
						- startingTime) /  1000000000.0;
				
                gc.clearRect(0, 0, 700, windowHeight);
                
                if (louie.onGround()) {
                	louie.setCanJump();
                	if (input.contains("SPACE")) {
                		louie.jump();
                	}
                } else {
                	louie.setCantJump();
                	louie.rebound();
                }
                
                gc.drawImage(background, 0, 0);
        
				louie.render(gc, deltaDifference);
				
				for (int i = 0; i < enemyList.size(); i++) {
					EvilExam enemy = enemyList.get(i);
					enemy.render(gc, deltaDifference, 96, 96);
                	enemy.chargeLeft();
                	
                	if (enemy.getPositionX() + 96 < 0) {
                		enemyList.remove(enemy);
                		spawnEnemy();
                	}
                	
                	if (louie.intersects(enemy)) {
                		stop();
            		System.out.println("Game Over!!!");
            		
            		try {
						Parent root = FXMLLoader.load(getClass().getResource("gameOverScreen.fxml"));
						Scene mainMenuScene = new Scene(root, 600, 400);
						Main.setScene(mainMenuScene);
					} catch (IOException e) {
						e.printStackTrace();
					}          	
				}               	
              }
			}
		}.start();
	}
		
	/**
	 * Returns the current gamescene to load in the main stage.
	 * @return the current game scene
	 */
	public Scene getGameScene() {
		return gameScene;
	}
	
	@Override
	public void start(final Stage gameStage) { }
}
	



package application.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import application.Main.Main;
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
 *@author Andrew Freiman
 */
public class Game extends Application {
		
	/** The scene that is represented within this class.**/
	private Scene gameScene;
	
	/** Sound managers to manage the in game sound effects. **/
	private SoundManager soundManager;
	
	/** For now we will use a pane as the Parent node.**/
	private Group root;
	
	/** Width of the game window.**/
	public static final int WIDTH = 600;
	
	/** Height of the game window.**/
	public static final int HEIGHT = 400;
	
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
	
	private ArrayList<EvilExam> subList;
	
	private double deltaDifference;
	
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
		canvas = new Canvas(700, Game.HEIGHT);
		root.getChildren().add(canvas);
				
		gc = canvas.getGraphicsContext2D();
		
		enemyList = new ArrayList<EvilExam>();
		subList = new ArrayList<EvilExam>();
				
		background = new Image("file:resources/Images/background.png");
	}
	
	/**
	 * Creates a new louie sprite and loads all frames into array
	 * to create the animation.
	 */
	private void loadLouie() {		
		louie = new Louie(32, 243);
		
		Image[] louieFrames = new Image[3];

		for (int i = 1; i < 4; i++) {
			louieFrames[i - 1] = new Image("file:resources/"
					+ "Images/Finished_Louie" + i + ".png");
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
			subList.add(enemy);
		} else {
			EvilExam enemy = new EvilExam(WIDTH 
					+ enemyList.size() * 250, 275);
			enemyList.add(enemy);
		}
	}
	
	/**
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
                	soundManager.playSound(SoundManager.Sounds.Jump);
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
		gameScene = new Scene(root, WIDTH, HEIGHT);
		soundManager = new SoundManager();
		soundManager.playSound(SoundManager.Sounds.Running);
		loadAssets();
		loadLouie();
		initListener();
		spawnEnemy();
		spawnEnemy();
		//remove for actual program
		spawnEnemy();
		
		gameStarted = true;
		
		final long startingTime = System.nanoTime();
		
		new AnimationTimer() {

			@Override
			public void handle(final long currentDeltaTime) {
				 deltaDifference = (currentDeltaTime
						- startingTime) /  1000000000.0;
				
                gc.clearRect(0, 0, 700, HEIGHT);
                
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
				
//				for (int i = 0; i < enemyList.size(); i++) {
//					EvilExam enemy = enemyList.get(i);
//					enemy.render(gc, deltaDifference, 96, 96);
//                	enemy.chargeLeft();
//                	
//                	if (enemy.getPositionX() + 96 < 0) {
//                		enemyList.remove(enemy);
//                		spawnEnemy();
//                	}
//                	
//                	if (louie.intersects(enemy)) {
//            		try {
//						Parent root = FXMLLoader.load(
//								getClass().getResource(
//								"gameOverScreen.fxml"));
//						Scene mainMenuScene = new Scene(root, 600, 400);
//						Main.setScene(mainMenuScene);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}          	
//            		stop();
//            		soundManager.stopSound();
//                }
//              }
				
				
				for(Iterator<EvilExam> iter = enemyList.iterator(); iter.hasNext();) {
					EvilExam evilExam = iter.next();
					evilExam.render(gc, deltaDifference, 96, 96);
					evilExam.chargeLeft();
					if (evilExam.getPositionX() + 96 < -50) {
                		iter.remove();
                		spawnEnemy();//GRAPHICS GLITCH IS HERE
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
	



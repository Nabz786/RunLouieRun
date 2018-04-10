package application.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import application.Main.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class will hold all the relevant material related to the game engine and
 * game states.
 * 
 * @author Nabeel Vali
 * @author Andrew Freiman
 * @author Kehlsey Lewis
 */
public class Game extends Application {

	/** The scene that is represented within this class. **/
	private Scene gameScene;

	/** Sound managers to manage the in game sound effects. **/
	private SoundManager soundManager;
	
	/** Asset loader to manage assets.**/
	private AssetLoader assetLoader;

	/** In-game store object. **/
	private Shop store;

	/** For now we will use a pane as the Parent node. **/
	private Group root;

	/** Graphics engine. **/
	private GraphicsContext gc;

	/** Background scenery image. **/
	private Image background;

	/** Main character Louie sprite. **/
	private Louie louie;
	
	/** Boolean value to identify whether game has been started. **/
	private boolean gameStarted = false;

	/** Array list holding all key events. **/
	private ArrayList<String> input;

	/** Array list holding all spawned enemies. **/
	private ArrayList<EvilExam> enemyList;
	
	/**
	 * Constructor for game class. Calls another method to initialize the game
	 * engine and draw all required game assets to the screen
	 **/
	public Game() {
		root = new Group();
		soundManager = new SoundManager();
		store = new Shop();
		assetLoader = new AssetLoader();
		loadGraphicsAssets();
		createGameInstance();
		loadLouie();
	}

	/**
	 * Instantiates all required members, and loads backgrounds.
	 */
	private void loadGraphicsAssets() {
		Canvas canvas = new Canvas(700, assetLoader.getWinHeight());
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		background = new Image("file:resources/Images/background.png");
	}

	/**
	 * Creates a new louie sprite and loads all frames into array to create the
	 * animation.
	 */
	private void loadLouie() {
		louie = new Louie(32, 243);

		Image[] louieFrames = new Image[3];

		for (int i = 1; i < 4; i++) {
			//louieFrames[i - 1] = new Image("file:resources/Images/Finished_Louie" + i + ".png");
			louieFrames[i - 1] = new Image((store.getActiveItem().getImage()) + i + ".png");
		}
		
		louie.setFrames(louieFrames);
		louie.setFrameDuration(0.100);
	}

	/**
	 * Creates a new louie sprite and loads all frames into array to create the
	 * animation.
	 */
	private void spawnEnemy() {
		enemyList = new ArrayList<EvilExam>();
		
		if (gameStarted) {
			EvilExam enemy = new EvilExam(enemyList.get(enemyList.size()
					- 1).getPositionX() + 350, 275);
		} else {
			EvilExam enemy = new EvilExam(assetLoader.getWinWidth() 
					+ enemyList.size() * 250, 275);
			enemyList.add(enemy);
		}
	}

	/**
	 * Initializes a key listener to record key presses and then pass each press
	 * into an arraylist to mitigate key spamming.
	 */
	private void initListener() {
		input = new ArrayList<String>();

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code)) {
					input.add(code);
				}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent e) {
				soundManager.playSound(SoundManager.Sounds.Jump);
				String code = e.getCode().toString();
				input.remove(code);
			}
		});
	}

	/**
	 * Initializes many aspects of the game including the gamescene and root
	 * node, this method also loads all game assets and spawns two starting
	 * enemies, and serves as the game engine to render all assets.
	 */
	
	private void createGameInstance() {
		
		gameScene = new Scene(root, assetLoader.getWinWidth(), assetLoader.getWinHeight());
		
		gc.drawImage(background, 0, 0);
    	gc.drawImage(new Image(store.getActiveItem().getImage() + 0 + ".png"),
    			32, 244, 142, 140);
    	
		//countdown logic
		Image one = new Image("file:resources/Images/countdown2.png");
		Image two = new Image("file:resources/Images/countdown1.png");
		Image three = new Image("file:resources/Images/countdown0.png");
		ImageView images = new ImageView();
		
		soundManager.playCountDown();
		
		//animation for counting
		Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(images.imageProperty(), three)),
                new KeyFrame(Duration.seconds(1), new KeyValue(images.imageProperty(), two)),
                new KeyFrame(Duration.seconds(2), new KeyValue(images.imageProperty(), one)),
                new KeyFrame(Duration.seconds(3), new KeyValue(images.imageProperty(), null)));
		
        timeline.play();
        root.getChildren().add(images);
        
        //logic was moved inside action handler to wait until countdown was done
		timeline.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameRenderer();
			}
		});	
	}

	
	private void gameRenderer() {
		soundManager.playSound(SoundManager.Sounds.Running);
		initListener();
		spawnEnemy();
		spawnEnemy();
		// remove for actual program
		spawnEnemy();
		
		gameStarted = true;

		final long startingTime = System.nanoTime();

		new AnimationTimer() {

			@Override
			public void handle(final long currentDeltaTime) {
				double deltaDifference = (currentDeltaTime - startingTime) / 1000000000.0;
				
				gc.clearRect(0, 0, 700, assetLoader.getWinHeight());

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

//				 for (int i = 0; i < enemyList.size(); i++) {
//				 EvilExam enemy = enemyList.get(i);
//				 enemy.render(gc, deltaDifference, 96, 96);
//				 enemy.chargeLeft();
//				
//				 if (enemy.getPositionX() + 96 < 0) {
//				 enemyList.remove(enemy);
//				 spawnEnemy();
//				 }
//				
//				 if (louie.intersects(enemy)) {
//				 try {
//				 Parent root = FXMLLoader.load(
//				 getClass().getResource(
//				 "gameOverScreen.fxml"));
//				 Scene mainMenuScene = new Scene(root, 600, 400);
//				 Main.setScene(mainMenuScene);
//				 } catch (IOException e) {
//				 e.printStackTrace();
//				 }
//				 stop();
//				 soundManager.stopSound();
//				 }
//				 }

				for (Iterator<EvilExam> iter = enemyList.iterator(); iter.hasNext();) {
					EvilExam evilExam = iter.next();
					evilExam.render(gc, deltaDifference, 96, 96);
					evilExam.chargeLeft();
					if (evilExam.getPositionX() + 96 < -50) {
						iter.remove();
						//spawnEnemy();// GRAPHICS GLITCH IS HERE ..... I can see the irritation in the caps
					}
				}
			}
		}.start();
	}
	
	/**
	 * Returns the current gamescene to load in the main stage.
	 * 
	 * @return the current game scene
	 */
	
	public Scene getGameScene() {
		return gameScene;
	}

	@Override
	public void start(final Stage gameStage) {
		
	}
}

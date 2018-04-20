package application.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import application.main.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	
	/**  Root for the Parent node. **/
	private Group root;

	/** Graphics engine. **/
	private GraphicsContext gc;

	/** Background scenery image. **/
	private Image background;

	/** Main character Louie sprite. **/
	private Louie louie;

	/** Array list holding all key events. **/
	private ArrayList<String> input;

	/** Array list holding all spawned enemies. **/
	private ArrayList<EvilExam> enemyList;

	/** Array list holding all spawned anchors. **/
	private ArrayList<Anchor> anchorList;

	/** Difference in time between frames.**/
	private double deltaDifference;

	/** Spawn interval between sprites.**/
	private int spawnInterval, distanceInterval;
	
	/** Number of coins collected.**/
	private static int numCoins;
	
	/** Traveled distance.**/
	private static int distanceScore;
	
	/** First Looping background .**/
	private BackGround bG1;

	/** Second looping background.**/
	private BackGround bG2;
	
	/** Label to hold scores.**/
	private Label scoreLabel, distanceScoreLabel;
	
	/** Symbol for score label.**/
	private Image scoreAnchor;
	
	/**
	 * Constructor for game class. Calls another method to initialize the game
	 * engine and draw all required game assets to the screen
	 **/
	public Game() {
		root = new Group();
		soundManager = new SoundManager();
		assetLoader = new AssetLoader();
		enemyList = new ArrayList<EvilExam>();
		anchorList = new ArrayList<Anchor>();
		
		loadGraphicsAssets();
		createGameInstance();
		loadLouie();
		setupScoreLabel();
				
		deltaDifference = 0.0;
		spawnInterval = 0;
		resetDistanceScores();
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
			louieFrames[i - 1] = new Image(StatsManager.getEquippedItem()
					.getImage() + i + ".png");
		}
		louie.setFrames(louieFrames);
		louie.setFrameDuration(0.1);
	}

	/**
	 * Creates new anchor and enemy sprites and adds them to their 
	 * respective arraylist.
	 */
	private void spawnEnemy() {

		spawnInterval++;
		if (spawnInterval >= 100) {
			Random randomSpawn = new Random();
			int random = randomSpawn.nextInt(4);

			switch (random) {
			case 1:
				int spacing = 1;
				int currentEnemy = enemyList.size();
				while (currentEnemy <= 2) {

					enemyList.add(new EvilExam(
							650 * spacing, 275));
					currentEnemy++;
					spacing++;
				}
				break;
			case 2:
				anchorList.add(new Anchor(800, 300));
				anchorList.add(new Anchor(900, 300));
				anchorList.add(new Anchor(1000, 300));
				break;
			case 3:
				int anchorSpacing = 1;
				int currentAnchor = anchorList.size();
				while (currentAnchor <= 3) {
					anchorList.add(new Anchor(770
							* anchorSpacing, 300));
					currentAnchor++;
					anchorSpacing++;
				}
				break;
			default:
				break;
			}
			spawnInterval = 0;
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
				if (e.getCode() == KeyCode.SPACE) {
					if (!input.contains(code)) {
						soundManager.playSound(
							SoundManager.Sounds.Jump);
						input.add(code);
					}
				}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent e) {
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

		gameScene = new Scene(root,
				assetLoader.getWinWidth(),
				assetLoader.getWinHeight());

		gc.drawImage(background, 0, 0);
		gc.drawImage(new Image(StatsManager.getEquippedItem()
				.getImage() + 0 + ".png"),
				32, 244, 142, 140);
		

		//countdown logic
		Image one = new Image("file:resources/Images/countdown2.png");
		Image two = new Image("file:resources/Images/countdown1.png");
		Image three = new Image("file:resources/Images/countdown0.png");
		ImageView images = new ImageView();

		soundManager.playCountDown();

		//animation for counting
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(images.imageProperty(),
								three)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(images.imageProperty(),
								two)),
				new KeyFrame(Duration.seconds(2),
						new KeyValue(images.imageProperty(),
								one)),
				new KeyFrame(Duration.seconds(3),
						new KeyValue(images.imageProperty(),
								null)));

		timeline.play();
		root.getChildren().add(images);

		timeline.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				gameRenderer();
			}
		});	
	}

	/**
	 * Renders all members of the game.
	 */
	private void gameRenderer() {
		
		soundManager.playSound(SoundManager.Sounds.Running);
		bG1 = new BackGround(0, 0);
		bG2 = new BackGround(2200, 0);
		
		initListener();

		final long startingTime = System.nanoTime();

		new AnimationTimer() {

			@Override
			public void handle(final long currentDeltaTime) {

				spawnEnemy();

				deltaDifference = 
						(currentDeltaTime - startingTime)
						/ 1000000000.0;

				gc.clearRect(0, 0, 700, assetLoader.getWinHeight());
				
				renderBackgrounds(gc);
				manageLouie(gc, deltaDifference);
				manageEnemies(gc, deltaDifference);
				manageAnchors(gc, deltaDifference);
				updateDistanceScoreText();

				for (int i = 0; i < enemyList.size(); i++) {
					if (louie.intersects(enemyList.get(i))) {
						try {
							Parent root = FXMLLoader.load(
							getClass()
						 .getResource("gameOverScreen.fxml"));
							Scene mainMenuScene 
							= new Scene(root, 600, 400);
							Main.setScene(mainMenuScene);
						} catch (IOException e) {
							e.printStackTrace();
						}
						StatsManager.setNumCoins(numCoins);
						stop();
						soundManager.stopSound();
					}
				}
			}
		}.start();
	}


	/**
	 * Renders and checks enemeies for collisions.
	 * @param gc - graphics context to render sprites
	 * @param deltaDifference - time difference between frames
	 */
	private void manageEnemies(final GraphicsContext gc,
			final double deltaDifference) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).render(gc, deltaDifference, 96, 96);
			enemyList.get(i).chargeLeft();
		}


		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getPositionX() + 96 < 0) {
				enemyList.remove(i);
				break;
			}
		}
	}

	/**
	 * Moves and checks whether the anchors have moved beyond the screen.
	 * @param gc - graphics context to render sprites
	 * @param deltaDifference - time difference between frames
	 */
	private void manageAnchors(final GraphicsContext gc, 
			final double deltaDifference) {
		for (int i = 0; i < anchorList.size(); i++) {
			anchorList.get(i).render(gc, deltaDifference, 43, 43);
			anchorList.get(i).chargeLeft();
		}

		for (int i = 0; i < anchorList.size(); i++) {
			if (anchorList.get(i).getPositionX() + 96 < 0) {
				anchorList.remove(i);
				break;
			}
		}
				
		for (int i = 0; i < anchorList.size(); i++) {
			if (anchorList.get(i).intersects(louie)) {
				numCoins++;
				updateScoreText(numCoins);
				anchorList.remove(i);
				soundManager.playSound(
						SoundManager.Sounds.CoinPickUp);
				break;
			}
		}
	}

	/**
	 * Renders louie and manages louie jumping.
	 * @param gc - graphics context to render sprites
	 * @param deltaDifference - time difference between frames
	 */
	private void manageLouie(final GraphicsContext gc,
			final double deltaDifference) {
		if (louie.onGround()) {
			louie.setCanJump();
			if (input.contains("SPACE")) {
				louie.jump();
			}
		} else {
			louie.setCantJump();
			louie.rebound();
		}

		louie.render(gc, deltaDifference);
	}

	/**
	 * Renders both backgrounds, moving them left and swapping positions.
	 * once one has moved beyond the left window border
	 * @param gc - graphics context to render sprites
	 */
	private void renderBackgrounds(final GraphicsContext gc) {
		bG1.render(gc);
		bG2.render(gc);

		bG1.moveRight();
		bG2.moveRight();

		if (bG1.getPositionX() <= -1 * 2200) {
			bG1.setPosition(bG1.getPositionX()
					+ 2200 * 2, bG1.getPositionY());
		} else if (bG2.getPositionX() <= -1 * 2200) {
			bG2.setPosition(bG2.getPositionX()
					+ 2200 * 2, bG2.getPositionY());
		}
	}
	
	/**
	 * Initializes and draws score label to screen.
	 */
	private void setupScoreLabel() {
		scoreLabel = new Label("");
		scoreLabel.setLayoutX(500);
		scoreLabel.setLayoutY(50);
		scoreLabel.setFont(new Font("Arial", 20));
		scoreLabel.setTextFill(Color.ANTIQUEWHITE);
		scoreAnchor = new Image("file:resources/Images/anchor0.png");
		
		distanceScoreLabel = new Label(distanceScore + "m");
		distanceScoreLabel.setLayoutX(512);
		distanceScoreLabel.setLayoutY(25);
		distanceScoreLabel.setFont(new Font("Arial", 20));
		distanceScoreLabel.setTextFill(Color.ANTIQUEWHITE);
		
		ImageView imageView = new ImageView(scoreAnchor);
		imageView.setFitHeight(43);
		imageView.setFitWidth(43);
		scoreLabel.setGraphic(imageView);
		
		root.getChildren().add(scoreLabel);
		root.getChildren().add(distanceScoreLabel);
	}
	
	/**
	 * Updates the score label with the current score.
	 * @param score - the number of anchors collected
	 */
	private void updateScoreText(final int score) {
		scoreLabel.setText(Integer.toString(numCoins));
	}
	
	/**
	 * Updates the distance score label with the distance traveled.
	 */
	private void updateDistanceScoreText() {
		distanceInterval++;
		if (distanceInterval % 3 == 0) {
			distanceScore++;
		}
		
		
		distanceScoreLabel.setText(Integer.toString(distanceScore) + "m");
	}

	/**
	 * Resets both score counters to 0.
	 */
	private void resetDistanceScores() {
		numCoins = 0;
		distanceScore = 0;
	}
	
	/**
	 * Returns the current gamescene to load in the main stage.
	 * @return the current game scene
	 */
	public Scene getGameScene() {
		return gameScene;
	}
	
	/**
	 * Returns the number of coins.
	 * @return - int, amount of coins
	 */
	public static int getNumCoins() {
		return numCoins;
	}
	
	/**
	 * Returns the player's distance.
	 * @return - int, distance
	 */
	public static int getDistance() {
		return distanceScore;
	}

	@Override
	public void start(final Stage gameStage) {

	}
}

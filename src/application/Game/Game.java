package application.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import application.Main.Main;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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

	/** Array list holding all key events. **/
	private ArrayList<String> input;

	/** Array list holding all spawned enemies. **/
	private ArrayList<EvilExam> enemyList;

	/** Array list holding all spawned anchors. **/
	private ArrayList<Anchor> anchorList;

	/** Difference in time between frames.**/
	private double deltaDifference = 0.0;

	/** Spawn interval between sprites.**/
	private int spawnInterval = 0;

	/** First Looping background .**/
	private BackGround bG1;

	/** Second looping background.**/
	private BackGround bG2;

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
		enemyList = new ArrayList<EvilExam>();
		anchorList = new ArrayList<Anchor>();

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
			//louieFrames[i - 1] = new Image
			//("file:resources/Images/Finished_Louie" + i + ".png");
			louieFrames[i - 1] = new Image(store.getActiveItem()
					.getImage() + i + ".png");
		}

		louie.setFrames(louieFrames);
		louie.setFrameDuration(0.1);
	}

	/**
	 * Creates new anchor and enemy sprites and adds them to their 
	 * respective arraylist
	 */
	private void spawnEnemy() {

		spawnInterval++;
		//	System.out.println(spawnInterval);
		if (spawnInterval >= 100) {
			Random randomSpawn = new Random();
			int random = randomSpawn.nextInt(4);


			switch(random) {
			case 1:
				int spacing = 1;
				int currentEnemy = enemyList.size();
				while(currentEnemy <= 2) {
					int posX = 650 * spacing;
					System.out.println(posX);
					enemyList.add(new EvilExam(650*spacing, 275));
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
				while(currentAnchor <= 3) {
					anchorList.add(new Anchor(770*anchorSpacing, 300));
					currentAnchor++;
					anchorSpacing++;
				}
				break;

			}

			spawnInterval = 0;
		}

		//System.out.print(enemyList.size());
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

		gameScene = new Scene(root,
				assetLoader.getWinWidth(),
				assetLoader.getWinHeight());

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
				new KeyFrame(Duration.ZERO,
						new KeyValue(images.imageProperty(), three)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(images.imageProperty(), two)),
				new KeyFrame(Duration.seconds(2),
						new KeyValue(images.imageProperty(), one)),
				new KeyFrame(Duration.seconds(3),
						new KeyValue(images.imageProperty(), null)));

		timeline.play();
		root.getChildren().add(images);

		//logic was moved inside action handler to wait until countdown was done
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
		bG1 = new BackGround(0,0);
		bG2 = new BackGround(2200,0);
		
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

				for(int i = 0; i < enemyList.size(); i++) {
					if (louie.intersects(enemyList.get(i))) {
						try {
							Parent root = FXMLLoader.load(
									getClass().getResource("gameOverScreen.fxml"));
							Scene mainMenuScene = new Scene(root, 600, 400);
							Main.setScene(mainMenuScene);
						} catch (IOException e) {
							e.printStackTrace();
						}
						stop();
						soundManager.stopSound();
					}
				}
			}
		}.start();
	}


	private void manageEnemies(GraphicsContext gc, double deltaDifference) {
		for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).render(gc, deltaDifference, 96, 96);
			enemyList.get(i).chargeLeft();
		}


		for(int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getPositionX() + 96 < 0) {
				enemyList.remove(i);
				break;
			}
		}
	}

	/**
	 * Moves and checks whether the anchors have moved beyond the screen
	 * @param gc - graphics context to render sprites
	 * @param deltaDifference - time difference between frames
	 */
	private void manageAnchors(GraphicsContext gc, double deltaDifference) {
		for(int i = 0; i < anchorList.size(); i++) {
			anchorList.get(i).render(gc, deltaDifference, 43, 43);
			anchorList.get(i).chargeLeft();
		}

		for(int i = 0; i < anchorList.size(); i++) {
			if (anchorList.get(i).getPositionX() + 96 < 0 || louie.intersects(anchorList.get(i))) {
				anchorList.remove(i);
				break;
			}
		}
	}

	/**
	 * Renders louie and manages louie jumping
	 * @param gc - graphics context to render sprites
	 * @param deltaDifference - time difference between frames
	 */
	private void manageLouie(GraphicsContext gc, double deltaDifference) {
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
	 * Renders both backgrounds, moving them left and swapping positions
	 * once one has moved beyond the left window border
	 * @param gc - graphics context to render sprites
	 */
	private void renderBackgrounds(GraphicsContext gc) {
		bG1.render(gc);
		bG2.render(gc);

		bG1.moveRight();
		bG2.moveRight();

		if(bG1.getPositionX() <= -1 * 2200) {
			bG1.setPosition(bG1.getPositionX() + 2200 * 2, bG1.getPositionY());
		}else if(bG2.getPositionX() <= -1 * 2200) {
			bG2.setPosition(bG2.getPositionX() + 2200 * 2, bG2.getPositionY());
		}
	}

	/**
	 * Returns the current gamescene to load in the main stage.
	 * @return the current game scene
	 */

	public Scene getGameScene() {
		return gameScene;
	}

	@Override
	public void start(final Stage gameStage) {

	}
}

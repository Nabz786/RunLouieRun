package application.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Creates a background sprite for the background image,
 * moves it left to simulate movement incrementally.
 * @author Nabeel
 */
public class BackGround {

	/** The image for the background .**/
	private Image backGround;
	
	/** Y-axis position of the sprite.**/
	private double positionY;
	
	/** X-axis position of the sprite.**/
	private double positionX;
	
	/**
	 * Constructor creates a new background image at specified
	 * x,y position.
	 * @param positionX - X coord to place background
	 * @param positionY - Y coord to place background
	 */
	public BackGround(final double positionX, final double positionY) {
		setPosition(positionX, positionY);
		setBackGround(new Image("file:resources/Images/BGF.png"));
	}
	
	/**
	 * Called from Game, renders background's position on the canvas.
	 * @param gc Graphics Context
	 */
	public void render(final GraphicsContext gc) {
		 gc.drawImage(backGround,
	        		getPositionX(), getPositionY(),
	        		2200, 400);
    }
	
	/**
	 * Decrements the background's x value, moving it right.
	 */
	public void moveRight() {
		setPosition(getPositionX() - 4, getPositionY());
	}

	/**
	 * Returns the background image.
	 * @return - Image of background
	 */
	public Image getBackGround() {
		return backGround;
	}

	/**
	 * Sets the background sprite's image.
	 * @param backGround - image to set
	 */
	public void setBackGround(final Image backGround) {
		this.backGround = backGround;
	}
	
	/**
	 * Sets the position of a sprite manually.
	 * @param x - x coordinate on screen 
	 * @param y - y coordinate on screen
	 */
	public void setPosition(final double x, final double y) {
        positionX = x;
        positionY = y;
    }
	
	/**
	 * Returns the current sprites x coordinate.
	 * @return Current sprites x coordinate
	 */
	public double getPositionX() {
		return positionX;
	}
	
	/**
	 * Returns the sprites y coordinate.
	 * @return Current sprites y coordinate
	 */
	public double getPositionY() {
		return positionY;
	}
}

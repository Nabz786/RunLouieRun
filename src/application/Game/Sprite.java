package application.Game;

import javafx.geometry.Rectangle2D;

/**********************************************************************
 * This class will represent the basic (Super class) for sprites 
 * all other sprites will inherit this class 
 * @author Nabeel Vali
 *********************************************************************/
public class Sprite {
	
	/** Height and width of the sprites image **/
	private int imageWidth, imageHeight;
	
	/** Position and speed variables of the sprite**/
	public double positionX, positionY, velocityX, velocityY;
	
	/** Whether or not the sprite is dead or alive **/
	public boolean isAlive;// this may be moved to just louie's class
	

	/**
	 * Creates a new sprite with the associated params
	 * @param imageHeight - height of the sprites image
	 * @param imageWidth - width of the sprites image
	 * @param positionX - X position of the sprite 
	 * @param positionY - Y position of the sprite
	 * @param velocityX - horizontal speed of the sprite
	 * @param velocityY - vertical speed of the sprite
	 */
	public Sprite(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX, double velocityY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		isAlive = true;
	}
	
	/**
	 * Set the vertical speed of the sprite
	 * @param velocityY - new speed of the sprite for vertical movement
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * set the horizontal speed of the sprite
	 * @param velocityX - new speed of the sprite for horizontal movement
	 */
	private void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	/**
	 * Creates a rectangle around the sprite so we can check
	 * for collision
	 * @return - new Rectangle at a specified position
	 */
	public Rectangle2D getSpriteBoundary() {
		return new Rectangle2D(positionX, positionY, imageWidth, imageHeight);
	}
	
	/**
	 * Checks if two rectangles have collided, indicating a collision
	 * between sprites
	 * @param sprite - other sprite to check if a collision have occurred
	 * @return - true or false, whether a collision occurred or not
	 */
	public boolean hasCollided(Sprite sprite) {
		return sprite.getSpriteBoundary().intersects(this.getSpriteBoundary());
	}
}
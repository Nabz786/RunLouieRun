package application.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Sprite class for louie, inherits all members from base sprite
 * and creates a new method for jumping.
 * @author Nabeel
 */
public class Louie extends BaseSprite {
	
	/** Speed at which louie begins to drop from jump.**/
	private double fallingVelocity = 0;
	
	/** Gravity factor that pulls louie back to the ground.  **/
	private float gravityForce = 0.3f;
	
	/** Speed at the instant the jump begins.**/
	private int jumpPower = -8;
	
	/** Whether or not louie is at ground level determines the value.**/
	private boolean canJump;
	
	/**
	 * Creates a louie sprite at a specifed position on the canvas.
	 * @param positionX - X Coordinate on the canvas
	 * @param positionY - Y Coordinate on the canvas
	 */
	public Louie(final double positionX, final double positionY) {
		super(positionX, positionY);
		setCanJump(true);
	}
	
	/**
	 * Called from Game, renders louie's position on the canvas.
	 * @param gc - Graphics Context
	 * @param deltaDifference - Difference in time between current/last frame
	 */
	public void render(final GraphicsContext gc,
			final double deltaDifference) {
        		super.render(gc, deltaDifference, 128, 128);
    }
	
	/** 
	 * Returns true or false depending on louie's positing on the map.
	 * @return True or false if louie is back on the ground
	 */
	public boolean onGround() {
		if (getPositionY() >= 243) {
    		return true;
    	}
    	return false;
	}
	
	/**
	 * Makes louie fall back to the ground after reaching his maximum
	 * possible height.
	 */
	public void rebound() {		 
		super.setPosition(super.getPositionX(),
				super.getPositionY() + fallingVelocity);
		fallingVelocity += gravityForce;
	}
	
	/**
	 * Makes louie jump, by inverting his falling velocity then.
	 * calling rebound to bring him back to the ground
	 */
	public void jump() {
		fallingVelocity = jumpPower;
		rebound();
	}
	
	/**
	 * Returns a rectangle boundary around the sprite.
	 * @return A new rectangle around the sprite
	 */
	public Rectangle2D getBoundary() {
		return new Rectangle2D(getPositionX(), getPositionY(), 32, 64);
	}
	
	/**
	 * Sets the value of can jump to true.
	 */
	public void setCanJump() {
		canJump = true;
	}
	
	/**
	 * Sets the vale of can jump to false.
	 */
	public void setCantJump() {
		setCanJump(false);
	}

	/** 
	 * Returns whether or not louie can jump.
	 * @return - True (able to jump), False (Unable to jump)
	 */
	public boolean canJump() {
		return canJump;
	}

	/**
	 * Sets the can jump param to whether or not louie can
	 * jump.
	 * @param canJump - True (able to jump), False (Unable to jump)
	 */
	public void setCanJump(final boolean canJump) {
		this.canJump = canJump;
	}
}

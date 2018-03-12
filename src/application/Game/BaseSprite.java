package application.Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**********************************************************************
 * Base class for all sprite, hold all the basic variables necessary
 * for sprite manipulation and animation.
 * @author Nabeel
 *********************************************************************/
public class BaseSprite {
	/** Holds all the images to animate of a sprite. **/
	private Image[] frames;
	
	/** How long to display each image.**/
	private double dispayDuration;
	
	/** Y-axis position of the sprite.**/
	private double positionY;
	
	/** X-axis position of the sprite.**/
	private double positionX;
	
	/** Default image height and width.**/
	private int defaultImgWidth, defaultImgHeight;
	
	/**
	 * Constructor for base sprite, initializes all position and
	 * movement variables.
	 * @param positionX X coordinate on screen
	 * @param positionY Y coordinate on screen
	 */
	public BaseSprite(final double positionX, final double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		defaultImgWidth = 64;
		defaultImgHeight = 64;
	}
	
	/**
	 * Called every time graphics context is called to update the sprite.
	 * @param gc - Graphics context 
	 * @param deltaDifference - Difference in time between frames
	 * @param defaultImgWidth - The default width to set an image 
	 * @param defaultImgHeight - The default height to set an image
	 */
	public void render(final GraphicsContext gc, 
			final double deltaDifference, 
			final int defaultImgWidth, 
			final int defaultImgHeight) {
        gc.drawImage(getCurrentFrame(deltaDifference),
        		positionX, positionY,
        		defaultImgWidth, defaultImgHeight);
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
	
	/**
	 * Returns a rectangle around the sprite for collision detection.
	 * @return A 2D rectangle around the sprite
	 */
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY,
				defaultImgWidth, defaultImgHeight);
	}

	/**
	 * Checks if this sprites rectangle intersects with another sprite.
	 * @param s - Sprite to check if collision occurred
	 * @return Whether or not a collision occurred
	 */
	public boolean intersects(final BaseSprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	/**
	 * Sets the duration to display each frame for the sprite animation.
	 * @param frameDuration - Duration to display each frame
	 */
	public void setFrameDuration(final double frameDuration) {
		this.dispayDuration = frameDuration;
	}
	
	/**
	 * Assigns each image for animation in a remote array to the. 
	 * sprites array
	 * @param frames - All images for animation
	 */
	public void setFrames(final Image[] frames) {
		this.frames = frames;
	}
	
	/**
	 * Returns each the index for a respective frame by incorporating.
	 * change in time within the game engine
	 * @param deltaTime - change in time between current and last frame
	 * @return Next image to draw
	 */
	public Image getCurrentFrame(final double deltaTime) {
		int index = (int) ((deltaTime 
				% (frames.length * dispayDuration))
				/ dispayDuration);
	    return frames[index];
	}
}

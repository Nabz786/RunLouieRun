package application.Game;

/**********************************************************************
 * This class controls the enemy and its characteristics for the game.
 * @author Nabeel Vali
 * @author Kehlsey Lewis
 * @author Andrew Freiman
 * @version Winter 2018
 *********************************************************************/

public class Enemy extends Sprite {

	/** Can we remove the sprite from the screen **/
	private boolean isRemovable;
	
	/**************************************************************
	 * constructor to set up an enemy sprite.
	 * @param imageHeight - height of enemy image
	 * @param imageWidth - width of enemy image
	 * @param positionX - location (X) of enemy
	 * @param positionY - location (Y) of enemy
	 * @param velocityX - how fast the enemy is moving horizontally
	 * @param velocityY - how fast the enemy is moving vertically
	 **************************************************************/
	public Enemy(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX,
			double velocityY) {
		super(imageHeight, imageWidth, positionX, positionY, velocityX, velocityY);
		
		isRemovable = false;
	}
	
	/************************************************
	 * Checks if the sprite has collided with another.
	 * @param thisSprite - other sprites in the game
	 ************************************************/
	private void checkRemovable(Sprite thisSprite) {
		if(hasCollided(thisSprite)) {
			isRemovable = true;
		}
	}
}

package application.Game;

public class Louie extends Sprite{

	
	/**
	 * Creates a new main character sprite for louie
	 * @param imageHeight - height of louie image
	 * @param imageWidth - width of louie image
	 * @param positionX - location (X) of louie
	 * @param positionY - location (Y) of louie
	 * @param velocityX - how fast the louie is moving horizontally
	 * @param velocityY - how fast the louie is moving vertically
	 */
	public Louie(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX,
			double velocityY) {
		super(imageHeight, imageWidth, positionX, positionY, velocityX, velocityY);
	}

	/**
	 * called when user clicks the spacebar to make louie jump
	 */
	private void jump() {
		positionY+= 20;
	}
}

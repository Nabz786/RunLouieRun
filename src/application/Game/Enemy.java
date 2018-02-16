package application.Game;

public class Enemy extends Sprite {

	private boolean isRemovable;
	
	public Enemy(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX,
			double velocityY) {
		super(imageHeight, imageWidth, positionX, positionY, velocityX, velocityY);
		
		isRemovable = false;
	}
	
	private void checkRemovable(Sprite thisSprite) {
		if(hasCollided(thisSprite)) {
			isRemovable = true;
		}
	}
}

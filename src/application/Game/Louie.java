package application.Game;

public class Louie extends Sprite{

	public Louie(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX,
			double velocityY) {
		super(imageHeight, imageWidth, positionX, positionY, velocityX, velocityY);
	}

	private void jump() {
		positionY+= 20;
	}
}

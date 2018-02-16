package application.Game;

import javafx.geometry.Rectangle2D;

/**********************************************************************
 * This class will represent the basic (Super class) for sprites 
 * all other sprites will inherit this class 
 * @author Nabeel Vali
 *********************************************************************/
public class Sprite {
	
	private int imageWidth, imageHeight;
	public double positionX, positionY, velocityX, velocityY;
	public boolean isAlive;
	

	public Sprite(int imageHeight, int imageWidth, double positionX, double positionY, double velocityX, double velocityY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		isAlive = true;
	}
	
	
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	private void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public Rectangle2D getSpriteBoundary() {
		return new Rectangle2D(positionX, positionY, imageWidth, imageHeight);
	}
	
	public boolean hasCollided(Sprite sprite) {
		return sprite.getSpriteBoundary().intersects(this.getSpriteBoundary());
	}
}
package application.Game;

/**********************************************************************
 * This class will represent the basic (Super class) for sprites 
 * all other sprites will inherit this class 
 * @author Nabeel Vali
 *********************************************************************/

//just basic params for now
public class Sprite {
	
	private float positionX, positionY, velocityX, velocityY;
	private boolean isAlive;
	

	public Sprite(float positionX, float positionY, float velocityX, float velocityY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		isAlive = true;
	}
	
	
	

	
}
package application.Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Louie extends BaseSprite {
	
	private double fallingVelocity = 0;
	private float gravityForce = 0.3f;
	private int jumpPower = -8;
	public boolean canJump;
	
	public Louie(double positionX, double positionY) {
		super(positionX, positionY);
	}
	
	public void render(GraphicsContext gc, double deltaDifference){
        super.render(gc, deltaDifference, 128, 128);
    }
	
	public boolean onGround(){
		if(positionY >= 243) {
    		return true;
    	}
    	return false;
	}
	
	public void rebound() {		 
		super.setPosition(super.getPositionX(), super.getPositionY() + fallingVelocity);
		fallingVelocity += gravityForce;
	}
	
	public void jump() {
		fallingVelocity = jumpPower;
		rebound();
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX,positionY,32,64);
	}

}

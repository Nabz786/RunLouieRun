package application.Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BaseSprite {
	public Image[] frames;
	public double dispayDuration;
	protected double positionY;
	protected double positionX;
	protected double velocityX;
	protected double velocityY;
	private int defaultImgWidth, defaultImgHeight;
	private Game game;
	
	public BaseSprite(final double positionX, final double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		defaultImgWidth = 64;
		defaultImgHeight = 64;
		velocityX = 0;
		velocityY = 0;
	}
	
	public void render(GraphicsContext gc, double deltaDifference, int defaultImgWidth, int defaultImgHeight) {
        gc.drawImage(getCurrentFrame(deltaDifference), positionX, positionY, defaultImgWidth, defaultImgHeight);
    }
	
	public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
	
	public void updatePosition(double deltaTime) {
        positionX += velocityX * deltaTime;
        positionY += velocityY * deltaTime;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public double getPositionY() {
		return positionY;
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX,positionY,defaultImgWidth,defaultImgHeight);
	}

	public boolean intersects(BaseSprite s) {
		return s.getBoundary().intersects( this.getBoundary() );
	}
	
	public Image getCurrentFrame(double deltaTime) {
		int index = (int) ((deltaTime % (frames.length * dispayDuration)) / dispayDuration);
	    return frames[index];
	}
}

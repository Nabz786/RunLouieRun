package application.Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class EvilExam extends BaseSprite {

	private float chargePower = 2.9f;
	
	public EvilExam(double positionX, double positionY) {
		super(positionX, positionY);
		initAnimation();
	}
	
	public void chargeLeft() {
		super.setPosition(super.getPositionX() - chargePower, super.getPositionY());
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX,positionY,32,64);
	}
	
	private void initAnimation() {
		Image[] enemyFrames = new Image[2];
		for(int i = 0; i < 2; i++)
			enemyFrames[i] = new Image("file:///C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Images/Evil_Exam" + i + ".png");
		this.frames = enemyFrames;
		this.dispayDuration = 0.100;
	}
}

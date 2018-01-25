package application.Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

/********************************************************************** 
 * This class will hold all the relevant material related to the game
 * and game states 
 *
 *********************************************************************/
public class Game extends Application {
	
	
	public Game() {
		timer.start();
	}
	
	AnimationTimer timer = new AnimationTimer() {
		
		@Override
		public void handle(long timerTick) {
			
		}
	};
	
	
	
	@Override
	public void start(Stage arg0) throws Exception {
	}
}	


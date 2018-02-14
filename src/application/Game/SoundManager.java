package application.Game;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**********************************************************************
 * Sound manager class for simple sound management throughout the game
 * @author Nabeel
 *********************************************************************/
public class SoundManager {

	/** Media player to play audio files **/
	private MediaPlayer mediaPlayer;
	
	/** The media played in the media player**/
	private Media currentSound;
	
	/** Various sound files for our game **/
	private String startMusicFile = "C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Sounds/MainThemeFinal.wav";
	private String runningMusic = "C:/Users/Nabeel/eclipse-workspace/RunLouieRun/src/application/Resources/Sounds/RunningTheme.wav";
	
	/****************************************************************** 
	 * Default constructor sets the current playing sound to null
	 *****************************************************************/
	public SoundManager() {
		currentSound = null;
	}
	
	/******************************************************************
	 *Types to represent the various sounds in our game
	 *****************************************************************/
	public enum Sounds{
		MainMenu,
		Running,
		Jump
	}
	
	/******************************************************************
	 * This method takes in a sound(type) as input then plays the 
	 * corresponding audio file 
	 * @param sound - the sound to play
	 *****************************************************************/
	public void playSound(Sounds sound) {
		switch(sound) {
			case MainMenu:
				currentSound = new Media(new File(startMusicFile).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.play();
				break;
			case Jump:
				break;
			case Running:
				clearCurrentSound();
				currentSound = new Media(new File(runningMusic).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.play();
				break;
		default:
			break;
		}
	}
	
	/******************************************************************
	 * Clears the sound that is being played
	 *****************************************************************/
	private void clearCurrentSound() {
		currentSound = null;
	}
	
	/******************************************************************
	 * Stops the music
	 *****************************************************************/
	public void stopSound() {
		mediaPlayer.stop();
	}

}

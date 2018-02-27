package application.Game;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Sound manager class for simple sound management throughout the game.
 * @author Nabeel Vali
 * @author Kehlsey Lewis
 * @author Andrew Freiman
 * @version Winter 2018
 */
public class SoundManager {

	/** Media player to play audio files. **/
	private MediaPlayer mediaPlayer;
	
	/** The media played in the media player .**/
	private Media currentSound;
	
	/** Main menu music. **/
	private String startMusicFile = "resources/Sounds/MainThemeFinal.wav";
	
	/** Running music.**/
	private String runningMusic = "resources/Sounds/RunningTheme.wav";
	
	/** Jumping sound effect. **/
	private String jumpSoundFile = "resources/Sounds/Jump.wav";
	
	/** Death sound effect.**/
	private String deathSoundFile = "resources/Sounds/DeathSound1.wav";

	/**
	 * Sets the current sound playing to null.
	 */
	public SoundManager() {
		currentSound = null;
	}
	
	/**
	 * Creates enumerated types to specify each unique sound.
	 * @author Nabeel
	 */
	public enum Sounds {
		MainMenu, 
		Running,
		Jump,
		Death
	}
	
	/**
	 * Takes in a sound(type) as input then plays the 
	 * corresponding audio file. 
	 * @param sound - the sound to play
	 */
	public void playSound(final Sounds sound) {
		switch (sound) {
			case MainMenu:
				currentSound = new Media(new File(startMusicFile
						).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				mediaPlayer.play();
				break;
			case Jump:
				currentSound = new Media(new File(jumpSoundFile
						).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.play();
				break;
			case Running:
				clearCurrentSound();
				currentSound = new Media(new File(
						runningMusic).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				mediaPlayer.play();
				break;
			case Death:
				clearCurrentSound();
				currentSound = new Media(new File(
						deathSoundFile).toURI().toString());
				mediaPlayer = new MediaPlayer(currentSound);
				mediaPlayer.play();
				break;
		default:
			break;
		}
	}
	
	/**
	 * Clears the current sound that is playing.
	 */
	private void clearCurrentSound() {
		currentSound = null;
	}

	/**
	 * Stops the music.
	 */
	public void stopSound() {
		mediaPlayer.stop();
	}

}

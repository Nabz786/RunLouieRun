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
	private MediaPlayer soundEffectPlayer;

	/** Media player to play game music.**/
	private MediaPlayer musicPlayer;
	
	/** The media played in the media player .**/
	private Media currentSoundEffect;
	
	/** Continuous running music.**/
	private Media currentMusic;
	
	/** Main menu music. **/
	private String startMusicFile = "resources/Sounds/MainThemeFinal.wav";
	
	/** Running music.**/
	private String runningMusic = "resources/Sounds/RunningTheme.wav";
	
	/** Jumping sound effect. **/
	private String jumpSoundFile = "resources/Sounds/Jump.wav";
	
	/** Death sound effect.**/
	private String deathSoundFile = "resources/Sounds/DeathSound1.wav";
	
	/** Countdown Sound. **/
	private String countDownFile = "resources/Sounds/CountDown_Beep.wav";

	/**
	 * Sets the current sound playing to null.
	 */
	public SoundManager() {
		currentSoundEffect = null;
		currentMusic = null;
	}
	
	/**
	 * Creates enumerated types to specify each unique sound.
	 */
	public enum Sounds {
		/** Main menu sound effect.**/
		MainMenu, 
		
		/** Running sound effect.**/
		Running,
		
		/** Jumping sound effect.**/
		Jump,
		
		/** Death sound effect.**/
		Death,
		
		/** Countdown sound effect.**/
		CountDown
	}
	
	/**
	 * Takes in a sound(type) as input then plays the 
	 * corresponding audio file. 
	 * @param sound - the sound to play
	 */
	public void playSound(final Sounds sound) {
		switch (sound) {
			case MainMenu:
				currentMusic = new Media(new File(startMusicFile
						).toURI().toString());
				musicPlayer = new MediaPlayer(currentMusic);
				musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				musicPlayer.play();
				break;
			case Running:
				currentMusic = new Media(new File(runningMusic
						).toURI().toString());
				musicPlayer = new MediaPlayer(currentMusic);
				musicPlayer.play();
				break;
			case Jump:
				currentSoundEffect = new Media(new File(jumpSoundFile
						).toURI().toString());
				soundEffectPlayer = new MediaPlayer(currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case Death:
				clearCurrentSound();
				currentSoundEffect = new Media(new File(
						deathSoundFile).toURI().toString());
				soundEffectPlayer = new MediaPlayer(currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case CountDown:
				clearCurrentSound();
				currentSoundEffect = new Media(new File(
						countDownFile).toURI().toString());
				soundEffectPlayer = new MediaPlayer(currentSoundEffect);
				soundEffectPlayer.play();
				break;
		default:
			break;
		}
	}
	
	/**
	 * Clears the current sound that is playing.
	 */
	private void clearCurrentSound() {
		currentSoundEffect = null;
	}

	/**
	 * Stops the music.
	 */
	public void stopSound() {
		if (musicPlayer != null) {
			musicPlayer.stop();
		}
	}
}

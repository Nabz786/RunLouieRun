package application.game;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
	
	/** Coin pickup sound.**/
	private String coinPickUp = "resources/Sounds/Coin_PickUp.wav";
	
	/** Store button sound. **/
	private String buttonPress = "resources/Sounds/rightleftbuttonclick.wav";
	
	/** Buy item sound. **/
	private String buyitem = "resources/Sounds/boughtItem.wav";
	
	/** Equip item sound. **/
	private String equipItem = "resources/Sounds/equippedItem.wav";

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
		CountDown,
		
		/** Coin Pickup sound effect.**/
		CoinPickUp,
		
		/** Buy item sound effect.**/
		BuyItem,
		
		/** Equip item sound effect.**/
		EquipItem,
		
		/** Store arrows sound effect.**/
		StoreArrow
		
	}
	
	/**
	 * Takes in a sound(type) as input then plays the 
	 * corresponding audio file. 
	 * @param sound the sound to play
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
				musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				musicPlayer.play();
				break;
			case Jump:
				currentSoundEffect = new Media(new File(jumpSoundFile
						).toURI().toString());
				soundEffectPlayer = new MediaPlayer(
						currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case Death:
				clearCurrentSound();
				currentSoundEffect = new Media(new File(
						deathSoundFile).toURI().toString());
				soundEffectPlayer = new MediaPlayer(
						currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case CountDown:
				clearCurrentSound();
				currentSoundEffect = new Media(new File(
						countDownFile).toURI().toString());
				soundEffectPlayer = new MediaPlayer(
						currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case CoinPickUp:
				clearCurrentSound();
				currentSoundEffect = new Media(new File(
						coinPickUp).toURI().toString());
				soundEffectPlayer = new MediaPlayer(
						currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case BuyItem:
				clearCurrentSound();
				currentSoundEffect = new Media(
						new File(buyitem).toURI().toString());
				soundEffectPlayer = 
						new MediaPlayer(currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case EquipItem:
				clearCurrentSound();
				currentSoundEffect = new Media(
						new File(equipItem)
						.toURI().toString());
				soundEffectPlayer = 
						new MediaPlayer(currentSoundEffect);
				soundEffectPlayer.play();
				break;
			case StoreArrow:
				clearCurrentSound();
				currentSoundEffect = new Media(
						new File(buttonPress)
						.toURI().toString());
				soundEffectPlayer = 
						new MediaPlayer(currentSoundEffect);
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
	 * Allows program to play countdown sound effect.
	 */
	public void playCountDown() {
		currentSoundEffect = new Media(new File(
				"resources/Sounds/CountDown_Beep.wav")
				.toURI().toString());
		MediaPlayer countingPlayer = new MediaPlayer(currentSoundEffect);
		
		countingPlayer.setStopTime(Duration.millis(850));
        countingPlayer.cycleCountProperty().set(3);
		countingPlayer.play();
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

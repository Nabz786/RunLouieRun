package application.Game;

/**
 * Holds and loads all game assets.
 * @author Nabeel
 */
public class AssetLoader {
	
	/** Width of program window.**/
	private static final int WIDTH = 600;
	
	/** Height of program window.**/
	private static final int HEIGHT = 400;
	
	/**
	 * Default constructor, calls loadAllAssets().
	 */
	public AssetLoader() {
		loadAllAssets();
	}
	
	/**
	 * Loads all assets for the entire game.
	 */
	private void loadAllAssets() {
		
	}

	public int getWinWidth() {
		return WIDTH;
	}
	
	public int getWinHeight() {
		return HEIGHT;
	}
}

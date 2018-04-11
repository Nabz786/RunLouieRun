package application.Game;

/**
 * Holds and loads all game assets.
 * @author Nabeel
 */
public class AssetLoader {
	
	/** Width of program window.**/
	private static final int WIDTH = 700;
	
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

	/**
	 * Returns width of program window.
	 * @return width of program window
	 */
	public int getWinWidth() {
		return WIDTH;
	}
	
	/**
	 * Returns height of program window.
	 * @return height, height of program window
	 */
	public int getWinHeight() {
		return HEIGHT;
	}
}

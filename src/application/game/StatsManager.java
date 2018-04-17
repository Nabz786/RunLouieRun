package application.game;

/**
 * Stats manager to manage in game statistics and coins.
 * @author Nabeel
 *
 */
public final class StatsManager {

	/** Number of anchors collected from game .**/
	private static int numAnchors;
	
	/**
	 * Default constructor sets num anchors to 0.
	 */
	private StatsManager() {
		numAnchors = 0;
	}
	
	/**
	 * Increments the number of collected anchors when the game
	 * has ended.
	 * @param nAnchors - anchors collected
	 */
	public static void setNumCoins(final int nAnchors) {
		numAnchors += nAnchors;
	}
	
	/**
	 * Returns the total number of anchors collected.
	 * @return int - number of collected anchors
	 */
	public static int getNumCoins() {
		return numAnchors;
	}
}

package application.game;

/**
 * Stats manager to manage in game statistics and coins.
 * @author Nabeel
 *
 */
public final class StatsManager {

	/** Number of anchors collected from game .**/
	private static int numAnchors = 500;
	
	private static ShopItem shopItem;
	
	/**
	 * Default constructor sets num anchors to 0.
	 */
	private StatsManager() {
		
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
	
	public static void setShopItem(ShopItem item) {
		System.out.println(item.getName());
		shopItem = null;
		shopItem = item;
		System.out.println(shopItem.getName());
				
	}
	
	public static ShopItem getShopItem() {
		if(shopItem == null) {
			new Shop().loadPlayerData();
		}
		return shopItem;
	}
}

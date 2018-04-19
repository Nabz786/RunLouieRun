package application.game;

/**
 * Stats manager to manage in game statistics and coins.
 * @author Nabeel
 *
 */
public final class StatsManager {

	/** Number of anchors collected from game .**/
	private static int numAnchors = 0;
	
	/** Current Shopitem equipped.**/
	private static ShopItem shopItem;
	
	/**
	 * Default constructor.
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
	 * If the user purchased an item, we will subtract the cost of
	 * the item from the users to total funds.
	 * @param withdrawnCoins - Amount to remove from users funds
	 */
	public static void storeTransaction(final int withdrawnCoins) {
		numAnchors = numAnchors - withdrawnCoins;
	}
	
	/**
	 * Returns the total number of anchors collected.
	 * @return int - number of collected anchors
	 */
	public static int getNumCoins() {
		return numAnchors;
	}
	
	/**
	 * Sets a new active item for the users equipped item.
	 * @param item - item that is to be equipped.
	 */
	public static void setShopItem(ShopItem item) {
		shopItem = null;
		shopItem = item;
	}
	
	/**
	 * Returns the current active item to whoever requested it.
	 * @return Shop Item to be equipped
	 */
	public static ShopItem getShopItem() {
		if(shopItem == null) {
			new Shop().loadPlayerData();
		}
		return shopItem;
	}
}

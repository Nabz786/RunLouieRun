package application.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stats manager to manage in game statistics and coins.
 * @author Nabeel
 *
 */
public final class StatsManager {

	/** Number of anchors collected from game .**/
	private static int numAnchors = 100;

	/** All the skins available for purchase .**/
	private static ShopItem defaultLouie, greenLouie, pinkLouie, rainbowLouie, 
	goldenLouie, kingLouie, patrioticLouie;

	/** The current users equipped item.**/
	private static ShopItem equippedItem;

	/** Static list to hold all purchasable items.**/
	private static final List<ShopItem> shopItems = new ArrayList<ShopItem>();

	static {
		rainbowLouie = new ShopItem("Rainbow Louie", 50,
				"file:resources/Images/rainbowLouie");
		goldenLouie = new ShopItem("Golden Louie", 50,
				"file:resources/Images/gold_");
		greenLouie = new ShopItem("Green Louie", 20,
				"file:resources/Images/green_");
		pinkLouie = new ShopItem("Pink Louie", 20,
				"file:resources/Images/pink_");
		patrioticLouie = new ShopItem("Patriotic Louie", 100,
				"file:resources/Images/patriotic_");
		kingLouie = new ShopItem("King Louie", 100,
				"file:resources/Images/kingLouie_");
		defaultLouie = new ShopItem("Default Louie", 0,
				"file:resources/Images/defaultLouie_");

		shopItems.addAll(Arrays.asList(rainbowLouie, goldenLouie, 
				greenLouie, pinkLouie, patrioticLouie, kingLouie,
				defaultLouie));

		setAllNotBoughtAvailble();
		defaultLouie.setEquipped(true);
		equippedItem = defaultLouie;
	}

	/**
	 * Returns the current equipped item.
	 * @return Item that is equipped
	 */
	public static ShopItem getEquippedItem() {
		for (int i = 0; i < shopItems.size(); i++) {
			if (shopItems.get(i).isEquipped()) {
				equippedItem = shopItems.get(i);
			}
		}
		return equippedItem;
	}

	/**
	 * Sets all items to their default not purchased
	 * unavailable state.
	 */
	private static void setAllNotBoughtAvailble() {
		for (int i = 0; i < shopItems.size(); i++) {
			shopItems.get(i).setPurchased(false);
			shopItems.get(i).setEquipped(false);
		}
	}

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
	public static void setShopItem(final ShopItem item) {
		equippedItem = null;
		equippedItem = item;
		System.out.println(equippedItem.getName());
	}

	/**
	 * Returns the list of all purchasable items.
	 * @return List of all purchasable items
	 */
	public static List<ShopItem> getShopItems() {
		return shopItems;
	}

	/**
	 * If pricing condition is met sets item's purchased state to true.
	 * @param item to be purchased
	 */
	public static void buyItem(final ShopItem item) {
		for (int i = 0; i < shopItems.size(); i++) {
			if (shopItems.get(i).getName().equals(item.getName()) 
					&& !shopItems.get(i).isEquipped() 
					&& !shopItems.get(i).isPurchased()) {
				storeTransaction(item.getPrice());
				System.out.println("herehere");
				shopItems.get(i).setPurchased(true);
			}
		}
	}

	/**
	 * Equips a purchasable item if it has been purchased and is 
	 * already not equipped.
	 * @param item to equip
	 */
	public static void equipItem(final ShopItem item) {

		System.out.println("This is the equippedItem: " + item.getName());
		for (int i = 0; i < shopItems.size(); i++) {
			if (shopItems.get(i).getName().equals(item.getName())
					&& shopItems.get(i).isPurchased() 
					&& !shopItems.get(i).isEquipped()) {
				setAllItemsNotEquipped();
				setShopItem(shopItems.get(i));
				shopItems.get(i).setEquipped(true);
			}
		}
	}

	/** 
	 * Sets all purchasable item's equipped state to false.
	 */
	private static void setAllItemsNotEquipped() {
		for (int i = 0; i < shopItems.size(); i++) {
			shopItems.get(i).setEquipped(false);
		}
	}
}

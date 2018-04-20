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
	private static final List<ShopItem> SHOP_ITEMS = new ArrayList<ShopItem>();

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

		SHOP_ITEMS.addAll(Arrays.asList(rainbowLouie, goldenLouie, 
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
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			if (SHOP_ITEMS.get(i).isEquipped()) {
				equippedItem = SHOP_ITEMS.get(i);
			}
		}
		return equippedItem;
	}

	/**
	 * Sets all items to their default not purchased
	 * unavailable state.
	 */
	private static void setAllNotBoughtAvailble() {
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			SHOP_ITEMS.get(i).setPurchased(false);
			SHOP_ITEMS.get(i).setEquipped(false);
		}
	}

	/**
	 * Increments the number of collected anchors when the game
	 * has ended.
	 * @param nAnchors anchors collected
	 */
	public static void setNumCoins(final int nAnchors) {
		numAnchors += nAnchors;
	}

	/**
	 * If the user purchased an item, we will subtract the cost of
	 * the item from the users to total funds.
	 * @param withdrawnCoins Amount to remove from users funds
	 */
	public static void storeTransaction(final int withdrawnCoins) {
		numAnchors = numAnchors - withdrawnCoins;
	}

	/**
	 * Returns the total number of anchors collected.
	 * @return int number of collected anchors
	 */
	public static int getNumCoins() {
		return numAnchors;
	}

	/**
	 * Sets a new active item for the users equipped item.
	 * @param item item that is to be equipped.
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
		return SHOP_ITEMS;
	}

	/**
	 * If pricing condition is met sets item's purchased state to true.
	 * @param item to be purchased
	 */
	public static void buyItem(final ShopItem item) {
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			if (SHOP_ITEMS.get(i).getName().equals(item.getName()) 
					&& !SHOP_ITEMS.get(i).isEquipped() 
					&& !SHOP_ITEMS.get(i).isPurchased()) {
				storeTransaction(item.getPrice());
				SHOP_ITEMS.get(i).setPurchased(true);
				equipItem(item);
			}
		}
	}

	/**
	 * Equips a purchasable item if it has been purchased and is 
	 * already not equipped.
	 * @param item to equip
	 */
	public static void equipItem(final ShopItem item) {
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			if (SHOP_ITEMS.get(i).getName().equals(item.getName())
					&& SHOP_ITEMS.get(i).isPurchased() 
					&& !SHOP_ITEMS.get(i).isEquipped()) {
				setAllItemsNotEquipped();
				setShopItem(SHOP_ITEMS.get(i));
				SHOP_ITEMS.get(i).setEquipped(true);
			}
		}
	}

	/**
	 * Resets game by changing score to zero, and setting all
	 * items to not purchased or equipped.
	 */
	public static void resetGame() {
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			SHOP_ITEMS.get(i).setEquipped(false);
			SHOP_ITEMS.get(i).setPurchased(false);
			StatsManager.setNumCoins(0);
			StatsManager.setShopItem(defaultLouie);
		}
	}

	/** 
	 * Sets all purchasable item's equipped state to false.
	 */
	private static void setAllItemsNotEquipped() {
		for (int i = 0; i < SHOP_ITEMS.size(); i++) {
			SHOP_ITEMS.get(i).setEquipped(false);
		}
	}
}

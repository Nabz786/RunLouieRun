package application.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.ImageView;

/**
 * Main shop class, manages all shop transactions.
 * 
 * @author Andrew Freiman
 * @author Nabeel Vali
 */
public class Shop {
	
	/** List to hold all unlockable items.**/
	private List<ShopItem> shopItems = new ArrayList<ShopItem>();
	
	/** Current item user has equipped.**/
	private ShopItem equippedItem;
	
	/** List of all unlockable items.**/
	private ShopItem defaultLouie, greenLouie, pinkLouie, rainbowLouie, 
	goldenLouie, kingLouie, patrioticLouie;
		
	/**
	 * Creates a new shop and instantiates images.
	 */
	public Shop() { 
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
		defaultLouie.setAvailable(false);
	}
	
	/**
	 * Allows user to buy an item.
	 * @param item - item to buy
	 */
	public void buyItem(final ShopItem item) {
		int price = item.getPrice();
		if (price > StatsManager.getNumCoins()) 
			System.out.println("Insufficient Funds!");
		else {
			StatsManager.storeTransaction(price);
			item.setAvailable(false);
			StatsManager.setShopItem(item);
		}
	}
		
	/**
	 * Returns the current equipped item.
	 * @return - item that is equipped
	 */
	public ShopItem getActiveItem() {
		return StatsManager.getShopItem();
	}
	
	/**
	 * Loads a players unlocked items.
	 */
	public void loadPlayerData() {
		StatsManager.setShopItem(defaultLouie); 
	}
	
	/**
	 * Returns an image view of all the items.
	 * @return Imageview - all store items
	 */
	public List<ShopItem> getShopItems() {
		return shopItems;
	}
	
	/**
	 * Sets the active/equipped item.
	 */
	public void setActiveItem(ShopItem item) {
		StatsManager.setShopItem(item);
	}
	
	
}

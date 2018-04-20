package application.game;

/**
 * Shop item class creates and adds attributes to shop items.
 * 
 * @author Andrew Freiman
 * @author Nabeel Vali
 * @author Kehlsey Lewis
 */
public class ShopItem {
	
	/** Name of shop item.**/
	private String name;
	
	/** price of shop item.**/
	private int price;
	
	/** whether the item has been sold.**/
	private boolean purchased, equipped;
	
	/** string of image url for item image .**/
	private String pictureFile;
	
	/**
	 * Creates a new shop item with specific attributes.
	 * @param item Name of item
	 * @param pri Price of item
	 * @param louie specified skin
	 */
	public ShopItem(final String item, final int pri, final String louie) {
		name = item;
		price = pri;
		pictureFile = louie;
		purchased = false;
		equipped = false;
	}
	
	/**
	 * Returns image url for a skin.
	 * @return image url for a skin
	 */
	public String getImage() {
		return pictureFile;
	}
	
	/**
	 * Returns price of item.
	 * @return price of item
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Returns name of item to purchase.
	 * @return name of purchased item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns whether or not an item is available to buy.
	 * @return can an item be purchased (yes or no)
	 */
	public boolean isPurchased() {
		return purchased;
	}
	
	/**
	 * Sets an items purchased state to true.
	 * @param purchased item has been bought (true)
	 */
	public void setPurchased(final boolean purchased) {
		this.purchased = purchased;
	}
	
	/** 
	 * Returns a boolean value based on whether this item has been 
	 * equipped.
	 * @return If this item has been equipped
	 */
	public boolean isEquipped() {
		return equipped;
	}
	
	/**
	 * Sets an items equipped to state to a specified state.
	 * @param equipped Value used to update the items equipped state.
	 */
	public void setEquipped(final boolean equipped) {
		this.equipped = equipped;
	}
	
	/**
	 * Sets price of an item. 
	 * @param cost price to set
	 */
	public void setPrice(final int cost) {
		this.price = cost;
	}
	
	/**
	 * Renames an item.
	 * @param rename new item name
	 */
	public void rename(final String rename) {
		this.name = rename;
	}
}

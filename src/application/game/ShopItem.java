package application.Game;

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
	private boolean sold;
	
	/** string of image url for item image .**/
	private String pictureFile;
	
	/**
	 * Creates a new shop item.
	 */
	public ShopItem() {
		new ShopItem(null, 0, null);
	}
	
	/**
	 * Creates a new shop item with specific attributes.
	 * @param item - Name of item
	 * @param pri - Price of item
	 * @param louie - specified skin
	 */
	public ShopItem(final String item, final int pri, final String louie) {
		name = item;
		price = pri;
		pictureFile = louie;
		sold = false;
	}
	
	/**
	 * Returns image url for a skin.
	 * @return - image url for a skin
	 */
	public String getImage() {
		return pictureFile;
	}
	
	/**
	 * Returns price of item.
	 * @return - price of item
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Returns name of item to purchase.
	 * @return - name of purchased item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns whether or not an item is available to buy.
	 * @return - can an item be purchased (yes or no)
	 */
	public boolean isAvailable() {
		return sold;
	}
	
	/**
	 * Sets an items purchased state to true.
	 * @param bought - item has been bought (true)
	 */
	public void setAvailable(final boolean bought) {
		this.sold = bought;
	}
	
	/**
	 * Sets price of an item. 
	 * @param cost -  price to set
	 */
	public void setPrice(final int cost) {
		this.price = cost;
	}
	
	/**
	 * Renames an item.
	 * @param rename - new item name
	 */
	public void rename(final String rename) {
		this.name = rename;
	}
}

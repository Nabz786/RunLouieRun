package application.Game;

import java.awt.Image;

public class ShopItem {
	private String name;
	private int price;
	private boolean sold;
	private String pictureFile;
	
	public ShopItem() {
		new ShopItem(null,0,null);
	}
	
	public ShopItem(String item,int pri, String louie) {
		name = item;
		price = pri;
		pictureFile = louie;
		sold = false;
	}
	
	public String getImage() {
		return pictureFile;
	}
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAvailable() {
		return sold;
	}
	
	public void setAvailable(boolean bought) {
		this.sold = bought;
	}
	
	public void setPrice(int cost) {
		this.price = cost;
	}
	
	public void rename(String rename) {
		this.name = rename;
	}
	
	
	
}

package application.Game;

import java.util.*;

public class Shop {
	private int funds;
	private List<ShopItem> shopitems = new ArrayList<ShopItem>();
	private ShopItem equippedItem;
	private ShopItem Default_Louie, Green_Louie, Pink_Louie, Rainbow_Louie, Golden_Louie, King_Louie, Patriotic_Louie;
	public Shop() { 
		funds = 0;
		Rainbow_Louie = new ShopItem("Rainbow Louie", 50, "file:resources/Images/rainbowLouie");
		Golden_Louie = new ShopItem("Golden Louie",50,"file:resources/Images/gold_");
		Green_Louie = new ShopItem("Green Louie",20,"file:resources/Images/green_");
		Pink_Louie = new ShopItem("Pink Louie",20,"file:resources/Images/pink_");
		Patriotic_Louie = new ShopItem("Patriotic Louie",100,"file:resources/Images/patriotic_");
		King_Louie = new ShopItem("King Louie",100,"file:resources/Images/kingLouie_");
		Default_Louie = new ShopItem("Default Louie",0,"file:resources/Images/Finished_Louie");
		shopitems.addAll(Arrays.asList(Rainbow_Louie,Golden_Louie,Green_Louie,Pink_Louie,Patriotic_Louie,Default_Louie));
		equippedItem = Default_Louie;
	}
	
	public void BuyItem(ShopItem item) {
		int price = item.getPrice();
		if(price > funds) {
			System.out.println("Insufficient Funds!");
		}else {
			funds = funds - price;
			item.setAvailable(false);
			equippedItem = item;
		}
	}
	
	public int getFunds() {
		return funds;
	}
	
	public void setFunds(int f) {
		funds = f;
	}
	
	public ShopItem getActiveItem() {
		return equippedItem;
	}
	
}

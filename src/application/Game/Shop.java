package application.Game;

import java.util.*;

public class Shop {
	private int funds;
	private List<String> unbought_items = new ArrayList<String>();
	private List<String> bought_items = new ArrayList<String>();
	private List<String> all_items = new ArrayList<String>();
	public Shop() {
		funds = 0;
		unbought_items.add("Rainbow_Louie");
		all_items.add("Rainbow_Louie");
	}
	
	public void BuyItem(String item,int price) {
		unbought_items.remove(item);
		bought_items.add(item);
		if(price > funds) {
			//error
		}else {
			funds = funds - price;
		}
		
	}
	
	public int getFunds() {
		return funds;
	}
	
}

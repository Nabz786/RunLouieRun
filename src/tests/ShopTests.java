package tests;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import application.game.ShopItem;
import application.game.StatsManager;
import application.main.Main;

public class ShopTests extends GameTestsBase {
	
	@Test
	public void verifyAllComponents() {
		clickOn("#storeButton");
		verifyThat("#buyButton", NodeMatchers.hasChild("Buy"));
		verifyThat("#equipButton", NodeMatchers.hasChild("Equip"));
		verifyThat("#menuButton", NodeMatchers.hasChild("Main Menu"));
		verifyThat("#rightButton", NodeMatchers.hasChild("right"));
		verifyThat("#leftButton", NodeMatchers.hasChild("left"));
		verifyThat("#playerCoinsLabel", NodeMatchers.hasChild("You Have: " + StatsManager.getNumCoins()));
		StatsManager.resetGame();
	}
	
	@Test
	public void verifyBuyWorks() {
		clickOn("#storeButton");
		StatsManager.setNumCoins(1000);
		clickOn("#buyButton");
		verifyThat("#playerCoinsLabel", NodeMatchers.hasChild("You Have: " + StatsManager.getNumCoins()));
		assertTrue(StatsManager.getNumCoins()!=1000);
		StatsManager.resetGame();
		
		}
	
	@Test
	public void verifyItemisEquippedWhenBought() {
		clickOn("#storeButton");
		ShopItem test1 = StatsManager.getEquippedItem();
		StatsManager.setNumCoins(1000);
		clickOn("#buyButton");
		ShopItem test2 = StatsManager.getEquippedItem();
		verifyThat("#playerCoinsLabel", NodeMatchers.hasChild("You Have: " + StatsManager.getNumCoins()));
		assertTrue(!test1.equals(test2));
		StatsManager.resetGame();
	}
	
	@Test
	public void verifyEquipWorksWhenMultipleItemsPurchased() {
		ShopItem test1 = StatsManager.getEquippedItem();
		StatsManager.setNumCoins(1000);
		clickOn("#storeButton");
		clickOn("#buyButton");
		ShopItem test2 = StatsManager.getEquippedItem();
		assertTrue(!test1.equals(test2));
		clickOn("#rightButton");
		clickOn("#buyButton");
		ShopItem test3 = StatsManager.getEquippedItem();
		assertTrue(!test3.equals(test2));
		clickOn("#leftButton");
		clickOn("#equipButton");
		assertTrue(StatsManager.getEquippedItem().equals(test2));
		StatsManager.resetGame();
	}
	
	@Test
	public void verifyAllItemsCanBePurchased() {
		StatsManager.setNumCoins(1000);
		clickOn("#storeButton");
		for(int i = 0; i < StatsManager.getShopItems().size();i++) {
			clickOn("#rightButton");
			clickOn("#buyButton");
		}
		assertTrue(StatsManager.getNumCoins()==660);
		StatsManager.resetGame();
	}
	


}

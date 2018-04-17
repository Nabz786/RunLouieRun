package application.main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.game.AssetLoader;
import application.game.Shop;
import application.game.ShopItem;
import application.game.SoundManager;
import application.game.StatsManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the fx:controller class which links the scene builder members from
 * the laker_store.fxml file to code within this class.
 *
 * @author Kehlsey Lewis
 * @author Nabeel Vali
 * @author Andrew Freiman
 * @version Winter 2018
 */

public class StoreMenuController {

	/** menu button .**/
	@FXML
	private Button menuButton;

	/** left button.**/
	@FXML
	private Button leftButton;

	/** right button.**/
	@FXML
	private Button rightButton;

	/** buy button.**/
	@FXML
	private Button buyButton;

	/** equip button.**/
	@FXML
	private Button equipButton;

	@FXML
	private ImageView itemView;
	
	@FXML
	private Label costLabel;
	
	@FXML
	private Label playerCoinsLabel;

	/** Sound manager to manage the start menu sound. **/
	private SoundManager soundManager;

	/** Asset Manager.**/
	private AssetLoader assetLoader;
	
	/** main link to shop class.**/
	private Shop shop;

	/** List to hold all shop item images.**/
	private List<ShopItem> shopItems;

	/** Iterator to traverse shop items.**/
	private int iterator;


	/**
	 * This method is called after all @FXML annotated members have
	 * been injected. 
	 */
	@FXML
	private void initialize() {	

		//start playing music
		soundManager = new SoundManager();
		assetLoader = new AssetLoader();
		soundManager.playSound(SoundManager.Sounds.MainMenu);
		shop = new Shop();
		shopItems = new ArrayList<ShopItem>();
		initShopItemList();
		iterator = 0;
		playerCoinsLabel.setText("You Have: " + StatsManager.getNumCoins());
		

		//back button click event returns back to main menu
		menuButton.setOnAction(e -> {
			try {

				Parent root = FXMLLoader.load(
						getClass().getResource(
								"MainMenuStyle.fxml"));
				Scene mainMenuScene = new Scene(
						root, assetLoader.getWinWidth(),
						assetLoader.getWinHeight());

				Main.setScene(mainMenuScene);

				soundManager.stopSound();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	@FXML
	private void leftItem() {
		String imgUrl = getItemLeft();
		Image whichLouie = new Image(imgUrl + "1.png");
		itemView.setImage(whichLouie);

	}


	@FXML
	private void rightItem() {
		System.out.println("Right");
		//					String imgUrl = getItemRight();
		//					System.out.println(imgUrl);
		//					System.out.println(imgUrl);
		//					Image pleaseWork = new Image(imgUrl + "0.png");
		//					itemView.setImage(pleaseWork);

	}

	@FXML
	private void equipItem() {
		System.out.println("Equip");
	}

	@FXML
	private void buyItem() {
		shop.buyItem(shopItems.get(iterator));
		StatsManager.setShopItem(shopItems.get(iterator));
		
	}

	private void initShopItemList() {
		for(int i = 0; i < shop.getShopItems().size(); i++) {
			shopItems.add(shop.getShopItems().get(i));
		}
	}

	private String getItemLeft() {		
		iterator++;

		if(iterator >= shopItems.size()) {
			iterator = 0;
			costLabel.setText("Cost: " + shopItems.get(0).getPrice());
			return shopItems.get(iterator).getImage();
		} else { 
			costLabel.setText("Cost: " + shopItems.get(iterator).getPrice());
			return shopItems.get(iterator).getImage();
		}
	}
	
	private int getIterator() {
		return iterator;
	}
	

	//Needs Fixing
	private String getItemRight() {
		iterator--;
		System.out.println(iterator);
		System.out.println(shopItems.size());
		if(iterator < 0) {
			iterator = 6;
			return shopItems.get(iterator).getImage();
		} else { 
			return shopItems.get(iterator).getImage();
		}
	}
}

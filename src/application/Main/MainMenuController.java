package application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**********************************************************************
 * This is the fx:controller class which links the scene builder
 * members from the fxml file to code within this class
 * @author Nabeel Vali
 *********************************************************************/

public class MainMenuController {
			
	@FXML
	private Button clickButton;
	
	@FXML
	private Label testLabel;

	//This method is called after all @FXML annotated members have been injected
	@FXML
	private void initialize() {
		clickButton.setOnAction(e -> {
			System.out.println("test");
		});

	}
}

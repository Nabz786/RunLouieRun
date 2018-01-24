package application.Main;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**********************************************************************
 * This is the main class, here the program will be launched placing
 * the user at the main menu screen. 
 *
 *********************************************************************/
public class Main extends Application {
	
	/** Int for the with of the program window **/
	private static final int WIDTH = 650;
	
	/** Int of the height of the program window **/
	private static final int HEIGHT = 400;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenuStyle.fxml"));
			Scene scene = new Scene(root,WIDTH,HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Run Louie Run");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

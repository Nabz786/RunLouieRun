package application.tests;

import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;
import application.main.Main;
import javafx.stage.Stage;

/**
 * Base class for testing using testFX, houses methods that will be
 * used in all tests.
 * @author Nabeel
 */
public class GameTestsBase extends ApplicationTest {
	
	/**
	 * Creates an instance of the main class and scene for use in 
	 * testing.
	 * @throws Exception Error when attempting to launch the main program
	 */
	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(Main.class);
	}
	
	/**
	 * Launches the main program stage.
	 */
	@Override
	public void start(final Stage stage) throws Exception {
		stage.show();
	}
}

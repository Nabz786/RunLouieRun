package application.tests;

import org.junit.Test;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.api.FxRobotException;
import static org.testfx.api.FxAssert.verifyThat;

/**
 * Test cases to validate the elements on the main menu.
 * @author Nabeel
 */
public class MainMenuTests extends GameTestsBase {

	/**
	 * False test to check for a non-existant element.
	 */
	@Test(expected = FxRobotException.class)
	public void clickFakeElement() {
		clickOn("#LeaveGame");
	}

	/**
	 * Verifies all components on the main menu exist.
	 */
	@Test
	public void verifyAllComponents() {
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}

	/**
	 * Verifies all components on the main menu exist after switching scenes.
	 */
	@Test
	public void verifyAllComponentsAfterHelpClick() {
		clickOn("#helpButton");
		clickOn("#backButton");
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}

	/**
	 * Verifies all components exist on the main menu after switching scenes
	 * to the shop.
	 */
	@Test
	public void verifyAllComponentsAfterStoreClick() {
		clickOn("#storeButton");
		clickOn("#menuButton");
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}
}

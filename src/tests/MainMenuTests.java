package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.api.FxRobotException;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.base.NodeMatchers;

public class MainMenuTests extends GameTestsBase {

	@Test(expected = FxRobotException.class)
	public void clickFakeElement() {
		clickOn("#LeaveGame");
	}

	@Test
	public void verifyAllComponents() {
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}

	@Test
	public void verifyAllComponentsAfterHelpClick() {
		clickOn("#helpButton");
		clickOn("#backButton");
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}

	@Test
	public void verifyAllComponentsAfterStoreClick() {
		clickOn("#storeButton");
		clickOn("#menuButton");
		verifyThat("#startButton", NodeMatchers.hasChild("start"));
		verifyThat("#helpButton", NodeMatchers.hasChild("Button"));
		verifyThat("#storeButton", NodeMatchers.hasChild("Store"));
	}

}

package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.base.NodeMatchers;


import application.game.Game;
import application.main.Main;
import javafx.scene.Node;
import javafx.stage.Stage;

public class GameTestsBase extends ApplicationTest {
	
	@Before
	public void setUpClass() throws Exception {
		ApplicationTest.launch(Main.class);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
	}
	
	@After
	public void afterEachTest() throws TimeoutException {
		
	}
	
	public <T extends Node> T find(final String query) {
		return (T) lookup(query).queryAll().iterator().next();
	}

}

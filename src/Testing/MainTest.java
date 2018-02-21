package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import application.Game.Game;
import javafx.scene.Scene;

class MainTest {
	private Game gametest;
	
	/**
	 * Instantiate Game object.
	 * Called before every test case method
	 * @throws IOException 
	 */
	
	@Before
	public void setUp() throws IOException
	{
		gametest = new Game();
	}
	
	@Test
	public void Test2equals() {
		assertTrue(2==4/2);
	}

}

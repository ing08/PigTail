package se.t1875085.card.test;

import junit.framework.TestCase;
import se.t1875085.card.game.KeyBoard;

public class KeyBoardTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInputString() {
		System.out.println("testと入力してください");
		assertEquals(KeyBoard.inputString(), "test");
	}

	public void testInputNumber() {
		System.out.println("1と入力してください");
		assertEquals(KeyBoard.inputNumber(), 1);
	}

}

package se.t1875085.card.test;

import junit.framework.TestCase;
import se.t1875085.card.entity.Card;
import se.t1875085.card.game.CPU;
import se.t1875085.card.game.PigTailGame;

public class CPUTest extends TestCase {

	private CPU cpu;

	private PigTailGame game;

	private Card spadeA, diamond10, heartQ, clubK;

	protected void setUp() throws Exception {
		super.setUp();

		// テスト用のcpuインスタンスを作っておく．
		cpu = new CPU("CPU");

		// テスト用のgameインスタンスを作っておく．
		game = new PigTailGame();

		// いくつかテスト用のカードインスタンスを作っておく．
		spadeA = new Card(0, 1); // スペードA
		diamond10 = new Card(1, 10); // ダイヤ10
		heartQ = new Card(2, 12); // ハートQ
		clubK = new Card(3, 13); // クラブK
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		cpu.getHand().clear();
		game.takeField().clear();
	}

	public void testDecideCard() {
		assertTrue(cpu.decideCard() >= 1 && cpu.decideCard() <= 52);
	}

	public void testThrowHandInt() {
		cpu.addHand(spadeA);
		cpu.addHand(diamond10);
		cpu.addHand(heartQ);
		cpu.addHand(clubK);

		game.addField(spadeA);
		assertTrue(cpu.throwHand(game.getTopOfField().getSuit()) != game.getTopOfField());
		game.addField(diamond10);
		assertTrue(cpu.throwHand(game.getTopOfField().getSuit()) != game.getTopOfField());
		game.addField(heartQ);
		assertTrue(cpu.throwHand(game.getTopOfField().getSuit()) != game.getTopOfField());
		cpu.addHand(spadeA);
		game.addField(clubK);
		assertTrue(cpu.throwHand(game.getTopOfField().getSuit()) != game.getTopOfField());
	}

	public void testDecideMethod() {
		cpu.addHand(spadeA);
		game.addField(spadeA);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 0);
		game.addField(diamond10);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(heartQ);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(clubK);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		cpu.getHand().clear();
		game.takeField().clear();

		cpu.addHand(diamond10);
		game.addField(diamond10);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 0);
		game.addField(heartQ);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(clubK);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(spadeA);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		cpu.getHand().clear();
		game.takeField().clear();

		cpu.addHand(heartQ);
		game.addField(heartQ);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 0);
		game.addField(clubK);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(spadeA);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(diamond10);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		cpu.getHand().clear();
		game.takeField().clear();

		cpu.addHand(clubK);
		game.addField(clubK);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 0);
		game.addField(spadeA);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(diamond10);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		game.addField(heartQ);
		assertEquals(cpu.decideMethod(game.getTopOfField().getSuit()), 1);
		cpu.getHand().clear();
		game.takeField().clear();

	}

	public void testThrowHand() {
		cpu.addHand(spadeA);
		cpu.addHand(diamond10);
		cpu.addHand(heartQ);
		cpu.addHand(clubK);

		cpu.throwHand();
		assertEquals(cpu.getHand().size(), 3);
		cpu.throwHand();
		assertEquals(cpu.getHand().size(), 2);
		cpu.throwHand();
		assertEquals(cpu.getHand().size(), 1);
		cpu.throwHand();
		assertEquals(cpu.getHand().size(), 0);
	}

	public void testGetName() {
		assertEquals(cpu.getName(), "CPU");
	}

	public void testGetHand() {
		cpu.addHand(spadeA);
		cpu.addHand(diamond10);
		cpu.addHand(heartQ);
		cpu.addHand(clubK);

		assertEquals(cpu.getHand().get(0), spadeA);
		assertEquals(cpu.getHand().get(1), diamond10);
		assertEquals(cpu.getHand().get(2), heartQ);
		assertEquals(cpu.getHand().get(3), clubK);
	}

	public void testAddHand() {
		cpu.addHand(spadeA);
		assertEquals(cpu.getHand().get(0), spadeA);
		cpu.addHand(diamond10);
		assertEquals(cpu.getHand().get(1), diamond10);
		cpu.addHand(heartQ);
		assertEquals(cpu.getHand().get(2), heartQ);
		cpu.addHand(clubK);
		assertEquals(cpu.getHand().get(3), clubK);
	}

	public void testReceivePunishment() {
		game.addField(spadeA);
		game.addField(diamond10);
		game.addField(heartQ);
		game.addField(clubK);

		cpu.ReceivePunishment(game.takeField());

		assertEquals(cpu.getHand().get(0), spadeA);
		assertEquals(cpu.getHand().get(1), diamond10);
		assertEquals(cpu.getHand().get(2), heartQ);
		assertEquals(cpu.getHand().get(3), clubK);
	}
}

package se.t1875085.card.test;

import junit.framework.TestCase;
import se.t1875085.card.entity.Card;
import se.t1875085.card.game.CPU;
import se.t1875085.card.game.PigTailGame;
import se.t1875085.card.game.User;

public class PigTailGameTest extends TestCase {

	private User user;
	private CPU cpu;

	private PigTailGame game;

	private Card spadeA, diamond10, heartQ, clubK;

	protected void setUp() throws Exception {
		super.setUp();

		// テスト用のUserインスタンスを作っておく．
		user = new User("User");

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

		cpu.getHand().clear();;
		game.takeField().clear();;
		game.getCardDeck().clear();
		game.getPlayers().clear();;
	}

	public void testAddPlayer() {
		game.addPlayer(user);
		assertEquals(game.getPlayers().get(0), user);
		game.addPlayer(cpu);
		assertEquals(game.getPlayers().get(1), cpu);
	}

	public void testAddField() {
		game.addField(spadeA);
		assertEquals(game.getTopOfField(), spadeA);
		game.addField(diamond10);
		assertEquals(game.getTopOfField(), diamond10);
		game.addField(heartQ);
		assertEquals(game.getTopOfField(), heartQ);
		game.addField(clubK);
		assertEquals(game.getTopOfField(), clubK);
	}

	public void testTakeField() {
		game.addField(spadeA);
		game.addField(diamond10);
		game.addField(heartQ);
		game.addField(clubK);

		assertEquals(game.takeField().get(0), spadeA);
		assertEquals(game.takeField().get(1), diamond10);
		assertEquals(game.takeField().get(2), heartQ);
		assertEquals(game.takeField().get(3), clubK);
	}

	public void testClearField() {
		game.addField(spadeA);
		game.addField(diamond10);
		game.addField(heartQ);
		game.addField(clubK);

		game.takeField().clear();

		assertTrue(game.takeField().isEmpty());
	}

	public void testGetTopOfField() {
		game.addField(spadeA);
		assertEquals(game.getTopOfField(), game.takeField().get((game.takeField().size() - 1)));
		game.addField(diamond10);
		assertEquals(game.getTopOfField(), game.takeField().get((game.takeField().size() - 1)));
		game.addField(heartQ);
		assertEquals(game.getTopOfField(), game.takeField().get((game.takeField().size() - 1)));
		game.addField(clubK);
		assertEquals(game.getTopOfField(), game.takeField().get((game.takeField().size() - 1)));
	}

	public void testDoPigTail() {
		game.addPlayer(user);
		game.addPlayer(cpu);

		System.out.println("doPigTail()をテストするために2人でぶたのしっぽをプレイしてください");
		game.doPigTail();

		assertTrue(game.getCardDeck().isEmpty());
	}

	public void testThrowDeck() {
		game.getCardDeck().createFullDeck();
		game.getCardDeck().shuffle();

		assertNotNull(game.throwDeck(1));
		assertEquals(game.getCardDeck().size(), 51);
		assertNotNull(game.throwDeck(51));
		assertEquals(game.getCardDeck().size(), 50);

	}

	public void testUserTurn() {
		game.addPlayer(user);
		game.getCardDeck().createFullDeck();
		game.getCardDeck().shuffle();

		System.out.println("userTurn()をテストするために1ターンプレイしてください");
		game.userTurn(0, null);
		assertEquals(game.getCardDeck().size(), 51);
		System.out.println("userTurn()をテストするために1ターンプレイしてください");
		game.userTurn(0, game.getTopOfField());
		assertEquals(game.getCardDeck().size(), 50);
		game.takeField().clear();

		user.addHand(spadeA);
		user.addHand(diamond10);
		user.addHand(heartQ);
		user.addHand(clubK);
		game.addField(spadeA);

		System.out.println("userTurn()をテストするために1ターンプレイしてください(手札からspadeAを捨ててください)");
		user.showHand();
		game.userTurn(0, game.getTopOfField());
		assertEquals(user.getHand().size(), 5);
		System.out.println("userTurn()をテストするために1ターンプレイしてください(手札からdiamond10を捨ててください)");
		user.showHand();
		game.userTurn(0, null);
		assertEquals(user.getHand().size(), 4);
	}

	public void testCPUTurn() {
		game.addPlayer(user);
		game.addPlayer(cpu);
		game.getCardDeck().createFullDeck();
		game.getCardDeck().shuffle();

		game.CPUTurn(1, null);
		assertEquals(game.getCardDeck().size(), 51);
		game.takeField().clear();
		game.getCardDeck().clear();
		user.getHand().clear();

		cpu.addHand(spadeA);
		cpu.addHand(diamond10);
		cpu.addHand(heartQ);
		cpu.addHand(clubK);
		game.addField(spadeA);

		game.CPUTurn(1, game.getTopOfField());
		assertEquals(cpu.getHand().size(), 3);
		game.CPUTurn(1, game.getTopOfField());
		assertEquals(cpu.getHand().size(), 2);
		cpu.getHand().clear();

		game.addField(spadeA);
		game.getCardDeck().addCard(spadeA);
		game.CPUTurn(1, game.getTopOfField());
		assertEquals(cpu.getHand().size(), 5);
	}

	public void testGetCardDeck() {
		game.getCardDeck().addCard(spadeA);
		assertEquals(game.getCardDeck().seeCard(1), spadeA);
		game.getCardDeck().addCard(diamond10);
		assertEquals(game.getCardDeck().seeCard(2), diamond10);
		game.getCardDeck().addCard(heartQ);
		assertEquals(game.getCardDeck().seeCard(3), heartQ);
		game.getCardDeck().addCard(clubK);
		assertEquals(game.getCardDeck().seeCard(4), clubK);

		game.getCardDeck().clear();
		game.getCardDeck().createFullDeck();
		assertEquals(game.getCardDeck().size(), 52);
	}

	public void testGetPlayers() {
		game.addPlayer(user);
		game.addPlayer(cpu);

		assertEquals(game.getPlayers().get(0), user);
		assertEquals(game.getPlayers().get(1), cpu);
	}

	public void testclearPlayers() {
		game.addPlayer(user);
		game.addPlayer(cpu);
		game.getPlayers().clear();;
		assertTrue(game.getPlayers().isEmpty());
	}

}

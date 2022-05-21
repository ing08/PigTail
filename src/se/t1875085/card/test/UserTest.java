package se.t1875085.card.test;

import junit.framework.TestCase;
import se.t1875085.card.entity.Card;
import se.t1875085.card.game.PigTailGame;
import se.t1875085.card.game.User;

public class UserTest extends TestCase {

	private User user;

	private PigTailGame game;

	private Card spadeA, diamond10, heartQ, clubK;

	protected void setUp() throws Exception {
		super.setUp();

		// テスト用のUserインスタンスを作っておく．
		user = new User("User");

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

		user.getHand().clear();
		game.takeField().clear();
	}

	public void testDecideCard() {
		System.out.println("decideCard()をテストするための数字を入力してください");
		assertNotNull(user.decideCard());
	}

	public void testThrowHand() {
		user.addHand(spadeA);
		user.addHand(diamond10);
		user.addHand(heartQ);
		user.addHand(clubK);

		user.showHand();
		System.out.println("throwHand()をテストするための数字を入力してください");

		assertTrue(user.throwHand() == spadeA || user.throwHand() == diamond10 ||
				user.throwHand() == heartQ || user.throwHand() == clubK || user.throwHand() == null);
	}

	public void testGetRank() {
		user.setRank(1);
		assertEquals(user.getRank(), 1);
	}

	public void testSetRank() {
		user.setRank(1);
		assertEquals(user.getRank(), 1);
	}

	public void testGetName() {
		assertEquals(user.getName(), "User");
	}

	public void testGetHand() {
		user.addHand(spadeA);
		user.addHand(diamond10);
		user.addHand(heartQ);
		user.addHand(clubK);

		assertEquals(user.getHand().get(0), spadeA);
		assertEquals(user.getHand().get(1), diamond10);
		assertEquals(user.getHand().get(2), heartQ);
		assertEquals(user.getHand().get(3), clubK);
	}

	public void testAddHand() {
		user.addHand(spadeA);
		assertEquals(user.getHand().get(0), spadeA);
		user.addHand(diamond10);
		assertEquals(user.getHand().get(1), diamond10);
		user.addHand(heartQ);
		assertEquals(user.getHand().get(2), heartQ);
		user.addHand(clubK);
		assertEquals(user.getHand().get(3), clubK);
	}

	public void testReceivePunishment() {
		game.addField(spadeA);
		game.addField(diamond10);
		game.addField(heartQ);
		game.addField(clubK);

		user.ReceivePunishment(game.takeField());

		assertEquals(user.getHand().get(0), spadeA);
		assertEquals(user.getHand().get(1), diamond10);
		assertEquals(user.getHand().get(2), heartQ);
		assertEquals(user.getHand().get(3), clubK);
	}

}

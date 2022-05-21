package se.t1875085.card.test;

import junit.framework.TestCase;
import se.t1875085.card.game.CPU;
import se.t1875085.card.game.PigTailApplication;
import se.t1875085.card.game.Player;
import se.t1875085.card.game.User;

public class PigTailApplicationTest extends TestCase {

	private User user;

	private PigTailApplication app;

	protected void setUp() throws Exception {
		super.setUp();

		// テスト用のUserインスタンスを作っておく．
		user = new User("User");

		// テスト用のappインスタンスを作っておく．
		app = new PigTailApplication();
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		app.getGame().getPlayers().clear();
		user.getHand().clear();
	}

	public void testStartApplication() {
		System.out.println("startApplication()をぶたのしっぽをプレイしてテストしてください");
		app.startApplication();
		assertTrue(app.getGame().getCardDeck().isEmpty());
		for (Player player : app.getGame().getPlayers()) {
			if (player instanceof User)
				user = (User) player;
		}
		assertNotNull(user.getRank());
	}

	public void testInitialize() {
		System.out.println("initialize()テストするために名前をtest, プレイ人数4人で初期化してください");
		app.initialize();
		for (Player player : app.getGame().getPlayers()) {
			if (player instanceof User)
				user = (User) player;
		}
		assertEquals(user.getName(), "test");
		assertEquals(app.getGame().getPlayers().size(), 4);
	}

	public void testDoGame() {
		System.out.println("doGame()をテストするため, ぶたのしっぽを4人でプレイしてください");

		app.getGame().addPlayer(user);
		for (int i = 0; i < 4 - 1; i++) {
			CPU cpu = new CPU("CPU" + (i + 1));
			app.getGame().addPlayer(cpu);
		}

		app.doGame();

		assertTrue(app.getGame().getCardDeck().isEmpty());
		assertNotNull(user.getRank());
	}
}

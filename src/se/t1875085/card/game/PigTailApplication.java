package se.t1875085.card.game;

/**
 * ぶたのしっぽの初期設定をしてゲームを開始するクラス
 *
 * @author 1875085T
 *
 */
public class PigTailApplication {

	/** ぶたのしっぽクラスを作成 */
	private PigTailGame game = new PigTailGame();

	/**
	 * ぶたのしっぽの初期設定を行いゲームを始めるメソッド;
	 */
	public void startApplication() {
		System.out.println("ぶたのしっぽを始めます.");
		initialize();
		doGame();
	}

	/**
	 * ぶたのしっぽの初期設定を行うメソッド
	 */
	public void initialize() {
		int number = 1;

		System.out.println("名前を入力してください.:");
		User user = new User(KeyBoard.inputString());
		game.addPlayer(user);
		while (number < 2) {
			System.out.println("何人でぶたのしっぽをしますか.:");
			number = KeyBoard.inputNumber();

			if (number < 2)
				System.out.println("2以上の整数を入力してください.もう一度.");
		}
		for(int i=0;i<number-1;i++) {
			CPU cpu = new CPU("CPU" + (i + 1));
			game.addPlayer(cpu);
		}
	}

	/**
	 * ぶたのしっぽを行うメソッド
	 */
	public void doGame() {
		game.doPigTail();
		game.showRecord();
	}

	/**
	 * gameのgetter
	 *
	 * @return game
	 */
	public PigTailGame getGame() {
		return game;
	}
}

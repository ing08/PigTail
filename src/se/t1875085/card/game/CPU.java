package se.t1875085.card.game;

import java.util.Random;

import se.t1875085.card.entity.Card;
/**
 * ぶたのしっぽのCPUクラス
 *
 * @author 1875085T
 */
public class CPU extends Player {

	/** 乱数を扱う変数 */
	Random random = new Random();

	/**
	 * 空のCPUインスタンスを作る
	 */
	public CPU() {
		super();
	}

	/**
	 * CPU名を指定してCPUインスタンスを作る
	 *
	 * @param name CPUの名前
	 */
	public CPU(String name) {
		super(name);
	}

	/**
	 *手札からランダムで場のカードと違う絵柄のカードを場に捨てるメソッド
	 *
	 * @param suit 場のカードの絵柄
	 * @return 捨てるカード
	 */
	public Card throwHand(int suit) {
		int rand = random.nextInt(hand.size());

		while (true) {
			rand = random.nextInt(hand.size());

			if (hand.get(rand).getSuit() != suit) {
				System.out.println(name + "さんは手札から" + hand.get(rand) + "を場に捨てます.");
				return hand.remove(rand);
			}
		}
	}

	/**
	 * 場のカードによって手札から捨てるか山札から捨てるか決定するメソッド
	 *
	 * @param suit 場のカードの絵柄
	 * @return 手札から捨てる:1, 山札から捨てる:0
	 */
	public int decideMethod(int suit) {
		for (Card card : hand)
			if (card.getSuit() != suit)
				return 1;

		return 0;
	}

	/**
	 * 1~52の数字をランダムに決めるメソッド
	 *
	 * @return 捨てるカード
	 */
	public int decideCard() {
		return random.nextInt(52);
	}

	/**
	 * 手札から捨てるカードをランダムで決めるメソッド
	 *
	 * @return 捨てるカード
	 */
	public Card throwHand() {
		int rand = random.nextInt(hand.size());
		System.out.println(name + "さんは手札から" + hand.get(rand) + "を場に捨てます.");
		return hand.remove(rand);
	}

}

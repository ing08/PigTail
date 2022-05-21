package se.t1875085.card.game;

import se.t1875085.card.entity.Card;

/**
 * ぶたのしっぽのUserクラス
 *
 * @author 1875085T
 */
public class User extends Player {

	/** Userの順位 */
	private int rank;

	/**
	 * 空のUserインスタンスを作る
	 */
	public User() {
		super();
	}

	/**
	 * User名を指定してUserインスタンスを作る
	 *
	 * @param name Userの名前
	 */
	public User(String name) {
		super(name);
	}

	/**
	 * 手札からユーザに捨てるカードを選択させて, そのカードを場に捨てるメソッド
	 *
	 * @return 捨てるカード
	 */
	public Card throwHand() {
		if (hand.size() == 0)
			System.out.println(name + "さんの手札は0枚です.");
		else {
			int number = -2;

			while (number < -1 || number > hand.size()) {
				System.out.println(name + "さんは手札からどのカードを捨てますか."
						+ "[-1:キャンセル 0~" + (hand.size() - 1) + ":捨てたいカード番号]:");
				number = KeyBoard.inputNumber();
				if (number == -1) {
					System.out.println("キャンセルします.");
					break;
				} else if (number < -1 || number >= hand.size())
					System.out.println("不正な入力です.もう一度.");
				else {
					System.out.println("手札から" + hand.get(number) + "を場に捨てます.");
					return hand.remove(number);
				}
			}
		}
		return null;
	}

	/**
	 * rankのgetter
	 *
	 * @return ユーザの順位
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * rankのsetter
	 *
	 * @param rank ユーザの順位
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * ユーザに山札から捨てるカードを選択させるメソッド
	 */
	public int decideCard() {
		return KeyBoard.inputNumber();
	}

}
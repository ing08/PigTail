package se.t1875085.card.game;

import java.util.ArrayList;
import java.util.List;

import se.t1875085.card.entity.Card;
/**
 * ぶたのしっぽのPlayerクラス
 *
 * @author 1875085T
 */
public abstract class Player {

	/** 手札のリスト */
	protected ArrayList<Card> hand = new ArrayList<Card>();

	/** 名前 */
	protected String name;

	/**
	 * 空のPlayerインスタンスを作る
	 */
	public Player() {
	}

	/**
	 * Player名を指定してUserインスタンスを作る
	 * @param name Player名
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Playerの手札を表示するメソッド
	 */
	public void showHand() {
		if (hand.size() == 0)
			System.out.println(name + "さんの手札は0枚です.");
		else {
			System.out.println(name + "さんの手札を表示します");
			for (int i = 0; i < hand.size(); i++)
				System.out.println(i + ":" + hand.get(i));
		}
	}

	/**
	 * nameのgetter
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * handのgetter
	 *
	 * @return hand
	 */
	public List<Card> getHand() {
		return hand;
	}

	/**
	 * Playerの手札にカードを追加するメソッド
	 *
	 * @param card 追加したいカード
	 */
	public void addHand(Card card) {
		hand.add(card);
	}

	/**
	 * Playerが場と同じカードを出した時の処理を行うメソッド
	 * @param field 場のカードのリスト
	 */
	public void ReceivePunishment(List<Card> field) {
		for (Card card : field)
			addHand(card);

	}

	/**
	 * 山札のカードを捨てる処理の抽象メソッド
	 *
	 * @return 捨てるカード番号
	 */
	public abstract int decideCard();

}

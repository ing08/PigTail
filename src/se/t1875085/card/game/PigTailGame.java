package se.t1875085.card.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.t1875085.card.entity.Card;
import se.t1875085.card.entity.CardDeck;

/**
 * ぶたのしっぽを動作させるクラス
 *
 * @author 1875085T
 */
public class PigTailGame {

	/** プレイヤを格納するリスト */
	private ArrayList<Player> players = new ArrayList<Player>();

	/** 場のカードを格納するリスト */
	private ArrayList<Card> field = new ArrayList<Card>();

	/** 山札を格納するリスト */
	private CardDeck carddeck = new CardDeck();

	/**
	 * プレイヤをリストに追加するメソッド
	 *
	 * @param player 新規プレイヤ
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * 場にカードを追加するメソッド
	 *
	 * @param card 場に追加するカード
	 */
	public void addField(Card card) {
		field.add(card);
	}

	/**
	 * fieldのgetter
	 *
	 * @return fieldのリスト
	 */
	public List<Card> takeField() {
		return field;
	}

	/**
	 * fieldの一番上のカードを所得するメソッド
	 *
	 * @return fieldの一番上のカード
	 */
	public Card getTopOfField() {
		return field.get(field.size() - 1);
	}

	/**
	 * ぶたのしっぽを行うメソッド
	 */
	public void doPigTail() {

		User user = null;

		Card topoffield = null;

		carddeck.createFullDeck();
		carddeck.shuffle();
		Collections.shuffle(players);

		for (int i = 0; i < players.size(); i++) {

			System.out.println("--------------------");
			System.out.println(players.get(i).getName() + "さんのターンです.");
			System.out.println("現在の状況を表示します.");
			if (field.size() > 0) {
				System.out.println("場のカード:" + getTopOfField());
				topoffield = getTopOfField();
			} else
				System.out.println("場のカードは0枚です.");
			System.out.println("山札:" + carddeck.size() + "枚");
			players.get(i).showHand();
			System.out.println("--------------------");

			if (players.get(i) instanceof User) {
				userTurn(i, topoffield);
			} else {
				CPUTurn(i, topoffield);
			}
			if (carddeck.size() == 0) {
				System.out.println("----------終了----------");
				break;
			}

			if (i == players.size() - 1)
				i = -1;
		}
		for (Player player : players) {
			if (player instanceof User) {
				user = (User) player;
				break;
			}
		}
		int rank = 1;
		for (Player player : players) {
			if (user.getHand().size() > player.getHand().size()) {
				rank++;
			}
		}
		user.setRank(rank);
	}

	/**
	 * ぶたのしっぽを行った後, 順位を表示するメソッド
	 */
	public void showRecord() {
		User user = null;
		for (Player player : players) {
			System.out.println(player.getName() + "さんの手札は" + player.getHand().size() + "枚残りました.");
			if (player instanceof User) {
				user = (User) player; //ダウンキャスト
			}
		}
		System.out.println(user.getName() + "さんの順位は" + user.getRank() + "位でした.");
	}

	/**
	 * 山札から指定された番号に該当するカードを捨てるメソッド
	 *
	 * @param number 指定された番号
	 * @return 捨てるカード
	 */
	public Card throwDeck(int number) {
		Card card = carddeck.takeCard(number);
		return card;
	}

	/**
	 * ユーザの順番の動作を記述したメソッド
	 * @param i ユーザの順番
	 * @param topoffield 場の一番上のカード
	 */
	public void userTurn(int i, Card topoffield) {

		User user = (User) players.get(i); //ダウンキャスト

		while (true) {
			System.out.println(user.getName() + "さんは山札, 手札どちらから捨てますか[0:山札 1:手札]");
			int which = KeyBoard.inputNumber();
			if (which == 0) { //山札から捨てる
				int number = -2;

				while (number == 0 || number < -1 || number > carddeck.size()) {
					System.out.println(user.getName()
							+ "さんは山札の上から何枚目のカードを捨てますか[-1:キャンセル 1~"
							+ carddeck.size() + ":捨てたいカード番号]");
					number = user.decideCard();
					if (number == 0)
						System.out.println("不正な入力です.もう一度.");

					if (number < -1 || number > carddeck.size())
						System.out.println("不正な入力です.もう一度.");
				}

				if (number == -1) {
					System.out.println("キャンセルします.");
				} else if (number >= 0 && number <= carddeck.size()) {
					Card throwcard = throwDeck(number);

					System.out.println(user.getName() + "さんは山札から"
							+ throwcard + "を場に捨てます.");

					addField(throwcard);

					if (field.size() > 1) {
						if (topoffield != null) {
							if (throwcard.getSuit() == topoffield.getSuit()) {
								System.out.println(user.getName()
										+ "さんは場のカードと同じ絵柄のカードを出しました.");
								System.out.println("場のカードを" + user.getName() + "さんの手札に加えます.");
								user.ReceivePunishment(field);
								takeField().clear();
							}
						}
					}
					break;
				}
			} else if (which == 1) { //手札から捨てる
				Card throwcard = user.throwHand();

				if (throwcard != null) {

					addField(throwcard);

					if (field.size() > 1) {
						if (topoffield != null) {
							if (throwcard.getSuit() == topoffield.getSuit()) {
								System.out.println(user.getName()
										+ "さんは場のカードと同じ絵柄のカードを出しました.");
								System.out.println("場のカードを" + user.getName() + "さんの手札に加えます.");
								user.ReceivePunishment(field);
								takeField().clear();
							}
						}
					}
					break;
				}
			} else
				System.out.println("不正な入力です.もう一度.");
		}
	}

	/**
	 * CPUの順番の動作を記述したメソッド
	 *
	 * @param i 取り扱うCPUの順番
	 * @param topoffield 場の一番上のカード
	 */
	public void CPUTurn(int i, Card topoffield) {
		CPU cpu = (CPU) players.get(i);
		if (field.size() == 0) {
			if (cpu.getHand().size() > 0) { //手札から捨てる
				Card throwcard = cpu.throwHand();

				if (throwcard != null) {
					addField(throwcard);
				}
			} else { //山札から捨てる
				int number = -1;
				while (number < 1 || number > carddeck.size()) {
					number = cpu.decideCard();
				}
				Card throwcard = throwDeck(number);

				System.out.println(cpu.getName() + "さんは山札から"
						+ throwcard + "を場に捨てます.");
				addField(throwcard);
			}
		} else {

			if (cpu.decideMethod(getTopOfField().getSuit()) == 0) { //山札から捨てる
				int number = -1;
				while (number < 1 || number > carddeck.size()) {
					number = cpu.decideCard();
				}
				Card throwcard = throwDeck(number);

				System.out.println(cpu.getName() + "さんは山札から"
						+ throwcard + "を場に捨てます.");
				addField(throwcard);

				if (field.size() > 1) {
					if (throwcard.getSuit() == topoffield.getSuit()) {
						System.out.println(cpu.getName()
								+ "さんは場のカードと同じ絵柄のカードを出しました.");
						System.out.println("場のカードを" + cpu.getName() + "さんの手札に加えます.");
						cpu.ReceivePunishment(field);
						takeField().clear();
					}
				}
			} else { //手札から捨てる
				Card throwcard = cpu.throwHand(getTopOfField().getSuit());

				if (throwcard != null) {

					addField(throwcard);

					if (field.size() > 1) {
						if (throwcard.getSuit() == topoffield.getSuit()) {
							System.out.println(cpu.getName()
									+ "さんは場のカードと同じ絵柄のカードを出しました.");
							System.out.println("場のカードを" + cpu.getName() + "さんの手札に加えます.");
							cpu.ReceivePunishment(field);
							takeField().clear();
						}
					}
				}
			}
		}
	}

	/**
	 * 山札のリストを返すメソッド
	 *
	 * @return 山札のリスト
	 */
	public CardDeck getCardDeck() {
		return carddeck;
	}

	/**
	 * playersのgetter
	 *
	 * @return players
	 */
	public List<Player> getPlayers() {
		return players;
	}
}

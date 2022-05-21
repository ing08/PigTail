package se.t1875085.card.game;

import java.io.BufferedReader;
/**
 * ユーザからの入力を扱うクラス
 */
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * ユーザの入力データを受け取るクラス
 *
 * @author 1875085T
 */
public class KeyBoard {

	/**
	 * ユーザが入力した文字列を受け取るメソッド
	 *
	 * @return ユーザが入力した文字列
	 */
	public static String inputString() {
		String line;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			line = br.readLine();
		} catch (IOException e) {
			System.err.println("エラー：入出力例外です．もう一度入力．");
			line = inputString();
		}

		return line;

	}

	/**
	 * ユーザが入力した整数を受け取るメソッド
	 *
	 * @return　ユーザが入力した整数
	 */
	public static int inputNumber() {
		int number;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
			number = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			System.err.println("フォーマット例外です．もう一度．");
			number = inputNumber(); // 再起呼び出し
		} catch (IOException e) {
			System.err.println("入出力例外です．もう一度．");
			number = inputNumber(); // 再起呼び出し
		}
		return number;
	}
}

package game2048;

import java.io.Serializable;

/****
 * 
 * object for sending tato to server
 * 
 */
public class SendDataHint implements Serializable {

	int score, depth;
	int[][] data;
	String gameName;

	/****
	 * Contractor to create sending object
	 * @param score
	 * @param data
	 * @param depth
	 * @param gameName
	 */
	public SendDataHint(int score, int[][] data, int depth, String gameName) {
		this.score = score;
		this.data = data;
		this.depth = depth;
		this.gameName = gameName;
	}

	public int getScore() {
		return score;
	}

	public int[][] getData() {
		return data;
	}

	public String getGameName() {
		return gameName;
	}

	public int getDepth() {
		return depth;
	}
}

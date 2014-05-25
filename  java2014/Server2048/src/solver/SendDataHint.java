package solver;

import java.io.Serializable;

public class SendDataHint implements Serializable{
	int score;
	int[][] data;
	
	public SendDataHint(int score, int[][] data) {
		this.score = score;
		this.data = data;
	}
	
	
}

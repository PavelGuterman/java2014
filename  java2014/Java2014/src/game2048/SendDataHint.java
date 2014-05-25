package game2048;

import java.io.Serializable;

public class SendDataHint implements Serializable{
	
	int score;
	int[][] data;
	String gameName;
	
	public SendDataHint(int score, int[][] data,String gameName) {
		this.score = score;
		this.data = data;
		
		this.gameName = gameName;
	}
	
	
}

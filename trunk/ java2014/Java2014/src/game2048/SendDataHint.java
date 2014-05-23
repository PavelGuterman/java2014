package game2048;

public class SendDataHint {
	int score;
	int[][] data;
	String gameName;
	
	public SendDataHint(int score, int[][] data,String gameName) {
		this.score = score;
		this.data = data;
		this.gameName = gameName;
	}
	
	
}

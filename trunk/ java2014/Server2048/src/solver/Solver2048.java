package solver;

public class Solver2048 {

	public int[][] data;
	int score, depth;
	
	public Solver2048(int score, int[][] data,  int depth) {
		this.data = data;
		this.score = score;
		this.depth = depth;
	}
	
	public int getBestMove() throws CloneNotSupportedException {
		Board board = new Board(data, score);
		Direction bestMove = AIsolver.findBestMove(board, depth);
		return bestMove.getCode();
	}
	
}

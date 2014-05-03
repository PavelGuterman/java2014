package gameMaze;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class ModelMaze extends Observable implements Model {
	
	private int[][] data;
	private Stack<int[][]> stepsDataHistorry;
	//private final int boardSize;
	private int score = 0;
	private Stack<Integer> scoresDataHistorry;
	boolean flag;
	
	
	

	
	
	public ModelMaze() {
		super();
		data = new int[4][4];
		stepsDataHistorry = new Stack<int[][]>();
		scoresDataHistorry = new Stack<Integer>();
		
		restartgame();
	}

	
	public void inputNewNumberToData() {
		/*
		 * // Draw Location and number
		 */
		// set new
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (data[i][j] == 0) {
					stack.push(i + "," + j);
				}
			}
		}
		data[3][3]  = 1;
		data[0][0]  = 2;
		
		
	}
	
	@Override
	public void moveUp() {
		

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public int[][] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public int[][] popStepBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void restartgame() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				data[i][j] = 0;
			}
		}
		stepsDataHistorry.clear();
		scoresDataHistorry.clear();
		score=0;

		inputNewNumberToData();
		inputNewNumberToData();
		
		System.out.println("Restart");

	}

	@Override
	public void saveGame(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getMesegeString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}

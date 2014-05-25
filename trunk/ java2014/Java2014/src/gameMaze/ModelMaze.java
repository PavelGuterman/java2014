package gameMaze;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class ModelMaze extends Observable implements Model {

	private int[][] data;
	private int coloms=10,rows=10;
	private int[] curState = new int[2];
	private Stack<int[][]> stepsDataHistorry;
	private int score = 0;
	private Stack<Integer> scoresDataHistorry;
	boolean flag;
	boolean youWin;
	boolean youLose;
	private String getMessageString = "";

	public ModelMaze() {
		super();
		data = new int[rows][coloms];
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
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < coloms; j++) {
				data[i][j] = 0;
			}
			System.out.println("");
		}
		
		for(int i = 0; i < 9; i++){
			data[8][i] = -1;
			data[6][i] = -1;
			data[4][i] = -1;
			
		}
		data[5][0] = 2;
		data[7][0] = 1;
		setCurState(7, 0);
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				if (data[i][j] == 0) {
//					stack.push(i + "," + j);
//				}
//			}
//		}
//		data[3][3] = 1;
//		setCurState(3, 3);
//		data[0][0] = 2;
//		data[1][0] = -1;
//		data[1][2] = -1;
//		data[1][3] = -1;
//		data[3][2] = -1;
		
	}

	public int[] getCurState() {
		return curState;
	}

	public void setCurState(int x, int y) {
		this.curState[0] = x;
		this.curState[1] = y;
	}

	@Override
	public void moveUp() {
		if (curState[1] == 0) {
			return;
		}
		if (data[curState[0]][curState[1] - 1] == -1) {
			return;
		}
		if (data[curState[0]][curState[1] - 1] == 2) {
			data[curState[0]][curState[1] - 1] = 3;
			data[curState[0]][curState[1]] = 0;
			setCurState(curState[0], curState[1] - 1);
			setChanged();
			notifyObservers();
			return;
		}
		data[curState[0]][curState[1] - 1] = 1;
		data[curState[0]][curState[1]] = 0;
		setCurState(curState[0], curState[1] - 1);
		
		setChanged();
		notifyObservers();
		
	}

	@Override
	public void moveDown() {
		if (curState[1] == 9) {
			return;
		}
		if (data[curState[0]][curState[1] + 1] == -1) {
			return;
		}
		if (data[curState[0]][curState[1] + 1] == 2) {
			data[curState[0]][curState[1] + 1] = 3;
			setCurState(curState[0], curState[1] + 1);
			data[curState[0]][curState[1]] = 0;
			setChanged();
			notifyObservers();
			return;
		}
		data[curState[0]][curState[1] + 1] = 1;
		data[curState[0]][curState[1]] = 0;
		setCurState(curState[0], curState[1] + 1);
		
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveRight() {
		if (curState[0] == 9) {
			return;
		}
		if (data[curState[0] +1][curState[1] ] == -1) {
			return;
		}
		if (data[curState[0] +1][curState[1] ] == 2) {
			data[curState[0] +1][curState[1]] = 3;
			data[curState[0]][curState[1]] = 0;
			setCurState(curState[0]+ 1, curState[1] );
			setChanged();
			notifyObservers();
			return;
		}
		data[curState[0]+1][curState[1] ] = 1;
		data[curState[0]][curState[1]] = 0;
		setCurState(curState[0]+ 1, curState[1] );
		
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveLeft() {
		if (curState[0] == 0) {
			return;
		}
		if (data[curState[0] -1][curState[1] ] == -1) {
			return;
		}
		if (data[curState[0] -1][curState[1] ] == 2) {
			data[curState[0] -1][curState[1]] = 3;
			data[curState[0]][curState[1]] = 0;
			setCurState(curState[0]- 1, curState[1] );
			setChanged();
			notifyObservers();
			return;
		}
		data[curState[0]-1][curState[1] ] = 1;
		data[curState[0]][curState[1]] = 0;
		setCurState(curState[0]- 1, curState[1] );
		
		setChanged();
		notifyObservers();

	}

	@Override
	public int[][] getData() {
		return data;
	}

	@Override
	public int[][] popStepBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void restartgame() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < coloms; j++) {
				data[i][j] = 0;
			}
		}
		stepsDataHistorry.clear();
		scoresDataHistorry.clear();
		score = 0;

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
		String retMessage = getMessageString;
		getMessageString = "";
		System.out.println("retMessage= " + retMessage);
		return retMessage;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void connectToHintServerAndSendParameters(int steps,int deep, String address) {
		// TODO Auto-generated method stub
		
	}

}

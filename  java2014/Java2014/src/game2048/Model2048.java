package game2048;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class Model2048 extends Observable implements Model {

	private int[][] data;
	private Stack<int[][]> stepsDataHistorry;
	
	private final int boardSize;
	
	public Model2048(int boardSize) {
		super();
		this.boardSize=boardSize;
		data = new int [boardSize][boardSize];
		stepsDataHistorry = new Stack<int[][]>();
		
		restartgame();
	}


	public void inputNewNumberToData(){
		/*
		 *  // Draw Location and number
		 */
		
		int[][] tmpHistory = new int [boardSize][boardSize];
		System.arraycopy(data, 0, tmpHistory, 0, boardSize);
		stepsDataHistorry.push(tmpHistory );
		
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (data[i][j] == 0) {
					stack.push(i + "," + j);
				}
			}
		}
		Random rn = new Random();
		int answer = rn.nextInt(stack.size());
		
		int [] strAnser={Integer.parseInt(stack.get(answer).split(",")[0]),Integer.parseInt(stack.get(answer).split(",")[1])};
		
		data[strAnser[0]][strAnser[1]]= Math.random() < 0.9 ? 2 :4 ;	
	}
	
	
	@Override
	public void moveUp() {
		// TODO -set new data
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveDown() {
		// TODO -set new data
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveRight() {
		// TODO -set new data
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveLeft() {
		// TODO -set new data
		setChanged();
		notifyObservers();
	}

	@Override
	public int[][] getData() {
		return data;
	}
	
	@Override
	public int[][] popStepBefore() {
		if(stepsDataHistorry.size()>0){
			return stepsDataHistorry.pop();
		}else{
			return null;
		}
	}


	@Override
	public void restartgame() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				data[i][j] = 0;
			}
		}
		stepsDataHistorry.clear();
		
		inputNewNumberToData();
		inputNewNumberToData();
		
	}

}

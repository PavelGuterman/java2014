package game2048;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class Model2048 extends Observable implements Model {

	private int[][] data;
	private Stack<int[][]> stepsDataHistorry;
	private final int boardSize;
	boolean flag;

	private String getMessageString = "";

	public Model2048(int boardSize) {
		super();
		this.boardSize = boardSize;
		data = new int[boardSize][boardSize];
		stepsDataHistorry = new Stack<int[][]>();

		restartgame();
	}

	public void inputNewNumberToData() {
		/*
		 * // Draw Location and number
		 */

		int[][] tmpHistory = new int[boardSize][boardSize];
		System.arraycopy(data, 0, tmpHistory, 0, boardSize);
		stepsDataHistorry.push(tmpHistory);

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

		int[] strAnser = { Integer.parseInt(stack.get(answer).split(",")[0]),
				Integer.parseInt(stack.get(answer).split(",")[1]) };

		data[strAnser[0]][strAnser[1]] = Math.random() < 0.9 ? 2 : 4;
	}

	@Override
	public void moveUp() {

		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> emptyIndex = new LinkedList<Integer>();
			LinkedList<Integer> fullIndex = new LinkedList<Integer>();
			int tmpFull = 0;
			for (int j = 0; j < data.length; j++) {
				if (isEmpty(data[i][j])) {
					emptyIndex.add(j);
					continue;
				}
				fullIndex.add(j);
			}
			if(emptyIndex.size() == data.length){
				continue;
			}
			while(!fullIndex.isEmpty()){
				tmpFull = fullIndex.poll();
				if (fullIndex.size() >= 1  && data[i][tmpFull] == data[i][fullIndex.getFirst()]){
					data[i][tmpFull] *=2;
					data[i][fullIndex.poll()] = 0;
					setMoveFlag(true);
				}
				if(!emptyIndex.isEmpty() && emptyIndex.getFirst() < tmpFull ){
					data[i][emptyIndex.poll()] = data[i][tmpFull];
					data[i][tmpFull] = 0;
					setMoveFlag(true);
				}
				
				
			}
		}
		if(getMoveFlag()){
			inputNewNumberToData();
			setMoveFlag(false);
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveDown() {
		for (int i = 0; i < data.length; i++) {

		}
		inputNewNumberToData();
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
		if (stepsDataHistorry.size() > 0) {
			return stepsDataHistorry.pop();
		} else {
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

	public boolean isEmpty(int volue) {
		if (volue == 0) {
			return true;
		}
		return false;
	}

	public void margeLine(int[] lineData) {

		LinkedList<Integer> emptyIndex = new LinkedList<Integer>();
		LinkedList<Integer> fullIndex = new LinkedList<Integer>();
		int tmp = 0;
		// int emptyIndex = 0;
		for (int j = 0; j < data.length; j++) {
			if (isEmpty(lineData[j])) {
				emptyIndex.add(j);
				continue;
			}
			fullIndex.add(j);
//			if (!emptyIndex.isEmpty() && j < 3) {
//				tmp = emptyIndex.poll();
//				if (!isEmpty(lineData[j + 1])) {
//					if (lineData[j] == lineData[j + 1]) {
//						lineData[j] *= 2;
//						lineData[j + 1] = 0;
//						lineData[tmp] = lineData[j];
//					}else{
//						lineData[tmp] = lineData[j];
//						lineData[tmp+1] = lineData[j+1];
//					}	
//				}
//				if(tmp>0 && lineData[tmp-1]==lineData[tmp]){
//					lineData[tmp-1] *= 2;
//					lineData[tmp] = 0;
//				 }
//				 lineData[tmp] = lineData[fullIndex.getLast()];
//				 lineData[j] = 0;
//			}else{
//				if (j!=0 && lineData[j] == lineData[j - 1]) {
//					lineData[j - 1] *= 2;
//					lineData[j] = 0;
//				}
//			}
		}
	}

	public void setMoveFlag(boolean is_moved) {
		flag = is_moved;
	}

	public boolean getMoveFlag() {
		return flag;
	}
	
	@Override
	public void saveGame() {
		try {
			SeaveAndLoadGame2048 saveGame2048 = new SeaveAndLoadGame2048();
			saveGame2048.SaveGame(stepsDataHistorry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadGame() {
		try {
			SeaveAndLoadGame2048 loadGame2048 = new SeaveAndLoadGame2048();
			Stack<int[][]> tmpStack = new Stack<int[][]>();
			tmpStack=loadGame2048.LoadGame();
			if (tmpStack.isEmpty()) {
				getMessageString="No Save is found /n Error 1020";
			}else{
				stepsDataHistorry.clear();
				stepsDataHistorry=tmpStack;
				data=stepsDataHistorry.pop();
			}
			
		} catch (IOException e) {
			getMessageString="Can't Load the game /n Error 1010";
			e.printStackTrace();
		}
		
		setChanged();
		notifyObservers();
		
	}

	@Override
	public String getMesegeString() {
		String retMessage=getMessageString;
		getMessageString="";
		return retMessage;
	}
}

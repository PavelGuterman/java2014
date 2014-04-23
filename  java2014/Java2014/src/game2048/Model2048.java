package game2048;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class Model2048 extends Observable implements Model {

	private int[][] data;
	private int [][] oneStepBeforeData;
	
	public void inputNewNumberToData(){
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 0) {
					stack.push(i + "," + j);
				}
			}
		}
		Random rn = new Random();
		int answer = rn.nextInt(stack.size());
		int [] strAnser={Integer.parseInt(stack.get(answer).split(",")[0]),Integer.parseInt(stack.get(answer).split(",")[1])};
		
		data[strAnser[0]][strAnser[1]]=2;	
	}
	
	
	@Override
	public void moveUp() {
		oneStepBeforeData=data;
		// TODO -set new data
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveDown() {
		oneStepBeforeData=data;
		// TODO -set new data
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveRight() {
		oneStepBeforeData=data;
		// TODO -set new data
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveLeft() {
		oneStepBeforeData=data;
		// TODO -set new data
		setChanged();
		notifyObservers();
	}

	@Override
	public int[][] getData() {
		return data;
	}
	
	public int[][] getDataStepBefore() {
		return oneStepBeforeData;
	}

}

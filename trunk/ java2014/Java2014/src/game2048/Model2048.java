package game2048;

import java.util.Observable;

import model.Model;

public class Model2048 extends Observable implements Model {

	private int[][] data;
	private int [][] oneStepBeforeData;
	
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

}

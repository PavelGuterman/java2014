package game2048;

import java.util.Observable;

import model.Model;

public class Model2048 extends Observable implements Model {

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
		System.out.println("dennis");
		
		
		setChanged();
		notifyObservers();

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
		int [][] temp = {{0,0,0,0},{0,0,2,0},{0,0,0,0},{0,0,1,0}};
		return temp;
	}

}

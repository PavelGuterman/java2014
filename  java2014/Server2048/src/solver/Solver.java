package solver;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;

import model.ClienHandler;

public class Solver implements ClienHandler{

	@Override
	public void handleClient(BufferedReader dataFromClient) {
			StateTree dataState = new StateTree(dataFromClient.data, dataFromClient.score)
	}

	
	
	public int[][] move(){
		return 
	}

	public void moveUp() {
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> newLine = new LinkedList<Integer>();
			int[] newData = new int[data.length];
			for (int j = 0; j < data.length; j++) {
				if (!isEmpty(data[i][j])) {
					newLine.add(data[i][j]);
				}
			}
			if(newLine.size() ==0){continue;}
			for (int k = 0; k < data.length && !newLine.isEmpty(); k++) {
				int tmp = newLine.poll();
				int num = tmp;
				if(newLine.size()>=1 && tmp == newLine.getFirst() && num!=0){
					num*=2;
					score += num;
					newLine.pop();
				}
				newData[k] = num;
			}
			if(!Arrays.equals(newData, data[i])){
				setMoveFlag(true);				
				System.arraycopy(newData, 0, data[i], 0, data.length);
			}
		}
		if (getMoveFlag()) {
			inputNewNumberToData();
			setMoveFlag(false);
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveDown() {
		setGameStepsStackAndScore();
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> newLine = new LinkedList<Integer>();
			int[] newData = new int[data.length];
			for (int j = data.length - 1; j >= 0; j--) {
				if (!isEmpty(data[i][j])) {
					newLine.add(data[i][j]);
				}
			}
			if(newLine.size() ==0){continue;}
			for (int k = data.length-1; k >=0 && !newLine.isEmpty(); k--) {
				int tmp = newLine.poll();
				int num = tmp;
				if(newLine.size()>=1 && tmp == newLine.getFirst() && num!=0){
					num*=2;
					score += num;
				}
				newData[k] = num;
			}
			if(!Arrays.equals(newData, data[i])){
				setMoveFlag(true);				
				System.arraycopy(newData, 0, data[i], 0, data.length);
			}
		}
		if (getMoveFlag()) {
			inputNewNumberToData();
			setMoveFlag(false);
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public void moveRight() {
		setGameStepsStackAndScore();
		int[][] newData = new int[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> newLine = new LinkedList<Integer>();
			for (int j = data.length - 1; j >= 0; j--) {
				if (!isEmpty(data[j][i])) {
					newLine.add(data[j][i]);
				}
			}
			if(newLine.size() ==0){continue;}
			for (int k = data.length-1; k >=0 && !newLine.isEmpty(); k--) {
				int tmp = newLine.poll();
				int num = tmp;
				if(newLine.size()>=1 && tmp == newLine.getFirst() && num!=0){
					num*=2;
					score += num;
					newLine.pop();
				}
				newData[k][i] = num;
			}
		}
			for (int i = 0; i < newData.length; i++) {
				if(!Arrays.equals(newData[i], data[i])){
					setMoveFlag(true);
					System.arraycopy(newData[i], 0, data[i], 0, data.length);
				}
				
			}
		
		if (getMoveFlag()) {
			inputNewNumberToData();
			setMoveFlag(false);
		}
		setChanged();
		notifyObservers();

	}

	@Override
	public void moveLeft() {
		setGameStepsStackAndScore();
		int[][] newData = new int[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> newLine = new LinkedList<Integer>();
			for (int j = 0; j < data.length; j++) {
				if (!isEmpty(data[j][i])) {
					newLine.add(data[j][i]);
				}
			}
			if(newLine.size() ==0){continue;}
			for (int k = 0; k < data.length && !newLine.isEmpty(); k++) {
				int tmp = newLine.poll();
				int num = tmp;
				if(newLine.size()>=1 && tmp == newLine.getFirst() && num!=0){
					num*=2;
					score += num;
					newLine.pop();
				}
				newData[k][i] = num;
			}
		}
		
		for (int i = 0; i < newData.length; i++) {
			if(!Arrays.equals(newData[i], data[i])){
				setMoveFlag(true);
				System.arraycopy(newData[i], 0, data[i], 0, data.length);
			}
			
		}
		
		if (getMoveFlag()) {
			inputNewNumberToData();
			setMoveFlag(false);
		}
		setChanged();
		notifyObservers();
	}

}

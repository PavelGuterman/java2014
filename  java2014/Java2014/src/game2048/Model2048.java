package game2048;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import org.eclipse.swt.SWT;

import model.Model;

public class Model2048 extends Observable implements Model {

	private int[][] data;
	private Stack<int[][]> stepsDataHistorry;
	private final int boardSize;
	private int score = 0;
	private Stack<Integer> scoresDataHistorry;
	boolean flag;

	private String getMessageString = "";

	public Model2048(int boardSize) {
		super();
		this.boardSize = boardSize;
		data = new int[boardSize][boardSize];
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

	private void setGameStepsStackAndScore() {
		int[][] tmpHistory = new int[boardSize][boardSize]; // create new temper
		// System.arraycopy(data, 0, tmpHistory, 0, boardSize);//Not work
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				tmpHistory[i][j] = data[i][j];
			}
		}
		stepsDataHistorry.push(tmpHistory);

		/*
		 * Score
		 */
		// Integer tmpScore=new Integer(score);
		scoresDataHistorry.add(new Integer(score));

	}

	

	@Override
	public int[][] getData() {
		return data;
	}

	@Override
	public int[][] popStepBefore() {
		if (stepsDataHistorry.size() > 0) {
			score = scoresDataHistorry.pop();
			data = stepsDataHistorry.pop();
			return data;
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
		scoresDataHistorry.clear();
		score = 0;

		inputNewNumberToData();
		inputNewNumberToData();

		System.out.println("Restart");

	}

	public boolean isEmpty(int volue) {
		if (volue == 0) {
			return true;
		}
		return false;
	}

	public void setMoveFlag(boolean is_moved) {
		flag = is_moved;
	}

	public boolean getMoveFlag() {
		return flag;
	}

	@Override
	public void saveGame(String fileName) {
		try {
			SeaveAndLoadGame2048 saveGame2048 = new SeaveAndLoadGame2048(
					fileName);
			stepsDataHistorry.push(data);
			scoresDataHistorry.push(score);

			saveGame2048.SaveGame(stepsDataHistorry, scoresDataHistorry);

			stepsDataHistorry.pop();
			scoresDataHistorry.pop();
		} catch (IOException e) {
			getMessageString = "Can't Save the game /n Error 1011" + "&&"
					+ SWT.ICON_ERROR;
			e.printStackTrace();
		}
	}

	@Override
	public void loadGame(String fileName) {
		try {
			Stack<int[][]> tmpStack = new Stack<int[][]>();
			Stack<int[][]> tmpScore = new Stack<int[][]>();

			SeaveAndLoadGame2048 loadGame2048 = new SeaveAndLoadGame2048(
					fileName);
			ArrayList<Stack<int[][]>> arrList = loadGame2048.LoadGame();

			tmpStack = arrList.get(0);
			tmpScore = arrList.get(1);

			if (tmpStack.isEmpty()) {
				getMessageString = "No Save is found /n Error 1020" + "&&"
						+ SWT.ICON_ERROR;
			} else {
				stepsDataHistorry.clear();
				scoresDataHistorry.clear();
				stepsDataHistorry = tmpStack;
				// scoresDataHistorry = tmpScore[0][0];
				for (int i = 0; i < tmpScore.size(); i++) {
					scoresDataHistorry.push(tmpScore.get(i)[0][0]);
				}
				data = stepsDataHistorry.pop();
				score = scoresDataHistorry.pop();
				getMessageString = "Game is Loaded" + "&&"
						+ SWT.ICON_INFORMATION;
			}

		} catch (IOException e) {
			getMessageString = "Can't Load the game /n Error 1010" + "&&"
					+ SWT.ICON_ERROR;
			e.printStackTrace();

		}

		setChanged();
		notifyObservers();

	}
	
	@Override
	public void moveUp() {
		setGameStepsStackAndScore();
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
			if (emptyIndex.size() == data.length) {
				continue;
			}
			while (!fullIndex.isEmpty()) {
				tmpFull = fullIndex.poll();
				int localScore = data[i][tmpFull];
				if (fullIndex.size() >= 1
						&& data[i][tmpFull] == data[i][fullIndex.getFirst()]) {
					localScore *= 2;
					data[i][tmpFull] = localScore;
					score += localScore;
					data[i][fullIndex.getFirst()] = 0;
					emptyIndex.add(fullIndex.poll());
					setMoveFlag(true);
				}
				if (!emptyIndex.isEmpty() && emptyIndex.getFirst() < tmpFull) {
					data[i][emptyIndex.poll()] = data[i][tmpFull];
					data[i][tmpFull] = 0;
					emptyIndex.add(tmpFull);
					setMoveFlag(true);
				}

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
			LinkedList<Integer> emptyIndex = new LinkedList<Integer>();
			LinkedList<Integer> fullIndex = new LinkedList<Integer>();
			int tmpFull = 0;
			for (int j = data.length - 1; j >= 0; j--) {
				if (isEmpty(data[i][j])) {
					emptyIndex.add(j);
					continue;
				}
				fullIndex.add(j);
			}
			if (emptyIndex.size() == data.length) {
				continue;
			}
			while (!fullIndex.isEmpty()) {
				tmpFull = fullIndex.poll();
				int localScore = data[i][tmpFull];
				if (fullIndex.size() >= 1
						&& data[i][tmpFull] == data[i][fullIndex.getFirst()]) {
					localScore *= 2;
					data[i][tmpFull] = localScore;
					score += localScore;
					data[i][fullIndex.getFirst()] = 0;
					emptyIndex.add(fullIndex.poll());
					setMoveFlag(true);
				}
				if (!emptyIndex.isEmpty() && emptyIndex.getFirst() > tmpFull) {
					data[i][emptyIndex.poll()] = data[i][tmpFull];
					data[i][tmpFull] = 0;
					emptyIndex.add(tmpFull);
					setMoveFlag(true);
				}

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
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> emptyIndex = new LinkedList<Integer>();
			LinkedList<Integer> fullIndex = new LinkedList<Integer>();
			int tmpFull = 0;
			for (int j = data.length - 1; j >= 0; j--) {
				if (isEmpty(data[j][i])) {
					emptyIndex.add(j);
					continue;
				}
				fullIndex.add(j);
			}
			if (emptyIndex.size() == data.length) {
				continue;
			}
			while (!fullIndex.isEmpty()) {
				tmpFull = fullIndex.poll();
				int localScore = data[tmpFull][i];
				if (fullIndex.size() >= 1
						&& data[tmpFull][i] == data[fullIndex.getFirst()][i]) {
					localScore *= 2;
					data[tmpFull][i] = localScore;
					score += localScore;
					data[fullIndex.getFirst()][i] = 0;
					emptyIndex.add(fullIndex.poll());
					setMoveFlag(true);
				}
				if (!emptyIndex.isEmpty() && emptyIndex.getFirst() > tmpFull) {
					data[emptyIndex.poll()][i] = data[tmpFull][i];
					data[tmpFull][i] = 0;
					emptyIndex.add(tmpFull);
					setMoveFlag(true);
				}
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
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> emptyIndex = new LinkedList<Integer>();
			LinkedList<Integer> fullIndex = new LinkedList<Integer>();
			int tmpFull = 0;
			for (int j = 0; j < data.length; j++) {
				if (isEmpty(data[j][i])) {
					emptyIndex.add(j);
					continue;
				}
				fullIndex.add(j);
			}
			if (emptyIndex.size() == data.length) {
				continue;
			}
			while (!fullIndex.isEmpty()) {
				tmpFull = fullIndex.poll();
				int localScore = data[tmpFull][i];
				if (fullIndex.size() >= 1
						&& data[tmpFull][i] == data[fullIndex.getFirst()][i]) {
					localScore *= 2;
					data[tmpFull][i] = localScore;
					score += localScore;
					data[fullIndex.getFirst()][i] = 0;
					emptyIndex.add(fullIndex.poll());
					setMoveFlag(true);
				}
				if (!emptyIndex.isEmpty() && emptyIndex.getFirst() < tmpFull) {
					data[emptyIndex.poll()][i] = data[tmpFull][i];
					data[tmpFull][i] = 0;
					emptyIndex.add(tmpFull);
					setMoveFlag(true);
				}
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
	public String getMesegeString() {
		String retMessage = getMessageString;
		getMessageString = "";
		System.out.println("retMessage= " + retMessage);
		return retMessage;
	}

	@Override
	public int getScore() {
		return score;
	}
}

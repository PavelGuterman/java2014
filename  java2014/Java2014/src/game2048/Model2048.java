package game2048;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import org.eclipse.swt.SWT;

import model.Model;

/**
 * for Game 2014 
 * 
 * 
 * @see Observable
 * @see Model
 */
public class Model2048 extends Observable implements Model {

	/** **/
	private int[][] data;
	private Stack<int[][]> stepsDataHistorry;
	private final int boardSize;
	private int score = 0;
	private Stack<Integer> scoresDataHistorry;
	boolean flag;

	private String getMessageString = "";

	/**
	 * 
	 * @param boardSize
	 */
	public Model2048(int boardSize) {
		super();
		this.boardSize = boardSize;
		data = new int[boardSize][boardSize];
		stepsDataHistorry = new Stack<int[][]>();
		scoresDataHistorry = new Stack<Integer>();

		restartgame();
	}

	/**
	 * Draw Location and number
	 * 
	 */
	public void inputNewNumberToData() {
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

	/**
	 * set new data of steps and score
	 */
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

	/*
	 * get the data that now
	 * 
	 * @see model.Model#getData()
	 */
	@Override
	public int[][] getData() {
		return data;
	}

	/*
	 * one step beafore
	 * 
	 * @see model.Model#popStepBefore()
	 */
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

	/*
	 * New game
	 * 
	 * @see model.Model#restartgame()
	 */
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

	/**
	 * is empty property
	 */
	public boolean isEmpty(int volue) {
		if (volue == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * flag property
	 */
	public void setMoveFlag(boolean is_moved) {
		flag = is_moved;
	}
	/**
	 * flag property
	 */
	public boolean getMoveFlag() {
		return flag;
	}
/*
 * to save game
 * saving all steps and score in text file.
 * 
 * @see model.Model#saveGame(java.lang.String)
 */
	@Override
	public void saveGame(String fileName) {
		try {
			SeaveAndLoadGame2048 saveGame2048 = new SeaveAndLoadGame2048(fileName);
			stepsDataHistorry.push(data);
			scoresDataHistorry.push(score);

			saveGame2048.SaveGame(stepsDataHistorry, scoresDataHistorry);

			stepsDataHistorry.pop();
			scoresDataHistorry.pop();
		} catch (IOException e) {
			getMessageString = "Can't Save the game /n Error 1011" + "&&" + SWT.ICON_ERROR;
			e.printStackTrace();
		}
	}
/*
 * load game 
 * get from text file all steps before 
 * @see model.Model#loadGame(java.lang.String)
 */
	@Override
	public void loadGame(String fileName) {
		try {
			Stack<int[][]> tmpStack = new Stack<int[][]>();
			Stack<int[][]> tmpScore = new Stack<int[][]>();

			SeaveAndLoadGame2048 loadGame2048 = new SeaveAndLoadGame2048(fileName);
			ArrayList<Stack<int[][]>> arrList = loadGame2048.LoadGame();

			tmpStack = arrList.get(0);
			tmpScore = arrList.get(1);

			if (tmpStack.isEmpty()) {
				getMessageString = "No Save is found /n Error 1020" + "&&" + SWT.ICON_ERROR;
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
				getMessageString = "Game is Loaded" + "&&" + SWT.ICON_INFORMATION;
			}

		} catch (IOException e) {
			getMessageString = "Can't Load the game /n Error 1010" + "&&" + SWT.ICON_ERROR;
			e.printStackTrace();

		}

		setChanged();
		notifyObservers();

	}
	/*
	 * go up
	 * @see model.Model#moveUp()
	 */
	@Override
	public void moveUp() {
		setGameStepsStackAndScore();
		for (int i = 0; i < data.length; i++) {
			LinkedList<Integer> newLine = new LinkedList<Integer>();
			int[] newData = new int[data.length];
			for (int j = 0; j < data.length; j++) {
				if (!isEmpty(data[i][j])) {
					newLine.add(data[i][j]);
				}
			}
			if (newLine.size() == 0) {
				continue;
			}
			for (int k = 0; k < data.length && !newLine.isEmpty(); k++) {
				int tmp = newLine.poll();
				int num = tmp;
				if (newLine.size() >= 1 && tmp == newLine.getFirst() && num != 0) {
					num *= 2;
					score += num;
					newLine.pop();
				}
				newData[k] = num;
			}
			if (!Arrays.equals(newData, data[i])) {
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
/*
 * Go down
 * @see model.Model#moveDown()
 */
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
			if (newLine.size() == 0) {
				continue;
			}
			for (int k = data.length - 1; k >= 0 && !newLine.isEmpty(); k--) {
				int tmp = newLine.poll();
				int num = tmp;
				if (newLine.size() >= 1 && tmp == newLine.getFirst() && num != 0) {
					num *= 2;
					score += num;
				}
				newData[k] = num;
			}
			if (!Arrays.equals(newData, data[i])) {
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
/*
 * go Rightt
 * @see model.Model#moveRight()
 */
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
			if (newLine.size() == 0) {
				continue;
			}
			for (int k = data.length - 1; k >= 0 && !newLine.isEmpty(); k--) {
				int tmp = newLine.poll();
				int num = tmp;
				if (newLine.size() >= 1 && tmp == newLine.getFirst() && num != 0) {
					num *= 2;
					score += num;
					newLine.pop();
				}
				newData[k][i] = num;
			}
		}
		for (int i = 0; i < newData.length; i++) {
			if (!Arrays.equals(newData[i], data[i])) {
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
/*
 * go left
 * @see model.Model#moveLeft()
 */
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
			if (newLine.size() == 0) {
				continue;
			}
			for (int k = 0; k < data.length && !newLine.isEmpty(); k++) {
				int tmp = newLine.poll();
				int num = tmp;
				if (newLine.size() >= 1 && tmp == newLine.getFirst() && num != 0) {
					num *= 2;
					score += num;
					newLine.pop();
				}
				newData[k][i] = num;
			}
		}

		for (int i = 0; i < newData.length; i++) {
			if (!Arrays.equals(newData[i], data[i])) {
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
/*
 * return message for error or warning.
 * @see model.Model#getMesegeString()
 */
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

	/**
	 * Open connection to Hint resolve server send to server parameters Expect
	 * from server integer of move
	 * 
	 * @param steps
	 *            - size of resorts
	 * @param address
	 *            - adders of server
	 * @param deep
	 *            - the deep of answer
	 * @see model.Model
	 */
	@Override
	public void connectToHintServerAndSendParameters(int steps, int depth, String address) {
		System.out.println("connectToHintServerAndSendParameters: steps= " + steps + " dephh= " + depth + " address= "
				+ address);
		try {
			for (int i = 0; i < steps; i++) {
				int step = 1;

				InetAddress netAddress = InetAddress.getByName(address);
				Socket socket = new Socket(netAddress, 6951);

				ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
				writer.writeObject(new SendDataHint(getScore(), data, depth, "game2048")); // send
				// object
				// of
				// state
				writer.flush();

				ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
				step = (int) reader.read();
				System.out.println("et from server: " + step);
				reader.close();
				writer.close();
				socket.close();

				System.out.println("auto Move is " + step);
				setSteapFromServerHint(step);

				if (i < steps - 1) {
					Thread.sleep(3000);
				}

			}// end of for

			/*
			 * all catches from server connection or answer
			 */
		} catch (ConnectException ce) {
			System.err.println("No connection to HINT server ");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Generate move from integer 0-up,1-right,2-down,3-left
	 * 
	 * @param stepNo
	 *            - int step number
	 */
	private boolean setSteapFromServerHint(int stepNo) {
		if (-1 < stepNo && stepNo < 4) {
			switch (stepNo) {
			case 0:
				moveUp();
				break;
			case 1:
				moveRight();
				break;
			case 2:
				moveDown();
				break;
			case 3:
				moveLeft();
				break;
			}
			return true;
		} else {
			return false;
		}

	}
}

package game2048;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SeaveAndLoadGame2048 {

	private final File saveFile;

	// public static void main(String[] args) throws IOException {
	// int[][] tmp = { { 0, 0, 0, 2 }, { 0, 0, 4, 0 }, { 4, 36, 2, 0 },
	// { 0, 0, 0, 0 } };
	// int[][] tmp2 = { { 0, 4, 0, 2 }, { 0, 0, 4, 36 }, { 4, 36, 2, 0 },
	// { 0, 0, 0, 0 } };
	// int[][] tmp3 = { { 0, 2, 0, 2 }, { 0, 8, 4, 0 }, { 4, 36, 2, 0 },
	// { 0, 126, 0, 0 } };
	//
	// Stack<int[][]> tmpStack = new Stack<int[][]>();
	//
	// tmpStack.add(tmp);
	// tmpStack.add(tmp2);
	// tmpStack.add(tmp3);
	//
	// SeaveAndLoadGame2048 game2048 = new SeaveAndLoadGame2048();
	// //game2048.SaveGame(tmpStack);
	//
	// tmpStack.empty();
	//
	// tmpStack=game2048.LoadGame();
	// System.out.println(tmpStack.isEmpty());
	// System.out.println("End");
	// }

	public SeaveAndLoadGame2048(String strFileName) throws IOException { // to
																			// load
																			// game
		saveFile = setFile(strFileName);
	}

	public void SaveGame(Stack<int[][]> stack, Stack<Integer> stack2)
			throws IOException {

		PrintWriter outputStream = new PrintWriter(new FileWriter(saveFile));

		for (int i = 0; i < stack.size(); i++) {
			int[][] arry = stack.get(i);
			int num = stack2.get(i);
			for (int j = 0; j < arry.length; j++) {
				for (int j2 = 0; j2 < arry[j].length; j2++) {
					outputStream.print(arry[j][j2]);
					if (j2 < arry[j].length - 1) {
						outputStream.print(",");
					}
				}
				if (j < arry.length - 1) {
					outputStream.print("@@");
				}
			}
			outputStream.print("&&" + num + "\n");
		}
		outputStream.close();
	}

	public ArrayList<Stack<int[][]>> LoadGame() throws FileNotFoundException {// Stack<int[][]>
		ArrayList<Stack<int[][]>> arrList = new ArrayList<Stack<int[][]>>();

		Stack<int[][]> loadGamge = new Stack<int[][]>();
		Stack<int[][]> loadScore = new Stack<int[][]>();
		Scanner myScaner = new Scanner(new BufferedReader(new FileReader(
				saveFile)));
		myScaner.useDelimiter("\n");
		while (myScaner.hasNext()) {

			System.out.println("--");

			String line = myScaner.next();
			System.out.println(line);

			String[] lineArr = line.split("&&");
			int[][] tmpScore = new int[1][1];
			try {
				tmpScore[0][0] = Integer.parseInt(lineArr[1]);
				loadScore.add(tmpScore);
			} catch (NumberFormatException e) {
				tmpScore[0][0] = 0;
				loadScore.add(tmpScore);
			} catch (ArrayIndexOutOfBoundsException e) {
				tmpScore[0][0] = 0;
				loadScore.add(tmpScore);
			}

			String[] srt_colum = lineArr[0].split("@@");
			int[][] arr = new int[srt_colum.length][srt_colum.length];
			for (int i = 0; i < srt_colum.length; i++) {
				String[] str_line = srt_colum[i].split(",");
				for (int j = 0; j < str_line.length; j++) {
					arr[i][j] = Integer.parseInt(str_line[j]);
				}

			}

			loadGamge.push(arr);
		}

		myScaner.close();

		arrList.add(loadGamge);
		arrList.add(loadScore);

		return arrList;
	}

	private File setFile(String fileString) throws IOException {
		File file = new File(fileString);
		if (file.exists()) {
			System.out.println(file.getPath());
		} else {
			if (file.createNewFile()) {
				System.out.println("new file " + file.getPath());
			}
		}
		return file;
	}
}

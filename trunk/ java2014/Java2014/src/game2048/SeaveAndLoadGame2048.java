package game2048;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SeaveAndLoadGame2048 {

//	public static void main(String[] args) throws IOException {
//		int[][] tmp = { { 0, 0, 0, 2 }, { 0, 0, 4, 0 }, { 4, 36, 2, 0 },
//				{ 0, 0, 0, 0 } };
//		int[][] tmp2 = { { 0, 4, 0, 2 }, { 0, 0, 4, 36 }, { 4, 36, 2, 0 },
//				{ 0, 0, 0, 0 } };
//		int[][] tmp3 = { { 0, 2, 0, 2 }, { 0, 8, 4, 0 }, { 4, 36, 2, 0 },
//				{ 0, 126, 0, 0 } };
//
//		Stack<int[][]> tmpStack = new Stack<int[][]>();
//		
//		tmpStack.add(tmp);
//		tmpStack.add(tmp2);
//		tmpStack.add(tmp3);
//
//		SeaveAndLoadGame2048 game2048 = new SeaveAndLoadGame2048();
//		//game2048.SaveGame(tmpStack);
//		
//		tmpStack.empty();
//		
//		tmpStack=game2048.LoadGame();
//		System.out.println(tmpStack.isEmpty());
//		System.out.println("End");
//	}

	public SeaveAndLoadGame2048() throws IOException { // to load game
		setFile();
	}

	public void SaveGame(Stack<int[][]> stack) throws IOException {
		
		PrintWriter outputStream = new PrintWriter(new FileWriter(
				"game2048.txt"));

		for (int i = 0; i < stack.size(); i++) {
			int[][] arry = stack.get(i);
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
			outputStream.print("\n");
		}
		outputStream.close();
	}

	public Stack<int[][]> LoadGame() throws FileNotFoundException {
		Stack<int[][]> loadGamge = new Stack<int[][]>();
		Scanner myScaner = new Scanner(new BufferedReader(new FileReader(
				"game2048.txt")));
		myScaner.useDelimiter("\n");
		while (myScaner.hasNext()) {

			System.out.println("--");

			String line = myScaner.next();
			System.out.println(line);
			
			String[] srt_colum = line.split("@@");
			int[][] arr = new int[srt_colum.length][srt_colum.length];
			for (int i = 0; i < srt_colum.length; i++) {
				String [] str_line = srt_colum[i].split(",");
				for (int j = 0; j < str_line.length; j++) {
					arr[i][j]=Integer.parseInt(str_line[j]);
				}
				
			}
			
			loadGamge.push(arr);
		}

		myScaner.close();
		return loadGamge;
	}

	private File setFile() throws IOException {
		File file = new File("game2048.txt");
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

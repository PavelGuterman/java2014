package controller;

import game2048.SendDataHint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import solver.MazeSolver;
import solver.Solver2048;
import model.ClienHandler;
import model.GamingServer;

public class RunServer extends Thread {
	GamingServer gameServer;

	public void createServer() throws Exception {

		gameServer = new GamingServer(6951, 5, new ClienHandler() {
			@Override
			public void handleClient(ObjectInputStream inFromClient, ObjectOutputStream out2Client)
					throws ClassNotFoundException, IOException {
				System.out.println("start the Solver");
				SendDataHint dataHint = (SendDataHint) inFromClient.readObject();
				switch (dataHint.getGameName()) {
				case "game2048":
					Solver2048 s_2048 = new Solver2048(dataHint.getScore(), dataHint.getData(), dataHint.getDepth());
					try {
						Integer answer = s_2048.getBestMove();
						System.out.println(answer);
						out2Client.write(answer);
						out2Client.flush();
					} catch (CloneNotSupportedException e) {
						System.out.println("The Object you try to work with is not Clonable ");
						e.printStackTrace();
					}
					break;
				case "gameMaze":
					MazeSolver s_maze = new MazeSolver(dataHint.getData());
					// out2Client.write(s_maze.getTheCheese());
					break;
				default:
					break;
				}
				// TODO Auto-generated method stub

			}
		});

		//gameServer.start();
//		Thread.sleep(2 * 60 * 1000);
//		gameServer.close();
	}

	public boolean stopServer() {
		if(gameServer!=null && gameServer.isAlive()){
			gameServer.close();
			return true;
		}else{
			return false;
		}
	}
	
	public void startServer() {
		try {
			createServer();
		} catch (Exception e) {
			System.err.println("Server Error 001 - server can't start !!!");
			e.printStackTrace();
			System.exit(0); // stop all program
		}
		gameServer.start();
	}
	
	
	public void run() {
		
	}
}

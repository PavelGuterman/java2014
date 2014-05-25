package controller;

import game2048.SendDataHint;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import solver.Solver2048;
import model.ClienHandler;
import model.GamingServer;



public class Main {

	public static void main(String[] args) throws Exception {
		
	GamingServer s = new GamingServer(6951, 5, new ClienHandler() {



		@Override
		public void handleClient(ObjectInputStream inFromClient, ObjectOutputStream out2Client)
				throws ClassNotFoundException, IOException {
			System.out.println("start the Solver");
			SendDataHint dataHint = (SendDataHint) inFromClient.readObject();
			switch (dataHint.getGameName()) {
			case "game2048":
				Solver2048 s_2048 = new Solver2048( dataHint.getScore(),dataHint.getData(), dataHint.getDepth());
				try {
					System.out.println(s_2048.getBestMove());
					out2Client.write(s_2048.getBestMove());
				} catch (CloneNotSupportedException e) {
					System.out.println("The Object you try to work with is not Clonable ");
					e.printStackTrace();
				}
				break;
			case "gameMaze":
				
				break;
			default:
				break;
			}
			// TODO Auto-generated method stub
			
		}
	}); 
	
		
	
	s.start();
	Thread.sleep(60*1000);
	s.close();

	}

}

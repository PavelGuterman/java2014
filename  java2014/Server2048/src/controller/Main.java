package controller;

import java.io.BufferedReader;

import solver.Board;
import solver.Solver;
import model.ClienHandler;
import model.Server2048;
import solver.AIsolver;
public class Main {

	public static void main(String[] args) throws Exception {
		
	Server2048 s = new Server2048(6951, new ClienHandler() {
		
		@Override
		public void handleClient(BufferedReader inFromClient) {
			// TODO Auto-generated method stub
			System.out.println("start the Solver");
			Board board = inFromClient;
			String line;
			try {
				while(!((line= inFromClient.readLine()).equals("exit"))){
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}); 
	
		
	
	s.start();
	Thread.sleep(60*1000);
	s.close();

	}

}

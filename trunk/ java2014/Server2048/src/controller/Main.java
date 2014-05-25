package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import solver.SendDataHint;
import model.ClienHandler;
import model.Server2048;
public class Main {

	public static void main(String[] args) throws Exception {
		
	Server2048 s = new Server2048(6951, new ClienHandler() {
		
		@Override
		public void handleClient(ObjectInputStream inFromClient ,ObjectOutputStream outToClient) throws ClassNotFoundException, IOException {
			System.out.println("start the Solver");
			SendDataHint dataFronClient = (SendDataHint) inFromClient.readObject();
			System.out.println(inFromClient);
			
		}
	}); 
	
		
	
	s.start();
	Thread.sleep(60*1000);
	s.close();

	}

}

package controller;

import java.io.BufferedReader;
import model.ClienHandler;
import model.Server2048;

public class Main {

	public static void main(String[] args) throws Exception {
		Server2048 s = new Server2048(6339, new ClienHandler() {
			
			@Override
			public void handleClient(BufferedReader inFromClient) {
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
		Thread.sleep(5000);
		s.close();

	}

}

package controller;

import solver.Solver;
import model.Server2048;

public class Main {

	public static void main(String[] args) throws Exception {
		Solver solver = new Solver();
	Server2048 s = new Server2048(6951, solver); 
	/*new ClienHandler() {
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
		})*/
	
	s.start();
	Thread.sleep(60*1000);
	s.close();

	}

}

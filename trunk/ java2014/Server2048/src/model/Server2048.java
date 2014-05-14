package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server2048 extends Thread {
	int port;
	ServerSocket server;
	boolean stop;
	ClienHandler ch;
	
	public Server2048(int port , ClienHandler ch){
		this.port = port;
		stop = false;
		this.ch = ch; 
	}
	
	public void run(){
		try {
			server = new ServerSocket(port);
			Socket client = server.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			ch.handleClient(inFromClient);
			inFromClient.close();
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void close(){
		stop = true;
	}
	
}

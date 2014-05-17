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
	
	public Server2048(int port, ClienHandler ch){
		this.port = port;
		stop = false;
		this.ch = ch; 
	}
	
	public void run(){
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(60*1000);
			Socket client = server.accept();
			System.out.println("got it");
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(inFromClient.readLine());
			ch.handleClient(inFromClient);
			inFromClient.close();
			client.close();
			server.close();
		} catch (IOException e) {
			System.out.println("tiered of waiting for connection");
			e.printStackTrace();
		}
	}
	
	
	public void close(){
		stop = true;
	}
	
}

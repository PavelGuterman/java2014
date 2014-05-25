package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(client.getOutputStream());
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//System.out.println(inFromClient.readLine());
			ch.handleClient(inFromClient, outToClient);
			inFromClient.close();
			outToClient.close();
			client.close();
			server.close();
		} catch (IOException e) {
			System.out.println("tiered of waiting for connection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void close(){
		stop = true;
	}
	
}

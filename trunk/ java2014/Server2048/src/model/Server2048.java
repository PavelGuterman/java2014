package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2048 extends Thread {
	int port;
	ServerSocket server;
	boolean stop;
	ClienHandler ch;
	int n_client;

	public Server2048(int port, ClienHandler ch) {
		this.port = port;
		stop = false;
		this.ch = ch;
	}

	public void run() {
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(60 * 1000);
			ExecutorService thredPool = Executors.newFixedThreadPool(n_client);
			try {
				while (!stop) {
					final Socket client = server.accept();
					System.out.println("got it");
					final ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
					final ObjectOutputStream outToClient = new ObjectOutputStream(client.getOutputStream());
					
					thredPool.execute(new Runnable() {
					
						@Override
						public void run() {
							try {
								ch.handleClient(inFromClient, outToClient);
								inFromClient.close();
								outToClient.close();
								client.close();
								server.close();
							} catch (Exception e) {
							}
						}
					});
				}
			} catch (SocketTimeoutException e) {
				System.out.println("tiered of waiting for connection");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		stop = true;
	}

}

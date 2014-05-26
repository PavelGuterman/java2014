package game2048;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import model.Model;

/****
 * 
 * connect to server hint
 * 
 */
public class Hint2048 extends Thread {
	private int steps, depth;
	private String address;
	private Model model;

	public Hint2048(int steps, int depth, String address, Model model) {
		super();
		this.steps = steps;
		this.depth = depth;
		this.address = address;
		this.model = model;
	}

	
	/**
	 * Open connection to Hint resolve server send to server parameters Expect
	 * from server integer of move
	 * 
	 * @param steps
	 *            - size of resorts
	 * @param address
	 *            - adders of server
	 * @param deep
	 *            - the deep of answer
	 * @see model.Model
	 */
	
	public void run() {
		System.out.println("start Hint2048 ");
		int move2make = -1;
		try {
			InetAddress netAddress = InetAddress.getByName(address);
			for (int i = 0; i < steps; i++) {

				Socket socket = new Socket(netAddress, 6951);
				ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
				writer.writeObject(new SendDataHint(model.getScore(), model.getData(), depth, "game2048")); // send
				// object
				// of
				// state
				writer.flush();
				ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
				move2make = (int) reader.read();
				System.out.println("et from server: " + move2make);
				writer.close();

				System.out.println("auto Move is " + move2make);
				setSteapFromServerHint(move2make);

				if (i < steps - 1) {
					Thread.sleep(2000);
				}
				socket.close();
			}// end of for

			/*
			 * all catches from server connection or answer
			 */
		} catch (ConnectException ce) {
			System.err.println("No connection to HINT server ");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate move from integer 0-up,1-right,2-down,3-left
	 * 
	 * @param stepNo
	 *            - int step number
	 */
	private boolean setSteapFromServerHint(int stepNo) {
		if (-1 < stepNo && stepNo < 4) {
			switch (stepNo) {
			case 0:
				model.moveUp();
				break;
			case 1:
				model.moveRight();
				break;
			case 2:
				model.moveDown();
				break;
			case 3:
				model.moveLeft();
				break;
			}
			return true;
		} else {
			return false;
		}

	}

}

package model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public interface ClienHandler {
	public void handleClient(ObjectInputStream inFromClient, ObjectOutputStream outToClient) throws ClassNotFoundException, IOException;
}

package model;

public interface Model {
	void moveUp();
	void moveDown();
	void moveRight();
	void moveLeft();
	int[][] getData();
	
	int[][] popStepBefore(); //to save and return steps before
	void restartgame(); // to restart the game 
	void saveGame(String fileName); // to save game 
	void loadGame(String fileName); // to load game
	String getMesegeString(); //to transfer messages from model to view
	int getScore();
}

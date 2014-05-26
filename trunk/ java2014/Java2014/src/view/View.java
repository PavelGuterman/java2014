package view;

import org.eclipse.swt.widgets.Shell;



public interface View {
	
	/****
	 * refresh view board with new instance
	 * @param data
	 * @param message
	 * @param score
	 */
	void dispayData(int[][] data,String message,int score);
	/****
	 * 
	 * @return number parameter of command
	 */
	int getUserCommand();
	/****
	 * create the message and type of message
	 * @param message
	 */
	void setMesegeString(String message);
	/****
	 * 
	 * @return file to save game
	 */
	String getFilePathToSave();
	/****
	 * create file to save the game
	 * @param type
	 */
	void setFilePathToSave(int type);
	
	/****
	 * 
	 * @return the shell of board
	 */
	public Shell getShell();
	
}

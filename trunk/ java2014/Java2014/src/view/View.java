package view;

import java.io.File;

public interface View {
	void dispayData(int[][] data,String message);
	int getUserCommand();
	
	void setMesegeString(String message);//message
	String getFilePathToSave();
	
	void setFilePathToSave(int type);
	
}

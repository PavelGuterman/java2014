package game2048;


import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
 


public class Board extends Composite {
	
	private  int N;
	private int[][] boardData;
	public Tile tile[][];
	
	public Board(Composite parent, int style) {
		super(parent, style);
		N=4;
		boardData=new int[N][N];
		System.out.println("sys---- "+boardData);
		setLayout(new GridLayout(N, true));
		
		tile = new Tile[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tile[i][j] = new Tile(this, SWT.BORDER);
				tile[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				tile[i][j].setVol(0);
			}
		}
	}

	
	
	public int[][] getBoardData() {
		return boardData;
	}

	public void setBoardData(int[][] boardData) {
		this.boardData = boardData;
	}
	
}
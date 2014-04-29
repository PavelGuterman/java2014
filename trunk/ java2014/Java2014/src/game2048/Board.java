package game2048;


import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
 


public class Board extends Canvas {
	
	HashMap<String , Image> tiles;
	private int[][] boardData;

	public Board(Composite parent, int style) {
		super(parent, style);
		
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Canvas canvas  = (Canvas) e.widget;
				int maxX = canvas.getSize().x;
				int maxY = canvas.getSize().y;
				int cell = Math.min(maxX,maxY)/5;
				int pad = 5;
				for(int i = 0; i < boardData.length; i++){
					for(int j = 0; j< boardData[i].length; j++){
						e.gc.drawRoundRectangle(pad+(pad+cell)*i, pad+(pad+cell)*j, cell, cell,14,14);
						if(boardData[i][j]==0) continue;
						e.gc.drawText(Integer.toString(boardData[i][j]), (pad+(pad+cell)*i+(cell/2)), (pad+(pad+cell)*j+(cell/2)));
						
					}
				}	
			}
		});
//		setLayout(new GridLayout(N, true));
//		Tile tile = new Tile(parent, style);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if(boardData[i][j]==0) continue;	
//			}
//		}
	}
	
	
	public int[][] getBoardData() {
		return boardData;
	}

	public void setBoardData(int[][] boardData) {
		this.boardData = boardData;
	}
	
}

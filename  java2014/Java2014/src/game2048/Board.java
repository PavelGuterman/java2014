package game2048;


import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
 
public class Board extends Canvas {
	
	private int[][] boardData;

	public Board(Composite parent, int style) {
		super(parent, style);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				Canvas canvas  = (Canvas) e.widget;
				int maxX = canvas.getSize().x;
				int maxY = canvas.getSize().y;
				int mx=maxX/2,my=maxY/2;
				int r=Math.min(maxX,maxY)/10;
				int cell = Math.min(maxX,maxY)/5;
				int pad = 5;
				//GC gc = e.gc;
				//gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
				//gc.setLineWidth(10);
				e.gc.drawRoundRectangle(0, 0, maxX, maxY, 5, 5);
				//e.gc.fillRoundRectangle(0, 0, maxX, maxY, 5, 5);
				for(int i = 0; i < boardData.length; i++){
					for(int j = 0; j<boardData[i].length; j++){
						e.gc.drawRoundRectangle(pad+(pad+cell)*i, pad+(pad+cell)*j, cell, cell,14,14);
						if(boardData[i][j]==0) continue;
						e.gc.drawText(Integer.toString(boardData[i][j]), (pad+(pad+cell)*i+(cell/2)), (pad+(pad+cell)*j+(cell/2)));
						//e.gc.fillRoundRectangle(x, y, width, height, arcWidth, arcHeight);
						//e.gc.fillRoundRectangle(pad+(pad+cell)*i, pad+(pad+cell)*j, cell, cell,14,14);
					}
				}	
			}
		});
	}
	
	
	public int[][] getBoardData() {
		return boardData;
	}

	public void setBoardData(int[][] boardData) {
		this.boardData = boardData;
	}
	
}

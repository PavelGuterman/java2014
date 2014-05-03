package gameMaze;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import view.Board;
import view.Tile;

public class MazeBoard extends Board {
	
	public Tile tile[][];

	public MazeBoard(Composite parent, int style, int size) {
		super(parent, style, size);
		
		this.tile = new Tile[size][size];;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.tile[i][j] = new MazeTile(this, SWT.BORDER);
				this.tile[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				this.tile[i][j].setVol(0);
			}
		}
	}

}

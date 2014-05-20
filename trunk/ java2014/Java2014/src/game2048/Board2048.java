package game2048;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import view.Board;
import view.Tile;
 

/**
 * bord for game2048
 * 
 * @see view.Board
 */
public class Board2048 extends Board {
	public Tile tile[][];
	/**
	 * Board contractor.
	 * 
	 * @see view.Board#Board(Composite, int, int)
	 */
	public Board2048(Composite parent, int style,int size) {
		super ( parent, style, size);
		
		this.tile = new Tile[size][size];//set new Tile array in border
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.tile[i][j] = new Tile2048(this, SWT.BORDER); //new tile 
				this.tile[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				this.tile[i][j].setVol(0);
			}
		}
		
		
	}

}

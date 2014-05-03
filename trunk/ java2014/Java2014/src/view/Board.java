package view;

import game2048.Tile2048;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class Board extends Composite {

	protected final int size;
	protected int[][] boardData;
	//public Tile tile[][];
	
	public Board(Composite parent, int style,int size) {
		super(parent, style);
		this.size=size;
		boardData=new int[size][size];
		setLayout(new GridLayout(size, true));
		
		
	}

}

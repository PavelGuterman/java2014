package game2048;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import view.View;

public class View2048 extends Observable implements View,Runnable {

	Display display;
	Shell shell;
	
	private void initComponents(){
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(450, 300);
		shell.setText("---2048---");
		
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("&File");
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.setText("Save");
		item.setText("Load");
		
		Button undoButton = new Button(shell, SWT.PUSH);
		undoButton.setText("UNDO");
		undoButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,1,1));
		
		Board board = new Board(shell, SWT.BORDER);
		board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		Button restartButton = new Button(shell, SWT.PUSH);
		restartButton.setText("RESTART");
		restartButton.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false,1,1));
		
		Button loadButton = new Button(shell, SWT.PUSH);
		loadButton.setText("LAOD");
		loadButton.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,false,false,1,1));
		
		Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("SAVE");
		saveButton.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,false,false,1,1));
		
		
		shell.open();
	}
	
	
	@Override
	public void dispayData(int[][] data) {
//		display.syncExec(new Runnable() {
//			@Override
//			public void run() {
//			//...
//			canvas.redraw();
//			}
//		})

	}

	@Override
	public int getUserCommand() {
//		shell.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent k_e){
//				switch(k_e.keyCode){
//				case SWT.ARROW_UP:
//					
//					break;
//				}
//			}
//		});
		return 0;
	}
	
	

	@Override
	public void run() {
		initComponents();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}

}

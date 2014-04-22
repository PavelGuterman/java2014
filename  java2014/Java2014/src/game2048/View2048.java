package game2048;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import view.View;

public class View2048 extends Observable implements View, Runnable {
	
	private Board board ;
	Display display;
	Shell shell;

	private int keyPresed;
	

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(450, 300);
		shell.setText("---2048---");

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("&File");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		item.setText("Save");
		item.setText("Load");

		Button undoButton = new Button(shell, SWT.PUSH);
		undoButton.setText("UNDO");
		undoButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 1));

		board = new Board(shell, SWT.BORDER);
		int [][] temp = {{0,0,0,0},{0,0,1,0},{0,0,0,0},{0,0,1,0}};
		//TODO set arry  
		board.setBoardData(temp);
		board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		board.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// System.out.println(arg0.keyCode);

			}

			@Override
			public void keyPressed(KeyEvent key) {
				switch (key.keyCode) {
				case 16777217:
					System.out.println("UP");
					setKeyPresed(1);
					setChanged();
					notifyObservers();
					break;
				case 16777218:
					System.out.println("Down");
					setKeyPresed(2);
					setChanged();
					notifyObservers();
					break;
				case 16777219:
					System.out.println("Left");
					setKeyPresed(3);
					setChanged();
					notifyObservers();
					break;
				case 16777220:
					System.out.println("Ritgh");
					setKeyPresed(4);
					setChanged();
					notifyObservers();
					break;

				default:
					break;
				}

				
			}
		});

		Button restartButton = new Button(shell, SWT.PUSH);
		restartButton.setText("RESTART");
		restartButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false,
				false, 1, 1));

		Button loadButton = new Button(shell, SWT.PUSH);
		loadButton.setText("LAOD");
		loadButton.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false,
				1, 1));

		Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("SAVE");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false,
				1, 1));

		shell.open();
	}

	@Override
	public void dispayData(final int[][] data) {
		System.out.println("displayData");
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				board.setBoardData(data);
				board.redraw();
			}
		});
	}

	@Override
	public int getUserCommand() {
		return keyPresed;
	}

	@Override
	public void run() {
		initComponents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		
	}
	

	
	
	public int getKeyPresed() {
		return keyPresed;
	}

	public void setKeyPresed(int keyPresed) {
		this.keyPresed = keyPresed;
	}


}

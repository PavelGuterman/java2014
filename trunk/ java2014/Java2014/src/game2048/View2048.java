package game2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import controller.Presenter;
import view.View;

public class View2048 extends Observable implements View, Runnable {

	private Board board;
	private final int boardSize;
	 

	Display display;
	Shell shell;
	private int keyPresed;

	public View2048(int boardSize) {
		super();
		this.boardSize = boardSize;
	}

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(300, 300);
		shell.setText("---2048---");
		HashMap<String , Image> tiles = new HashMap<>();
		tiles.put("2",new Image(display,"src/game2048/Tile2.jpg"));
		tiles.put("4",new Image(display,"src/game2048/Tile4.jpg"));
		tiles.put("8",new Image(display,"src/game2048/Tile8.jpg"));
		tiles.put("16",new Image(display,"src/game2048/Tile16.jpg"));
		tiles.put("32",new Image(display,"src/game2048/Tile32.jpg"));
		tiles.put("64",new Image(display,"src/game2048/Tile64.jpg"));
		tiles.put("128",new Image(display,"src/game2048/Tile128.jpg"));
		tiles.put("256",new Image(display,"src/game2048/Tile256.jpg"));
		tiles.put("512",new Image(display,"src/game2048/Tile512.jpg"));
		tiles.put("1024",new Image(display,"src/game2048/Tile1024.jpg"));
		tiles.put("2014",new Image(display,"src/game2048/Tile2048.jpg"));

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
		undoButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setKeyPresed(10);
				setChanged();
				notifyObservers();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		board = new Board(shell, SWT.BORDER);
		int[][] temp = { { 0 }, { 0 } };
		board.setBoardData(temp);
		board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 10));

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
		restartButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setKeyPresed(11);
				setChanged();
				notifyObservers();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		Button loadButton = new Button(shell, SWT.PUSH);
		loadButton.setText("LAOD");
		loadButton.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false,
				1, 1));

		Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("SAVE");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false,
				1, 1));

		shell.open();

		setKeyPresed(0);
		setChanged();
		notifyObservers();
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

	private int[][] setNewFeaalds(int[][] data) {
		int[][] newData = null;

		return newData;
	}

	public int getKeyPresed() {
		return keyPresed;
	}

	public void setKeyPresed(int keyPresed) {
		this.keyPresed = keyPresed;
	}

}

package game2048;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import view.View;

public class View2048 extends Observable implements View, Runnable {

	private Board board;
	private int score = 0;
	private final int boardSize;
	private int keyPresed;
	private String messageString = "";
	private String saveFilePath;
	Display display;
	Shell shell;
	TextLayout scoreDisplay;

	public View2048(int boardSize) {
		super();
		this.boardSize = boardSize;
	}

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(550, 500);
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

		scoreDisplay = new TextLayout(display);
		scoreDisplay.setText("SCORE: "+score+"$");
		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Paint:
					scoreDisplay.draw(event.gc, 0, 200);
					break;
				case SWT.Resize:
					scoreDisplay.setWidth(shell.getSize().x - 20);
					break;
				
				}
			}
		};

		shell.addListener(SWT.Paint, listener);
		shell.addListener(SWT.Resize, listener);
		
		Button undoButton = new Button(shell, SWT.PUSH);
		undoButton.setText("UNDO");
		undoButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 2));
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

		board = new Board(shell, SWT.BORDER,boardSize);
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
				System.out.println("restart");
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
		loadButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setFilePathToSave(SWT.LEAD);
				if (saveFilePath == null || saveFilePath == "") {
					return;
				}
				setKeyPresed(12);
				setChanged();
				notifyObservers();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("SAVE");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 1));
		saveButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("save");
				setFilePathToSave(SWT.SAVE);
				if (saveFilePath == null || saveFilePath == "") {
					return;
				}
				setKeyPresed(13);
				setChanged();
				notifyObservers();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		shell.open();

		setKeyPresed(0);
		setChanged();
		notifyObservers();
	}

	@Override
	public void dispayData(final int[][] data, String message, int score) {
		setMesegeString(message);
		setScore(score);
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!messageString.isEmpty()) {
					MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
					box.setText("Game");
					box.setMessage(messageString);
					int ret = box.open();
					if (ret == SWT.OK) {
						System.out.println("OK");
						setMesegeString("");
					}
				}
				System.out.println("score= " + View2048.this.score);
				scoreDisplay.setText("Score: " + View2048.this.score);
				board.setBoardData(data);
				board.redraw();
				
				board.setFocus();

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

	@Override
	public void setMesegeString(String message) {
		messageString = message;

	}

	@Override
	public String getFilePathToSave() {
		return saveFilePath;
	}

	@Override
	public void setFilePathToSave(int type) {
		FileDialog fd = new FileDialog(shell, type);
		fd.setText("Select File");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.txt" };
		fd.setFilterExtensions(filterExt);
		saveFilePath = fd.open();
	}

	public void setScore(int score) {
		this.score = score;
	}
}

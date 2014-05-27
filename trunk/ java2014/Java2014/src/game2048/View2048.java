package game2048;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import controller.Constants;
import view.View;
/****
 * View of game 2048 
 *@see View
 */
public class View2048 extends Observable implements View, Runnable {

	private Board2048 board;
	private int score = 0;
	private int keyPresed;
	private String messageString = "";
	private int messageType = 0;
	private String saveFilePath;
	private int[][] dataState;
	Shell shell;
	Shell perentShell;
	private Label sD;
	private final int bordSize;
/****
 * Constructor 
 * @param boardSize
 * @param perentShell
 */
	public View2048(int boardSize, Shell perentShell) {
		super();
		this.bordSize = boardSize;
		this.perentShell = perentShell;
		dataState=new int[boardSize][boardSize];
	}
	/****
	 * start UI
	 */
	private void initComponents() {

		shell = new Shell(Display.getDefault());
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(550, 500);

		// location to center
		Monitor primary = Display.getDefault().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);

		shell.setText("---2048---");
		shell.addShellListener(new ShellListener() {

			@Override
			public void shellIconified(ShellEvent arg0) {
				// Auto-generated method stub

			}

			@Override
			public void shellDeiconified(ShellEvent arg0) {
				// Auto-generated method stub

			}

			@Override
			public void shellDeactivated(ShellEvent arg0) {
				// Auto-generated method stub

			}

			@Override
			public void shellClosed(ShellEvent arg0) {
				shell.setEnabled(false);
				perentShell.setVisible(true);
				perentShell.setFocus();
			}

			@Override
			public void shellActivated(ShellEvent arg0) {
				// Auto-generated method stub

			}

		});

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("&File");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem itemSave = new MenuItem(submenu, SWT.PUSH);
		itemSave.setText("Save");
		MenuItem itemLoad = new MenuItem(submenu, SWT.PUSH);
		itemSave.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				saveGame();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		itemLoad.setText("Load");
		itemLoad.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ladeGame();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		MenuItem itemExit = new MenuItem(submenu, SWT.PUSH);
		itemExit.setText("Exit");
		itemExit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				perentShell.setVisible(true);
				perentShell.setFocus();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

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

		board = new Board2048(shell, SWT.BORDER, bordSize);
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
					setKeyPresed(Constants.MUVE_UP);
					setChanged();
					notifyObservers();
					break;
				case 16777218:
					System.out.println("Down");
					setKeyPresed(Constants.MUVE_DOWN);
					setChanged();
					notifyObservers();
					break;
				case 16777219:
					System.out.println("Left");
					setKeyPresed(Constants.MUVE_LEFT);
					setChanged();
					notifyObservers();
					break;
				case 16777220:
					System.out.println("Ritgh");
					setKeyPresed(Constants.MUVE_RIGHT);
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
				setKeyPresed(Constants.RESTART);
				setChanged();
				notifyObservers();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		Button loadButton = new Button(shell, SWT.PUSH);
		loadButton.setText("LOAD");
		loadButton.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false,
				1, 1));
		loadButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ladeGame();
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
				saveGame();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		Button hintButton = new Button(shell, SWT.PUSH);
		hintButton.setText("HINT");
		hintButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 2));
		hintButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// display.wait();
				hint();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		sD = new Label(shell, SWT.TITLE);
		sD.setText(getScore());
		sD.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 2));

		shell.open();

		setKeyPresed(Constants.START_GAME);
		setChanged();
		notifyObservers();
	}
/*
 * dispayDataui data display
 * @see view.View#dispayData(int[][], java.lang.String, int)
 */
	@Override
	public void dispayData(final int[][] data, String message, int score) {
		if (checkIfGameIsOver(data)) {
			MessageBox box = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES
					| SWT.NO);
			box.setText("Game Over");
			box.setMessage(messageString);
			int ret = box.open();
			if (ret == SWT.YES) {
				setKeyPresed(Constants.RESTART);
				setChanged();
				notifyObservers();

			} else {
				shell.setEnabled(false);
				perentShell.setVisible(true);
			}
			setMesegeString("");
		}
		setMesegeString(message);
		setScore(score);
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				if (!messageString.isEmpty()) {
					MessageBox box = new MessageBox(shell, messageType);
					box.setText("Game");
					box.setMessage(messageString);
					int ret = box.open();
					if (ret == SWT.OK) {
						System.out.println("OK");
						setMesegeString("");
					}
				}
				 
					for (int i = 0; i < data.length; i++) {
						for (int j = 0; j < data.length; j++) {
							board.tile[j][i].setVol(data[i][j]);
						}
					}
				
				System.out.println("score= " + View2048.this.score);
				sD.setText("Score: " + View2048.this.score);
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
	}

	public int getKeyPresed() {
		return keyPresed;
	}

	public void setKeyPresed(int keyPresed) {
		this.keyPresed = keyPresed;
	}
	/*
	 * create massage from model
	 * @see view.View#setMesegeString(java.lang.String)
	 */
	@Override
	public void setMesegeString(String message) {
		if (message.indexOf(Constants.MESAGE_DIVIDE) > -1) {
			String[] str = message.split(Constants.MESAGE_DIVIDE);
			messageString = str[0];
			try {
				messageType = Integer.parseInt(str[1]);
			} catch (Exception e) {
				messageType = SWT.ICON_INFORMATION;
			}
		} else {
			messageString = message;
			messageType = SWT.ICON_INFORMATION;
		}

	}

	@Override
	public String getFilePathToSave() {
		return saveFilePath;
	}
/*
 * file path
 * @see view.View#setFilePathToSave(int)
 */
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

	private String getScore() {
		return Integer.toString(score);
	}

	private void ladeGame() {
		setFilePathToSave(SWT.LEAD);
		if (saveFilePath == null || saveFilePath == "") {
			return;
		}
		setKeyPresed(Constants.LOAD_GAME);
		setChanged();
		notifyObservers();
	}

	private void saveGame() {
		System.out.println("save");
		setFilePathToSave(SWT.SAVE);
		if (saveFilePath == null || saveFilePath == "") {
			return;
		}
		setKeyPresed(Constants.SAVE_GAME);
		setChanged();
		notifyObservers();
	}
	/**
	 * Start hint resolts
	 * connect to server from presenter to model
	 */
	protected void hint() {
		setKeyPresed(50);
		setChanged();
		notifyObservers();
	}
	
	public int[][] getdataState() {
		return dataState;
	}
	/***
	 * to now if game is over
	 */
	private boolean checkIfGameIsOver(int[][] data) {
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 2048) {//the game is over, win
					messageString = "You Win the game !!! \n Do you whont to play again ?";
					messageType = SWT.ICON_QUESTION;
					return true;
				}
				if (data[i][j] == 0) { // if data have empty please
					dataState=data;
					return false;
				}

			}
		}

		messageString = "You lost the game !!! \n Do you whont to play again ?"; //game is over, lost
		messageType = SWT.ICON_QUESTION;
		return true;
	}
	
	/*
	 * @see view.View#getShell()
	 */
	@Override
	public Shell getShell() {
		return shell;
	}
}

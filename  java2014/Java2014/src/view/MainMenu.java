package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import game2048.Main2048;
import gameMaze.MazeMain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * Main menu. start program ! 
 * Shows menu of games.
 * 
 */
public class MainMenu extends Thread {
	Display display;
	Shell shell;

	public MainMenu() {
		
	}
	
	/**
	 * view menu.
	 * Game2048
	 * mazeGame
	 */
	private void initComponents() {
		display = new Display();
		shell = new Shell(display);

		shell.setText("Hello");

		shell.setSize(200, 400);
		
		Monitor primary = display.getPrimaryMonitor ();
		Rectangle bounds = primary.getBounds ();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);

		Button btn_game2048 = new Button(shell, SWT.PUSH);
		btn_game2048.setText("Play 2048");

		Button btn_gameMaze = new Button(shell, SWT.PUSH);
		btn_gameMaze.setText("Play Maze");

		btn_game2048.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btn_gameMaze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		btn_game2048.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				Main2048.startGame2048(shell);
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btn_gameMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				MazeMain.startGameMaze(shell);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		shell.open();
	}
	/**
	 * Runnable method to view window.
	 * include while to display
	 */
	public void run() {
		initComponents();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();

			}
		}
		display.dispose();

	}

	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.start();
		
		
	}
}

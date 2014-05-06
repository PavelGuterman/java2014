package view;

import game2048.Main2048;
import game2048.Model2048;
import game2048.View2048;
import gameMaze.MazeMain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controller.Presenter;

public class MainMenu extends Thread {
	Display display;
	Shell shell;

	public MainMenu() {
		
	}

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);

		shell.setText("Hello");

		shell.setSize(400, 400);
		shell.setLayout(new GridLayout(2, true));

		Button btn_game2048 = new Button(shell, SWT.PUSH);
		btn_game2048.setText("Play 2048");

		Button btn_gameMaze = new Button(shell, SWT.PUSH);
		btn_gameMaze.setText("Play Maze");

		btn_game2048.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btn_gameMaze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		btn_game2048.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				display.close();
				Main2048.startGame2048();
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btn_gameMaze.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				display.close();
				MazeMain.startGameMaze();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		shell.open();
	}

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

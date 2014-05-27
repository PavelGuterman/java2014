package view;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import controller.RunServer;



public class ServerMenu  extends Thread{
	Display display;
	Shell shell;
	
	RunServer server;
	
	
	
	public ServerMenu() {
		server = new RunServer();
		server.start();
	}

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Game Server ");
		shell.setLayout(new GridLayout(2, false));
		shell.setSize(300, 200);
		
		Monitor primary = display.getPrimaryMonitor ();
		Rectangle bounds = primary.getBounds ();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);
		
		shell.addShellListener(new ShellListener() {
			
			@Override
			public void shellIconified(ShellEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeiconified(ShellEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeactivated(ShellEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellClosed(ShellEvent arg0) {
				
				server.stopServer();
			}
			
			@Override
			public void shellActivated(ShellEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btn_StartServer = new Button(shell, SWT.PUSH);
		btn_StartServer.setText("Start");
		btn_StartServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btn_StartServer.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				server.startServer();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		
		Button btn_StopServer = new Button(shell, SWT.PUSH);
		btn_StopServer.setText("Stop");
		btn_StopServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btn_StopServer.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(server.stopServer()){
					
				}else{
					
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

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
		ServerMenu menu = new ServerMenu();
		menu.start();
		
		
	}
}

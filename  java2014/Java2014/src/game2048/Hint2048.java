package game2048;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;


public class Hint2048 {
	
	Shell hintShell;
	View2048 view2048;
	Shell perentShell;
	int n_moves;
	
	public Hint2048(Shell perentShell) {
		
		hintShell=new Shell(Display.getDefault());
		hintShell.pack();
		this.perentShell=perentShell;
	}
	
	
	private void initComponents() {
		//hintShell = new Shell(hintDisplay);
		hintShell.setLayout(new GridLayout(2, false));
		hintShell.setSize(155,150);
		hintShell.setLayout(new GridLayout());
		
		//location to center
		Monitor primary = Display.getDefault().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = hintShell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		hintShell.setLocation(x, y);
		
		
		new Label(hintShell, SWT.NONE).setText("Choose number of Moves");
		String[] moveOption = "1 2 3 4 5 solve".split(" ");
		
		final Combo combo = new Combo(hintShell, SWT.DROP_DOWN);
		combo.setItems(moveOption);
		System.out.println(combo.getData());
 
		Button executeButton = new Button(hintShell, SWT.PUSH);
		executeButton.setText("GO");
		executeButton.setLayoutData(new GridData());
		executeButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(combo.getItem(combo.getSelectionIndex()));
				n_moves = (int) combo.getData();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		hintShell.addShellListener(new ShellListener() {
			
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
				System.out.println("sdsds ");
				hintShell.setEnabled(false);
				perentShell.setEnabled(true);
			}
			
			@Override
			public void shellActivated(ShellEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		hintShell.open();
		
	}
	
	public void openWIn(){
		initComponents();
	}
}

package game2048;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import view.MainMenu;
import view.View;

public class Hint2048 {
	
	Shell hintShell;
	View2048 view2048;
	Shell perentShell;
	
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
		
		new Label(hintShell, SWT.NONE).setText("Choose number of Moves");
		String[] moveOption = "1_move 2_moves 3_moves 4_moves solve".split(" ");
		
		final Combo combo = new Combo(hintShell, SWT.DROP_DOWN);
		combo.setItems(moveOption);
 
		Button executeButton = new Button(hintShell, SWT.PUSH);
		executeButton.setText("GO");
		executeButton.setLayoutData(new GridData());
		executeButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(combo.getItem(combo.getSelectionIndex()));
				
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

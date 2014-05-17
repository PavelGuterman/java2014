package game2048;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import view.View;

public class Hint2048 implements Runnable{
	
	Display hintDisplay;
	Shell hintShell;
	
	public Hint2048() {
		this.hintDisplay = new Display();
	}
	
	
	private void initComponents() {
		hintShell = new Shell(hintDisplay);
		hintShell.setLayout(new GridLayout(2, false));
		hintShell.setSize(155,150);
		hintShell.setLayout(new GridLayout());
		new Label(hintShell, SWT.NONE).setText("Choose number of Moves");
		String[] moveOption = "1_move 2_moves 3_moves 4_moves solve".split(" ");
		Combo combo = new Combo(hintShell, SWT.DROP_DOWN);
		combo.setItems(moveOption);
		Button executeHint = new Button(hintShell, SWT.PUSH);
		executeHint.setText("GO");
		executeHint.setLayoutData(new GridData());
		executeHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		hintShell.open();
		
	}
	
	public void run(){
		initComponents();
		while(!hintShell.isDisposed()){
			if(hintDisplay.readAndDispatch()){
				hintDisplay.sleep();
			}
		}
		hintDisplay.dispose();
	}
}

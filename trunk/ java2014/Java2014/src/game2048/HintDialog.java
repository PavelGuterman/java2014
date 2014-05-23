package game2048;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class HintDialog extends Dialog {
	int n_moves;

	/**
	 * @param parent
	 */
	public HintDialog(Shell parent) {
		super(parent);
	}

	/**
	 * @param parent
	 * @param style
	 */
	public HintDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Makes the dialog visible.
	 * 
	 * @return
	 */
	public int open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Number of steps");

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setText("Choose number of Moves - ");

		String[] moveOption = " 1 2 3 4 5 solve".split(" ");
		final Combo combo = new Combo(shell, SWT.DROP_DOWN);
		combo.setItems(moveOption);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		combo.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				try {
					n_moves = Integer.parseInt(combo.getItem(combo.getSelectionIndex()));
					buttonOK.setEnabled(true);
				} catch (NumberFormatException e) {
					String inp = combo.getItem(combo.getSelectionIndex());
					buttonOK.setEnabled(true);
					if (inp.equals("solve")) {
						n_moves = 0;
					} else {
						n_moves = -1;
						buttonOK.setEnabled(false);
					}
				} catch (IllegalArgumentException e) {
					buttonOK.setEnabled(false);
					n_moves = -3;
				}
				//System.out.println("select in dialog:" + n_moves);
			}
		});

		buttonOK.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				n_moves = -1;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		combo.setText("");
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return n_moves;
	}

//	public static void main(String[] args) {
//		Shell shell = new Shell();
//		HintDialog dialog = new HintDialog(shell);
//		System.out.println(dialog.open());
//	}
}

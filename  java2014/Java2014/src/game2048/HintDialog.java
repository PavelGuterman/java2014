package game2048;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
import org.eclipse.swt.widgets.Text;

public class HintDialog extends Dialog {

	private int n_steps;
	private int n_depth;
	private String serverAddresText;

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
		try {
			serverAddresText = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			serverAddresText = "localhost";
			e1.printStackTrace();
		}
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Server Query");

		shell.setLayout(new GridLayout(2, true));

		// server adders
		Label label1 = new Label(shell, SWT.NULL);
		label1.setText("Server addres: ");
		final Text inp_ServerAddres = new Text(shell, SWT.SINGLE | SWT.BORDER);
		inp_ServerAddres.setText(serverAddresText);

		inp_ServerAddres.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event e) {
				serverAddresText = inp_ServerAddres.getText();
			}
		});

		// deep
		Label label2 = new Label(shell, SWT.NULL);
		label2.setText("Choose the deep: ");

		String[] moveOption = "1 2 3 4 5 6 7 8 9 10".split(" ");
		final Combo cmb_Depth = new Combo(shell, SWT.DROP_DOWN);
		cmb_Depth.setItems(moveOption);
		cmb_Depth.select(1);

		// Steps
		Label label3 = new Label(shell, SWT.NULL);
		label3.setText("Number of steps: ");

		final Text inp_Steps = new Text(shell, SWT.SINGLE | SWT.BORDER);
		inp_Steps.setTextLimit(2);
		inp_Steps.setText("1");
		n_steps=1;
		inp_Steps.selectAll();
		inp_Steps.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("sdsdsssssss");
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}	
				n_steps = Integer.parseInt(string);
			}
			
		});

		// buttons
		final Button btn_OK = new Button(shell, SWT.PUSH);
		btn_OK.setText("Ok");
		btn_OK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button btn_Cancel = new Button(shell, SWT.PUSH);
		btn_Cancel.setText("Cancel");

		cmb_Depth.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {

				if (inp_ServerAddres.getText().length() < 2) {
					inp_ServerAddres.setFocus();
					return;
				}
				try {
					n_depth = Integer.parseInt(cmb_Depth.getItem(cmb_Depth.getSelectionIndex()));
					btn_OK.setEnabled(true);
				} catch (NumberFormatException e) {
					n_depth = -1;
					btn_OK.setEnabled(false);
				} catch (IllegalArgumentException e) {
					btn_OK.setEnabled(false);
					n_depth = -3;
				}
			}
		});

		btn_OK.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});

		btn_Cancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				n_depth = -1;
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});

		cmb_Depth.setText("");
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return n_depth;
	}

	public String getServerAddresText() {
		return serverAddresText;
	}
	
	public int getSteps() {
		return n_steps;
	}

//	public static void main(String[] args) {
//		Shell shell = new Shell();
//		HintDialog dialog = new HintDialog(shell);
//		System.out.println(dialog.open());
//	}
}

package controller;

import game2048.HintDialog;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import view.View;
import model.Model;

public class Presenter implements Observer {
	Model mod;
	View ui;

	public Presenter(Model mod, View ui) {
		this.mod = mod;
		this.ui = ui;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == ui) {
			System.out.println("ui command");
			switch (ui.getUserCommand()) {
			case 0:// start game
				ui.dispayData(mod.getData(), "Ready to play !" + "&&" + SWT.ICON_INFORMATION, mod.getScore());
				break;
			case 1:
				mod.moveUp();
				break;
			case 2:
				mod.moveDown();
				break;
			case 3:
				mod.moveLeft();
				break;
			case 4:
				mod.moveRight();
				break;
			case 10: // undo pressed
				int[][] step = mod.popStepBefore();
				if (step != null) {
					ui.dispayData(step, "", mod.getScore());
				}
				break;
			case 11: // restart pressed
				mod.restartgame();
				ui.dispayData(mod.getData(), "New Game" + "&&" + SWT.ICON_INFORMATION, mod.getScore());
				break;
			case 12: // load pressed
				mod.loadGame(ui.getFilePathToSave());
				break;
			case 13: // save pressed
				mod.saveGame(ui.getFilePathToSave());
				break;
			case 50: // connect to Hint server
				Shell shell = new Shell();
				HintDialog dialog = new HintDialog(shell);

				int deep = dialog.open();
				String serverAddres = dialog.getServerAddresText();

				if (deep > 0) {
					if (serverAddres.length() < 2) {
						return;
					}
					int steps = dialog.getSteps(); 	
					mod.connectToHintServerAndSendParameters(steps, deep, serverAddres);
				}

				break;
			default:
				break;
			}

		} else {
			// ui.setMesegeString(mod.getMesegeString());
			ui.dispayData(mod.getData(), mod.getMesegeString(), mod.getScore());
		}
	}
}

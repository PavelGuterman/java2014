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
/**
 * Presenter contriver
 * @param o - object that is Observable
 * @param arg - object of argument
 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == ui) {
			System.out.println("ui command");
			switch (ui.getUserCommand()) {
			case 0:// start game
				ui.dispayData(mod.getData(), "Ready to play !" + "&&" + SWT.ICON_INFORMATION, mod.getScore());
				break;
			case Constants.MUVE_UP:
				mod.moveUp();
				break;
			case Constants.MUVE_DOWN:
				mod.moveDown();
				break;
			case Constants.MUVE_LEFT:
				mod.moveLeft();
				break;
			case Constants.MUVE_RIGHT:
				mod.moveRight();
				break;
			case Constants.UNDO: // undo pressed
				int[][] step = mod.popStepBefore();
				if (step != null) {
					ui.dispayData(step, "", mod.getScore());
				}
				break;
			case Constants.RESTART: // restart pressed
				mod.restartgame();
				ui.dispayData(mod.getData(), "New Game" + "&&" + SWT.ICON_INFORMATION, mod.getScore());
				break;
			case Constants.LOAD_GAME: // load pressed
				mod.loadGame(ui.getFilePathToSave());
				break;
			case Constants.SAVE_GAME: // save pressed
				mod.saveGame(ui.getFilePathToSave());
				break;
			case Constants.HINT_GAME: // connect to Hint server
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

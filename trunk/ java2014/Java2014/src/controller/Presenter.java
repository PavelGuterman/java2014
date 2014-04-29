package controller;

import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

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
			case 0://start game 
				ui.dispayData(mod.getData());
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
				int[][] step=mod.popStepBefore();
				if(step!=null){
					ui.dispayData(step);
				}
				break;	
			case 11: // undo pressed
				mod.restartgame();
				ui.dispayData(mod.getData());
				break;
			case 12: // load pressed
				mod.loadGame();
				break;
			case 13: // save pressed
				mod.saveGame();
				break;		
			default:
				break;
			}

		}else{
			ui.dispayData(mod.getData());
			ui.setMesegeString(mod.getMesegeString());
		}
	}
}

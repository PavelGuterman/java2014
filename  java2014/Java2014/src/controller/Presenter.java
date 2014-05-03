package controller;

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
				ui.dispayData(mod.getData(),"Ready to play",mod.getScore());
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
					ui.dispayData(step,"",mod.getScore());
				}
				break;	
			case 11: // restart pressed
				mod.restartgame();
				ui.dispayData(mod.getData(),"New Game",mod.getScore());
				break;
			case 12: // load pressed
				mod.loadGame(ui.getFilePathToSave());
				break;
			case 13: // save pressed
				mod.saveGame(ui.getFilePathToSave());
				break;		
			default:
				break;
			}

		}else{
			//ui.setMesegeString(mod.getMesegeString());
			ui.dispayData(mod.getData(),mod.getMesegeString(),mod.getScore());
		}
	}
}

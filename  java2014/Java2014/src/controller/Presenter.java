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

			default:
				break;
			}
			// ui.dispayData(mod.getData());

		}else{
			ui.dispayData(mod.getData());
		}
	}
}

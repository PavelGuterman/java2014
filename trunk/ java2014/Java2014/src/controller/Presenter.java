package controller;

import java.util.Observable;
import java.util.Observer;

import view.View;
import model.Model;



public class Presenter implements Observer {
	Model mod;
	View ui;
	
	
	@Override
	public void update(Observable o, Object arg) {
		if(o==ui){
			ui.dispayData(mod.getData());
		}
		
	}
}

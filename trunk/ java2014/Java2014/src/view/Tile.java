package view;

import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas {
	protected HashMap<Integer, Image> tiles; 
	protected int vol;
	
	public Tile(Composite parent, int style) {
		super(parent, style);
		
		inishilizeHashMap();
		vol = 0;
		
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				int oWidth = e.width;
				int oHigth = e.height;
				e.gc.drawImage(tiles.get(vol), 0, 0, tiles.get(vol).getBounds().width, 
						tiles.get(vol).getBounds().height, 0, 0, oWidth, oHigth);
			}
		});
		 parent.setFocus();
	}
	
	/**
	 * Tiles icons
	 */
	
	protected void inishilizeHashMap(){
		tiles = new HashMap<>();
		
	}
	
	protected HashMap<Integer, Image> getTiles(){
		return tiles;
	}
	
	
	public int getVol(){
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
		redraw();
	}
	
}

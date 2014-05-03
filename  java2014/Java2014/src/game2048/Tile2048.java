package game2048;

import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import view.Tile;

public class Tile2048 extends Tile {
	

	public Tile2048(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void inishilizeHashMap() {
		super.inishilizeHashMap();
		
		tiles.put(0,new Image(this.getDisplay(),"src/resource/Image/Tile0.jpg"));
		tiles.put(2,new Image(this.getDisplay(),"src/resource/Image/Tile2.jpg"));
		tiles.put(4,new Image(this.getDisplay(),"src/resource/Image/Tile4.jpg"));
		tiles.put(8,new Image(this.getDisplay(),"src/resource/Image/Tile8.jpg"));
		tiles.put(16,new Image(this.getDisplay(),"src/resource/Image/Tile16.jpg"));
		tiles.put(32,new Image(this.getDisplay(),"src/resource/Image/Tile32.jpg"));
		tiles.put(64,new Image(this.getDisplay(),"src/resource/Image/Tile64.jpg"));
		tiles.put(128,new Image(this.getDisplay(),"src/resource/Image/Tile128.jpg"));
		tiles.put(256,new Image(this.getDisplay(),"src/resource/Image/Tile256.jpg"));
		tiles.put(512,new Image(this.getDisplay(),"src/resource/Image/Tile512.jpg"));
		tiles.put(1024,new Image(this.getDisplay(),"src/resource/Image/Tile1024.jpg"));
		tiles.put(2014,new Image(this.getDisplay(),"src/resource/Image/Tile2048.jpg"));
		
	}
	
	
	


}

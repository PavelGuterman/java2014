package game2048;

import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas {

	private HashMap<String, Image> tiles; 
	private int vol;
	
	Tile(Composite parent, int style) {
		super(parent, style);
		tiles = new HashMap<>();
		vol = 0;
		
		tiles.put("2",new Image(this.getDisplay(),"src/Tile2.jpg"));
		tiles.put("4",new Image(this.getDisplay(),"src/Tile4.jpg"));
		tiles.put("8",new Image(this.getDisplay(),"src/Tile8.jpg"));
		tiles.put("16",new Image(this.getDisplay(),"src/Tile16.jpg"));
		tiles.put("32",new Image(this.getDisplay(),"src/Tile32.jpg"));
		tiles.put("64",new Image(this.getDisplay(),"src/Tile64.jpg"));
		tiles.put("128",new Image(this.getDisplay(),"src/Tile128.jpg"));
		tiles.put("256",new Image(this.getDisplay(),"src/Tile256.jpg"));
		tiles.put("512",new Image(this.getDisplay(),"src/Tile512.jpg"));
		tiles.put("1024",new Image(this.getDisplay(),"src/Tile1024.jpg"));
		tiles.put("2014",new Image(this.getDisplay(),"src/Tile2048.jpg"));
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				e.gc.drawImage(tiles.get(vol), x, y);
				
			}
		});
		 
	}

	public HashMap<String, Image> getTiles() {
		return tiles;
	}


}

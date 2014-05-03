package gameMaze;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import view.Tile;

public class MazeTile extends Tile {

	public MazeTile(Composite parent, int style) {
		super(parent, style);
	}
	
	protected void inishilizeHashMap() {
		tiles.put(-1,new Image(this.getDisplay(),"src/resource/TileImage/wall.jpg"));
		tiles.put(0,new Image(this.getDisplay(),"src/resource/TileImage/FreeTile.jpg"));
		tiles.put(1,new Image(this.getDisplay(),"src/resource/TileImage/mouse.jpg"));
		tiles.put(2,new Image(this.getDisplay(),"src/resource/TileImage/cheasse.jpg"));
		tiles.put(3,new Image(this.getDisplay(),"src/resource/TileImage/coolMouse.jpg"));
	}
}

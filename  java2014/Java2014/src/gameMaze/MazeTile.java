package gameMaze;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import view.Tile;

public class MazeTile extends Tile {

	public MazeTile(Composite parent, int style) {
		super(parent, style);
	}
	
	protected void inishilizeHashMap() {
		super.inishilizeHashMap();
		
		tiles.put(0,new Image(this.getDisplay(),"src/resource/MazeImage/FreeTile.jpg"));
		tiles.put(1,new Image(this.getDisplay(),"src/resource/MazeImage/mouse.jpg"));
		tiles.put(2,new Image(this.getDisplay(),"src/resource/MazeImage/cheasse.jpg"));
		tiles.put(3,new Image(this.getDisplay(),"src/resource/MazeImage/coolMouse.jpg"));
		tiles.put(-1,new Image(this.getDisplay(),"src/resource/MazeImage/wall.jpg"));
	}
}

package game2048;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import view.Tile;

/**
 * Tile for Game2048
 * 
 * @see view.Tile
 */
public class Tile2048 extends Tile {
	/**
	 * tile contractor to game2048
	 * 
	 * @see view.Tile#Tile(Composite, int)
	 */
	public Tile2048(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Set tiles Has map to game2048 Images from resource package liberty
	 * 
	 * @see view.Tile#inishilizeHashMap()
	 */
	@Override
	protected void inishilizeHashMap() {
		super.inishilizeHashMap();

		tiles.put(0, new Image(this.getDisplay(), "src/resource/TileImage/Tile0.jpg"));
		tiles.put(2, new Image(this.getDisplay(), "src/resource/TileImage/Tile2.jpg"));
		tiles.put(4, new Image(this.getDisplay(), "src/resource/TileImage/Tile4.jpg"));
		tiles.put(8, new Image(this.getDisplay(), "src/resource/TileImage/Tile8.jpg"));
		tiles.put(16, new Image(this.getDisplay(), "src/resource/TileImage/Tile16.jpg"));
		tiles.put(32, new Image(this.getDisplay(), "src/resource/TileImage/Tile32.jpg"));
		tiles.put(64, new Image(this.getDisplay(), "src/resource/TileImage/Tile64.jpg"));
		tiles.put(128, new Image(this.getDisplay(), "src/resource/TileImage/Tile128.jpg"));
		tiles.put(256, new Image(this.getDisplay(), "src/resource/TileImage/Tile256.jpg"));
		tiles.put(512, new Image(this.getDisplay(), "src/resource/TileImage/Tile512.jpg"));
		tiles.put(1024, new Image(this.getDisplay(), "src/resource/TileImage/Tile1024.jpg"));
		tiles.put(2014, new Image(this.getDisplay(), "src/resource/TileImage/Tile2048.jpg"));

	}

}

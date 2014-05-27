package view;

import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
/**
 * Tile class. contract all tiles on board.
 */
public class Tile extends Canvas {
	/** Has map of icons  **/
	protected HashMap<Integer, Image> tiles; 
	/** vol	of tile **/
	protected int vol;
	/**
	 * Tile Contractor
	 * @param parent	parent that hold this tile
	 * @param style		integer of SWT stile  
	 **/
	public Tile(Composite parent, int style) {
		super(parent, style);
		
		inishilizeHashMap();
		vol = 0;
		// Listener 
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
	 * Initialize HasMap of tiles Tiles icons ,use on extended class of specific tile of game
	 * set tiles to new has map
	 **/
	protected void inishilizeHashMap(){
		tiles = new HashMap<Integer, Image>();
		
	}
	
	/**
	 *@return	tiles member 
	 */
	protected HashMap<Integer, Image> getTiles(){
		return tiles;
	}
	
	
	public int getVol(){
		return vol;
	}
	
	/**
	 * set type of tile  
	 * @param vol of tile
	 */
	public void setVol(int vol) {
		this.vol = vol;
		redraw();
	}
	
}

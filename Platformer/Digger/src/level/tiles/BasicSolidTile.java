package level.tiles;

import gfx.Sprite;

public class BasicSolidTile extends BasicTile {

	public BasicSolidTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean isSolid(){
		return true;
	}

}

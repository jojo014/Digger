package level.tiles;

import gfx.Sprite;

public class BasicHarmfulTile extends BasicSolidTile {

	public BasicHarmfulTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean isHarmful(){
		return true;
	}

}

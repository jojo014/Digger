package level.tiles;

import gfx.Screen;
import gfx.Sprite;

public class Tile {

	public static Tile grass = new BasicTile(Sprite.grass);

	public static Tile voidTile = new BasicSolidTile(Sprite.voidTile);

	public static Tile background = new BasicTile(Sprite.background);

	public static Tile dirt = new BasicSolidTile(Sprite.dirt);
	
	public static Tile spikes = new BasicHarmfulTile(Sprite.spikes);

	public Sprite sprite;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isSolid() {
		return false;
	}
	
	public boolean isHarmful(){
		return false;
	}

	

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}

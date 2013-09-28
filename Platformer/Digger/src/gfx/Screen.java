package gfx;

import level.tiles.Tile;

public class Screen {

	public int[] pixel;

	public int xOffset = 0;
	public int yOffset = 0;

	public int width;
	public int height;
	public int scale;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixel = new int[width * height];
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = tile.sprite.pixels[x + y * 16];
				if (col != 0xffff00ff)
					pixel[xa + ya * width] = col;

			}
		}
	}
	
	

	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0xffff00ff)
					pixel[xa + ya * width] = col;

			}
		}

	}

	public void renderHud(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[x + y * 16];
				if (col != 0xffff00ff)
					pixel[xa + ya * width] = col;

			}
		}
	}
}

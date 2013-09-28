package gfx;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidTile = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite background = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite spikes = new Sprite(16, 4, 0, SpriteSheet.tiles);

	public static Sprite playerIdle = new Sprite(32, 0, 0, SpriteSheet.mobs);
	public static Sprite playerIdle2 = new Sprite(32, 1, 0, SpriteSheet.mobs);
	public static Sprite spiderIdle1 = new Sprite(32, 2, 0, SpriteSheet.mobs);
	public static Sprite spiderIdle2 = new Sprite(32, 3, 0, SpriteSheet.mobs);

	public static Sprite healthBar0 = new Sprite(16, 0, 2, SpriteSheet.mobs);
	public static Sprite healthBar1 = new Sprite(16, 0, 3, SpriteSheet.mobs);
	public static Sprite healthBar2 = new Sprite(16, 0, 4, SpriteSheet.mobs);
	public static Sprite staminaBar0 = new Sprite(16, 1, 2, SpriteSheet.mobs);
	public static Sprite staminaBar1 = new Sprite(16, 1, 3, SpriteSheet.mobs);
	public static Sprite heart = new Sprite(16, 2, 2, SpriteSheet.mobs);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}

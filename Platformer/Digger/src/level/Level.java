package level;

import gfx.Screen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import level.tiles.Tile;
import display.Game;
import display.entities.Entity;
import display.entities.Player;
import display.entities.Spider;

public class Level {

	private int[] tiles;
	public int width, height;
	public static List<Entity> entities = new ArrayList<Entity>();
	public Player player;
	public Spider spider;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Level(String path) {
		loadLevel(path);
		player = new Player(this, 100, 100, Game.input);
		spider = new Spider(this, 100, 100);
	}

	protected void loadLevel(String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(Level.class.getResource(path));

			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		if (xScroll < 0)
			xScroll = 0;
		if (xScroll > ((width << 4) - screen.width))
			xScroll = ((width << 4) - screen.width);
		if (yScroll < 0)
			yScroll = 0;
		if (yScroll > ((height << 4) - screen.height))
			yScroll = ((height << 4) - screen.height);
		screen.setOffset(xScroll, yScroll);

		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

	}

	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height) {
			return Tile.voidTile;
		}
		if (tiles[x + y * width] == 0xff00FF00) {
			return Tile.grass;
		}
		if(tiles [x + y * width] == 0xff0000FF){
			return Tile.background;
		}
		if(tiles [x + y * width] == 0xff808080){
			return Tile.dirt;
		}
		if(tiles [x + y * width] == 0xffffffff){
			return Tile.spikes;
		} else {
			return Tile.voidTile;
		}
	}

	public void tick() {
		for(Entity e : entities){
			e.tick();
		}
		
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}

	public static void renderEntities(Screen screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}
}

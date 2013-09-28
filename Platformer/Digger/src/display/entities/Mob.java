package display.entities;

import level.Level;
import level.tiles.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected int speed;
	protected Player player;
	protected int numSteps = 0;
	public boolean isDead = false;
	protected int movingDir = 0;
	protected boolean isMoving;

	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		
		numSteps++;

		if (!hasCollided(xa, ya)) {
			if (xa > 0) {
				movingDir = 1;
			}
			if (xa < 0) {
				movingDir = 2;
			}
			x += xa * speed;
			y += ya * speed;
		}

		else {
			xa = 0;
			ya = 0;
		}

		

	}

	

	public abstract boolean hasCollided(int xa, int ya);

	public abstract boolean hitHarmfulTile(int xa, int ya);

	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}

		Tile lastTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile newTile = level.getTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	protected boolean isHarmfulTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}

		Tile lastTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile newTile = level.getTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		if (!lastTile.equals(newTile) && newTile.isHarmful()) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

}

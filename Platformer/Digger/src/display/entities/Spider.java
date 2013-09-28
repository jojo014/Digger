package display.entities;

import gfx.Screen;
import gfx.Sprite;

import java.util.Random;

import display.Game;
import display.InputHandler;

import level.Level;
import level.tiles.Tile;

public class Spider extends Mob {

	private Screen screen;
	private Game game;
	private InputHandler input;
	private boolean moving = false;
	private int movingDir = 0;
	private int movingTimer = 0;
	public int jumpTimer = 0;
	public double fullHealth = 100;
	public double health = fullHealth;
	public int healing = 0;
	public double healthPercent;
	public int staminaDegen = 0;
	public int damageTimer = 0;
	public double stamina = 50;
	public double staminaPercent;
	public int updates = 0;
	public boolean respawned = false;
	private Tile tile;

	private boolean jumping = false;

	public Spider(Level level, int x, int y) {
		super(level, "Spider", x, y, 1);
		this.input = input;
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 2;
		int xMax = 19;
		int yMin = 0;
		int yMax = 29;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;

			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;

			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}

		return false;
	}

	public boolean hitHarmfulTile(int xa, int ya) {
		int xMin = 2;
		int xMax = 19;
		int yMin = 0;
		int yMax = 29;
		for (int x = xMin; x < xMax; x++) {
			if (isHarmfulTile(xa, ya, x, yMin)) {
				return true;

			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isHarmfulTile(xa, ya, x, yMax)) {
				return true;

			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isHarmfulTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isHarmfulTile(xa, ya, xMax, y)) {
				return true;
			}
		}

		return false;
	}

	public boolean die(int xa, int ya) {
		if (hitHarmfulTile(xa, ya)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void tick() {
		moving = false;
		updates++;
		healthPercent = health / 100;
		staminaPercent = stamina / 50;
		healing++;

		if (healing > 50) {
			health += 5;
			stamina += 5;
			healing = 0;
		}
		if (health < 0 || health > 100) {
			health = 100;
		}
		if (stamina < 0 || stamina > 50) {
			stamina = 50;
		}
		if (updates > 1000) {
			updates = 0;

		}
		int xa = 0;
		int ya = 0;

		ya = ya + 3;

		Random random = new Random();


		if (movingTimer == 0) {
			movingDir = random.nextInt(2);
			
		}
		movingTimer++;
		if (movingDir == 0) {
			xa--;
		}

		if (movingDir == 1) {
			xa++;
		}
		if (movingTimer > 100) {
			movingTimer = 0;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;

		}

		if (hasCollided(xa, ya)) {
			jumpTimer = 0;
			jumping = false;
		}
		if (die(xa, ya)) {
			damageTimer++;
			if (damageTimer == 2) {
				health -= 55;
			}
			if (damageTimer > 50) {
				damageTimer = 0;
			}
			if (respawned) {
				damageTimer = 0;
				respawned = false;
			}

		}

	}

	@Override
	public void render(Screen screen) {
		Sprite sprite = Sprite.spiderIdle1;
		if (updates % 40 > 20) {
			sprite = sprite.spiderIdle1;
		}
		if (updates % 40 > 20) {
			sprite = sprite.spiderIdle2;
		}

		screen.renderPlayer(x, y, sprite);
	}

}

package gfx;

import java.awt.Graphics;

import level.Level;
import display.Game;
import display.entities.Player;

public class Hud {

	private Player player;
	private Game game;
	public int hPosX, hPosY;
	private int playerX;
	private int playerY;
	public int lives = 3;
	public boolean gameOver = false;

	public Hud(Level level, int hPosX, int hPosY) {
		this.hPosX = hPosX;
		this.hPosY = hPosY;
	}

	public void render(Screen screen) {
		Sprite sprite = Sprite.healthBar0;

		screen.renderHud(hPosX, hPosY - 16, sprite);

		sprite = Sprite.healthBar1;

		screen.renderHud(hPosX, hPosY, sprite);

		sprite = Sprite.healthBar2;

		screen.renderHud(hPosX, hPosY + 16, sprite);

		sprite = Sprite.staminaBar0;

		screen.renderHud(hPosX + 15, hPosY, sprite);

		sprite = Sprite.staminaBar1;

		screen.renderHud(hPosX + 15, hPosY + 16, sprite);

		sprite = Sprite.heart;
		for (int i = 1; i <= lives; i++) {
			screen.renderHud(hPosX + 16 + (i * 16), hPosY + 16, sprite);
		}
		
		if(lives <= 0){
			gameOver = true;
		}
	}

}

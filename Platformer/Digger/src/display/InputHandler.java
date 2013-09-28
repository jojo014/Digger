package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import display.entities.Player;

public class InputHandler implements KeyListener {

	public int jumpTimer = 0;
	private Player player;

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public class Key {
		private int numTimesPressed = 0;
		public boolean pressed = false;

		public int getNumTimesPressed() {
			return numTimesPressed;
		}

		public boolean isPressed() {
			return pressed;
		}

		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (isPressed) {
				numTimesPressed++;
			}
		}

	}

	public Key left = new Key();
	public Key right = new Key();
	public Key jump = new Key();
	public Key sprint = new Key();
	public boolean pause = false;

	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!pause) {
				pause = true;
			} else {
				pause = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			right.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			jump.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			sprint.toggle(isPressed);
		}

	}

}

package me.commonsenze.Platformer.Util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Levels;
import me.commonsenze.Platformer.Util.Enums.Role;

public class KeyInput extends KeyAdapter {
	// setting a global double jump variable (maybe we can use it for challenges
	// later, but also so it's only defined once.
	private int doubleJump;
	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		MouseInput.clicked();
		int key = e.getKeyCode();
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getRole() == GameData.getSelectedCharacter()) {
				if (key == KeyEvent.VK_W) {
					if (!Main.DEV_MODE) {
						if (!object.isJumping()) {
							object.jump();
							object.setJumping(true);
							doubleJump = 1;
						} else if (object.getRole() == Role.SARAH) {
							if (!(doubleJump == 0)) {
								object.jump();
								doubleJump = 0;
							}
						}
					}

				}

				if (key == KeyEvent.VK_Q) {
					GameData.setCharacter(handler.getObject(object.getIntX(), "Left").getRole());
				}

				if (key == KeyEvent.VK_E) {
					GameData.setCharacter(handler.getObject(object.getIntX(), "Right").getRole());
				}

				if (key == KeyEvent.VK_A) {
					if (!Main.DEV_MODE)
						object.setVelocity(-5);
				}
				if (key == KeyEvent.VK_D) {
					if (!Main.DEV_MODE)
						object.setVelocity(5);
				}
				break;
			}
		}
		if (Main.DEV_MODE) {
			if (key == KeyEvent.VK_W) {
				Main.CAMERA.setYSpeed(-5);
			}
			if (key == KeyEvent.VK_A) {
				Main.CAMERA.setXSpeed(-5);
			}
			if (key == KeyEvent.VK_D) {
				Main.CAMERA.setXSpeed(5);
			}
			if (key == KeyEvent.VK_S) {
				Main.CAMERA.setYSpeed(5);
			}
			if (key == KeyEvent.VK_Q) {
				Levels.prevLevel();
			}

			if (key == KeyEvent.VK_E) {
				Levels.nextLevel();
			}
		}
		if (key == KeyEvent.VK_F) {
			Main.CAMERA.setZoom((Main.CAMERA.getZoom() + 0.1));
		}
		if (key == KeyEvent.VK_R) {
			Main.CAMERA.setZoom((Main.CAMERA.getZoom() - 0.1));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getRole() == GameData.getSelectedCharacter()) {

				if (key == KeyEvent.VK_A) {
					if (object.getVelocity() == -5)
						object.setVelocity(0);
				}
				if (key == KeyEvent.VK_D) {
					if (object.getVelocity() == 5)
						object.setVelocity(0);
				}

			} else
				object.setVelocity(0);
		}
		if (Main.DEV_MODE) {
			if (key == KeyEvent.VK_W) {
				if (Main.CAMERA.getYSpeed() < 0) {
					Main.CAMERA.setYSpeed(0);
				}
			}
			if (key == KeyEvent.VK_S) {
				if (Main.CAMERA.getYSpeed() > 0) {
					Main.CAMERA.setYSpeed(0);
				}
			}
			if (key == KeyEvent.VK_A) {
				if (Main.CAMERA.getXSpeed() < 0) {
					Main.CAMERA.setXSpeed(0);
				}
			}
			if (key == KeyEvent.VK_D) {
				if (Main.CAMERA.getXSpeed() > 0) {
					Main.CAMERA.setXSpeed(0);
				}
			}
		}
	}
}

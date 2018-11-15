package me.commonsenze.Platformer.Util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
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
		int key = e.getKeyCode();
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getRole() == GameData.getSelectedCharacter()) {
				if (key == KeyEvent.VK_W) {
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

				if (key == KeyEvent.VK_Q) {
					GameData.setCharacter(handler.getObject(object.getIntX(), "Left").getRole());
				}

				if (key == KeyEvent.VK_E) {
					GameData.setCharacter(handler.getObject(object.getIntX(), "Right").getRole());
				}

				if (key == KeyEvent.VK_A) {
					object.setVelocity(-5);
				}
				if (key == KeyEvent.VK_D) {
					object.setVelocity(5);
				}
				if (key == KeyEvent.VK_F) {
					Main.CAMERA.setZoom((Main.CAMERA.getZoom() + 0.1));
				}
				if (key == KeyEvent.VK_R) {
					Main.CAMERA.setZoom((Main.CAMERA.getZoom() - 0.1));
				}
				break;
			}
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

				if (Main.CAMERA.getSpeed() != 0) {
					Main.CAMERA.setSpeed(0);
				}
			} else
				object.setVelocity(0);
		}
	}
}

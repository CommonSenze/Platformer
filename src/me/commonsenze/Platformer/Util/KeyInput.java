package me.commonsenze.Platformer.Util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import me.commonsenze.Platformer.Handler;

public class KeyInput extends KeyAdapter {

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
					}
				}

				if (key == KeyEvent.VK_A) {
					object.setVelocity(-5);
				}
				if (key == KeyEvent.VK_D) {
					object.setVelocity(5);
				}
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
					if (object.getVelocity() == -5) object.setVelocity(0);
				}
				if (key == KeyEvent.VK_D) {
					if (object.getVelocity() == 5) object.setVelocity(0);
				}
			}
		}
	}
}

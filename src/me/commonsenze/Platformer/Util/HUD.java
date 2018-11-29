package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Graphics;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;

public class HUD {

	private Handler handler;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getClassifier() == GameData.getSelectedCharacter()) {
				g.setColor(Color.WHITE);
				int middle = (20*(i+1))+10;
				g.fillPolygon(new int[] {middle-10, middle, middle+10}, new int[] {Main.HEIGHT-95, Main.HEIGHT-85, Main.HEIGHT-95}, 3);
			}
			g.setColor(object.getColor());
			g.fillRect(20*(i+1), Main.HEIGHT-80, 20, 20);
		}
	}
}

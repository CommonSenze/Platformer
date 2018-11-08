package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Util.Renderable;

public class Walls implements Renderable {

	private HashMap<String, Rectangle> rectanlges = new HashMap<>();
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		for (Rectangle rect : rectanlges.values()) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
	
	public void createFloor(int x, int y) {
		Rectangle rect = new Rectangle(x, y, Main.WIDTH-x, Main.HEIGHT-y);
		rectanlges.put("Floor",rect);
	}
	
	public boolean touchingFloor(Rectangle rectangle) {
		if (!rectanlges.containsKey("Floor"))return false;
		Rectangle rect = rectanlges.get("Floor");
		return rectangle.intersects(rect);
	}
}

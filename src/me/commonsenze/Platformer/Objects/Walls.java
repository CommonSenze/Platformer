package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Util.Renderable;

public class Walls implements Renderable, Obsticales {

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
		Rectangle rect = new Rectangle(-200, y, 20000, Main.HEIGHT-y);
		rectanlges.put("Floor",rect);
	}
	
	public boolean insideWall(Rectangle rectangle) {
		for (Rectangle rect : rectanlges.values()) {
			if (rect.intersects(rectangle))return true;
		}
		return false;
	}
	
	public int getFloorY() {
		if (!rectanlges.containsKey("Floor"))return Main.HEIGHT;
		Rectangle rect = rectanlges.get("Floor");
		return rect.y;
	}
	
	public void createCeiling(int x, int y) {
		Rectangle rect = new Rectangle(-200,0, 20000, y);
		rectanlges.put("Ceiling",rect);
	}
	
	public boolean touchingCeiling(Rectangle rectangle) {
		if (!rectanlges.containsKey("Ceiling"))return false;
		Rectangle rect = rectanlges.get("Ceiling");
		return rect.intersects(rectangle);
	}
	
	public int getCeilingY() {
		if (!rectanlges.containsKey("Ceiling"))return Main.HEIGHT;
		Rectangle rect = rectanlges.get("Ceiling");
		return rect.y+rect.height;
	}

	@Override
	public void gravity(Walls walls) {}
}

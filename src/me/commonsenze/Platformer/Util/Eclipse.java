package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Eclipse {

	private ArrayList<Point> points = new ArrayList<>();
	
	public Eclipse(Point startPoint, int vertical, int horizontal) {
		
		for (int x = startPoint.x; x < (startPoint.x+vertical); x++) {
			for (int y = startPoint.y; y < (startPoint.y+horizontal); y++) {
				if (Math.pow((x-startPoint.x), 2)/Math.pow((vertical), 2)+Math.pow((y-startPoint.y), 2)/Math.pow((horizontal), 2) <= 0.8) {
					points.add(new Point(x, y));
				}
			}
		}
		for (int x = startPoint.x; x > (startPoint.x-vertical); x--) {
			for (int y = startPoint.y; y < (startPoint.y+horizontal); y++) {
				if (Math.pow((x-startPoint.x), 2)/Math.pow((vertical), 2)+Math.pow((y-startPoint.y), 2)/Math.pow((horizontal), 2) <= 0.8) {
					points.add(new Point(x, y));
				}
			}
		}
		System.out.println(points.size());
		ArrayList<Point> otherSide = new ArrayList<>(points);
		for (Point point : otherSide) {
			int y = startPoint.y - Math.abs(startPoint.y - point.y);
			
			points.add(new Point(point.x, y));
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		for (Point point : points) {
			g.fillRect(point.x, point.y, 1, 1);
		}
	}
}

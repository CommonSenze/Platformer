package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Circle {

	private double area;
	private ArrayList<Point> points = new ArrayList<>();
	
	public Circle(Point startPoint, int radius) {
		this.area = Math.pow(radius, 2)-20;
		for (int x = startPoint.x; x < (startPoint.x+radius); x++) {
			for (int y = startPoint.y; y < (startPoint.y+radius); y++) {
				if (Math.pow((x-startPoint.x), 2)+Math.pow((y-startPoint.y), 2) <= area) {
					points.add(new Point(x, y));
				}
			}
		}
		for (int x = startPoint.x; x > (startPoint.x-radius); x--) {
			for (int y = startPoint.y; y < (startPoint.y+radius); y++) {
				if (Math.pow((x-startPoint.x), 2)+Math.pow((y-startPoint.y), 2) <= area) {
					points.add(new Point(x, y));
				}
			}
		}
		for (int x = startPoint.x; x < (startPoint.x+radius); x++) {
			for (int y = startPoint.y; y > (startPoint.y-radius); y--) {
				if (Math.pow((x-startPoint.x), 2)+Math.pow((y-startPoint.y), 2) <= area) {
					points.add(new Point(x, y));
				}
			}
		}
		for (int x = startPoint.x; x > (startPoint.x-radius); x--) {
			for (int y = startPoint.y; y > (startPoint.y-radius); y--) {
				if (Math.pow((x-startPoint.x), 2)+Math.pow((y-startPoint.y), 2) <= area) {
					points.add(new Point(x, y));
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		for (Point point : points) {
			g.fillRect(point.x, point.y, 1, 1);
		}
	}
}

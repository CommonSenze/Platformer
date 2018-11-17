package me.commonsenze.Platformer.Util;

import java.awt.Point;

import me.commonsenze.Platformer.Main;

public class Camera {

	private int positionX, positionY, speedX,speedY;
	private double zoom;
	
	public Camera(int positionX, int positionY, int speedX, int speedY) {
		if (Main.DEV_MODE) {
			zoom = .8;
		}
		this.positionX = positionX;
		this.positionY = positionY;
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public Point getPosition() {
		return new Point(positionX, positionY);
	}

	public void setPosition(Point point) {
		this.positionX = point.x;
		this.positionY = point.y;
	}
	
	public void setX(int x) {
		this.positionX = x;
	}
	
	public void setY(int y) {
		this.positionY = y;
	}

	public int getXSpeed() {
		return speedX;
	}

	public void setXSpeed(int speedX) {
		this.speedX = speedX;
	}
	
	public int getYSpeed() {
		return speedY;
	}

	public void setYSpeed(int speedY) {
		this.speedY = speedY;
	}
	
	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
}

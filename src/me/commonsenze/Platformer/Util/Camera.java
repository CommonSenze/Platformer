package me.commonsenze.Platformer.Util;

public class Camera {

	private int position, speed;
	
	public Camera(int position, int speed) {
		this.position = position;
		this.speed = speed;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}

package me.commonsenze.Platformer.Util;

public class Distance {

	private int distance;
	private boolean finished;

	public Distance(int distance) {
		this.distance = distance;
	}
	
	public double getSpeed() {
		if (distance > 0) {
			double dist = Math.ceil(distance / 20.0);
			distance -= dist;
			if (distance <= 0) {
				finished = true;
				distance = 0;
			}
			return dist;
		} else {
			double dist = Math.floor(distance / 20.0);
			distance -= dist;
			if (distance >= 0) {
				finished = true;
				distance = 0;
			}
			return dist;
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
}

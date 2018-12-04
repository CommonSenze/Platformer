package me.commonsenze.Platformer.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Distance {

	private int distance, divisor;
	private boolean finished;

	public Distance(int distance) {
		this.distance = distance;
	}
	
	public void subtractDistance(float speed) {
		if (distance > 0) {
			distance -= speed;
			if (distance < 1) {
				finished = true;
				distance = 0;
			}
		} else {
			distance -= speed;
			System.out.println(distance);
			if (distance > -1) {
				finished = true;
				distance = 0;
			}
		}
	}
	
	public float getSpeed() {
		double dist = distance / 200.0;
		return new BigDecimal(dist).setScale(1, RoundingMode.HALF_UP).floatValue();
	}
	
	public boolean isFinished() {
		return finished;
	}
}

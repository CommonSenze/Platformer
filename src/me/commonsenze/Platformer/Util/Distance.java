package me.commonsenze.Platformer.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Distance {

	private int distance;
	private boolean finished;

	public Distance(int distance) {
		this.distance = distance;
	}
	
	public float getSpeed() {
		double dist = distance / 20.0;
		if (distance > 0) {
			distance -= dist;
			if (distance < 1) {
				finished = true;
				distance = 0;
			}
		} else {
			distance -= dist;
			if (distance > -1) {
				finished = true;
				distance = 0;
			}
		}
		return new BigDecimal(dist).setScale(1, RoundingMode.HALF_UP).floatValue();
	}
	
	public boolean isFinished() {
		return finished;
	}
}

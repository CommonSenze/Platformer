package me.commonsenze.Platformer.Util;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.LevelManager;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Objects.Silhouette;
import me.commonsenze.Platformer.Objects.Water;

public class Camera implements Renderable {

	private int positionX, positionY;
	private float speedX,speedY;
	private double zoom;
	private Distance distance;
	
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

	public float getXSpeed() {
		return speedX;
	}

	public void setXSpeed(float speedX) {
		this.speedX = speedX;
	}
	
	public float getYSpeed() {
		return speedY;
	}

	public void setYSpeed(float speedY) {
		this.speedY = speedY;
	}
	
	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	
	public boolean hasDistance() {
		return distance != null;
	}

	@Override
	public void tick() {
		if (distance != null) {
			setXSpeed((float) distance.getSpeed());
			if (distance.isFinished())distance = null;
		}
		if (Main.FINISHED)setXSpeed(0);
		
		ArrayList<HitBox> hits = new ArrayList<>(Handler.getHitBoxes());
		ArrayList<Silhouette> silhouettes = new ArrayList<>(Main.LEVEL.getLevel().getSilhouettes());
		
		for (HitBox hitBox : hits) {
			if (hitBox instanceof GameObject) {
				GameObject object = (GameObject) hitBox;
				if (object.getClassifier() == GameData.getSelectedCharacter()&&!hasDistance()) {
					checkZoom(object);
					break;
				}
			}
		}
		
		for (Silhouette silhouette : silhouettes) {
			silhouette.changeX(Main.CAMERA.getXSpeed());
			silhouette.changeY(Main.CAMERA.getYSpeed());
			silhouette.rebuild();
		}
		
		for (Obstacle obs : LevelManager.getObstacles()) {
			if (obs instanceof Water) {
				Water water = (Water) obs;
				if (water.getLevel() != Main.LEVEL.getLevel())continue;
				water.setX(water.getX()-Main.CAMERA.getXSpeed());
				water.setY(water.getY()-Main.CAMERA.getYSpeed());
				water.rebuild();
			}
		}
		for (HitBox hitBox : hits) {
			if (hitBox instanceof GameObject) {
				GameObject object = (GameObject) hitBox;
				if (object.getClassifier() == GameData.getSelectedCharacter()&&!hasDistance()) continue;
			} else if (hitBox instanceof Block) {
				Block block = (Block) hitBox;
				if (block.getLevel() != Main.LEVEL.getLevel())continue;
			}
			hitBox.setX(hitBox.getX()-Main.CAMERA.getXSpeed());
			hitBox.setY(hitBox.getY()-Main.CAMERA.getYSpeed());
			hitBox.rebuild();
		}
		Main.CAMERA.setX((int) (Main.CAMERA.getPosition().x+Main.CAMERA.getXSpeed()));
		Main.CAMERA.setY((int) (Main.CAMERA.getPosition().y+Main.CAMERA.getYSpeed()));
	}
	
	private void checkZoom(GameObject main) {
		for (int y = main.getIntY(); y > -100000; y--) {
			Obstacle obstacle = LevelManager.getObstacleAt(main.getIntX(), y);
			if (obstacle != null) {
				if (obstacle.getObsticale().y < 0)
				zoomOut(obstacle.getObsticale().y);
				else if (getYSpeed() == -1) {
					setYSpeed(0);
				}
				break;
			}
		}
	}
	
	private void zoomOut(int y) {
		setYSpeed(-1);
		setZoom(getZoom()+0.002);
	}

	@Override
	public void render(Graphics g) {}
	
//	public void calculateCenterSpeed(int distance) {
//		setXSpeed((distance/distance)*(distance/));
//	}
}

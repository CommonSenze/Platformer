package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.Enums.Classifier;

public abstract class GameObject extends HitBox {

	private Classifier classifier;
	private Color color;
	private float vertical, velocity = 0, gameX, gameY;
	private boolean jumping, onFloor;
	protected int jump;
	private boolean finished;

	public GameObject(Classifier classifier, Rectangle character, Color color) {
		super(character);
		this.color = color;
		this.classifier = classifier;
	}

	public String getName() {
		return classifier.name().substring(0, 1) + classifier.name().substring(1, classifier.name().length()).toLowerCase();
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean isOnScreen() {
		return getX() > Main.CAMERA.getPosition().x+100&&getX()<Main.CAMERA.getPosition().x+Main.WIDTH-100;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean onFloor() {
		return onFloor;
	}

	public void setOnFloor(boolean onFloor) {
		this.onFloor = onFloor;
	}

	public float getVertical() {
		return vertical;
	}

	public void setVertical(float vertical) {
		this.vertical = vertical;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
	public void setJump(int jump) {
		this.jump = jump;
	}
	
	public int getJump() {
		return jump;
	}

	public float getGameX() {
		return gameX;
	}

	public void setGameX(float screenX) {
		this.gameX = screenX;
	}

	public float getGameY() {
		return gameY;
	}

	public void setGameY(float gameY) {
		this.gameY = gameY;
	}

	@Override
	public void walk() {
		int prevX = getIntX();

		// Extended level movement
		if (getX()+getWidth() >= 900&&getVelocity() >0&&getClassifier() == GameData.getSelectedCharacter()) {
			Main.CAMERA.setXSpeed(5);
			setGameX(getGameX()+5);
		} else if (getX() < 100&&getVelocity()<0&&getClassifier() == GameData.getSelectedCharacter()) {
			Main.CAMERA.setXSpeed(-5);
			setGameX(getGameX()-5);
		} else {
			// Renders James' x-axis movement to the JFrame
			setX(getX()+getVelocity());
			setGameX(getGameX()+getVelocity());
			if(getClassifier() == GameData.getSelectedCharacter())Main.CAMERA.setXSpeed(0);
		}

		rebuild();

		boolean collide = false;

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (Main.CAMERA.getXSpeed() != 0) {
					if (prevX+5 >= hitBox.getIntX()+hitBox.getWidth()) {
						setX(hitBox.getIntX()+hitBox.getWidth());
						setGameX((int)(hitBox.getX()+getWidth()));
						Main.CAMERA.setXSpeed(0);
						collide = true;
					} else if (prevX+getWidth()-5 <= hitBox.getIntX()) {
						setX(hitBox.getIntX()-getWidth());
						setGameX((int)(hitBox.getX()-getWidth()));
						Main.CAMERA.setXSpeed(0);
						collide = true;
					}
				} else {
					if (prevX >= hitBox.getIntX()+hitBox.getWidth()) {
						setX(hitBox.getX()+hitBox.getWidth());
						setGameX((int)(hitBox.getX()+hitBox.getWidth()));
						collide = true;
					} else if (prevX+getWidth() <= hitBox.getIntX()) {
						setX(hitBox.getX()-getWidth());
						setGameX((int)(hitBox.getX()-getWidth()));
						collide = true;
					}
				}
			}
		}

		if (getClassifier() == GameData.getSelectedCharacter()) {

			for (HitBox hitBox : Handler.getHitBoxes()) {
				if (!(hitBox instanceof GameObject)||hitBox == this)continue;
				if (prevX <= hitBox.getX()+hitBox.getWidth()&&prevX+getWidth() >= hitBox.getX()) {
					GameObject object = (GameObject) hitBox;
					if (object.getFeet() == getIntY()&&onFloor())
						if (!collide)object.setVelocity(getVelocity());
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	public abstract int getFeet();

	public abstract void jump();
}

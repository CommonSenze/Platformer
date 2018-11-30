package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Obstacle;

public class Water implements Obstacle {

	private float x, y;
	private Rectangle character;
	private Level level;

	public Water(int x, int y, int width, int height, Level level) {
		this(new Rectangle(x,y,width,height), level);
	}

	public Water(Rectangle character, Level level) {
		this.character = character;
		this.x = getCharacter().x;
		this.y = getCharacter().y;
		this.level = level;
	}

	@Override
	public void tick() {
		checkEntities();
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	
	public Level getLevel() {
		return level;
	}

	public void checkEntities() {
		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox instanceof GameObject) {
				GameObject object = (GameObject) hitBox;
				if (hitBox.insideBlock(getCharacter())) {
					if (hitBox.getGravity()!=0.05F) {
						object.setJump((int)(object.getJump()/1.5));
						hitBox.setGravity(0.05F);
					}
				} else if (hitBox.getGravity() != 0.5F) {
					hitBox.setGravity(0.5F);
					object.setJump(object.getJump()+5);
				}
			}
		}
	}

	@Override
	public Rectangle getObsticale() {
		return getCharacter();
	}
	
	public Rectangle getCharacter() {
		return character;
	}

	public boolean insideBlock(Rectangle rectangle) {
		return getCharacter().intersects(rectangle);
	}

	public void rebuild() {
		getCharacter().setLocation(getIntX(), getIntY());
	}

	public int getIntX() {
		return (int) x;
	}

	public int getIntY() {
		return (int) y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
	}
}

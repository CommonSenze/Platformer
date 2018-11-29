package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Classifier;


public class Laura extends GameObject implements Renderable {


	public Laura() {
		super(Classifier.LAURA, new Rectangle(70, 15), new Color(200, 150, 150)); // Laura's length == 50, height == 20
		setX(Main.WIDTH/3+40); // Spawn Laura on the left third
		setY(Main.HEIGHT/2); // Spawn Laura above the floor
		rebuild();
		jump = 6;
	}

	@Override
	public void tick() {
		walk();
		gravity();
		special();
	}

	@Override
	public void render(Graphics g) {
		// Claire' color set to green and creates his rectangle
		g.setColor(getColor());
		g.fillRect(getIntX(), getIntY(), getCharacter().width, getCharacter().height);
	}
	@Override
	public void gravity() {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-getGravity());
		setOnFloor(false);

		int prevY = getIntY();

		// Moves James by (Y - Vertical)
		setY(getY()-getVertical());

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
		
		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevY >= hitBox.getIntY()) {
					setVertical(0);
					setY(hitBox.getY()+hitBox.getHeight());
				} else if (prevY+getHeight() <= hitBox.getIntY()) {
					setVertical(0);
					setY(hitBox.getY()-getHeight());
					setOnFloor(true);
					setJumping(false);
				} else {
					setY(prevY);
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	public int getFeet() {
		return getIntY()+getHeight();
	}

	// James' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(jump);
	}

	public void special() {
		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (!(hitBox instanceof GameObject)||hitBox == this)continue;
			if (getIntX() < hitBox.getX()+hitBox.getWidth()&&getIntX()+getWidth() > hitBox.getX()) {
				GameObject object = (GameObject) hitBox;
				if (object.getFeet() == getIntY()) {
					object.setY(getIntY()-object.getHeight());
					object.setJumping(true);
					int velocity = 15;
					for (HitBox hB : Handler.getHitBoxes()) {
						if (hB.getIntY() + hB.getHeight() == object.getIntY())
							velocity = 0;
					}
					object.setVertical(velocity);
				}
			}
		}
	}
}

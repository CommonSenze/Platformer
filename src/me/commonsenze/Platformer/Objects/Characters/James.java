package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Walls;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class James extends GameObject implements Renderable {
	// Constructor for James the character
	public James() {
		super(Role.JAMES, new Rectangle(20, 30));
		setX(Main.WIDTH/2);
		setY(80);
		rebuild();
	}
	
	@Override
	public void gravity(Walls walls) {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		if (!walls.insideWall(getCharacter())) {
			setVertical(getVertical()-0.5F);
			setOnFloor(false);
		}
		// Moves James by (Y - upY)
		setY(getY()+getVertical());
		// Renders James' movements onto the JFrame
		rebuild();
		//If James is in the wall, he will move out until he isn't in the wall anymore
		if (walls.insideWall(getCharacter())) {
			setVertical(1);
			setY(walls.getCeilingY());
//			setY(walls.getFloorY()-getHeight());
			setOnFloor(true);
			setJumping(false);
		}
		// Renders James' movements onto the JFrame
		rebuild();
	}

	@Override
	public void tick() {
		// Renders James' x-axis movement to the Jframe
		setX(getX()+getVelocity());
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		// James' color set to green and creates his rectangle
		g.setColor(Color.GREEN);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	// James' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(10);
	}
}

package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Block;
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
	public void gravity(ArrayList<Block> blocks) {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-0.5F);
		setOnFloor(false);

		float prevY = getY();

		// Moves James by (Y - upY)
		setY(getY()+getVertical());

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();

		for (Block block : blocks) {
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (block.insideBlock(getCharacter())) {
				if (prevY > block.getY()) {
					setVertical(1);
					setY(block.getY()+block.getHeight());
					//			setY(walls.getFloorY()-getHeight());
					setOnFloor(true);
					setJumping(false);
				} else if (prevY+getHeight() <= block.getY()) {
					setVertical(-1);
					setY(prevY);
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	@Override
	public void tick() {

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

	@Override
	public void walk(ArrayList<Block> blocks) {
		// Renders James' x-axis movement to the JFrame
		setX(getX()+getVelocity());
		rebuild();

		for (Block block : blocks) {
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (block.insideBlock(getCharacter())) {
				setX(getX()-getVelocity());
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}
}

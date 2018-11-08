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

	public James() {
		super(Role.JAMES, new Rectangle(20, 30));
		setX(Main.WIDTH/2);
		setY(70);
		rebuild();
	}

	@Override
	public void gravity(Walls walls) {
		if (!walls.touchingFloor(getCharacter())) setUpY(getUpY()-0.5F);
	}

	@Override
	public void tick() {
		setY(getY()-getUpY());
		setX(getX()+getVelocity());
		
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
}

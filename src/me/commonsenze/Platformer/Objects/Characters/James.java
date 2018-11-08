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
		super(Role.JAMES, new Rectangle(Main.WIDTH/2, Main.HEIGHT/2, 12, 18));
	}

	@Override
	public void gravity(Walls walls) {
		if (walls.touchingFloor(getCharacter()))setUpY(0);
		else setUpY(getUpY()-1);
	}

	@Override
	public void tick() {
		setY(getY()+getUpY());
		setX(getX()+getVelocity());
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
}

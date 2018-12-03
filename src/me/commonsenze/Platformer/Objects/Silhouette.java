package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class Silhouette implements Renderable {

	private Rectangle character;
	private Role role;
	private int x, y;
	
	public Silhouette(int x, int y, Role role) {
		this.character = new Rectangle(role.getGameObject().getCharacter());
		this.role = role;
		this.x = x;
		this.y = y;
		
		this.character.setLocation(x, y);
	}

	@Override
	public void tick() {
		if (Main.DEV_MODE)return;
		changeX(Main.CAMERA.getXSpeed());
		changeY(Main.CAMERA.getYSpeed());
		rebuild();
		if (!role.isUnlocked(Main.LEVEL.getPointValue()))return;
		if (role.getGameObject().getCharacter().intersects(character)) {
			role.getGameObject().setFinished(true);
		} else {
			role.getGameObject().setFinished(false);
		}
	}

	@Override
	public void render(Graphics g) {
		if (Main.DEV_MODE)return;
		g.setColor(Color.WHITE);
		g.fillRoundRect(getCharacter().x-2, getCharacter().y-2, getCharacter().width+4, getCharacter().height+4, 5, 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	
	public void changeX(int x) {
		this.x -= x;
	}
	
	public void changeY(int y) {
		this.y -= y;
	}
	
	public void rebuild() {
		getCharacter().setLocation(x, y);
	}
	
	public Rectangle getCharacter() {
		return character;
	}
}

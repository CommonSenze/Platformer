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
		this.y = y-role.getGameObject().getHeight();
		
		this.character.setLocation(x, y);
	}

	@Override
	public void tick() {
		if (Main.DEV_MODE)return;
		if (!role.isUnlocked(Main.LEVEL.getPointValue()))return;
		rebuild();
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
		g.drawRect(getCharacter().x-1, getCharacter().y-1, getCharacter().width+1, getCharacter().height+1);
		g.drawRect(getCharacter().x-2, getCharacter().y-2, getCharacter().width+3, getCharacter().height+2);
	}
	
	public void changeX(float f) {
		this.x -= f;
	}
	
	public void changeY(float y) {
		this.y -= y;
	}
	
	public void rebuild() {
		getCharacter().setLocation(x, y);
	}
	
	public Rectangle getCharacter() {
		return character;
	}
}

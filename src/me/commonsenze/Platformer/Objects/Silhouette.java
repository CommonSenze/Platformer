package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class Silhouette implements Renderable {

	private Rectangle character;
	private Role role;
	
	public Silhouette(int x, int y, Role role) {
		this.character = new Rectangle(role.getGameObject().getCharacter());
		this.role = role;
		
		this.character.setBounds(x, y, character.width, character.height);
	}

	@Override
	public void tick() {
		if (role.getGameObject().getCharacter().intersects(character)) {
			role.getGameObject().setFinished(true);
		} else {
			role.getGameObject().setFinished(false);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(character.x-2, character.y-2, character.width+4, character.height+4, 5, 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(character.x, character.y, character.width, character.height);
	}
	
	public void changeX(int x) {
		character.setBounds(character.x-x, character.y, character.width, character.height);
	}
	
	public void changeY(int y) {
		character.setBounds(character.x, character.y-y, character.width, character.height);
	}
}

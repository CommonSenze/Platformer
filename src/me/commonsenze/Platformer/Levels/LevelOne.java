package me.commonsenze.Platformer.Levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;

public class LevelOne extends Level {

	private ArrayList<GameObject> objects;
	
	public LevelOne(Handler handler) {
		this.objects = handler.getObjects();
		// floor
		add(new Block(0, Main.HEIGHT-100, Main.WIDTH+2000, 100));
		// ceiling
		add(new Block(0, 0, Main.WIDTH+2000, 70));
		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT));
		
		add(new Block(400, 150, 20, 20));
	}

	@Override
	public void tick() {
		for (Block block : getBlocks()) {
			block.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for (Block block : getBlocks()) {
			block.render(g);
		}
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if (object.getRole() == GameData.getSelectedCharacter()) {
				g.setColor(Color.WHITE);
				int middle = (20*(i+1))+10;
				g.fillPolygon(new int[] {middle-10, middle, middle+10}, new int[] {Main.HEIGHT-95, Main.HEIGHT-85, Main.HEIGHT-95}, 3);
			}
			g.setColor(object.getColor());
			g.fillRect(20*(i+1), Main.HEIGHT-80, 20, 20);
		}
	}
}

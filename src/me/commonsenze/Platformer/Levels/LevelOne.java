package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;

public class LevelOne extends Level {

	public LevelOne() {
		// floor
		add(new Block(0, Main.HEIGHT, Main.WIDTH+2000, -100));
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
	}
}

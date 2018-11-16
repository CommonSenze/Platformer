package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;

public class LevelOne extends Level {

	public LevelOne() {
		// floor
		add(new Block(0, Main.HEIGHT-100, Main.WIDTH+2000, 100));
		// ceiling
		ceiling();
		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT));

		

	}
	
	private void floor() {
		add(new Block(Main.WIDTH+20, Main.HEIGHT-150, 200, 200));
		add(new Block(Main.WIDTH+220, Main.HEIGHT-300, 200, 200));
	}

	private void ceiling() {
		add(new Block(0, 0, Main.WIDTH-300, 70));
		add(new Block(Main.WIDTH-400, -50, Main.WIDTH-300, 70));

		add(new Block(400, 150, 20, 20));

		add(new Block(Main.WIDTH+200, -200, 200, 100));
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

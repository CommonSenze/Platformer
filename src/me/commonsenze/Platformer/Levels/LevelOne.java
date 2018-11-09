package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;

public class LevelOne extends Level {

	public LevelOne() {
		// floor
		add(new Block(-2000, Main.HEIGHT-100, Main.WIDTH+2000, 100));
		// ceiling
		add(new Block(-2000, 0, Main.WIDTH+2000, 70));
		
		add(new Block(400, 80, 20, 20));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		for (Block block : getBlocks()) {
			block.render(g);
		}
	}
}

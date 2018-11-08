package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Walls;

public class LevelOne extends Level {

	private Walls walls;
	
	public LevelOne() {
		walls = new Walls();
		walls.createFloor(0, Main.HEIGHT-200);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		walls.render(g);
	}
}

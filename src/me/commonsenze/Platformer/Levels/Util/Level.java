package me.commonsenze.Platformer.Levels.Util;

import java.util.ArrayList;

import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Renderable;

public abstract class Level implements Renderable {

	protected ArrayList<Block> blocks = new ArrayList<>();
	
	public void add(Block block) {
		blocks.add(block);
	}
	
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
}

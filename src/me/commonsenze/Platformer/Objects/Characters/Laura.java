package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;


public class Laura extends GameObject implements Renderable{

	public Laura(Role role, Rectangle character) {
		super(Role.LAURA, new Rectangle(50, 20)); // Laura's length == 50, height == 20
		setX(Main.WIDTH/3); // Spawn Laura on the left third
		setY(890); // Spawn Laura above the floor
		rebuild();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gravity(ArrayList<Block> blocks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walk(ArrayList<Block> blocks) {
		// TODO Auto-generated method stub
		
	}

}

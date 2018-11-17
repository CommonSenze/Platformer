package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;

public class PressurePlate extends HitBox implements Renderable {

	private boolean pressed, openWidth;
	private Door door;
	
	public PressurePlate(int x, int y, int width, int height, Door door) {
		this(new Rectangle(x,y,width,height), door);
	}
	
	public PressurePlate(Rectangle character, Door door) {
		super(character);
		this.door = door;
		this.openWidth = getCharacter().width < getCharacter().height;
	}
	
	@Override
	public void tick() {
		checkedPressed();
		if (isPressed()) {
			vanish();
		}
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	
	public void checkedPressed() {
		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (!(hitBox instanceof GameObject)||hitBox == this)continue;
			if (getIntX() < hitBox.getX()+hitBox.getWidth()&&getIntX()+getWidth() > hitBox.getX()) {
				GameObject object = (GameObject) hitBox;
				if (object.getFeet() == getIntY()&&openWidth) {
					pressed = true;
				} else if ((object.getIntX()+object.getWidth() == getIntX()||object.getIntX() == getIntX())&&!openWidth) {
					pressed = true;
				}
			}
		}
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	private void vanish() {
		if (openWidth&&getCharacter().width != 0)
		getCharacter().setBounds(getCharacter().x, getCharacter().y, getCharacter().width-1, getCharacter().height);
		else if (getCharacter().height!= 0)getCharacter().setBounds(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height-1);
		if (getCharacter().width == 0 || getCharacter().height == 0) {
			door.unlock();
		}
	}

	@Override
	public void gravity() {}

	@Override
	public void walk() {}
}

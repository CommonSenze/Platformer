package me.commonsenze.Platformer;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Objects.Characters.James;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;

public class Handler {

	private ArrayList<GameObject> objects = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>(), removeRend = new ArrayList<>();
	
	public Handler() {
		objects.add(new James());
		
		for (GameObject object : objects) {
			if (object instanceof Renderable) {
				renderables.add((Renderable)object);
			}
		}
	}
	
	public void tick() {
		for (Renderable renderable : renderables) {
			renderable.tick();
		}
	}
	
	public void render(Graphics g) {
		renderables.removeAll(removeRend);
		for (Renderable renderable : renderables) {
			renderable.render(g);
		}
	}
	
	public ArrayList<GameObject> getObjects(){
		return objects;
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	public void addRenderable(Renderable renderable) {
		renderables.add(renderable);
	}
	
	public void removeRenderable(Renderable renderable) {
		removeRend.add(renderable);
	}
}

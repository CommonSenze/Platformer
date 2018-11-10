package me.commonsenze.Platformer;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Objects.Characters.James;
import me.commonsenze.Platformer.Objects.Characters.Laura;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;

public class Handler {

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private ArrayList<HitBox> hitBoxes = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>(), removeRend = new ArrayList<>();
	
	public Handler() {
		gameObjects.add(new James());
		gameObjects.add(new Laura());
		
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof Renderable) {
				renderables.add((Renderable)gameObject);
			}
			if (gameObject instanceof HitBox) {
				hitBoxes.add((HitBox)gameObject);
			}
		}
	}
	
	public void tick() {
		for (Renderable renderable : renderables) {
			renderable.tick();
		}
		for (HitBox hitBox : hitBoxes) {
			if (hitBox instanceof GameObject) {
				GameObject object = (GameObject) hitBox;
				if (object.getRole() == GameData.getSelectedCharacter())continue;
			}
			hitBox.setX(hitBox.getX()-Main.CAMERA.getSpeed());
		}
	}
	
	public void render(Graphics g) {
		renderables.removeAll(removeRend);
		for (Renderable renderable : renderables) {
			renderable.render(g);
		}
	}
	
	public ArrayList<GameObject> getObjects(){
		return gameObjects;
	}
	
	public void addGameObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void removeObject(GameObject object) {
		gameObjects.remove(object);
	}
	
	public void addRenderable(Renderable renderable) {
		renderables.add(renderable);
	}
	
	public void removeRenderable(Renderable renderable) {
		removeRend.add(renderable);
	}
	
	public ArrayList<HitBox> getHitBoxes(){
		return hitBoxes;
	}
	
	public void addHitBox(HitBox hitBox) {
		hitBoxes.add(hitBox);
	}
}

package me.commonsenze.Platformer;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Objects.Characters.Chris;
import me.commonsenze.Platformer.Objects.Characters.James;
import me.commonsenze.Platformer.Objects.Characters.Laura;
import me.commonsenze.Platformer.Objects.Characters.Thomas;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;

public class Handler {

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private static ArrayList<HitBox> hitBoxes = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>(), removeRend = new ArrayList<>();

	public Handler() {
		gameObjects.add(new Thomas());
		gameObjects.add(new Laura());
		gameObjects.add(new James());
		gameObjects.add(new Chris());

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

	public static ArrayList<HitBox> getHitBoxes(){
		return hitBoxes;
	}

	public GameObject getObject(int x, String direction){
		boolean right = direction.equalsIgnoreCase("Right");
		
		for (int i = 0; i < gameObjects.size(); i++) {
			int slot = (right ? i+1:i-1);
			if (slot == gameObjects.size())slot = 0;
			if (slot == -1)slot = gameObjects.size()-1;
			if (gameObjects.get(i).getRole() == GameData.getSelectedCharacter())return gameObjects.get(slot);
		}
		
		return null;
	}

	public static void addHitBox(HitBox hitBox) {
		hitBoxes.add(hitBox);
	}
}

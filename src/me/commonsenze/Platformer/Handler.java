package me.commonsenze.Platformer;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Levels.Util.LevelManager;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Objects.Water;
import me.commonsenze.Platformer.Objects.Characters.Chris;
import me.commonsenze.Platformer.Objects.Characters.Claire;
import me.commonsenze.Platformer.Objects.Characters.James;
import me.commonsenze.Platformer.Objects.Characters.John;
import me.commonsenze.Platformer.Objects.Characters.Laura;
import me.commonsenze.Platformer.Objects.Characters.Sarah;
import me.commonsenze.Platformer.Objects.Characters.Thomas;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;

public class Handler {

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private static ArrayList<HitBox> hitBoxes = new ArrayList<>(), removeHit = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>(), removeRend = new ArrayList<>();

	public Handler() {
		if (!Main.DEV_MODE) {
			gameObjects.add(new Thomas());
			gameObjects.add(new Laura());
			gameObjects.add(new James());
			gameObjects.add(new Chris());
			gameObjects.add(new John());
			gameObjects.add(new Claire());
			gameObjects.add(new Sarah());
		}

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
		ArrayList<Renderable> renders = new ArrayList<>(renderables);
		ArrayList<HitBox> hits = new ArrayList<>(hitBoxes);
		
		for (Obstacle obs : LevelManager.getObstacles()) {
			if (obs instanceof Water) {
				Water water = (Water) obs;
				if (water.getLevel() != Main.LEVEL.getLevel())continue;
				water.setX(water.getIntX()-Main.CAMERA.getXSpeed());
				water.setY(water.getIntY()-Main.CAMERA.getYSpeed());
				water.rebuild();
			}
		}		
		for (HitBox hitBox : hits) {
			if (hitBox instanceof GameObject) {
				GameObject object = (GameObject) hitBox;
				if (object.getRole() == GameData.getSelectedCharacter())continue;
			} else if (hitBox instanceof Block) {
				Block block = (Block) hitBox;
				if (block.getLevel() != Main.LEVEL.getLevel())continue;
			}
			hitBox.setX(hitBox.getX()-Main.CAMERA.getXSpeed());
			hitBox.setY(hitBox.getY()-Main.CAMERA.getYSpeed());
			hitBox.rebuild();
			Main.CAMERA.setX(Main.CAMERA.getPosition().x+Main.CAMERA.getXSpeed());
			Main.CAMERA.setY(Main.CAMERA.getPosition().y+Main.CAMERA.getYSpeed());
		}
		for (Renderable renderable : renders) {
			renderable.tick();
		}
		
		hitBoxes.removeAll(removeHit);
		renderables.removeAll(removeRend);
		removeHit.clear();
		removeRend.clear();
	}

	public void render(Graphics g) {
		ArrayList<Renderable> renders = new ArrayList<>(renderables);
		
		for (Obstacle obs : LevelManager.getObstacles()) {
			if (obs instanceof Water) {
				obs.render(g);
			}
		}
		for (Renderable renderable : renders) {
			renderable.render(g);
		}
		
		renderables.removeAll(removeRend);
		removeRend.clear();
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
	
	public static void removeHitBox(HitBox hitBox) {
		removeHit.add(hitBox);
	}
}

package me.commonsenze.Platformer;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Effects.FinishedAnimation;
import me.commonsenze.Platformer.Levels.Util.LevelManager;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Objects.Water;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class Handler {

	private static ArrayList<GameObject> gameObjects = new ArrayList<>();
	private static ArrayList<HitBox> hitBoxes = new ArrayList<>(), removeHit = new ArrayList<>();
	private static ArrayList<Renderable> renderables = new ArrayList<>(), removeRend = new ArrayList<>();

	public Handler() {
		if (!Main.DEV_MODE) {
			for (Role role : Role.values()) {
				if (role.isUnlocked(Main.LEVEL.getPointValue()))
					gameObjects.add(role.getGameObject());
			}
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
		reorganize(Role.getByClassifier(GameData.getSelectedCharacter()).getGameObject());
		ArrayList<Renderable> renders = new ArrayList<>(renderables);
		if (Main.FINISHED) {
			for (Renderable renderable : renders) {
				renderable.tick();
			}
			return;
		}

		for (Renderable renderable : renders) {
			renderable.tick();
		}
		
		checkFinsihed();

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
	
	private void reorganize(GameObject mainCharacter) {
		if (renderables.get(1) == mainCharacter)return;
		renderables.remove((Renderable)mainCharacter);
		renderables.add(1, (Renderable) mainCharacter);
	}
	
	private void checkFinsihed() {
		for (GameObject object : gameObjects) {
			if (!object.isFinished())return;
		}
		
		Main.FINISHED = true;
		FinishedAnimation animation = new FinishedAnimation(gameObjects);
		
		renderables.add(animation);
	}

	public static void addGameObject(GameObject object) {
		gameObjects.add(object);
	}

	public void removeObject(GameObject object) {
		gameObjects.remove(object);
	}

	public static void addRenderable(Renderable renderable) {
		if (renderable instanceof LevelManager) {
			renderables.add(0, renderable);
		}
		else renderables.add(renderable);
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
			if (gameObjects.get(i).getClassifier() == GameData.getSelectedCharacter())return gameObjects.get(slot);
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

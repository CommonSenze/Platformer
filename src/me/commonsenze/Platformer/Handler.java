package me.commonsenze.Platformer;

import java.awt.Color;
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
		reset();
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
		
		renderCurrentCharacterArrow(g);

		renderables.removeAll(removeRend);
		removeRend.clear();
	}

	private void renderCurrentCharacterArrow(Graphics g) {
		if (Main.FINISHED)return;
		g.setColor(Color.WHITE);
		for (Role role : Role.values()) {
			if (GameData.getSelectedCharacter() == role.getGameObject().getClassifier()) {
				int triangleBase = 15;
				int spacing = Math.abs(triangleBase - role.getGameObject().getWidth())/2;
				int pointOne = role.getGameObject().getIntX() + triangleBase + spacing;
				int pointTwo = role.getGameObject().getIntX() + spacing;
				int pointThree = role.getGameObject().getIntX() +(pointOne - pointTwo)/2+spacing;
				int[] xPoints = {pointOne, pointThree,pointTwo};
				int[] yPoints = {role.getGameObject().getIntY()-10, role.getGameObject().getIntY()-3, role.getGameObject().getIntY()-10};
				g.fillPolygon(xPoints, yPoints, 3);
			}
		}
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
		FinishedAnimation animation = new FinishedAnimation(this);
		
		renderables.add(animation);
	}
	
	public void reset() {
		FinishedAnimation animation = null;
		for (Renderable renderable : renderables) {
			if (renderable instanceof FinishedAnimation) {
				animation = (FinishedAnimation) renderable;
			}
		}
		renderables.remove(animation);
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof Renderable) {
				renderables.remove((Renderable)gameObject);
			}
			if (gameObject instanceof HitBox) {
				hitBoxes.remove((HitBox)gameObject);
			}
		}
		gameObjects.clear();
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
		
		if (animation != null) {
			renderables.add(animation);
		}
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

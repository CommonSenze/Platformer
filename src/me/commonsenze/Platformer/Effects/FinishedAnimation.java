package me.commonsenze.Platformer.Effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Classifier;

public class FinishedAnimation implements Renderable {

	private ArrayList<GameObject> objects;
	private int timer = 0;
	private boolean finishedShrinking;
	private float alpha = 0, life = 0.002F;
	private HashMap<Classifier, Double> decrementationHeightAmount = new HashMap<>();
	private HashMap<Classifier, Double> decrementationWidthAmount = new HashMap<>();
	private HashMap<Classifier, Double[]> realDimensions = new HashMap<>();

	public FinishedAnimation(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public void tick() {
		timer++;
		if (timer >= 30) {
			if (finishedShrinking) {
				if (alpha == 1&&timer >30&&life >0) {
					timer = -60;
				} else if (alpha == 1&&timer>= 20&&life >0){
					life *=-1;
				} else {
					alpha += life + (0.004f * (life/Math.abs(life)));
					if (alpha > 1)alpha = 1;
					if (alpha < 0) {
						alpha = 0;
					}
				}
			} else {
				if (timer == 30) {
					for (GameObject object : objects) {
						Double[] array = {object.getCharacter().getWidth(),object.getCharacter().getHeight()};
						decrementationHeightAmount.put(object.getClassifier(), object.getCharacter().height/30.0);
						decrementationWidthAmount.put(object.getClassifier(), object.getCharacter().width/30.0);
						realDimensions.put(object.getClassifier(), array);
					}
				}
				shrink();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		g2d.setComposite(makeTransparent(alpha));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH,Main.HEIGHT);

		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	private void shrink() {
		if (finishedShrinking) {
			timer = 0;
			return;
		}
		for (GameObject object : objects) {
			if (object.getCharacter().height == 0) {
				finishedShrinking = true;
				continue;
			}
			double height = realDimensions.get(object.getClassifier())[1]-(decrementationHeightAmount.get(object.getClassifier())*2);
			double width = realDimensions.get(object.getClassifier())[0]-(decrementationWidthAmount.get(object.getClassifier())*2);
			Double[] array = {width, height};
			realDimensions.put(object.getClassifier(), array);
			object.getCharacter().setSize((int)width, (int)height);
			object.setX((float) (object.getX()+decrementationWidthAmount.get(object.getClassifier())));
			object.setY((float) (object.getY()+decrementationHeightAmount.get(object.getClassifier())));
			object.rebuild();
		}
	}
}

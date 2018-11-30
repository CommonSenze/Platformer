package me.commonsenze.Platformer.Util;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Util.Enums.Classifier;

public class GameData {

	private static Classifier selectedCharacter = Classifier.THOMAS;
	private Handler handler;
	
	public GameData(Handler handler) {
		this.handler = handler;
	}
	
	public static void setCharacter(Classifier character) {
		selectedCharacter = character;
	}
	
	public static Classifier getSelectedCharacter() {
		return selectedCharacter;
	}
}

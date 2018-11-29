package me.commonsenze.Platformer.Util;

import me.commonsenze.Platformer.Util.Enums.Classifier;

public class GameData {

	private static Classifier selectedCharacter = Classifier.THOMAS;
	
	public static void setCharacter(Classifier character) {
		selectedCharacter = character;
	}
	
	public static Classifier getSelectedCharacter() {
		return selectedCharacter;
	}
}

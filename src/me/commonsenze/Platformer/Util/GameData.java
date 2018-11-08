package me.commonsenze.Platformer.Util;

import me.commonsenze.Platformer.Util.Enums.Role;

public class GameData {

	private static Role selectedCharacter;
	
	public static void setCharacter(Role character) {
		selectedCharacter = character;
	}
	
	public static Role getSelectedCharacter() {
		return selectedCharacter;
	}
}

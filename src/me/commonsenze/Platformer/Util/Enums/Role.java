package me.commonsenze.Platformer.Util.Enums;

import me.commonsenze.Platformer.Objects.Characters.Chris;
import me.commonsenze.Platformer.Objects.Characters.Claire;
import me.commonsenze.Platformer.Objects.Characters.James;
import me.commonsenze.Platformer.Objects.Characters.John;
import me.commonsenze.Platformer.Objects.Characters.Laura;
import me.commonsenze.Platformer.Objects.Characters.Sarah;
import me.commonsenze.Platformer.Objects.Characters.Thomas;
import me.commonsenze.Platformer.Util.GameObject;

public enum Role {

	THOMAS(new Thomas(),1),
	CHRIS(new Chris(),2),
	JOHN(new John(),5),
	CLAIRE(new Claire(),4),
	LAURA(new Laura(),3),
	JAMES(new James(),6),
	SARAH(new Sarah(),8);

	private GameObject object;
	private int level;
	
	private Role(GameObject object, int level) {
		this.object = object;
		this.level = level;
	}
	
	public GameObject getGameObject() {
		return object;
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean isUnlocked(int level) {
		return this.level <= level;
	}
	
	public static Role getByClassifier(Classifier classifier) {
		return Role.valueOf(classifier.name().toUpperCase());
	}
}

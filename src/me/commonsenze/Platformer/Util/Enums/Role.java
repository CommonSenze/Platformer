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

	THOMAS(new Thomas()),
	CHRIS(new Chris()),
	JOHN(new John()),
	CLAIRE(new Claire()),
	LAURA(new Laura()),
	JAMES(new James()),
	SARAH(new Sarah());

	private GameObject object;
	
	private Role(GameObject object) {
		this.object = object;
	}
	
	public GameObject getGameObject() {
		return object;
	}
}

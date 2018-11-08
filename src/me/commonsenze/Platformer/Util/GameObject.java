package me.commonsenze.Platformer.Util;

import me.commonsenze.Platformer.Util.Enums.Role;

public abstract class GameObject {

	private Role role;
	
	public GameObject(Role role) {
		this.role = role;
	}
	
	public void getRole() {
		return role;
	}
	
	public abstract void gravity();
}

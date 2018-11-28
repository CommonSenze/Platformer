package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.commonsenze.Platformer.Main;

public class MouseInput extends MouseAdapter {

	private static boolean clicked;

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
	}

	public void render(Graphics g) {
		if (!clicked) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font(g.getFont().getFontName(), 20, 30));
			g.drawRect(Main.WIDTH/3, Main.HEIGHT*10/24, Main.WIDTH/3, Main.HEIGHT/6);
			g.drawString("Click To Start", (Main.WIDTH/3)+((Main.WIDTH/3)/2)-95, (Main.HEIGHT*10/24)+55);
		}
	}
	
	public static void clicked() {
		clicked = true;
	}
}

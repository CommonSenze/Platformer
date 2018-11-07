package me.commonsenze.Platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197924032874913024L;

	public static final int FIELD_SIZE = 100;
	public static final int WIDTH = FIELD_SIZE * 8+(FIELD_SIZE/16), HEIGHT = (int)(FIELD_SIZE * 8+(FIELD_SIZE/3.5));
	public static boolean PAUSED = false;
	private Thread thread;
	private boolean running = false;
	private int frames = 0;
	private long timer;
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public synchronized void start() {
		if (thread == null) thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			System.exit(0);
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.frames = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		

		Font defaultFont = new Font("Helvetica", 100, 25);

		Font titleFont = new Font("Arial Black", 100, 25);

		g.setColor(Color.YELLOW);
		g.setFont(titleFont);

		g.drawString("FPS: "+frames, WIDTH-150, 40);

		g.setFont(defaultFont);

		
		
		if (PAUSED) {
			titleFont = new Font("Arial Black", 100, 50);
			g.setFont(titleFont);
			g.setColor(Color.RED);
			g.drawString("Paused", WIDTH/2-110, HEIGHT/2-20);
		}

		g.dispose();
		bs.show();
	}
}

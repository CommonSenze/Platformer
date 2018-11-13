package me.commonsenze.Platformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import me.commonsenze.Platformer.Levels.Util.LevelManager;
import me.commonsenze.Platformer.Util.Camera;
import me.commonsenze.Platformer.Util.HUD;
import me.commonsenze.Platformer.Util.KeyInput;
import me.commonsenze.Platformer.Util.MouseInput;
import me.commonsenze.Platformer.Util.Window;

public class Main extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197924032874913024L;

	public static final int WIDTH = 1000, HEIGHT = WIDTH /16*9;
	private Thread thread;
	private Handler handler;
	private MouseInput mouse;
	private HUD hud;
	public static boolean running = false;
	public static final Camera CAMERA = new Camera(0, 0);
	
	public Main() {
		this.handler = new Handler();
		this.hud = new HUD(handler);
		new LevelManager(handler);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mouse = new MouseInput());
		
		new Window(WIDTH, HEIGHT, "Test", this);
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
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(20, 30, 100));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(WIDTH/2, HEIGHT/2);
		g2.scale(1-CAMERA.getZoom(), 1-CAMERA.getZoom());
		g2.translate(-WIDTH/2, -HEIGHT/2);
		handler.render(g);
		mouse.render(g);
		g2.translate(WIDTH/2, HEIGHT/2);
		g2.scale(1.0/g2.getTransform().getScaleX(),1.0/g2.getTransform().getScaleY());
		g2.translate(-WIDTH/2, -HEIGHT/2);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
}

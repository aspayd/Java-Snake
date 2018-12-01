package com.snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -2931033240563787969L;
	
	public static final int HEIGHT = 539, WIDTH = 541;
	private boolean running = false;
	private Thread thread;
	
	private Handler handler;
	private HUD hud;
	
	private void test() {
		
	}
	
	public Game() {	
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Snake", this);
		
		hud = new HUD();
		
		handler.addObject(new Player(0, 0, ID.Player, handler));
		handler.addObject(new Food(50, 50, ID.Food));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 10.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		final int gameFrameRate = 120;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			try {
				Thread.sleep(1000 / gameFrameRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	
	public void tick() {
		handler.tick();
		hud.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,  0,  WIDTH,  HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float val, float min, float max) {
		if(val >= max) {
			return max;
		} else if(val <= min ) {
			return min;
		} else {
			return val;
		}
		
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
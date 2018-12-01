package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	public static final int width = 25;
	public static final int height= 25;
	
	private float x, y;
	private Handler handler;
	
	private Tail tail;
	
	private Random r;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.id = id;
		
		r = new Random();
		
		velX = 0;
		velY = 0;
		
	}
		
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 41);
		y = Game.clamp(y, 0,  Game.HEIGHT - 64);
		
		collision();
	}

	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Food) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//Move food to a new location
					handler.removeObject(tempObject);
					handler.addObject(new Food((float)r.nextInt(Game.WIDTH)%25 * 25, (float)r.nextInt(Game.HEIGHT)%25 * 25, ID.Food));
					HUD.score++;
					//Add length to the snake
//					tail.addTail();
					
					
				}
			}
		}
	}
	
}

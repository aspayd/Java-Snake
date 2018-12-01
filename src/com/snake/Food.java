package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject{
	
	private float x, y;
	
	public Food(float x, float y, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void tick() {
		
		x = Game.clamp(x, 0, Game.WIDTH - 41);
		y = Game.clamp(y, 0, Game.HEIGHT - 64);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 25, 25);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}
	
}

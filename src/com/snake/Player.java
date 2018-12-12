package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject{
	
	ArrayList<Point> tail = new ArrayList<Point>();
	public boolean gameOver = false;
	private int total = 0;
	
	public static final int width = 25;
	public static final int height= 25;
	
	private float x, y;
	private Handler handler;
	
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
		
		
		if(tail.size() != 0) {
			if(tail.size() == total) {
				for(int i = 0; i < tail.size() - 1; i++) {
					tail.add(i, tail.get(i + 1));
					tail.remove(i);
				}
				tail.add(tail.size(), new Point((int)x - (int)velX, (int)y - (int)velY));
				tail.remove(0);
			}
		}
		
		collision();
	}

	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, width, height);
		
		for(Point p: tail) {
			g.setColor(Color.orange);
			g.fillRect(p.x, p.y, 25, 25);
		}
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
					handler.addObject(new Food((float)r.nextInt(Game.WIDTH-25)%25 * 25, (float)r.nextInt(Game.HEIGHT-25)%25 * 25, ID.Food));
					HUD.score++;
					//Add length to the snake
					total++;
					addTail();
					
					//Add second tail to give player proper length
//					if(HUD.score == 1) {
//						addTail();
//					}
					
				}
			}
		}
	}
	
	private void addTail() {
		if(total != 0)
			tail.add(new Point((int)x - (int)velX, (int)y - (int)velY));
		else
			tail.add(new Point((int)x, (int)y));
	}
	
}

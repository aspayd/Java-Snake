package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Tail extends GameObject{

	private LinkedList<Point> tail = new LinkedList<Point>();
	
	private Handler handler;
	
	public Tail(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		//Move each part up the tail, and send the first piece to the back
		for(int i = 0; i < tail.size(); i++) {
			Point tailPart = tail.get(i);
			
			
		}
		
	}

	public void render(Graphics g) {
		//Draw in the tail
		for(int i = 0; i < tail.size(); i++) {
			Point tailPart = tail.get(i);
			
			g.setColor(Color.green);
			g.fillRect((int)tailPart.x, (int)tailPart.y, 25, 25);
		}
	}

	public void addTail() {
		handler.addObject(new Tail(x, y, ID.Tail, handler));
	}
	
	public Rectangle getBounds() {
		return null;
	}

	public LinkedList<Point> getTail() {
		return tail;
	}
	public void setTail(LinkedList<Point> tail) {
		this.tail = tail;
	}
}

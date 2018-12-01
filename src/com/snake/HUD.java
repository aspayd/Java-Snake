package com.snake;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int score;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Score: " + score, 15, 15);
	}
}

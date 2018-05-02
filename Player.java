package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private Handler handler;
	public static int speed;

	public Player(int x,int y,ID id,Handler handler) {
		super(x,y,id);
		this.handler=handler;
		this.speed=4;
	}
	public void tick() {
		x+=velX;
		y+=velY;
		
		x=Game.clamp(x, 0, Game.WIDTH-38);
		y=Game.clamp(y, 0, Game.HEIGHT-66);
		
		handler.addObject(new Trail(x,y,id.trail,Color.white,32,32,0.08f,handler));
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	
	
}

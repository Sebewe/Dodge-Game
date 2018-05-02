package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class OneWayEnemy extends GameObject {
	
	private Handler handler;
	private Random r;

	public OneWayEnemy(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
		r=new Random();
		this.setVelocity();
	}
	
	public void setVelocity() {
		if(r.nextBoolean()) {
			if(r.nextBoolean()) this.velX=5;
			else this.velX=-5;
		}else {
			if(r.nextBoolean())this.velY=5;
			else this.velY=-5;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,25,25);
	}

	public void tick() {
		this.x+=velX;
		this.y+=velY;	
		if(this.y <=5 || y>= Game.HEIGHT-50)  this.velY*=-1;
		if(this.x <=5 || x>= Game.WIDTH-32)   this.velX*=-1;
		handler.addObject(new Trail(x,y,id.trail,Color.blue,25,25,0.04f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 25, 25);
	}

}

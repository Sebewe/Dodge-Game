package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HealthPack extends GameObject{
	
	private Handler handler;
	private Random r=new Random();

	public HealthPack(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y, 30,30);
	}

	public void tick() {
		// check meet player
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==id.player) {
				if(tempObject.getBounds().intersects(this.getBounds())) {
					handler.removeObject(this);
					HUD.HEALTH+=45;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y+10, 30, 10);
		g.fillRect(x+10, y, 10, 30);
	}

}

package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SpeedPack extends GameObject {
	
	private Handler handler;

	public SpeedPack(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler=handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,25,25);
	}

	public void tick() {
		// check meet player
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==id.player) {
				if(tempObject.getBounds().intersects(this.getBounds())) {
					handler.removeObject(this);
					Player.speed++;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, 25, 25);
	}

}

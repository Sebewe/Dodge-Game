package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH=200;
	private Handler handler;
	private int score=0;
	private int lvl=1;
	
	public HUD(Handler handler) {
		this.handler=handler;
	}

	public void tick() {
		score++;
		collision();
		HEALTH=Game.clamp(HEALTH, -1, 200);
		if(HEALTH<=0) Game.alive=false;
	}

	public void collision() {
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				for(int i1=0;i1<handler.object.size();i1++) {
					GameObject tempObject1=handler.object.get(i1);
					if(tempObject1.getId()==ID.basicEnemy||tempObject1.getId()==ID.oneWayEnemy) {
						if(tempObject1.getBounds().intersects(tempObject.getBounds())) HEALTH-=3;
		}}}}}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		if(HEALTH>60) g.setColor(Color.green);
		else g.setColor(Color.red);
	    g.fillRect(15, 15, HEALTH, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: "+score, 15, 64);
		g.drawString("Level: "+lvl, 15, 80);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
}

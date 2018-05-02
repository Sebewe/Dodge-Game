package com.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r;
	
	private int scoreKeep=0;
	
	public Spawn(Handler handler,HUD hud) {
		this.handler=handler;
		this.hud=hud;
		r=new Random();
	}
	
	public void tick() {
		scoreKeep++;
		if(hud.getLvl()<5) {
			if(scoreKeep>=300) {
				scoreKeep=0;
				hud.setLvl(hud.getLvl()+1);			
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.basicEnemy,handler));
				if(hud.getLvl()==5)lvl2();
			}
		}else if(hud.getLvl()<9) {
			if(scoreKeep>=300) {
				scoreKeep=0;
				hud.setLvl(hud.getLvl()+1);
				handler.addObject(new OneWayEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.oneWayEnemy,handler));
				if(hud.getLvl()==9)lvl3();
			}
		}else {
			if(scoreKeep>300) {
				scoreKeep=0;
				hud.setLvl(hud.getLvl()+1);
				int rand=r.nextInt(3);
				if(rand==0) handler.addObject(new OneWayEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.oneWayEnemy,handler));
				else if(rand==2) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.basicEnemy,handler));
				else {
					handler.addObject(new OneWayEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.oneWayEnemy,handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.basicEnemy,handler));
				}
			}
		}
	}
	
	public void lvl2() {
		int counter=0;
		for(int i=0;i<handler.object.size();i++) {
			if(counter<3) {
				GameObject tempObject=handler.object.get(i);
				if(tempObject.getId()==ID.basicEnemy) {
					handler.object.remove(tempObject);
					counter++;
				}
			}
		}
		handler.addObject(new OneWayEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.oneWayEnemy, handler));
		handler.addObject(new OneWayEnemy(r.nextInt(Game.WIDTH-100), r.nextInt(Game.HEIGHT-100), ID.oneWayEnemy, handler));
	}
	
	public void lvl3() {
		int counter=0;
		for(int i=0;i<handler.object.size();i++) {
			if(counter<3) {
				GameObject tempObject=handler.object.get(i);
				if(tempObject.getId()==ID.oneWayEnemy) {
					handler.object.remove(tempObject);
					counter++;
				}
			}
		}
	}
}

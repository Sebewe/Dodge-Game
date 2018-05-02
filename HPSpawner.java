package com.main;

import java.util.Random;

public class HPSpawner extends Thread {
	
	private Random r=new Random();
	private Game game;
	private int rand;
	
	public HPSpawner(Game game) {
		this.game=game;
	}
	
	public void run() {
		while (true) {
			rand=r.nextInt(50)+100;
			for(int i=0;i<rand;i=i) {
				try {Thread.sleep(100);}catch(Exception e) {}
				if(!Game.pause&&game.alive) i++;				
			}
			this.game.handler.object.add(new HealthPack(r.nextInt(game.WIDTH-100),r.nextInt(game.HEIGHT-100),ID.healthPack,game.handler));		
		}
	}
}
package com.main;

import java.util.Random;

public class SpeedSpawner extends Thread {
	
	private Game game;
	private Random r=new Random();
	private int rand;
	
	public SpeedSpawner(Game game) {
		this.game=game;
	}
	
	public void run() {
		while (true) {
			rand=r.nextInt(50)+200;
			for(int i=0;i<rand;i=i) {
				try {Thread.sleep(100);}catch(Exception e) {}
				if(!Game.pause&&game.alive) i++;				
			}
			this.game.handler.object.add(new SpeedPack(r.nextInt(game.WIDTH-100),r.nextInt(game.HEIGHT-100),ID.speedPack,game.handler));		
		}
	}
}

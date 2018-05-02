package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 4088146271165387233L;
	public static final int WIDTH=640,HEIGHT=WIDTH/12*9;
	public static boolean alive,pause;
	
	private Random r=new Random();
	public static Thread thread;
	private boolean running=false;
	Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	public Game() {
		alive=true;
		pause=false;
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler,this));
		new Window(WIDTH, HEIGHT, "Game", this);
		hud=new HUD(handler);
		spawner=new Spawn(handler,hud);
		handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.basicEnemy,handler));
		handler.addObject(new SpeedPack(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.speedPack,handler));
	}
	
	public void run() {
		this.requestFocus();
		long lastTime=System.nanoTime();
		double ammountOfTicks=60.0;
		double ns=1000000000 / ammountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(running) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running) {
				render();
				frames++;
			}
			if(System.currentTimeMillis()-timer>100) {
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames=0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(alive&&!pause) {
		handler.tick();
		hud.tick();
		spawner.tick();
		}
	}

	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		if(alive) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		//paused section
		if(pause) {
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.cyan);
			g.setFont(new Font("", 1, 100));
			g.drawString("PAUSED", 100, 250);
			g.setFont(new Font("",1,25));
			g.drawString("Press P to unpause",105,300);
			
		}
		
		}else {
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			g.setFont(new Font("times new roman", 3, 75));
			g.drawString("END.",100,100);
			g.drawString("Score: "+hud.getScore(), 100, 225);
			g.drawString("Level: "+hud.getLvl(), 100, 350);
			g.setFont(new Font("times new roman", 2, 25));
			g.drawString("Thank you for playing! You can press SPACE to exit.",50,425);
			g.setFont(new Font("", 1, 15));
			g.drawString("Created by Sebastian Modafferi", 400, 20);
		}
		
		g.dispose();
		bs.show();		
	}
	
	public static int clamp(int var,int min,int max) {
		if(var>=max) return var=max;
		else if(var<=min)return var=min;
		else return var;
	}
	
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			
			running=false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
    	Game game=new Game();
    	new HPSpawner(game).start();
    	new SpeedSpawner(game).start();
    }
}

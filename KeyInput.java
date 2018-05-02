package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private Game game;
	
	public KeyInput(Handler handler,Game game) {
		this.handler=handler;
		this.game=game;
	}
	
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				
				if(key==KeyEvent.VK_UP||key==KeyEvent.VK_W) tempObject.setVelY(- Player.speed);
				if(key==KeyEvent.VK_DOWN||key==KeyEvent.VK_S) tempObject.setVelY(Player.speed);
				if(key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_D) tempObject.setVelX(Player.speed);
				if(key==KeyEvent.VK_LEFT||key==KeyEvent.VK_A) tempObject.setVelX(- Player.speed);		
				
			}
		}
		if(key==KeyEvent.VK_SPACE) System.exit(1);
		if(key==KeyEvent.VK_P) game.pause=!game.pause;
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getId()==ID.player) {
				
				if(key==KeyEvent.VK_UP||key==KeyEvent.VK_W) tempObject.setVelY(0);
				if(key==KeyEvent.VK_DOWN||key==KeyEvent.VK_S) tempObject.setVelY(0);
				if(key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_D) tempObject.setVelX(0);
				if(key==KeyEvent.VK_LEFT||key==KeyEvent.VK_A) tempObject.setVelX(0);
				
				
			}
		}
			
	}
	
}

package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x,y;
	protected ID id;
	protected int velX,velY;
	
	public GameObject(int x,int y,ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	public abstract Rectangle getBounds();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public void setId(ID id) {this.id=id;}
	public ID getId() {return this.id;}
	public int getVelX() {return velX;}
	public void setVelX(int velX) {this.velX = velX;}
	public int getVelY() {return velY;}
	public void setVelY(int velY) {this.velY = velY;}	
	
}

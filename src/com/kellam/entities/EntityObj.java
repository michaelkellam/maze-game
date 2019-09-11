package com.kellam.entities;

import java.awt.Graphics;

import com.kellam.level.LevelManager;

/*
 * Basic Object class that every
 * object has, such as x/y, ID,
 * listing in the LinkedList.
 */

public class EntityObj {

	protected int x, y;
	protected int tX,tY;
	protected int dx, dy;
	protected int width, height;
	protected int list;
	
	protected boolean isSolid;
	protected boolean isFree;
	
	protected ID id;
	
	public EntityObj(ID id, int x, int y, int list) {
		this.id = id;
		this.x = x;
		this.y = y;
		tX = x;
		tY = y;
		this.list = list;	// Each object saves its place in the LinkedList
							// so that the Handler can modify it easily.
		
		isSolid  = true;
		
		width = 8;
		height = 8;
	}
	
	public EntityObj() {
		
		x = 0;
		y = 0;
		
		isSolid = false;

	}
	
	public void tick() {
		
	}
		

	public boolean intersects(EntityObj obj) {

		/* Not the best implementation for changing levels using the door.
		 * All of the other logic in the game is in the Handler, and this should
		 * be no exception. However, it still functions as intended.
		 */
		
		if (/*x*/(this.tX + this.width > obj.x && this.tX < obj.getX() + obj.getWidth()) &&	
			/*y*/(this.tY + this.height > obj.y && this.tY < obj.getY() + obj.getHeight())
			&& this.getID().equals(ID.Player) && obj.getID().equals(ID.Door)) {
			
			LevelManager.nextLevel();
			System.out.println("Current Level: " + LevelManager.currentLevel);
			return true;
		} else {
			if (/*x*/(this.tX + this.width > obj.x && this.tX < obj.getX() + obj.getWidth()) &&
				/*y*/(this.tY + this.height > obj.y && this.tY < obj.getY() + obj.getHeight())) {
					
					
					this.setFree(false);
					return true;
				}
		}
		
		this.setFree(true);
		return false;
		
	}
	
	public boolean intersects(int x, int y) {

		if (/*x*/(x > this.x && x < this.x + this.width) &&
			/*y*/(y > this.y && y < this.y + this.height)) {
			
			return true;
		} 
		return false;
	}
		 
	
	public void testJump(int jump) {
		
		tX += jump;
		tY += jump;
	}
	
	
	
	public void testJump(int jumpX, int jumpY) {
		
		tX += jumpX;
		tY += jumpY;
		isFree = false;
		for (int i = 0; i < Handler.getObjects().size(); i++) {
			if (this.intersects(Handler.getObjects().get(i))) {
				tX -= jumpX;
				tY -= jumpY;
				return;
			} else {
				x = tX;
				y = tY;
				
			}
		}
		tX = x;
		tY = y;
	}
	
	
	public void render(Graphics g) {
		
	}
	
	public ID getID() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getTX() {
		return tX;
	}
	
	public int getTY() {
		return tY;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public boolean isFree() {
		return isFree;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setTX(int tX) {
		this.tX = tX;
	}
	
	public void setTY(int tY) {
		this.tY = tY;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setFree(boolean free) {
		isFree = free;
	}
	
	public void setSolid(boolean solid) {
		isSolid = solid;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isSolid() {
		return isSolid;
	}

	
}

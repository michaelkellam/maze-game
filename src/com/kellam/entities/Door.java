package com.kellam.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Door extends EntityObj {

	/*
	 * This is what allows the player
	 * to move to the next level
	 * when they collide.
	 */
	
	private BufferedImage image;
	private File file;
	
	public Door() {		
		x = 0;
		y = 0;
		isSolid = false;	
	}
	
	public Door(int x, int y, int list) {
		super(ID.Door, x, y, list);
		
		file = new File("./res/sprites/Door.png");
		try {
			image = ImageIO.read(file);
		} catch(Exception e) {
			System.out.println("Error finding " + file.getAbsolutePath());
		}
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}

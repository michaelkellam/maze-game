package com.kellam.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Block extends EntityObj {

	/*
	 * Block class are the walls
	 * in the game. There is not much to do here
	 * because its interactivity with the player
	 * is in the Handler, which has most of the
	 * logic.
	 */
	
	private BufferedImage image;
	private File file;
	
	public Block(int x, int y, int list) {
		super(ID.Block, x, y, list);
		
		file = new File("./res/sprites/Block.png");
		try {
			image = ImageIO.read(file);
		} catch(Exception e) {
			System.out.println("Error finding " + file.getAbsolutePath());
		}
	}
	
	public Block() {
		super();
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		
		g.drawImage(image, x, y, null);
	}
}

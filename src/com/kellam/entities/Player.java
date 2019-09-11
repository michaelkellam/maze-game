package com.kellam.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * Player is the object
 * that the user directly
 * controls.
 * 
 * NOTE: For more explanation of the object,
 * refer to the EntityObj.java to see the
 * variables and its purpose.
 */

public class Player extends EntityObj {	//Extension of the EntityObj

	private BufferedImage image;
	private File fileup, filedown, fileleft, fileright;
	
	private String path = "./res/sprites/";
	private String up = "Player_Up.png";
	private String down = "Player_Down.png";
	private String left = "Player_Left.png";
	private String right = "Player_Right.png";
	
	private int side = 1;
	
	public Player(int x, int y, int list) {
		super(ID.Player, x, y, list);
		width = 12;
		height = 12;
		fileup = new File(path + up);
		filedown = new File(path + down);
		fileleft = new File(path + left);
		fileright = new File(path + right);
		
		try {
			image = ImageIO.read(filedown);
		} catch(Exception e) {
			System.out.println("Invalid directory: " + filedown.getAbsolutePath());
		}
	}
	
	public Player() {
		super();
	}
	
	public void tick() {
		super.tick();
		
		/* These if statements read the player's speed, which
		 * lets the game decide which direction the player
		 * should be facing by changing its image to the 
		 * player facing one of the four directions.
		 */
		if (dx > 0 && side != 0 && dy == 0) {
			side = 0;
			
			try {
				image = ImageIO.read(fileright);
			} catch(Exception e) {
				System.out.println("Error, cannot find the image @ " + fileright.getAbsolutePath());
			}
		} else if (dx < 0 && side != 2 && dy == 0) {
			side = 2;

			try {
				image = ImageIO.read(fileleft);
			} catch(Exception e) {
				System.out.println("Error, cannot find the image @ " + fileleft.getAbsolutePath());
			}
		}
		if (dy > 0 && side != 1 && dx == 0) {
			side = 1;

			try {
				image = ImageIO.read(filedown);
			} catch(Exception e) {
				System.out.println("Error, cannot find the image @ " + filedown.getAbsolutePath());
			}
		} else if (dy < 0 && side != 3 && dx == 0) {
			side = 3;

			try {
				image = ImageIO.read(fileup);
			} catch(Exception e) {
				System.out.println("Error, cannot find the image @ " + fileup.getAbsolutePath());
			}
		}
		
	}
	
	
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(image, x, y, null);
	}
}

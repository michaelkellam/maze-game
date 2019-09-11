package com.kellam.level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import com.kellam.entities.Handler;
import com.kellam.entities.ID;
import com.kellam.entities.Player;

public class Level {

	/*
	 * Level is loaded using the
	 * RGB values of the image of the 
	 * level. This is a good method to
	 * creating levels without having to type
	 * the coordinates of every single
	 * object, background, etc.
	 */
	
	/* In summary:
	 * Each level has a BufferedImage, which is the actual level.
	 * In the constructor, there is a nested for loop to basically
	 * read every single pixel color and determine the RGB value of each,
	 * which is why there are r,g,b int designated for the Level class.
	 * I put two constructors, one in case for actual levels that end in integers,
	 * such as "Level3", and one constructor that end in a phrase or String, such as
	 * "LevelHelp".
	 * 
	 * The isRGB(int r,int g, int b) method compares the current pixel that's being read to
	 * the RGB value expressed in the method parameters. 
	 * 
	 * For each if (isRGB()) statement,
	 * you see the x = i * 8, and the y = i * 8.
	 * This is because my window is 640 x 480, and my png files
	 * for my levels are 80 x 60. Thus, I want to make the level
	 * and the coordinates proportional to each other.
	 */
	
	private File file;
	private BufferedImage image;	//Image of the level.
	
	private int p,r,g,b;	//RGB values of each pixel.
		
	private int level;
	private String sLevel;
	
	private int x, y;
	private int list;
	
	private boolean isStarted = true;
	
	/* Used for levels called Level1, Level2, etc. */
	public Level(int level) {
		
		this.level = level;
		LevelManager.setCLevel(level);
		file = new File("./res/levels/Level" + level + ".png");
		try {
			image = ImageIO.read(file);	//Loads image of the level.
			System.out.println("Loaded Level " + level + " sucessfully.");
		} catch(Exception e) {
			System.out.println("Error: File " + file.getAbsolutePath() + " not found.");
		}
		
		for (int i = 0; i < image.getWidth(); i++) {	//This loop will analyze each pixel's RGB.
			for (int j = 0; j < image.getHeight(); j++) {
				readPixel(i, j);	//Updates each r, g, and b value.
								
				/*
				 * These if statements are used to read what each RGB combination
				 * equals.
				 * 
				 * "LevelManager.load(id,x,y,list)" line tells the LevelManager
				 * to add the object at those coordinates.
				 */
				
				/* Return Button - NEON_BLUE */
				if (isRGB(0,255,255)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Button, x, y, list, "Return");
				}
				
				/*Help Button - ORANGE */
				if (isRGB(255,175,0)) {
					x = i * 8;
					y = i * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Button, x, y, list, "Help");
				}
				
				/* Start Button - GREEN */
				if (isRGB(0,255,0)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Button, x, y, list, "Start");
					System.out.println("Loaded Button @ X: " + i + ", Y: " + j);
				}
				
				/* Exit Button - RED */
				if (isRGB(255,0,0)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Button, x, y, list, "Exit");
				}

				/* Player - BLUE */
				if (isRGB(0,0,255)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Player, x, y, list);
					System.out.println("Loaded Player @ X: " + i + ", Y: " + j);
				}
				/* Block - YELLOW */
				if (isRGB(255,255,0)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Block, x, y, list);
					System.out.println("Loaded Block @ X: " + i + ", Y: " + j);
				}
				/* Door - PINK */
				if (isRGB(255,0,255)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Door, x, y, list);
					System.out.println("Loaded Door @ X: " + i + ", Y: " + j);
				}
			}
		}
		
	}
	
	/* Used for levels named LevelHelp, etc. */
	public Level(String level) {
		
		sLevel = level;
		LevelManager.setCSLevel(level);
		file = new File("./res/levels/Level" + level + ".png");	//TODO: Fix the absolute path,
																					//		make relative.
		try {
			image = ImageIO.read(file);	//Loads image of the level.
			System.out.println("Loaded Level " + level + " sucessfully.");
		} catch(Exception e) {
			System.out.println("Error: File " + file.getAbsolutePath() + " not found.");
		}
		
		for (int i = 0; i < image.getWidth(); i++) {	//This loop will analyze each pixel's RGB.
			for (int j = 0; j < image.getHeight(); j++) {
				readPixel(i, j);	//Updates each r, g, and b value.
								
				/*
				 * These if statements are used to read what each RGB combination
				 * equals. In this case, (255,0,0) (RED) is an enemy.
				 * 
				 * "LevelManager.load(id,x,y,list)" line tells the LevelManager
				 * to add the object at those coordinates.
				 */
				
				
				/* Return Button - NEON_BLUE */
				if (isRGB(2,255,255)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Button, x, y, list, "Return");
				}
				/* Enemy - RED */
				if (isRGB(255,0,0)) {
					x = i * 8;	//Coordinates are magnified to match resolution of screen.
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Enemy, x, y, list);
					System.out.println("Loaded Enemy @ X: " + i + ", Y: " + j);
				}
				/* Player - BLUE */
				if (isRGB(0,0,255)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Player, x, y, list);
					System.out.println("Loaded Player @ X: " + i + ", Y: " + j);
				}
				/* Block - YELLOW */
				if (isRGB(255,255,0)) {
					x = i * 8;
					y = j * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Block, x, y, list);
					System.out.println("Loaded Block @ X: " + i + ", Y: " + j);
				}
				/* Door - PINK */
				if (isRGB(255,0,255)) {
					x = i * 8;
					y = i * 8;
					list = Handler.getObjects().size();
					LevelManager.load(ID.Door, x, y, list);
					System.out.println("Loaded Door @ X: " + i + ", Y: " + j);
				}
			}
		}
			
	}
	
	private void readPixel(int x, int y) {	//This method updates the rgb value in each pixel.
		
		/*
		 * One disadvantage of this method is that
		 * the RGB values are not saved for each pixel,
		 * only quickly analyzed then continued.
		 */
		
		p = image.getRGB(x, y);
		r = (p >> 16) & 0xff;	// Retrieves red value.
		g = (p >> 8) & 0xff;	// Retrieves green value.
		b = p & 0xff;			// Retrieves blue value.
				
	}
	
	private boolean isRGB(int r, int g, int b) {	// Returns true if the current RGB value matches the one
													// in the parameters.
		if (this.r == r && this.g == g && this.b == b) {
			return true;
		}
		
		return false;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	public void setStarted(boolean start) {
		isStarted = start;
	}
	
	
}

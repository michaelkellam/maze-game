package com.kellam.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.kellam.level.LevelManager;

public class Button extends EntityObj {
	
	/*
	 * The button class is my own implementation. Since I didn't want to use
	 * swing or JButton, I had to creatively make my own from scratch, and I'm
	 * pretty happy with the implementation.
	 */

	private BufferedImage image;
	private File file;
	private String function;	// Identifies what the button is supposed to do when activated.
	private boolean isIns = false;	// is Intersected, which is true if the mouse is over the button.
	
	private boolean isClicked = false;	// is Clicked, which is true if the mouse clicks when over it.
	
	/* Button:
	 *  "Start" - Start game [nextLevel()]
	 *  "Help" - Help [level(LevelHelp)]
	 *  "Options" - Options [Not this game.]
	 *  "Exit" - Exit [System.exit(0)]
	 */
	
	public Button(int x, int y, int list, String function) {
		super(ID.Button, x, y, list);
		this.function = function;
		
		file = new File("./res/sprites/" + function + ".png");
		try {
			image = ImageIO.read(file);
			width = image.getWidth();
			height = image.getHeight();
		} catch(Exception e) {
			System.out.println("Cannot load file @ " + file.getAbsolutePath());
		}
		
	}
	
	public void activate() {	// This was my best attempt at creating multiple
								// functions for buttons without having to create
								// a separate class for each function.
		
		switch(function) {
		case "Start":
			LevelManager.nextLevel();
			break;
		case "Help":
			//TODO: Help button
			LevelManager.setLevel("Help");
			Handler.isHelp = true;
			break;
		case "Options":
			//TODO: Options button
			System.out.println("Options is currently being worked on.");
			break;
		case "Exit":
			//TODO: Exit button;
			System.exit(0);
			break;
		case "Return":
			LevelManager.setLevel(1);
			Handler.isHelp = false;
			Handler.isWin = false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public boolean isClicked() {
		return isClicked;
	}
	
	public void setClicked(boolean clicked) {
		isClicked = clicked;
	}
	
	public void setIns(boolean ins) {	// This function lets the button change
										// its color when hovered over.
		
		this.isIns = ins;
		String ext = "";
		if (isIns) {
			ext = "_ins";
		} else ext = "";
		file = null;
		file = new File ("./res/sprites/" + function + ext + ".png");
		image = null;
		try {
			image = ImageIO.read(file);
		} catch(Exception e) {
			System.out.println("Cannot load file @ " + file.getAbsolutePath());
		}
	}
	
}

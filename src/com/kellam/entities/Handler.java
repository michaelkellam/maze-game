package com.kellam.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.kellam.level.LevelManager;
import com.kellam.main.Window;
import com.kellam.level.LevelProperties;

public class Handler {
	
	/*
	 * The main heart of the game's logic, such as
	 * mouse position, angle, every single object,
	 * and inputs.
	 * 
	 */

	private static LinkedList<EntityObj> obj = new LinkedList<EntityObj>();
	private static LinkedList<Player> player = new LinkedList<Player>();
	
	private Window window;
			
	public boolean paused = false;
	public static boolean gameStarted = false;
	
	private int iMouseX, iMouseY;	// Mouse location, used for clicking on menu items.
	
	private File background = new File("./res/sprites/Background.png");	// Grass background for gameplay
	private BufferedImage image;
	
	private File pauseButton = new File("./res/sprites/Paused.png");	// This is the "PAUSED"
																		// text when escape is pressed
	private BufferedImage pause;
	
	public static boolean isHelp = false;
	public static boolean isWin = false;
	
	private File helpBG = new File("./res/sprites/Info.png");	// The help screen is simply this png.
	private BufferedImage help;

	private File winScreen = new File("./res/sprites/Level10.png");	// The win screen is the final level
	private BufferedImage win;
	
	public Handler(Window window) {
		this.window = window;
		
		try {
			image = ImageIO.read(background);	
			pause = ImageIO.read(pauseButton);
			help = ImageIO.read(helpBG);
			win = ImageIO.read(winScreen);
			
		} catch(Exception e) {
			System.out.println("Error: " + background.getAbsolutePath() + " is an invalid path, and the background cannot be found.");
		}
		
		LevelProperties.executeProp(1,null);
	}
	
	public void tick() {
		
		iMouseX = window.getInput().getMouseX();	// These are the mouse coordinates that matter.
		iMouseY = window.getInput().getMouseY();
	
		if (window.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			paused = !paused;
		}
		
		if (!paused) {
			
			// The function testJump lets the game check whether or not they're going to hit a wall
			// before actually letting them move, allowing for seamless movement.
			
			if (window.getInput().isKey(KeyEvent.VK_W)) {
				player.get(0).testJump(0,-3);
				player.get(0).setDy(-1);
			} else if (window.getInput().isKey(KeyEvent.VK_S)) {
				player.get(0).testJump(0,3);
				player.get(0).setDy(1);
			} else player.get(0).setDy(0);
			if (window.getInput().isKey(KeyEvent.VK_A)) {
				player.get(0).testJump(-3,0);
				player.get(0).setDx(-1);
			} else if (window.getInput().isKey(KeyEvent.VK_D)) {
				player.get(0).testJump(3,0);
				player.get(0).setDx(1);
			} else player.get(0).setDx(0);
			
			for (int i = 0; i < obj.size(); i++) {	// This loop updates every single
													// object in the game every tick.
				
				if (obj.get(i).getID().equals(ID.Button)) {	// Allows buttons to be activated when hovered
															// over with mouse
					if (obj.get(i).intersects(iMouseX, iMouseY)) {
						((Button) obj.get(i)).setIns(true);
						if (window.getInput().isMouseDown(MouseEvent.BUTTON1)) {
							((Button) obj.get(i)).activate();
						}
					} else ((Button) obj.get(i)).setIns(false);

				}
				
				if (!isWin && LevelManager.currentLevel == 10) {
					isWin = true;
					
				}
				
				if (obj.get(i).getID().equals(ID.Player)) {
					
					//TODO: Player input
					
				}
					
				obj.get(i).tick();	//After all of the logic, the object is updated.
				player.get(0).tick(); //Player is updated
			}
		}
		
	}
	
	public static void movePlayer(int index) {	// Allows the players data to be updated
												// after the rest of the objects, which
												// lets it be above the other sprites in case
												// there are any intersections
		player.add((Player) obj.get(index));
		obj.remove(index);
	}
	
	public void render(Graphics g) {
		
		if (LevelManager.currentLevel != 1) {
			g.drawImage(image, 0, 0, null);
		}
		
		if (isHelp) {	// When the Help button is activated, the help image is drawn.
			g.drawImage(help, 0, 0, null);
		}
		
		if (isWin) {	// When the player wins, the win image is drawn.
			g.drawImage(win, 0, 0, null);
		}
		
		for (int i = 0; i < obj.size(); i++) {	// One for loop can render every single object.
			obj.get(i).render(g);
		}
		
		player.get(0).render(g);
		
		if (paused) {	// Draws the "PAUSED" text  onto the screen when the game is paused.
			g.drawImage(pause,window.getWidth() / 2 - (pause.getWidth() / 2),window.getHeight() / 2 - (pause.getHeight() / 2),null);
		}

	}
	
	public static LinkedList<EntityObj> getObjects() {	// This is the LinkedList that holds the objects.
		return obj;
	}
	
	public static LinkedList<Player> getPlayer() {	// This is the LinkedList that holds the player.
		return player;
	}
}

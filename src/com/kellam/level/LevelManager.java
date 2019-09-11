package com.kellam.level;

import com.kellam.entities.Block;
import com.kellam.entities.Button;
import com.kellam.entities.Door;
import com.kellam.entities.Handler;
import com.kellam.entities.ID;
import com.kellam.entities.Player;

public class LevelManager {
	
	public static int currentLevel = 0;
	public static String sCurrentLevel = "";
		
	/*
	 * Loads each level. This class wasn't necessary,
	 * and could've been added to the level class,
	 * but it's nice to have each part separated
	 * and organized.
	 */
	
	/*
	 * My LevelManager basically loads each object one-by-one
	 * by adding the objects to the Object LinkedList. I use an enum
	 * to identify each object, that way it's easy to identify object types
	 * as well as create new ones.
	 */
	
	public LevelManager() {
		
	}
	
	/*
	 * The load method being static makes it so that you don't have to
	 * make a new LevelManager object just to use it.
	 */
	
	public static void load(ID id, int x, int y, int list) {	//Simply tells the Handler what
																//to add to the LinkedList.
		if (id.equals(ID.Player)) {
			Handler.getObjects().add(new Player(x, y, list));
		}
		if (id.equals(ID.Block)) {
			Handler.getObjects().add(new Block(x, y, list));
		}
		if (id.equals(ID.Door)) {
			Handler.getObjects().add(new Door(x, y, list));
		}
		if (id.equals(ID.Button)) {
			Handler.getObjects().add(new Button(x, y, list, "Start"));
		}
		
		for (int i = 0; i < Handler.getObjects().size(); i++) {
			if (Handler.getObjects().get(i).getID().equals(ID.Player)) {
				Handler.movePlayer(i);
				System.out.println("Player has sucessfully been moved to the Player LinkedList");
			}
		}
		
	}
	
	public static void load(ID id, int x, int y, int list, String function) {	//Simply tells the Handler what
																				//to add to the LinkedList.
		if (id.equals(ID.Player)) {
			Handler.getObjects().add(new Player(x, y, list));
		}
		if (id.equals(ID.Block)) {
			Handler.getObjects().add(new Block(x, y, list));
		}
		if (id.equals(ID.Door)) {
			Handler.getObjects().add(new Door(x, y, list));
		}
		if (id.equals(ID.Button)) {
			Handler.getObjects().add(new Button(x, y, list, function));
		}
		
		for (int i = 0; i < Handler.getObjects().size(); i++) {
			if (Handler.getObjects().get(i).getID().equals(ID.Player)) {
				Handler.movePlayer(i);
				System.out.println("Player has sucessfully been moved to the Player LinkedList");
			}
		}
		
	}
	public static void nextLevel() {	// Sets the current level to its value + 1, then calls the Level
										// class to load each item.
		
		for (int i = Handler.getObjects().size() - 1; i >= 0; i--) {
			Handler.getObjects().remove(i);
		}
		Handler.getPlayer().removeFirst();
		Level level = new Level(currentLevel + 1);
		
		LevelProperties.executeProp(currentLevel, null);
	}
	
	public static void setLevel(int slevel) {
		currentLevel = slevel;
		for (int i = Handler.getObjects().size() - 1; i >= 0; i--) {
			Handler.getObjects().remove(i);
		}
		if (Handler.getPlayer().size() > 0)
			Handler.getPlayer().removeFirst();
		Level level = new Level(currentLevel);
		
		LevelProperties.executeProp(currentLevel, null);
				
	}
	
	public static void setCLevel(int level) {
		currentLevel = level;
	}
	
	public static void setCSLevel(String slevel) {
		sCurrentLevel = slevel;
	}
	
	public static void setLevel(String slevel) {	// Useful for when you want to either go back a level
													// or the main menu, or even the help screen. This is
													// because the menus are technically levels.
		
		for (int i = Handler.getObjects().size() - 1; i >= 0; i--) {
			Handler.getObjects().remove(i);
		}
		Handler.getPlayer().removeFirst();
		Level level = new Level(slevel);
		
		LevelProperties.executeProp(-1, slevel);
		
		
	}
}

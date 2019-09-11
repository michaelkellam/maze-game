package com.kellam.main;

import com.kellam.level.Level;

public class GameManager {

	/*
	 * Main method, used to simply
	 * start the game loop with
	 * the window & title parameters
	 */
	
	public static void main(String[] args) {
		
		String title = "Doom Clone";	// Title
		int width = 640;	// These are the dimensions
		int height = 480;	// of the window
		
		GameLoop gl = new GameLoop(title, width, height);	// GameLoop is called
		gl.start();	// Starts the thread
	}
}

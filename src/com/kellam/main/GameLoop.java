package com.kellam.main;

import java.awt.Color;
import java.awt.Font;

public class GameLoop implements Runnable {

	/*
	 * Runs the loop of the game to keep
	 * consistent 60 ticks per second.
	 * This helps the game run smoothly.
	 */
	
	private Thread thread;
	private Window window;
	
	private final double UPDATE_CAP = 1.0 / 60.0;	// Tickrate
	
	public static int fps, ticks = 0;
	public static int frames, ups;
	
	private boolean running = false;
		
	public GameLoop(String title, int width, int height) {	// Initializes Window and Thread
		
		thread = new Thread(this);
		window = new Window(title, width, height);	// This is important, because
													// all of the logic is essentially
													// channeled through this object.
	}
	
	public void start() {
		thread.start();
		running = true;
	}
	
	public void stop() {
		
		running = false;
		
		try {
			thread.join();	// Stops the thread and exits the program
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		double firstTime = 0;									
		double lastTime = System.nanoTime() / 1000000000.0;		// Used for keeping accurate, fast,
		double passedTime = 0;									// and consistent current time
		double unprocessedTime = 0;								
		double frameTime = 0;
		
		
		while (running) {
			
			firstTime = System.nanoTime() / 1000000000.0;		// These next 5 lines keep track of time
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			render();	// Render frames as much as possible, so it's not under any cap, just under the while loop.
			
			if (unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				
				tick();	// Tells game to update once we hit an update frame
				
				if (frameTime >= 1.0) {
					frameTime -= 1.0;
					
					frames = fps;
					ups = ticks;
					fps = 0;
					ticks = 0;
				}
			}
			
		}
	}
	
	private void tick() {	// Updates game
		ticks++;
		window.tick();
	}
	
	private void render() {	// Draw graphics
		fps++;
		
		window.render();
	}
	
}

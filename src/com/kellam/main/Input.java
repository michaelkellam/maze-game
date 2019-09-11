package com.kellam.main;

import java.awt.Canvas;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * This is used for capturing our
 * input which is essential for
 * making the game.
 */

public class Input implements KeyListener, MouseMotionListener, MouseListener {
	
	private boolean[] keys = new boolean[256];	//Used for all the keys on the keyboard.
	private boolean[] keys_last = new boolean[keys.length];	//This is used for differentiating the
															//inputs between current and previous frames.
	
	private boolean[] mouseClicks = new boolean[4];	//Used for pressing of a button.
	private boolean[] mouseClicks_last = new boolean[mouseClicks.length];
	
	private int mouseX, mouseY;
	private int mouseXLast, mouseYLast;
		
	private Window window;
	
	public Input(Window window) {	//Not necessary, but good to have
									//in case we want to directly access window through input.
		this.window = window;
	}
	
	/*----KEYS-----------------------------------------------------------------------*/
	
	public void keyPressed(KeyEvent e) {	//Used to signal a key is pressed.
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {	//Used to signal a key is released.
		keys[e.getKeyCode()] = false;
	}
	/*---CUSTOM KEYS----------------------------------------------------------------*/
	public boolean isKey(int keyCode) {	//Returns true if the key is being held down.
		return keys[keyCode];
	}
	
	public boolean isKeyDown(int keyCode) {	//This is used for a simple single press
		return keys[keyCode] && !keys_last[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {	//This is used to see when the key is released
		return !keys[keyCode] && keys_last[keyCode];
	}
	
	/*----MOUSE_MOTION------------------------------------------------------------------*/
	
	public void mousePressed(MouseEvent e) {
		mouseClicks[e.getButton()] = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseClicks[e.getButton()] = false;
	}
	
	/*----CUSTOM MOUSE_MOTION----------------------------------------------------------*/
	
	public boolean isMouse(int bCode) {
		return mouseClicks[bCode];
	}
	public boolean isMouseDown(int bCode) {
		return mouseClicks[bCode] && !mouseClicks_last[bCode];
	}
	public boolean isMouseUp(int bCode) {
		return !mouseClicks[bCode] && mouseClicks_last[bCode];
	}
	
	/*----MOUSE_LISTENER---------------------------------------------------------------*/
	
	
	public void mouseEntered(MouseEvent e) {/*IGNORE*/}	
	public void mouseExited(MouseEvent e) {/*IGNORE*/}
	public void mouseClicked(MouseEvent e) {/*IGNORE*/}

	public void tick() {
				
		for (int i = 0; i < keys.length; i++) {	//This is paramount to make the single presses
												//possible.
			keys_last[i] = keys[i];
		}
		
		for (int i = 0; i < mouseClicks.length; i++) {
			mouseClicks_last[i] = mouseClicks[i];
		}
		mouseXLast = mouseX;	//Used to get mouse information
		mouseYLast = mouseY;
		
		
	}
	
	public boolean[] getKeys() {
		return keys;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public int mousePosX() {
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	public int mousePosY() {
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}
	
	public int getMouseXLast() {
		return mouseXLast;
	}
	
	public int getMouseYLast() {
		return mouseYLast;
	}
	public void keyTyped(KeyEvent e) {}	//Ignore

	
	public void mouseDragged(MouseEvent e) {
				
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = (int) ((int) mousePosX() - window.getCanvas().getLocationOnScreen().getX());
		mouseY = (int) ((int) mousePosY() - window.getCanvas().getLocationOnScreen().getY());
	}
}

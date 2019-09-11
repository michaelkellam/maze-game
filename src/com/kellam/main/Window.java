package com.kellam.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.kellam.entities.Handler;
import com.kellam.level.Level;

/*
 * Window class is used for holding all of
 * our information and displaying it. 
 * Our game logic and graphics are put here.
 */

public class Window {

	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	
	private String title;
	private int width, height;
	
	private Input input;
	private static Handler handler;
	
	private Level level;
		
	public Window(String title, int width, int height) {	// Creates most of what we need
															// for the game to function.
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		Dimension s = new Dimension(width, height);
		
		frame = new JFrame(title);	// Creates the window itself
		canvas = new Canvas();
		input = new Input(this);
		handler = new Handler(this);
		canvas.setPreferredSize(s);
		canvas.setBackground(Color.BLACK);
		
		level = new Level(1);
		
		canvas.addKeyListener(input);			// By having the canvas add these input listeners,
		canvas.addMouseMotionListener(input);	// the Window class is able to gain information from
		canvas.addMouseListener(input);			// the Input class, allowing the game to function.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(canvas);	// This allows us to see what's on the screen.
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		canvas.createBufferStrategy(2);	
		bs = canvas.getBufferStrategy();	// These 3 commands allow the graphics to
		g = bs.getDrawGraphics();			// give and receive information, i.e. drawing.
		
		frame.setVisible(true);
		
	}
	
	public void tick() {	//Where everything is updated.
		
		//TODO: entities, menus, etc.
		handler.tick();
		
		input.tick();
	}
	
	public void render() {
		
		g.setColor(canvas.getBackground());		// Keep these two lines as
		g.fillRect(0, 0, width, height);		// first lines of render() method.
												// It allows the screen to be cleared.
		
		//TODO: render
		handler.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN,14));
		g.drawString("FPS: " + GameLoop.frames + ", Ticks: " + GameLoop.ups, 8, 16);	// Shows FPS & tickrate
		bs.show();	//Keep as last line of render() method.
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Input getInput() {
		return input;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public Graphics getGraphics() {
		return g;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}

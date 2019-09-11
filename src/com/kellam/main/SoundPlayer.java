package com.kellam.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

	/*
	 * This is what I use to have music
	 * in the game, both in the main menu
	 * and in the actual gameplay.
	 */
	
	public static Clip clip;
	
	public SoundPlayer() {
		
	}
	
	public static void play(String path, int loops) {
		
		try {
			File soundFile = new File(path);
			
			AudioInputStream input = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(input);
			clip.start();
			clip.loop(loops);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
